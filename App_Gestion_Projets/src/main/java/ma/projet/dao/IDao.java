package ma.projet.dao;

import ma.projet.classes.EmployeTache;
import ma.projet.classes.EmployeTacheId;

import java.util.List;

public interface IDao<T> {
    //Les méthodes
    boolean save(T entity);
    boolean update(T entity);
    boolean delete(T entity);
    T findById(int id);

    EmployeTache findById(EmployeTacheId id);

    List<T> findAll();
}