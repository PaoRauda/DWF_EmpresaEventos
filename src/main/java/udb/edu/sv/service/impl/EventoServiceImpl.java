package udb.edu.sv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import udb.edu.sv.commons.GenericServiceImpl;
import udb.edu.sv.dao.EventoDAO;
import udb.edu.sv.dao.model.Evento;
import udb.edu.sv.service.EventoService;

@Service
public class EventoServiceImpl extends GenericServiceImpl<Evento,Long>  implements EventoService {
    @Autowired
    private EventoDAO eventoDAO;

    @Override
    public CrudRepository<Evento, Long> getDao(){
        return eventoDAO;
    }
}
