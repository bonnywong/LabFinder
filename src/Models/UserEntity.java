package Models;

import javax.persistence.*;

/**
 * Created by swebo_000 on 2016-03-29.
 */
@Entity
@Table(name = "User", schema = "fypniqhc_intnet16", catalog = "")
public class UserEntity {
    private int id;
    private long facebookId;
    private String name;
    private String email;
    private String program;
    private String master;
    private String comments;
    private String school;
    private String ambition;   //used only in special cases

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
    @Column(name = "facebook_id")
    public long getFacebookId() {
        return facebookId;
    }
    public void setFacebookId(long facebookId) {
        this.facebookId = facebookId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "program")
    public String getProgram() {
        return program;
    }
    public void setProgram(String program) {
        this.program = program;
    }

    @Basic
    @Column(name = "school")
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }

    @Basic
    @Column(name = "master")
    public String getMaster() {
        return master;
    }
    public void setMaster(String master) {
        this.master = master;
    }

    @Basic
    @Column(name = "comments")
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    @Basic
    @Column(name = "ambition")
    public String getAmbition() {return ambition; }
    public void setAmbition(String ambition) { this.ambition = ambition; }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (facebookId != that.facebookId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (program != null ? !program.equals(that.program) : that.program != null) return false;
        if (master != null ? !master.equals(that.master) : that.master != null) return false;
        if (comments != null ? !comments.equals(that.comments) : that.comments != null) return false;
        if (comments != null ? !school.equals(that.school) : that.school != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) (facebookId ^ (facebookId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (program != null ? program.hashCode() : 0);
        result = 31 * result + (master != null ? master.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (comments != null ? school.hashCode() : 0);
        return result;
    }
}
