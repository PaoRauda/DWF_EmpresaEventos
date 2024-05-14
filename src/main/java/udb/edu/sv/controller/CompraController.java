package udb.edu.sv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import udb.edu.sv.dao.model.Compra;
import udb.edu.sv.service.CompraService;
import udb.edu.sv.service.EventoService;
import udb.edu.sv.service.Evento_BoleteriaService;
import udb.edu.sv.service.UsuarioService;


@Controller
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private Evento_BoleteriaService eventoBoleteriaService;

    @RequestMapping("/showCompra")
    public String index(Model model) {
        model.addAttribute("list", compraService.getAll());
        return "empleado/compra/index";
    }

    @GetMapping("/saveCompra/{id}")
    public String showSave(@PathVariable("id") Long id, Model model) {
        if (id != null && id != 0) {
            model.addAttribute("compra", compraService.get(id));
            model.addAttribute("usuarios", usuarioService.getAll());
            model.addAttribute("eventos", eventoService.getAll());
        } else {
            model.addAttribute("compra", new Compra());
            model.addAttribute("usuarios", usuarioService.getAll());
            model.addAttribute("eventos", eventoService.getAll());
        }
        return "empleado/compra/save";
    }

    @PostMapping("/saveCompra")
    public String save(Compra compra, Model model) {
        compraService.save(compra);
        return "redirect:/showCompra";
    }

    @GetMapping("/deleteCompra/{id}")
    public String delete(@PathVariable Long id, Model model) {
        compraService.delete(id);

        return "redirect:/showCompra";
    }

    //Funciones de Compra para el CLIENTE
    @GetMapping("/comprarEvento/{id}")
    public String comprarEvento(@PathVariable("id") Long id, Model model) {
        if (id != null && id != 0) {
            model.addAttribute("usuarios", usuarioService.getAll());
            model.addAttribute("compra", new Compra());
            model.addAttribute("boleteria", eventoBoleteriaService.get(id));
            return "cliente/HacerCompra";
        } else {
            return "cliente/IndexCliente";
        }
    }

    @PostMapping("/comprarEvento")
    public String comprarEvento(Compra compra, Model model) {
        compraService.save(compra);
        model.addAttribute("compra", compra);
        return "cliente/Recibo";
    }

}