package udb.edu.sv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import udb.edu.sv.dao.model.Usuarios_Roles;
import udb.edu.sv.service.UsuarioService;
import udb.edu.sv.service.Usuarios_RolesService;

@Controller //Controller para templates dentro de carpeta "empleado/usuarios_roles". Acceso restringido
@PreAuthorize("hasAnyRole('EMPLEADO','MANAGER')")
public class Usuarios_RolesController {

    @Autowired
    private Usuarios_RolesService usuariosRolesService;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("/showUsuarios_Roles")
    public String index(Model model){
        model.addAttribute("list", usuariosRolesService.getAll());
        return "empleado/usuarios_roles/index";
    }

    @GetMapping("/saveUsuarios_Roles/{id}")
    public String showSave(@PathVariable("id") Long id, Model model) {
        if (id != null && id != 0) {
            model.addAttribute("usuario_rol", usuariosRolesService.get(id));
            model.addAttribute("usuarios", usuarioService.getAll());
        } else {
            model.addAttribute("usuario_rol", new Usuarios_Roles());
            model.addAttribute("usuarios", usuarioService.getAll());
        }
        return "empleado/usuarios_roles/save";
    }

    @PostMapping("/saveUsuarios_Roles")
    public String save(Usuarios_Roles usuario_Rol, Model model) {
        usuariosRolesService.save(usuario_Rol);
        return "redirect:/showUsuarios_Roles";
    }

    @GetMapping("/deleteUsuarios_Roles/{id}")
    public String delete(@PathVariable Long id, Model model) {
        usuariosRolesService.delete(id);

        return "redirect:/showUsuarios_Roles";
    }
}
