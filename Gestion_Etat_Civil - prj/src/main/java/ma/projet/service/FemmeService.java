package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class FemmeService implements IDao<Femme> {

    @Override
    public void create(Femme femme) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            session.save(femme);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Femme femme) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            session.update(femme);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Femme femme) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            session.delete(session.load(Femme.class, femme.getClass()));
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Femme findById(int id) {
        Session session = null;
        Femme femme = null;
        try {
            session = HibernateUtil.getSession();
            femme = session.get(Femme.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return femme;
    }

    @Override
    public List<Femme> findAll() {
        Session session = null;
        List femmes = null;
        try {
            session = HibernateUtil.getSession();
            femmes = session.createQuery("from Femme").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return femmes;
    }


    public int getNombreEnfantsEntreDates(int idFemme, Date date1, Date date2) {
        Session session = null;
        int total = 0;
        try {
            session = HibernateUtil.getSession();

            // Utilisation de la requête nommée définie dans l'entité Femme
            Long result = (Long) session.getNamedQuery("Femme.nombreEnfantsEntreDates")
                    .setParameter("idFemme", idFemme)
                    .setParameter("date1", date1)
                    .setParameter("date2", date2)
                    .uniqueResult();

            total = (result != null) ? result.intValue() : 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return total;
    }


    public List getFemmesMarieesDeuxFois() {
        Session session = null;
        List femmes = null;
        try {
            session = HibernateUtil.getSession();

            femmes = session.getNamedQuery("Femme.femmesMarieesDeuxFois")
                    .list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return femmes;
    }

    // La femme la plus âgée

    public Femme getFemmeLaPlusAgee() {
        Session session = null;
        Femme femme = null;
        try {
            session = HibernateUtil.getSession();

            String hql = "FROM Femme f ORDER BY f.dateNaissance ASC";
            List<Femme> femmes = session.createQuery(hql, Femme.class)
                    .setMaxResults(1)
                    .list();

            if (!femmes.isEmpty()) {
                femme = femmes.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return femme;
    }
}
