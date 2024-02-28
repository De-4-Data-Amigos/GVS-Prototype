package dk.lyngby.dao.impl;

import dk.lyngby.dao.IDao;
import dk.lyngby.exception.ApiException;

import dk.lyngby.model.Manager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class ManagerDAO implements IDao<Manager, Integer> {

    private static ManagerDAO instance;

    private static EntityManagerFactory emf;


    public static ManagerDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ManagerDAO();
        }
        return instance;
    }

    @Override
    public Manager read(Integer id) throws ApiException {
        try (var em = emf.createEntityManager()) {
            return em.find(Manager.class, id);
        }
    }

    @Override
    public List<Manager> readAll() throws ApiException {
        try (var em = emf.createEntityManager()) {
            var query = em.createQuery("SELECT m FROM Manager m", Manager.class);
            return query.getResultList();
        }
    }

    @Override
    public Manager create(Manager manager) throws ApiException {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(manager);
            em.getTransaction().commit();
            return manager;
        }
    }

    @Override
    public Manager update(Integer id, Manager manager) throws ApiException {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Manager managerToUpdate = em.find(Manager.class, id);
            managerToUpdate.setFirstName(manager.getFirstName());
            managerToUpdate.setLastName(manager.getLastName());
            managerToUpdate.setEmail(manager.getEmail());
            managerToUpdate.setPhone(manager.getPhone());
            Manager managerMerge = em.merge(managerToUpdate);
            em.getTransaction().commit();
            return managerMerge;
        }
    }

    @Override
    public boolean delete(Integer id) throws ApiException {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Manager manager = em.find(Manager.class, id);
            em.remove(manager);
            em.getTransaction().commit();
        }

        return false;
    }

    public Boolean validatePrimaryKey(Integer integer) {
        try (var em = emf.createEntityManager()) {
            var manager = em.find(Manager.class, integer);
            return manager != null;
        }
    }
}