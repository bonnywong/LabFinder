package Persist;

import Models.UserEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by swebo_000 on 2016-03-28.
 */
public class JPAStore {
    private EntityManagerFactory emfactory;
    private EntityManager em;
    public JPAStore() {
        emfactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        em = emfactory.createEntityManager();
    }

    /**
     * Stores a UserEntity into the database.
     * @param user UserEntity to be stored
     */
    public void persistUser(UserEntity user) {
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Entity already exists.");
        }

    }


    /**
     * Fetch a user based on unique parameter facebookId
     * @param facebookId
     * @return
     */
    public UserEntity fetchUser(Long facebookId) {
        Query query = em.createQuery("select u from UserEntity u where u.facebookId = :facebookId");
        query.setParameter("facebookId", facebookId);
        return (UserEntity) query.getSingleResult();
    }

    /**
     * Fetch users with name
     * @param name
     * @return List of UserEntities with provided name
     */
    public  List<UserEntity> fetchUser(String name) {
        Query query = em.createQuery("select u from UserEntity u where u.name = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    /**
     * Fetch all users in database.
     * @return List of all UserEntities in database.
     */
    public List<UserEntity> fetchAllUsers() {
        //TODO: Case if db has no Users
        Query query = em.createQuery("select u from UserEntity u");
        return query.getResultList();
    }
}
