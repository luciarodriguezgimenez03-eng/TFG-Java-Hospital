package io.bootify.web_hospital.consumo_medicamento.controller;

import io.bootify.web_hospital.consumo_medicamento.model.ConsumoMedicamentoDTO;
import io.bootify.web_hospital.consumo_medicamento.service.ConsumoMedicamentoService;
import io.bootify.web_hospital.prescripcion.domain.Prescripcion;
import io.bootify.web_hospital.prescripcion.repos.PrescripcionRepository;
import io.bootify.web_hospital.usuario.domain.Usuario;
import io.bootify.web_hospital.usuario.repos.UsuarioRepository;
import io.bootify.web_hospital.util.CustomCollectors;
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
@RequestMapping("/consumoMedicamentos")
public class ConsumoMedicamentoController {

    private final ConsumoMedicamentoService consumoMedicamentoService;
    private final PrescripcionRepository prescripcionRepository;
    private final UsuarioRepository usuarioRepository;

    public ConsumoMedicamentoController(final ConsumoMedicamentoService consumoMedicamentoService,
            final PrescripcionRepository prescripcionRepository,
            final UsuarioRepository usuarioRepository) {
        this.consumoMedicamentoService = consumoMedicamentoService;
        this.prescripcionRepository = prescripcionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("prescripcionValues", prescripcionRepository.findAll(Sort.by("idPrescripcion"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Prescripcion::getIdPrescripcion, Prescripcion::getDosis)));
        model.addAttribute("pacienteValues", usuarioRepository.findAll(Sort.by("idUsuario"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Usuario::getIdUsuario, Usuario::getDni)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("consumoMedicamentoes", consumoMedicamentoService.findAll());
        return "consumoMedicamento/list";
    }

    @GetMapping("/add")
    public String add(
            @ModelAttribute("consumoMedicamento") final ConsumoMedicamentoDTO consumoMedicamentoDTO) {
        return "consumoMedicamento/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("consumoMedicamento") @Valid final ConsumoMedicamentoDTO consumoMedicamentoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "consumoMedicamento/add";
        }
        consumoMedicamentoService.create(consumoMedicamentoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("consumoMedicamento.create.success"));
        return "redirect:/consumoMedicamentos";
    }

    @GetMapping("/edit/{idConsumo}")
    public String edit(@PathVariable(name = "idConsumo") final Integer idConsumo,
            final Model model) {
        model.addAttribute("consumoMedicamento", consumoMedicamentoService.get(idConsumo));
        return "consumoMedicamento/edit";
    }

    @PostMapping("/edit/{idConsumo}")
    public String edit(@PathVariable(name = "idConsumo") final Integer idConsumo,
            @ModelAttribute("consumoMedicamento") @Valid final ConsumoMedicamentoDTO consumoMedicamentoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "consumoMedicamento/edit";
        }
        consumoMedicamentoService.update(idConsumo, consumoMedicamentoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("consumoMedicamento.update.success"));
        return "redirect:/consumoMedicamentos";
    }

    @PostMapping("/delete/{idConsumo}")
    public String delete(@PathVariable(name = "idConsumo") final Integer idConsumo,
            final RedirectAttributes redirectAttributes) {
        consumoMedicamentoService.delete(idConsumo);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("consumoMedicamento.delete.success"));
        return "redirect:/consumoMedicamentos";
    }

}
