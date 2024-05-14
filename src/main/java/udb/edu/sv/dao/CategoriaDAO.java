package udb.edu.sv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import udb.edu.sv.dao.model.Categoria;

public interface CategoriaDAO extends JpaRepository<Categoria, Long> {
}
