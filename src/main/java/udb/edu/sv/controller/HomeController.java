package udb.edu.sv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import udb.edu.sv.service.Evento_BoleteriaService;

@Controller //Controller para navegación de index y paginas iniciales
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
