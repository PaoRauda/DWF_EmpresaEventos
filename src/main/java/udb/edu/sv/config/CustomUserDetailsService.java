package udb.edu.sv.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import udb.edu.sv.dao.UsuarioDAO;
import udb.edu.sv.dao.model.Usuario;

public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioDAO.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        //Cargamos una lista de String con los roles alojados en BD
        return new CustomUserDetails(usuario);
    }

}
