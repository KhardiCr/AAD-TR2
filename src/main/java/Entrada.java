import database.FlywayMigrationUtil;
import entrenadores.Entrenador;
import entrenadores.EntrenadorDAO;
import equipos.Equipo;
import equipos.EquipoDAO;
import jugadores.Jugador;
import jugadores.JugadorDAO;
import ligas.Liga;
import ligas.LigaDAO;
import org.hibernate.exception.ConstraintViolationException;

import java.sql.SQLException;
import java.util.Date;

public class Entrada {

    public static void main(String[] args) {

        //Seedea la base de datos con la estructura de V1__CREATE-DATABASE-STRUCTURE.sql
        try {
            FlywayMigrationUtil.runMigrations();
        } catch (SQLException sqlException){
            System.out.println("La db no está lista o no se puede conectar a ella."+ sqlException.getMessage());
        }

        LigaDAO ligaDao = new LigaDAO();
        EquipoDAO equipoDAO = new EquipoDAO();
        JugadorDAO jugadorDAO = new JugadorDAO();
        EntrenadorDAO entrenadorDAO = new EntrenadorDAO();

            /*
            Esto realiza:
                - Ficha un par de jugadores para otro equipo
                - Crea tres entrenadores y asócialos a un equipo
            */
        Liga l1 = new Liga("Boletus League", new Date(), new Date());
        ligaDao.insertLiga(l1);

        Equipo eq1 = new Equipo("Toad FC", "Reino Champiñón", l1);
        equipoDAO.insertEquipo(eq1);

        Jugador j1 = new Jugador("Mario","Fontanero centro",550000,8,"Italiano",eq1);
        Jugador j2 = new Jugador("Luigi","Defensa de cañerías",450000,4,"Italiano",eq1);
        jugadorDAO.insertJugador(j1);
        jugadorDAO.insertJugador(j2);

        Entrenador en1 = new Entrenador("Bowser",5,2,eq1);
        Entrenador en2 = new Entrenador("Kamek", 4,3,eq1);
        Entrenador en3 = new Entrenador("Yoshi",9,8,eq1);

        try {
            entrenadorDAO.insertEntrenador(en1);
        } catch (ConstraintViolationException e){
            System.out.println("No se ha podido insertar el entrenador, revisa que el equipo no tenga ya un entrenador");
        }

        try {

            entrenadorDAO.insertEntrenador(en2);
        } catch (ConstraintViolationException e){
            System.out.println("No se ha podido insertar el entrenador, revisa que el equipo no tenga ya un entrenador");
        }

        try {
            entrenadorDAO.insertEntrenador(en3);
        } catch (ConstraintViolationException e){
            System.out.println("No se ha podido insertar el entrenador, revisa que el equipo no tenga ya un entrenador");
        }

        /*
            Esto realiza:
            - Muestra datos de todos los equipos
            - Muestra los jugadores de un equipo
            - Muestra los equipos de una liga
            - Muestra todos los entrenadores de los equipos de una liga
         */

        System.out.println("""
            ######################
            #  Listando Equipos  #
            ######################
        """);

        equipoDAO.listAllEquipo();


        System.out.println("""
            ############################################
            #  Recogiendo equipo y listando jugadores  #
            ############################################
        """);
        Equipo equipo = equipoDAO.listEquipos(1);
        jugadorDAO.listJugadorByEquipo(equipo);


        System.out.println("""
            ########################################
            #  Recogiendo liga y listando equipos  #
            ########################################
        """);
        Liga liga = ligaDao.listLiga(1);
        equipoDAO.listEquipoByLiga(liga);


        System.out.println("""
            ##############################################################
            #  Listando entrenadores de los equipos de la liga anterior  #
            ##############################################################
        """);
        entrenadorDAO.listEntrenadorOfEquipoByLiga(liga);
    }
}