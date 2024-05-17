package udb.edu.sv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import udb.edu.sv.commons.GenericServiceImpl;
import udb.edu.sv.dao.Evento_BoleteriaDAO;
import udb.edu.sv.dao.model.Evento_Boleteria;
import udb.edu.sv.service.Evento_BoleteriaService;

import java.util.ArrayList;
import java.util.List;

@Service
public class Evento_BoleteriaServiceImpl extends GenericServiceImpl<Evento_Boleteria,Long> implements Evento_BoleteriaService {
    @Autowired
    private Evento_BoleteriaDAO evento_boleteriaDAO;

    @Override
    public CrudRepository<Evento_Boleteria, Long> getDao() {
        return evento_boleteriaDAO;
    }

    public List<Evento_Boleteria> getByEstado(int estado) {
        List<Evento_Boleteria> returnList = new ArrayList<>();
        evento_boleteriaDAO.findByEstado(estado).forEach(obj -> returnList.add(obj));
        return returnList;
    }
}
