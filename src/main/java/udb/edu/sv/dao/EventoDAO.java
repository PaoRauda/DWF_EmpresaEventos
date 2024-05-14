package udb.edu.sv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import udb.edu.sv.dao.model.Evento;

public interface EventoDAO  extends JpaRepository<Evento, Long> {
}
