package Models;

import javax.persistence.*;

/**
 * Created by swebo_000 on 2016-03-29.
 */
@Entity
@Table(name = "Ambition", schema = "fypniqhc_intnet16")
public class AmbitionEntity {

    private int id;
    private String description;

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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AmbitionEntity that = (AmbitionEntity) o;

        //private int course_id;
        //private String code;
        //private String name;
        //private String description;


        if (id != that.id) return false;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
