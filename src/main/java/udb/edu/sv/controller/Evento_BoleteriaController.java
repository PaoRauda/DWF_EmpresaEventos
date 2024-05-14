package udb.edu.sv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import udb.edu.sv.dao.model.Evento_Boleteria;
import udb.edu.sv.service.EventoService;
import udb.edu.sv.service.Evento_BoleteriaService;

@Controller
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
        if (id != null && id != 0) {
            model.addAttribute("evento_boleteria", eventoBoleteriaService.get(id));
            model.addAttribute("eventos", eventoService.getAll());
        } else {
            model.addAttribute("evento_boleteria", new Evento_Boleteria());
            model.addAttribute("eventos", eventoService.getAll());
        }
        return "empleado/evento_boleteria/save";
    }

    @PostMapping("/saveEvento_Boleteria")
    public String save(Evento_Boleteria evento_boleteria, Model model) {
        eventoBoleteriaService.save(evento_boleteria);
        return "redirect:/showEvento_Boleteria";
    }

    @GetMapping("/deleteEvento_Boleteria/{id}")
    public String delete(@PathVariable Long id, Model model) {
        eventoBoleteriaService.delete(id);

        return "redirect:/showEvento_Boleteria";
    }
}
