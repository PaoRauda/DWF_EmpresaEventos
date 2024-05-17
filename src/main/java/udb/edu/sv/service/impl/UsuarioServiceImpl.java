package udb.edu.sv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import udb.edu.sv.commons.GenericServiceImpl;
import udb.edu.sv.dao.UsuarioDAO;
import udb.edu.sv.dao.model.Evento_Boleteria;
import udb.edu.sv.dao.model.Usuario;
import udb.edu.sv.service.UsuarioService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario,Long> implements UsuarioService {
    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public CrudRepository<Usuario,Long> getDao(){
        return usuarioDAO;
    }

    public boolean IsExistsByEmail(String email){
        Usuario usuario = usuarioDAO.findByEmail(email);
        return usuario != null;
    }
}
