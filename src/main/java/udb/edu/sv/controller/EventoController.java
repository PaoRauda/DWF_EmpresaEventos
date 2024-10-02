package udb.edu.sv.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import udb.edu.sv.dao.model.Evento;
import udb.edu.sv.service.CategoriaService;
import udb.edu.sv.service.EventoService;

@Controller //Controller para templates dentro de carpeta "empleado/evento". Acceso restringido
@PreAuthorize("hasAnyRole('EMPLEADO','MANAGER')")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping("/showEvento")
    public String index(Model model){
        model.addAttribute("list", eventoService.getAll());
        return "empleado/evento/index";
    }

    @GetMapping("/saveEvento/{id}")
    public String showSave(@PathVariable("id") Long id, Model model) {
        if (!model.containsAttribute("evento")) {
            if (id != null && id != 0) {
                model.addAttribute("evento", eventoService.get(id));
            } else {
                model.addAttribute("evento", new Evento());
            }
        }

        model.addAttribute("categorias", categoriaService.getAll());

        return "empleado/evento/save";
    }

    @PostMapping("/saveEvento")
    public String save(@Valid @ModelAttribute("evento") Evento evento, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.evento", result);
            redirectAttributes.addFlashAttribute("evento", evento);
            return "redirect:/saveEvento/" + (evento.getId() != null ? evento.getId() : 0);
        }
        eventoService.save(evento);
        return "redirect:/showEvento";
    }

    @GetMapping("/deleteEvento/{id}")
    public String delete(@PathVariable Long id, Model model) {
        eventoService.delete(id);

        return "redirect:/showEvento";
    }
}
