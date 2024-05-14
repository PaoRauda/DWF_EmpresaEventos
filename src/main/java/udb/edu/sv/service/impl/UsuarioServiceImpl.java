package udb.edu.sv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import udb.edu.sv.commons.GenericServiceImpl;
import udb.edu.sv.dao.UsuarioDAO;
import udb.edu.sv.dao.model.Usuario;
import udb.edu.sv.service.UsuarioService;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario,Long> implements UsuarioService {
    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public CrudRepository<Usuario,Long> getDao(){
        return usuarioDAO;
    }
}
