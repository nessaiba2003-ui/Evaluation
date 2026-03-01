package ma.projet.service;

import ma.projet.classes.Categorie;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static ma.projet.util.HibernateUtil.getSessionFactory;

public class CategorieService implements IDao<Categorie> {

    //Méthodes
    @Override
    public boolean create(Categorie categorie) {
        Session session = null;
        Transaction tx = null;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(categorie);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean update(Categorie categorie) {
        Session session = null;
        Transaction tx = null;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(categorie);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean delete(Categorie categorie) {
        Session session = null;
        Transaction tx = null;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(categorie);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Categorie findById(int id) {
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            return session.get(Categorie.class, id);
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Categorie> findAll() {
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            return session.createQuery("FROM Categorie", Categorie.class).list();
        } finally {
            if (session != null) session.close();
        }
    }
}
