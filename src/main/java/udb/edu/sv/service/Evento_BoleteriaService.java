package udb.edu.sv.service;

import udb.edu.sv.commons.GenericService;
import udb.edu.sv.dao.model.Evento_Boleteria;

import java.util.List;

public interface Evento_BoleteriaService extends GenericService<Evento_Boleteria, Long> {

    List<Evento_Boleteria> getByEstado(int estado);

    void actualizarEstado();
}
