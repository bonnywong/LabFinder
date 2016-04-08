package Models;

import javax.persistence.*;

/**
 * Created by swebo_000 on 2016-04-08.
 */
@Entity
@Table(name = "LabTeam", schema = "fypniqhc_intnet16", catalog = "")
public class LabTeamEntity {
    private int id;
    private int userAId;
    private int userBId;
    private Integer courseId;

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
    public int getUserAId() {
        return userAId;
    }

    public void setUserAId(int userAId) {
        this.userAId = userAId;
    }

    @Basic
    @Column(name = "user_b_id")
    public int getUserBId() {
        return userBId;
    }

    public void setUserBId(int userBId) {
        this.userBId = userBId;
    }

    @Basic
    @Column(name = "course_id")
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LabTeamEntity that = (LabTeamEntity) o;

        if (id != that.id) return false;
        if (userAId != that.userAId) return false;
        if (userBId != that.userBId) return false;
        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userAId;
        result = 31 * result + userBId;
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        return result;
    }
}
