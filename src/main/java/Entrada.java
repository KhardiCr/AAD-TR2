import database.FlywayMigrationUtil;
import database.HibernateUtils;
import entrenadores.Entrenador;
import equipos.Equipo;
import json.JsonReader;
import jugadores.Jugador;
import ligas.Liga;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import json.DataSet;

public class Entrada {
    public static void main(String[] args) {

        //Seedea la base de datos con la estructura de V1__CREATE-DATABASE-STRUCTURE.sql
        FlywayMigrationUtil.runMigrations();

        //Preparamos el dataset.json
        /*
            Al pasarle una uri al objeto dataset, hay problemas al escapar '/', de ahí el uso de paths.get()
            https://medium.com/@AlexanderObregon/javas-paths-get-method-explained-9586c13f2c5c

            Esto se ha resuelto de otra forma al crear un inputStreamReader en JsonReader, y dejar que sea este
            quién busque el recurso en el classpath recibiendo únicamente el nombre del .json
         */
        DataSet dataset = JsonReader.loadJson("dataset.json");

        SessionFactory sessionFactory = new HibernateUtils().getSessionFactory();



        /*
            Si uso getCurrent, cuando hago commit, cierro la sesion.
            Session session = sessionFactory.getCurrentSession();

            en cambio, con openSession dentro de un try, logro persistirla entre commits.

            Los objetos tienen otros objetos como atributos por que el dataset.json define un objeto N con id Y
            Esto facilita la generación de relaciones con hibernate.
         */

        if(dataset != null){
            try (Session session = sessionFactory.openSession()) {
                //Primero insertamos la liga que no tiene dependencias
                session.beginTransaction();
                for (Liga liga : dataset.getLigas()) {
                    session.persist(liga);
                }
                session.getTransaction().commit();


                //Luego insertamos equipos, por que el equipo debe jugar en una liga existente
                session.beginTransaction();
                for (Equipo equipo : dataset.getEquipos()) {
                    session.persist(equipo);
                }
                session.getTransaction().commit();

                // Tanto entrenadores como Jugadores necesitan un equipo que exista.
                session.beginTransaction();
                for (Entrenador entrenador : dataset.getEntrenadores()) {
                    session.persist(entrenador);
                }
                session.getTransaction().commit();

                session.beginTransaction();
                for (Jugador jugador : dataset.getJugadores()) {
                    session.persist(jugador);
                }
                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        /*
            SessionFactory
            Control de session
            beginTransaction
            accion
            commit
         */





    }
}
