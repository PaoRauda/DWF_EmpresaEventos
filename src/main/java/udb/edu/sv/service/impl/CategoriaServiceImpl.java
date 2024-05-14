package udb.edu.sv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import udb.edu.sv.commons.GenericServiceImpl;
import udb.edu.sv.dao.CategoriaDAO;
import udb.edu.sv.dao.model.Categoria;
import udb.edu.sv.service.CategoriaService;

@Service
public class CategoriaServiceImpl extends GenericServiceImpl<Categoria,Long> implements CategoriaService {
    @Autowired
    private CategoriaDAO categoriaDAO;

    @Override
    public CrudRepository<Categoria,Long> getDao(){
        return categoriaDAO;
    }
}
