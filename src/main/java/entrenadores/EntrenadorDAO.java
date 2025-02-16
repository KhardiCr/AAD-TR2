package entrenadores;

import database.HibernateUtils;
import ligas.Liga;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.util.List;

public class EntrenadorDAO {

    private Session session;

    public void insertEntrenador(Entrenador entrenador)throws ConstraintViolationException {
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String queryString = "FROM Entrenador e WHERE e.equipo.id=:idEquipo";
        Query<Entrenador> query = session.createQuery(queryString,Entrenador.class);
        query.setParameter("idEquipo",entrenador.getEquipo().getId());
        if (!query.getResultList().isEmpty()) {
            System.out.println("El equipo ya tiene un entrenador.");
        } else {
            session.merge(entrenador);
            session.getTransaction().commit();
            System.out.println("Entrenador asignado a equipo correctamente.");
        }
        session.close();
    }

    public void updateEntrenador(Entrenador entrenadorCambiado){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Entrenador entrenador = session.get(Entrenador.class, entrenadorCambiado.getId());
        if (entrenador != null){
            entrenador.setEquipo(entrenadorCambiado.getEquipo());
            session.merge(entrenador);
        }
        session.getTransaction().commit();
        session.close();
    }

    public void deleteEntrenador(Entrenador entrenador){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
    }

    public void listEntrenadorOfEquipoByLiga(Liga liga){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String queryString = "FROM Entrenador e WHERE e.equipo.liga.id=:idLiga";
        Query<Entrenador> query = session.createQuery(queryString,Entrenador.class);
        query.setParameter("idLiga",liga.getId());
        List<Entrenador> lista = query.list();
        session.getTransaction().commit();
        session.close();
        for (Entrenador entrenador : lista){
            System.out.println(entrenador.toString());
        }
    }
}
