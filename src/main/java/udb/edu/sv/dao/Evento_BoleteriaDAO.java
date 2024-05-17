package udb.edu.sv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import udb.edu.sv.dao.model.Evento;
import udb.edu.sv.dao.model.Evento_Boleteria;

import java.util.List;

public interface Evento_BoleteriaDAO extends JpaRepository<Evento_Boleteria, Long> {

    @Query("SELECT eb FROM Evento_Boleteria eb WHERE eb.estado_boleteria.id = :estado")
        public List<Evento_Boleteria> findByEstado(int estado);
}
