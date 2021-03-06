package Persist;

import Models.*;

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
     * Fetch users with name
     * @param id
     * @return List of UserEntities with provided name
     */
    public UserEntity fetchUser(int id) {
        Query query = em.createQuery("select u from UserEntity u where u.id = :id");
        query.setParameter("id", id);
        return (UserEntity) query.getSingleResult();
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
     * Fetch all users in database.
     * @return List of all UserEntities in database.
     */
    public List<AmbitionEntity> fetchAllAmbitions() {
        //TODO: Case if db has no Users
        Query query = em.createQuery("select u from AmbitionEntity u");
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


    public void persistEnroll(EnrollEntity enrollment) {
        try {
            em.getTransaction().begin();
            em.persist(enrollment);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Entity already exists.");
        }
    }



    public List<EnrollEntity> fetchAllEnrolls(int user_id) {
        Query query = em.createQuery("select u from EnrollEntity u where u.user_id = :user_id");
        query.setParameter("user_id", user_id);
        return query.getResultList();
    }

    /**
     * Retrieves a list of EnrollEntity objects based on course id.
     * @param course_id
     * @return
     */
    public List<EnrollEntity> fetchAllEnrollsByCourse(int course_id) {
        Query query = em.createQuery("select u from EnrollEntity u where u.course_id = :course_id");
        query.setParameter("course_id", course_id);
        return query.getResultList();
    }

    public void removeEnroll(EnrollEntity enrollment) {
        EnrollEntity enroll = null;

        for(EnrollEntity e : fetchAllEnrolls(enrollment.getUser_id())){
            if(enrollment.getUser_id() == e.getUser_id() &&  enrollment.getCourse_id() == e.getCourse_id()){
                enroll = e;
                break;
            }
        }

        if(enroll != null){
            em.getTransaction().begin();
            em.remove(enroll);
            em.getTransaction().commit();
        }
    }









    public UserEntity[] getUsersWithCourse(String course_id){
        List<EnrollEntity> enrolls0 = fetchAllEnrollsByCourse(Integer.parseInt(course_id));
        ArrayList<UserEntity> users = new ArrayList<UserEntity>();

        for(EnrollEntity ee : enrolls0){
            boolean userAlreadyAdded = false;
            for(UserEntity uu : users){
                if(ee.getUser_id() == uu.getId()){
                    userAlreadyAdded = true;
                }
            }
            if(userAlreadyAdded == false){
                UserEntity newUser = fetchUser(ee.getUser_id());
                String id = Integer.toString(ee.getAmbition());
                String ambition = fetchAmbition(id).getDescription();
                newUser.setAmbition(ambition);
                users.add(newUser);
            }
        }
        UserEntity[] userArray = new UserEntity[users.size()];
        return users.toArray(userArray);
    }









    /**
     * Fetch users with name
     * @param idr
     * @return List of UserEntities with provided name
     */
    public AmbitionEntity fetchAmbition(String idr) {
        try{
            Query query = em.createQuery("select u from AmbitionEntity u where u.id = :id");
            query.setParameter("id", Integer.parseInt(idr));
            return (AmbitionEntity) query.getSingleResult();
        }catch(javax.persistence.NoResultException e){
            AmbitionEntity dummy = new AmbitionEntity();
            dummy.setDescription("OVER9000!!!");
            return dummy;
        }

    }

    /**
     * Fetch a CourseEntity by id
     * @param id
     * @return
     */
    public CourseEntity fetchCourse(int id) {
        CourseEntity found = em.find(CourseEntity.class, id);
        return found;
    }


    public void persistLabTeam(LabTeamEntity team) {
        em.getTransaction().begin();
        em.persist(team);
        em.getTransaction().commit();
    }

    public List<LabTeamEntity> fetchAllLabTeams() {
        Query query = em.createQuery("select t from LabTeamEntity t");
        return query.getResultList();
    }

    public List<LabTeamEntity> fetchOwnLabTeams(int user_id) {
        Query query = em.createQuery("select t from LabTeamEntity t where t.userAId = :user_id or t.userBId = :user_id");
        query.setParameter("user_id", user_id);
        return query.getResultList();
    }

    public void removeLabTeam(int id) {
        LabTeamEntity found = em.find(LabTeamEntity.class, id);
        em.getTransaction().begin();
        em.remove(found);
        em.getTransaction().commit();
    }

    public void persistProposal(String proposed_user_id, String proposer_user_id, String course_id) {
        ProposalEntity pe = new ProposalEntity();
        pe.setCourse_id(Integer.parseInt(course_id));
        pe.setProposed_id(Integer.parseInt(proposed_user_id));
        pe.setProposer_id(Integer.parseInt(proposer_user_id));
        addCourseTag(pe);
        addAmbitionTag(pe);
        addUserTags(pe);

        List<ProposalEntity> proposals = fetchAllSentProposals(Integer.parseInt(proposer_user_id));
        for(ProposalEntity p : proposals){
            if(pe.getCourse_id() == p.getCourse_id() && pe.getProposed_id() == p.getProposed_id() && pe.getProposer_id() == p.getProposer_id()){
                return;
            }
        }

        try {
            em.getTransaction().begin();
            em.persist(pe);
            em.getTransaction().commit();
            System.out.println(proposer_user_id + " PROPOSED TO " + proposed_user_id + " IN COURSE " + course_id);
        } catch (Exception e) {
            System.out.println("Entity already exists.");
        }
    }



    public List<ProposalEntity> fetchAllReceivedProposals(int proposed_id) {
        Query query = em.createQuery("select u from ProposalEntity u where u.proposed_id = :proposed_id");
        query.setParameter("proposed_id", proposed_id);
        return query.getResultList();
    }

    public List<ProposalEntity> fetchAllSentProposals(int proposer_id) {
        Query query = em.createQuery("select u from ProposalEntity u where u.proposer_id = :proposer_id");
        query.setParameter("proposer_id", proposer_id);
        return query.getResultList();
    }

    public void removeProposal(int id) {
        ProposalEntity p = em.find(ProposalEntity.class, id);
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
    }

    public void addCourseTag(ProposalEntity data){

        List<CourseEntity> courses = fetchAllCourses();
        for(CourseEntity c : courses){
            if(c.getCourse_id() == data.getCourse_id()){
                data.setCourse_tag(c.getCode() + " - " + c.getName());
                break;
            }
        }
    }

    public void addAmbitionTag(ProposalEntity data){

        List<EnrollEntity> enrolls = fetchAllEnrolls(data.getProposer_id());
        int ambition = -1;
        for(EnrollEntity e : enrolls){
            if(e.getCourse_id() == data.getCourse_id()){
                ambition = e.getAmbition();
                break;
            }
        }

        AmbitionEntity amb = (AmbitionEntity) fetchAmbition(Integer.toString(ambition));
        if(amb.getId() == ambition){
            data.setAmbition(amb.getDescription());
        }
    }


    public void addAmbitionTag(UserEntity data, int course_id){
        List<EnrollEntity> enrolls = fetchAllEnrolls(data.getId());
        int ambition = -1;
        for(EnrollEntity e : enrolls){
            if(e.getCourse_id() == course_id){
                ambition = e.getAmbition();
                break;
            }
        }

        AmbitionEntity amb = (AmbitionEntity) fetchAmbition(Integer.toString(ambition));
        if(amb.getId() == ambition){
            data.setAmbition(amb.getDescription());
        }
    }


    private void addUserTags(ProposalEntity pe) {
        pe.setUser_tag(fetchUser(pe.getProposer_id()).getName());
        pe.setTouser_tag(fetchUser(pe.getProposed_id()).getName());
    }


}

















