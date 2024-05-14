package udb.edu.sv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import udb.edu.sv.dao.model.Evento;
import udb.edu.sv.dao.model.Usuario;
import udb.edu.sv.service.CategoriaService;
import udb.edu.sv.service.EventoService;
import udb.edu.sv.service.UsuarioService;

@Controller
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
        if (id != null && id != 0) {
            model.addAttribute("evento", eventoService.get(id));
            model.addAttribute("categorias", categoriaService.getAll());
        } else {
            model.addAttribute("evento", new Evento());
            model.addAttribute("categorias", categoriaService.getAll());
        }
        return "empleado/evento/save";
    }

    @PostMapping("/saveEvento")
    public String save(Evento evento, Model model) {
        eventoService.save(evento);
        return "redirect:/showEvento";
    }

    @GetMapping("/deleteEvento/{id}")
    public String delete(@PathVariable Long id, Model model) {
        eventoService.delete(id);

        return "redirect:/showEvento";
    }
}
