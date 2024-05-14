package udb.edu.sv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import udb.edu.sv.dao.model.Rol;

public interface RolDAO extends JpaRepository<Rol, Long> {
}
