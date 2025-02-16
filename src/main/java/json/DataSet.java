package json;

import ligas.Liga;
import equipos.Equipo;
import jugadores.Jugador;
import entrenadores.Entrenador;

import java.util.List;


public class DataSet {
    private List<Liga> Ligas;
    private List<Equipo> Equipos;
    private List<Jugador> Jugadores;
    private List<Entrenador> Entrenadores;

    public List<Liga> getLigas() { return Ligas; }
    public List<Equipo> getEquipos() { return Equipos; }
    public List<Jugador> getJugadores() { return Jugadores; }
    public List<Entrenador> getEntrenadores() { return Entrenadores; }
}