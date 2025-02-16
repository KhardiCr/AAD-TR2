package jugadores;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import equipos.Equipo;

import java.io.Serializable;

@Entity
@Table(name = "jugadores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Jugador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;

    @Column
    private String posicion;

    @Column
    private int valor_mercado;

    @Column
    private int goles;

    @Column
    private String nacionalidad;

    @ManyToOne
    @JoinColumn(name = "id_equipo", nullable = false)
    private Equipo equipo;

    @Override
    public String toString() {
        return "El jugador tiene el id: " + id + ", con nombre "+ nombre +
                ", juega en la posicion "+ posicion +" para el equipo " + equipo.getNombre_equipo();
    }

    public Jugador(String nombre, String posicion, int valor_mercado, int goles, String nacionalidad, Equipo equipo) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.valor_mercado = valor_mercado;
        this.goles = goles;
        this.nacionalidad = nacionalidad;
        this.equipo = equipo;
    }
}
