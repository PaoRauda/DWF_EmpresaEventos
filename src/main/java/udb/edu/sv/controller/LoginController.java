package udb.edu.sv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import udb.edu.sv.dao.model.Usuario;
import udb.edu.sv.service.UsuarioService;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "general/signup_form";
    }

    @PostMapping("/procesarSignUp")
    public String processRegister(Usuario usuario) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encodedPassword);

        usuarioService.save(usuario);

        return "general/utils/register_success";
    }
}
