package udb.edu.sv.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import udb.edu.sv.dao.model.Usuario;
import udb.edu.sv.service.UsuarioService;
import org.springframework.ui.Model;

@Controller //Controller para templates dentro de carpeta "empleado/usuario". Acceso restringido
@PreAuthorize("hasAnyRole('EMPLEADO','MANAGER')")
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
        if (!model.containsAttribute("usuario")) {
            if (id != null && id != 0) {
                model.addAttribute("usuario", usuarioService.get(id));
            } else {

                model.addAttribute("usuario", new Usuario());
            }
        }
        return "empleado/usuario/save";
    }

    @PostMapping("/saveUsuario")
    public String save(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result,  RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.usuario", result);
            redirectAttributes.addFlashAttribute("usuario", usuario);
            return "redirect:/saveUsuario/" + (usuario.getId() != null ? usuario.getId() : 0);
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encodedPassword);

        usuarioService.save(usuario);
        return "redirect:/showUsuario";
    }

    @GetMapping("/deleteUsuario/{id}")
    public String delete(@PathVariable Long id, Model model) {
        usuarioService.delete(id);

        return "redirect:/showUsuario";
    }
}
