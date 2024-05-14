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

    @RequestMapping("/")
    public String index() {
        return "general/Index";
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
