package io.bootify.web_hospital.cita.controller;

import io.bootify.web_hospital.cita.model.CitaDTO;
import io.bootify.web_hospital.cita.service.CitaService;
import io.bootify.web_hospital.sala.domain.Sala;
import io.bootify.web_hospital.sala.repos.SalaRepository;
import io.bootify.web_hospital.usuario.domain.Usuario;
import io.bootify.web_hospital.usuario.repos.UsuarioRepository;
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
@RequestMapping("/citas")
public class CitaController {

    private final CitaService citaService;
    private final UsuarioRepository usuarioRepository;
    private final SalaRepository salaRepository;

    public CitaController(final CitaService citaService, final UsuarioRepository usuarioRepository,
            final SalaRepository salaRepository) {
        this.citaService = citaService;
        this.usuarioRepository = usuarioRepository;
        this.salaRepository = salaRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("pacienteValues", usuarioRepository.findAll(Sort.by("idUsuario"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Usuario::getIdUsuario, Usuario::getDni)));
        model.addAttribute("empleadoValues", usuarioRepository.findAll(Sort.by("idUsuario"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Usuario::getIdUsuario, Usuario::getDni)));
        model.addAttribute("salaValues", salaRepository.findAll(Sort.by("idSala"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Sala::getIdSala, Sala::getNombreSala)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("citas", citaService.findAll());
        return "cita/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("cita") final CitaDTO citaDTO) {
        return "cita/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("cita") @Valid final CitaDTO citaDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "cita/add";
        }
        citaService.create(citaDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("cita.create.success"));
        return "redirect:/citas";
    }

    @GetMapping("/edit/{idCita}")
    public String edit(@PathVariable(name = "idCita") final Integer idCita, final Model model) {
        model.addAttribute("cita", citaService.get(idCita));
        return "cita/edit";
    }

    @PostMapping("/edit/{idCita}")
    public String edit(@PathVariable(name = "idCita") final Integer idCita,
            @ModelAttribute("cita") @Valid final CitaDTO citaDTO, final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "cita/edit";
        }
        citaService.update(idCita, citaDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("cita.update.success"));
        return "redirect:/citas";
    }

    @PostMapping("/delete/{idCita}")
    public String delete(@PathVariable(name = "idCita") final Integer idCita,
            final RedirectAttributes redirectAttributes) {
        final ReferencedWarning referencedWarning = citaService.getReferencedWarning(idCita);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR,
                    WebUtils.getMessage(referencedWarning.getKey(), referencedWarning.getParams().toArray()));
        } else {
            citaService.delete(idCita);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("cita.delete.success"));
        }
        return "redirect:/citas";
    }

}
