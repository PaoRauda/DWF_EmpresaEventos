package udb.edu.sv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import udb.edu.sv.dao.UsuarioDAO;
import udb.edu.sv.dao.model.Compra;
import udb.edu.sv.dao.model.Evento_Boleteria;
import udb.edu.sv.dao.model.Usuario;
import udb.edu.sv.service.CompraService;
import udb.edu.sv.service.Evento_BoleteriaService;

@Controller
public class ClienteController
{
    @Autowired
    private CompraService compraService;

    @Autowired
    private Evento_BoleteriaService eventoBoleteriaService;

    @Autowired
    private UsuarioDAO usuarioDAO;


    @GetMapping("/comprarEvento/{id}")
    public String comprarEvento(@PathVariable("id") Long id, Model model) {
        if (id != null && id != 0) {
            Evento_Boleteria boleteria = eventoBoleteriaService.get(id);
            Compra compra = new Compra();
            compra.setEvento(boleteria.getEvento());

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            Usuario usuario = usuarioDAO.findByEmail(email);
            compra.setUsuario(usuario);

            model.addAttribute("compra", compra);
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
