package udb.edu.sv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import udb.edu.sv.commons.GenericServiceImpl;
import udb.edu.sv.dao.CompraDAO;
import udb.edu.sv.dao.model.Compra;
import udb.edu.sv.service.CompraService;

@Service
public class CompraServiceImpl extends GenericServiceImpl<Compra,Long> implements CompraService {
    @Autowired
    private CompraDAO compraDAO;

    @Override
    public CrudRepository<Compra, Long> getDao() {
        return compraDAO;
    }
}
