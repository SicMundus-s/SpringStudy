package spring.course.springsecurity.models;

import javax.persistence.*;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "year_of_brith")
    private int year_of_brith;

    @Column(name = "password")
    private String password;

    public Person() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getYear_of_brith() {
        return year_of_brith;
    }

    public void setYear_of_brith(int year_of_brith) {
        this.year_of_brith = year_of_brith;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", year_of_brith=" + year_of_brith +
                ", password='" + password + '\'' +
                '}';
    }
}
