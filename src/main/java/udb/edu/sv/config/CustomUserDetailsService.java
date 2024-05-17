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

        Usuario usuario = usuarioDAO.findByEmail(email); // Busca datos del usuario
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new CustomUserDetails(usuario);
    }

}
