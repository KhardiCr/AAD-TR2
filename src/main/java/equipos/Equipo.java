package equipos;

import entrenadores.Entrenador;
import jakarta.persistence.*;
import jugadores.Jugador;
import ligas.Liga;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "equipos")
@NamedQuery(name = "Equipo.findAll", query = "FROM Equipo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre_equipo;

    @Column
    private String ciudad;

    @ManyToOne
    @JoinColumn(name = "id_liga", nullable = false)
    private Liga liga;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jugador> jugadores;

    @OneToOne(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Entrenador entrenador;


    @Override
    public String toString() {
        return "Este equipo tiene el id: " + id + ", se llama "+ nombre_equipo +
                ", es de la ciudad de "+ciudad+" y juega en la liga: " + liga.getNombre_liga();
    }

    public Equipo(String nombre_equipo, String ciudad, Liga liga) {
        this.nombre_equipo = nombre_equipo;
        this.ciudad = ciudad;
        this.liga = liga;
    }
}
