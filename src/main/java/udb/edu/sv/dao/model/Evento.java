package udb.edu.sv.dao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "La fecha no puede estar vacía")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "La fecha debe tener el formato DD-MM-YYYY")
    @Column(nullable = false)
    private String fecha;

    @NotBlank(message = "El lugar no puede estar vacío")
    @Size(max = 100, message = "El lugar no puede tener más de 100 caracteres")
    @Column(nullable = false)
    private String lugar;

    @Min(value = 1, message = "La capacidad total debe ser al menos 1")
    @Column(nullable = false)
    private int capacidad_total;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 255, message = "La descripción no puede tener más de 255 caracteres")
    @Column
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    private Categoria categoria;
}
