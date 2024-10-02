package udb.edu.sv.dao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Column
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    @Column
    private String apellido;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "Debe proporcionar un correo electrónico válido")
    @Column
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 100, message = "La contraseña debe tener entre 8 y 100 caracteres")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "La contraseña debe contener al menos un número, una letra minúscula, una letra mayúscula y un carácter especial"
    )
    private String password;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(min = 5, max = 100, message = "La dirección debe tener entre 5 y 100 caracteres")
    @Column
    private String direccion;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^\\+?[0-9]{1,4}?[-.\\s]?([0-9]{4})[-.\\s]?([0-9]{4})?$|^([0-9]{8})$\n", message = "Debe proporcionar un número de teléfono válido")
    @Column
    private String telefono;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    /* Se crea un relacion ManyToMany entre las tablas "rol" y "usuario", juntandose en la tabla "usuarios_roles".
    * Los datos se guardan en un Hashset por si el usuario tiene más de 1 rol*/
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id")
            ,inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Rol> roles = new HashSet<>();
}
