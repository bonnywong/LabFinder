package Models;

import javax.persistence.*;

/**
 * Created by swebo_000 on 2016-03-29.
 */
@Entity
@Table(name = "Proposal", schema = "fypniqhc_intnet16")
public class ProposalEntity {
    private int id;
    private int proposer_id;
    private int proposed_id;
    private int course_id;
    private String course_tag;
    private String ambition;



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
    @Column(name = "proposer_id")
    public int getProposer_id() {
        return proposer_id;
    }
    public void setProposer_id(int proposer_id) {
        this.proposer_id = proposer_id;
    }


    @Basic
    @Column(name = "proposed_id")
    public int getProposed_id() {
        return proposed_id;
    }
    public void setProposed_id(int proposed_id) {
        this.proposed_id = proposed_id;
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
    @Column(name = "course_tag")
    public String getCourse_tag() {
        return course_tag;
    }
    public void setCourse_tag(String course_tag) {
        this.course_tag = course_tag;
    }


    @Basic
    @Column(name = "ambition")
    public String getAmbition() {
        return ambition;
    }
    public void setAmbition(String ambition) {
        this.ambition = ambition;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProposalEntity that = (ProposalEntity) o;

        //private int course_id;
        //private String code;
        //private String name;
        //private String description;


        if (course_id != that.course_id) return false;
        if (id != that.id) return false;
        if (proposed_id != that.proposed_id) return false;
        if (proposer_id != that.proposer_id) return false;



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
