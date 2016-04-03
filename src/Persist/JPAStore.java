package Persist;

import Models.CourseEntity;
import Models.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
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


    public void updateUser(UserEntity user){

        UserEntity id_user = fetchUser(user.getFacebookId());
        UserEntity old_user = em.find(UserEntity.class, id_user.getId());
        em.getTransaction().begin();

        old_user.setName(user.getName());
        old_user.setProgram(user.getProgram());
        old_user.setMaster(user.getMaster());
        old_user.setEmail(user.getEmail());
        old_user.setSchool(user.getSchool());
        old_user.setComments(user.getComments());

        em.getTransaction().commit();
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
        System.out.println("boop");
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

    /**
     * Stores a UserEntity into the database.
     * @param course UserEntity to be stored
     */
    public void persistCourse(CourseEntity course) {
        try {
            em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Entity already exists.");
        }
    }


    /**
     * Fetch all users in database.
     * @return List of all CourseEntities in database.
     */
    public List<CourseEntity> fetchAllCourses() {
        //TODO: Case if db has no Courses
        Query query = em.createQuery("select u from CourseEntity u");
        return query.getResultList();
    }



}



















