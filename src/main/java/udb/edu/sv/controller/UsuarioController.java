package udb.edu.sv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import udb.edu.sv.dao.model.Usuario;
import udb.edu.sv.service.UsuarioService;
import org.springframework.ui.Model;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("/showUsuario")
    public String index(Model model){
        model.addAttribute("list", usuarioService.getAll());
        return "empleado/usuario/index";
    }

    @GetMapping("/saveUsuario/{id}")
    public String showSave(@PathVariable("id") Long id, Model model) {
        if (id != null && id != 0) {
            model.addAttribute("usuario", usuarioService.get(id));
        } else {
            model.addAttribute("usuario", new Usuario());
        }
        return "empleado/usuario/save";
    }

    @PostMapping("/saveUsuario")
    public String save(Usuario usuario, Model model) {
        usuarioService.save(usuario);
        return "redirect:/showUsuario";
    }

    @GetMapping("/deleteUsuario/{id}")
    public String delete(@PathVariable Long id, Model model) {
        usuarioService.delete(id);

        return "redirect:/showUsuario";
    }
}