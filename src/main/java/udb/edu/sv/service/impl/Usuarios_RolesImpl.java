package udb.edu.sv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import udb.edu.sv.commons.GenericServiceImpl;
import udb.edu.sv.dao.Usuarios_RolesDAO;
import udb.edu.sv.dao.model.Usuarios_Roles;
import udb.edu.sv.service.Usuarios_RolesService;

@Service
public class Usuarios_RolesImpl extends GenericServiceImpl<Usuarios_Roles,Long> implements Usuarios_RolesService {
    @Autowired
    private Usuarios_RolesDAO usuarios_rolesDAO;

    @Override
    public CrudRepository<Usuarios_Roles,Long> getDao(){
        return usuarios_rolesDAO;
    }
}
