package model;

import javax.persistence.*;
@Entity
@Table(name = "school")
public class School {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "school_number")
    private int school_number;


    @OneToOne
    @JoinColumn(name = "schooldirector_id", referencedColumnName = "id")
    private SchoolDirector schoolDirector;

    public School() {

    }

    public School(int school_number) {
        this.school_number = school_number;
    }

    public School(int school_number, SchoolDirector schoolDirector) {
        this.school_number = school_number;
        this.schoolDirector = schoolDirector;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchool_number() {
        return school_number;
    }

    public void setSchool_number(int school_number) {
        this.school_number = school_number;
    }

    public SchoolDirector getSchoolDirector() {
        return schoolDirector;
    }

    public void setSchoolDirector(SchoolDirector schoolDirector) {
        this.schoolDirector = schoolDirector;
        schoolDirector.setSchool(this);

    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", school_number=" + school_number +
                '}';
    }
}
