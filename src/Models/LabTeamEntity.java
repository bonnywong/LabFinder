package Models;

import javax.persistence.*;

/**
 * Created by swebo_000 on 2016-03-29.
 */
@Entity
@Table(name = "LabTeam", schema = "fypniqhc_intnet16")
public class LabTeamEntity {
    private int id;
    private int user_a_id;
    private int user_b_id;
    private int course_id;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_a_id")
    public int getUser_a_id() {
        return user_a_id;
    }
    public void setUser_a_id(int user_a_d) {
        this.user_a_id = user_a_id;
    }


    @Basic
    @Column(name = "user_b_id")
    public int getUser_b_id() {
        return user_b_id;
    }
    public void setUser_b_id(int user_b_d) {
        this.user_b_id = user_b_id;
    }


    @Basic
    @Column(name = "course_id")
    public int getCourse_id() {
        return course_id;
    }
    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LabTeamEntity that = (LabTeamEntity) o;

        //private int course_id;
        //private String code;
        //private String name;
        //private String description;


        if (course_id != that.course_id) return false;
        if (id != that.id) return false;
        if (user_a_id != that.user_a_id) return false;
        if (user_b_id != that.user_b_id) return false;



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
