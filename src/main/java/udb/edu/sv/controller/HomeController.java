package udb.edu.sv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import udb.edu.sv.dao.model.Usuario;
import udb.edu.sv.service.Evento_BoleteriaService;
import udb.edu.sv.service.UsuarioService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private Evento_BoleteriaService eventoBoleteriaService;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("/")
    public String index() {
        return "general/Index";
    }

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

    @GetMapping("/indexEmpleado")
    public String indexEmpleado() {
        return "empleado/IndexEmpleado";
    }

    @GetMapping("/indexCliente")
    public String indexCliente(Model model) {
        model.addAttribute("list", eventoBoleteriaService.getAll());
        return "cliente/IndexCliente";
    }
}
