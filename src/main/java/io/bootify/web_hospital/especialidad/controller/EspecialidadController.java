package io.bootify.web_hospital.especialidad.controller;

import io.bootify.web_hospital.especialidad.model.EspecialidadDTO;
import io.bootify.web_hospital.especialidad.service.EspecialidadService;
import io.bootify.web_hospital.util.WebUtils;
import jakarta.validation.Valid;
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
@RequestMapping("/especialidads")
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    public EspecialidadController(final EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("especialidads", especialidadService.findAll());
        return "especialidad/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("especialidad") final EspecialidadDTO especialidadDTO) {
        return "especialidad/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("especialidad") @Valid final EspecialidadDTO especialidadDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "especialidad/add";
        }
        especialidadService.create(especialidadDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("especialidad.create.success"));
        return "redirect:/especialidads";
    }

    @GetMapping("/edit/{idEspecialidad}")
    public String edit(@PathVariable(name = "idEspecialidad") final Integer idEspecialidad,
            final Model model) {
        model.addAttribute("especialidad", especialidadService.get(idEspecialidad));
        return "especialidad/edit";
    }

    @PostMapping("/edit/{idEspecialidad}")
    public String edit(@PathVariable(name = "idEspecialidad") final Integer idEspecialidad,
            @ModelAttribute("especialidad") @Valid final EspecialidadDTO especialidadDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "especialidad/edit";
        }
        especialidadService.update(idEspecialidad, especialidadDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("especialidad.update.success"));
        return "redirect:/especialidads";
    }

    @PostMapping("/delete/{idEspecialidad}")
    public String delete(@PathVariable(name = "idEspecialidad") final Integer idEspecialidad,
            final RedirectAttributes redirectAttributes) {
        especialidadService.delete(idEspecialidad);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("especialidad.delete.success"));
        return "redirect:/especialidads";
    }

}
