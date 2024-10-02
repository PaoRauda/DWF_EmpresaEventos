package udb.edu.sv.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import udb.edu.sv.dao.model.Usuario;
import udb.edu.sv.service.UsuarioService;

@Controller //Controller para templates relacionados a logueo y registro de usuarios.
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/login")
    public String login(){
        return "general/login";
    }

    @RequestMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "general/signup_form";
    }

    @PostMapping("/procesarSignUp")
    public String processRegister(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "general/signup_form";
        }

        boolean usuarioExiste = usuarioService.IsExistsByEmail(usuario.getEmail());

        if (usuarioExiste) {
            model.addAttribute("errorMessage", "El correo electrónico ya está registrado.");
            return "general/signup_form"; // o "general/utils/register_unsuccessful" si es lo que deseas
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encodedPassword);

        usuarioService.save(usuario);

        return "general/utils/register_success";
    }
}
