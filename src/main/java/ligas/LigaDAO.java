package ligas;

import database.HibernateUtils;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

@NoArgsConstructor
public class LigaDAO {

    private Session session;

    public void insertLiga(Liga liga){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(liga);
        session.getTransaction().commit();
        session.close();
    }

    public Liga listLiga(int id) throws NullPointerException{
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Liga liga = session.get(Liga.class,id);

        //Este syso provoca el nullpointer
        System.out.println(liga.toString());
        session.getTransaction().commit();
        session.close();
        return liga;
    }

    public void listAllLiga() throws NullPointerException{
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Liga> query = session.createNamedQuery("Liga.findAll",Liga.class);
        List<Liga> lista = query.list();
        session.getTransaction().commit();
        session.close();
        for (Liga liga : lista){
            System.out.println(liga);
        }
        //System.out.println(liga.toString());

    }

    public void updateLiga(int id){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Liga liga = session.get(Liga.class, id);
        if (liga != null){
            liga.setNombre_liga("Liga Nacional");
            //ligaInDB.setNombre_liga(liga.getNombre_liga());
            System.out.println("nombre seteado?");
            session.merge(liga);
        }
        session.getTransaction().commit();
        session.close();
    }

    public void deleteLiga(int id){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Liga liga = session.get(Liga.class,id);
        if (liga != null){
            session.delete(id);
        }

    }

}
