package udb.edu.sv.dao.model;

import jakarta.persistence.*;
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

    @Column
    private double precio;

    @Column
    private int cantidad_disponible;

    @Column
    private String inicio_venta;

    @Column
    private String fin_venta;

    @ManyToOne
    @JoinColumn(name = "estado", referencedColumnName = "id")
    private Estado_Boleteria estado_boleteria;

}
