package udb.edu.sv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import udb.edu.sv.dao.model.Compra;
import udb.edu.sv.dao.model.Usuario;

import java.util.List;

public interface CompraDAO extends JpaRepository<Compra, Long> {

    @Query("SELECT c FROM Compra c WHERE c.usuario.email = :email")
    public List<Compra> findByUsuarioEmail(String email);
}
