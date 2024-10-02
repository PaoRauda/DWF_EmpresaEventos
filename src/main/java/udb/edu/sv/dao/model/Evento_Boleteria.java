package udb.edu.sv.dao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Evento_Boleteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_evento", referencedColumnName = "id")
    private Evento evento;

    @Min(value = 0, message = "El precio no puede ser negativo")
    @Column(nullable = false)
    private double precio;

    @Min(value = 0, message = "La cantidad disponible no puede ser negativa")
    @Column(nullable = false)
    private int cantidad_disponible;

    @NotBlank(message = "La fecha de inicio de venta no puede estar vacía")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}", message = "La fecha debe tener el formato correcto: YYYY-MM-DDTHH:mm")
    @Column(nullable = false)
    private String inicio_venta;

    @NotBlank(message = "La fecha de fin de venta no puede estar vacía")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}", message = "La fecha debe tener el formato correcto: YYYY-MM-DD HH:mm")
    @Column(nullable = false)
    private String fin_venta;

    @ManyToOne
    @JoinColumn(name = "estado", referencedColumnName = "id")
    private Estado_Boleteria estado_boleteria;

}
