package udb.edu.sv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import udb.edu.sv.dao.model.Compra;
import udb.edu.sv.service.CompraService;
import udb.edu.sv.service.Evento_BoleteriaService;
import udb.edu.sv.service.UsuarioService;

@Controller
public class ClienteController
{
    @Autowired
    private CompraService compraService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private Evento_BoleteriaService eventoBoleteriaService;

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
