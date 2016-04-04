package Models;

import javax.persistence.*;

/**
 * Created by swebo_000 on 2016-03-29.
 */
@Entity
@Table(name = "Enrollment", schema = "fypniqhc_intnet16")
public class EnrollEntity {
    private int enrollment_id;
    private int course_id;
    private int user_id;
    private int ambition;
    private String course_name;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    public int getEnrollment_id() {
        return enrollment_id;
    }
    public void setEnrollment_id(int enrollment_id) {
        this.enrollment_id = enrollment_id;
    }


    @Basic
    @Column(name = "course_id")
    public int getCourse_id() {
        return course_id;
    }
    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    @Basic
    @Column(name = "user_id")
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Basic
    @Column(name = "ambition")
    public int getAmbition() {
        return ambition;
    }
    public void setAmbition(int ambition) {
        this.ambition = ambition;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnrollEntity that = (EnrollEntity) o;

        //private int course_id;
        //private String code;
        //private String name;
        //private String description;


        if (course_id != that.course_id) return false;
        if (user_id != that.user_id) return false;
        if (ambition != that.ambition) return false;



        return true;
    }

    @Override
    public int hashCode() {
        //TODO: are we even using this? I don't know how to finish :<
        //int result = course_id;
        //result = 31 * result + (int) (id ^ (id >>> 32));
        //result = 31 * result + (course_id != null ? course_id() : 0);
        //result = 31 * result + (code != null ? code.hashCode() : 0);
        //result = 31 * result + (description != null ? description.hashCode() : 0);
        return 0;
    }
}
