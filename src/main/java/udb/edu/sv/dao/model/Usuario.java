package udb.edu.sv.dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String direccion;

    @Column
    private String telefono;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    /* Se crea un relacion ManyToMany entre las tablas "rol" y "usuario", juntandose en la tabla "usuarios_roles".
    * Los datos se guardan en un Hashset por si el usuario tiene más de 1 rol*/
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id")
            ,inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Rol> roles = new HashSet<>();
}
