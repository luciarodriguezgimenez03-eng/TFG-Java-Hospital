package io.bootify.web_hospital.prescripcion.controller;

import io.bootify.web_hospital.cita.domain.Cita;
import io.bootify.web_hospital.cita.repos.CitaRepository;
import io.bootify.web_hospital.medicamento.domain.Medicamento;
import io.bootify.web_hospital.medicamento.repos.MedicamentoRepository;
import io.bootify.web_hospital.prescripcion.model.PrescripcionDTO;
import io.bootify.web_hospital.prescripcion.service.PrescripcionService;
import io.bootify.web_hospital.util.CustomCollectors;
import io.bootify.web_hospital.util.ReferencedWarning;
import io.bootify.web_hospital.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/prescripcions")
public class PrescripcionController {

    private final PrescripcionService prescripcionService;
    private final CitaRepository citaRepository;
    private final MedicamentoRepository medicamentoRepository;

    public PrescripcionController(final PrescripcionService prescripcionService,
            final CitaRepository citaRepository,
            final MedicamentoRepository medicamentoRepository) {
        this.prescripcionService = prescripcionService;
        this.citaRepository = citaRepository;
        this.medicamentoRepository = medicamentoRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("citaValues", citaRepository.findAll(Sort.by("idCita"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Cita::getIdCita, Cita::getIdCita)));
        model.addAttribute("medicamentoValues", medicamentoRepository.findAll(Sort.by("idMedicamento"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Medicamento::getIdMedicamento, Medicamento::getNombre)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("prescripcions", prescripcionService.findAll());
        return "prescripcion/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("prescripcion") final PrescripcionDTO prescripcionDTO) {
        return "prescripcion/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("prescripcion") @Valid final PrescripcionDTO prescripcionDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "prescripcion/add";
        }
        prescripcionService.create(prescripcionDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("prescripcion.create.success"));
        return "redirect:/prescripcions";
    }

    @GetMapping("/edit/{idPrescripcion}")
    public String edit(@PathVariable(name = "idPrescripcion") final Integer idPrescripcion,
            final Model model) {
        model.addAttribute("prescripcion", prescripcionService.get(idPrescripcion));
        return "prescripcion/edit";
    }

    @PostMapping("/edit/{idPrescripcion}")
    public String edit(@PathVariable(name = "idPrescripcion") final Integer idPrescripcion,
            @ModelAttribute("prescripcion") @Valid final PrescripcionDTO prescripcionDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "prescripcion/edit";
        }
        prescripcionService.update(idPrescripcion, prescripcionDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("prescripcion.update.success"));
        return "redirect:/prescripcions";
    }

    @PostMapping("/delete/{idPrescripcion}")
    public String delete(@PathVariable(name = "idPrescripcion") final Integer idPrescripcion,
            final RedirectAttributes redirectAttributes) {
        final ReferencedWarning referencedWarning = prescripcionService.getReferencedWarning(idPrescripcion);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR,
                    WebUtils.getMessage(referencedWarning.getKey(), referencedWarning.getParams().toArray()));
        } else {
            prescripcionService.delete(idPrescripcion);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("prescripcion.delete.success"));
        }
        return "redirect:/prescripcions";
    }

}
