package equipos;

import database.HibernateUtils;
import jugadores.Jugador;
import ligas.Liga;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.util.List;

public class EquipoDAO {

    private Session session;

    public void insertEquipo(Equipo equipo)throws ConstraintViolationException {
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(equipo);
        session.close();
    }

    public Equipo listEquipos(int id) throws NullPointerException{
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Equipo equipo = session.get(Equipo.class,id);
        session.close();
        return equipo;
    }

    public void updateEquipo(Equipo equipo)throws ConstraintViolationException {
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
    }

    public void deleteEquipo(Equipo equipo){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
    }

    public void listAllEquipo() throws NullPointerException{
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Equipo> query = session.createNamedQuery("Equipo.findAll",Equipo.class);
        List<Equipo> lista = query.list();
        session.getTransaction().commit();
        session.close();
        for (Equipo equipo : lista){
            System.out.println(equipo.toString());
        }

    }

    public void listEquipoByLiga(Liga liga){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String queryString = "FROM Equipo e WHERE e.liga=:liga";
        Query<Equipo> query = session.createQuery(queryString,Equipo.class);
        query.setParameter("liga",liga);
        List<Equipo> lista = query.list();
        session.getTransaction().commit();
        session.close();
        for (Equipo equipo : lista){
            System.out.println(equipo.toString());
        }
    }
}
