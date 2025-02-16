package ligas;


import equipos.Equipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ligas")
@NamedQuery(name = "Liga.findAll", query = "FROM Liga")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Liga implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre_liga;

    @Column
    private Date fecha_inicio;

    @Column
    private Date fecha_fin;

    @OneToMany(mappedBy = "liga", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Equipo> equipos;

    public Liga(String nombre_liga, Date fecha_inicio, Date fecha_fin) {
        this.nombre_liga = nombre_liga;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    @Override
    public String toString() {
        return "La liga tiene el id: " + id + ", con nombre "+ nombre_liga +
                ", empezó el día "+ fecha_inicio +" y acaba el día "+fecha_fin;
    }
}
