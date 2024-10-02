package udb.edu.sv.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import udb.edu.sv.dao.model.Evento_Boleteria;
import udb.edu.sv.service.EventoService;
import udb.edu.sv.service.Evento_BoleteriaService;

@Controller //Controller para templates dentro de carpeta "empleado/evento_boleteria". Acceso restringido
@PreAuthorize("hasAnyRole('EMPLEADO','MANAGER')")
public class Evento_BoleteriaController {
    @Autowired
    private Evento_BoleteriaService eventoBoleteriaService;

    @Autowired
    private EventoService eventoService;

    @RequestMapping("/showEvento_Boleteria")
    public String index(Model model){
        model.addAttribute("list", eventoBoleteriaService.getAll());
        return "empleado/evento_boleteria/index";
    }

    @GetMapping("/saveEvento_Boleteria/{id}")
    public String showSave(@PathVariable("id") Long id, Model model) {
        if (!model.containsAttribute("evento_boleteria")) {
            if (id != null && id != 0) {
                model.addAttribute("evento_boleteria", eventoBoleteriaService.get(id));
            } else {
                model.addAttribute("evento_boleteria", new Evento_Boleteria());
            }
        }

        model.addAttribute("eventos", eventoService.getAll());

        return "empleado/evento_boleteria/save";
    }

    @PostMapping("/saveEvento_Boleteria")
    public String save(@Valid @ModelAttribute("evento_boleteria") Evento_Boleteria evento_boleteria, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {

            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.evento_boleteria", result);
            redirectAttributes.addFlashAttribute("evento_boleteria", evento_boleteria);
            return "redirect:/saveEvento_Boleteria/" + (evento_boleteria.getId() != null ? evento_boleteria.getId() : 0);
        }

        eventoBoleteriaService.save(evento_boleteria);
        return "redirect:/showEvento_Boleteria";
    }

    @GetMapping("/deleteEvento_Boleteria/{id}")
    public String delete(@PathVariable Long id, Model model) {
        eventoBoleteriaService.delete(id);

        return "redirect:/showEvento_Boleteria";
    }

    @RequestMapping("/actualizarEstado")
    public String actualizarEstado(Model model) {
        eventoBoleteriaService.actualizarEstado();
        return "redirect:/showEvento_Boleteria";
    }

}
