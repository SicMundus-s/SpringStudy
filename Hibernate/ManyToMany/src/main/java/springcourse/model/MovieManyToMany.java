package springcourse.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "moviemanytomany")
public class MovieManyToMany {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "year_of_production")
    private int year_of_production;

    @ManyToMany(mappedBy = "movieManyToManyList")
    private List<Actor> actorList;

    public MovieManyToMany() {

    }

    public MovieManyToMany(String name, int year_of_production) {
        this.name = name;
        this.year_of_production = year_of_production;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear_of_production() {
        return year_of_production;
    }

    public void setYear_of_production(int year_of_production) {
        this.year_of_production = year_of_production;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieManyToMany that = (MovieManyToMany) o;

        if (id != that.id) return false;
        if (year_of_production != that.year_of_production) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + year_of_production;
        return result;
    }

    @Override
    public String toString() {
        return "MovieManyToMany{" +
                "name='" + name + '\'' +
                ", year_of_production=" + year_of_production +
                '}';
    }
}
