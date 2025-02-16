package equipos;

import entrenadores.Entrenador;
import jakarta.persistence.*;
import jugadores.Jugador;
import ligas.Liga;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "equipos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipo {

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

}
