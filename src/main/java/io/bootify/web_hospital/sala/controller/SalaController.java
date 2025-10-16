package io.bootify.web_hospital.sala.controller;

import io.bootify.web_hospital.sala.model.SalaDTO;
import io.bootify.web_hospital.sala.service.SalaService;
import io.bootify.web_hospital.util.ReferencedWarning;
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
@RequestMapping("/salas")
public class SalaController {

    private final SalaService salaService;

    public SalaController(final SalaService salaService) {
        this.salaService = salaService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("salas", salaService.findAll());
        return "sala/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("sala") final SalaDTO salaDTO) {
        return "sala/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("sala") @Valid final SalaDTO salaDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "sala/add";
        }
        salaService.create(salaDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("sala.create.success"));
        return "redirect:/salas";
    }

    @GetMapping("/edit/{idSala}")
    public String edit(@PathVariable(name = "idSala") final Integer idSala, final Model model) {
        model.addAttribute("sala", salaService.get(idSala));
        return "sala/edit";
    }

    @PostMapping("/edit/{idSala}")
    public String edit(@PathVariable(name = "idSala") final Integer idSala,
            @ModelAttribute("sala") @Valid final SalaDTO salaDTO, final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "sala/edit";
        }
        salaService.update(idSala, salaDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("sala.update.success"));
        return "redirect:/salas";
    }

    @PostMapping("/delete/{idSala}")
    public String delete(@PathVariable(name = "idSala") final Integer idSala,
            final RedirectAttributes redirectAttributes) {
        final ReferencedWarning referencedWarning = salaService.getReferencedWarning(idSala);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR,
                    WebUtils.getMessage(referencedWarning.getKey(), referencedWarning.getParams().toArray()));
        } else {
            salaService.delete(idSala);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("sala.delete.success"));
        }
        return "redirect:/salas";
    }

}
