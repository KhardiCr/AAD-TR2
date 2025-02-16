package entrenadores;

import equipos.Equipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

import java.io.Serializable;


@Entity
@Table(name = "entrenadores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Entrenador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;

    @Column(name = "calificacion")
    @Min(1) @Max(10)
    private int calificacion;

    @Column
    private int titulos;

    @OneToOne
    @JoinColumn(name = "id_equipo", unique = true, nullable = false)
    private Equipo equipo;

    public Entrenador(String nombre, int calificacion, int titulos, Equipo equipo) {
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.titulos = titulos;
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "Este entrenador tiene el id: " + id + ", se llama "+ nombre +
                ", y pertenece al equipo "+ equipo;
    }
}
