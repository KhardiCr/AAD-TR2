package jugadores;

import database.HibernateUtils;
import equipos.Equipo;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.util.List;

public class JugadorDAO {

    private Session session;

    public void insertJugador(Jugador jugador)throws ConstraintViolationException {
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(jugador);
        session.close();
    }

    public void updateJugador(Jugador jugador)throws ConstraintViolationException {
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
    }

    public void deleteJugador(Jugador jugador){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
    }

    public void listJugadorByEquipo(Equipo equipo){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String queryString = "FROM Jugador j WHERE j.equipo=:equipo";
        Query<Jugador> query = session.createQuery(queryString,Jugador.class);
        query.setParameter("equipo",equipo);
        List<Jugador> lista = query.list();
        session.getTransaction().commit();
        session.close();
        for (Jugador jugador : lista){
            System.out.println(jugador.toString());
        }
    }
}
