package udb.edu.sv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import udb.edu.sv.dao.model.Compra;

public interface CompraDAO extends JpaRepository<Compra, Long> {
}
