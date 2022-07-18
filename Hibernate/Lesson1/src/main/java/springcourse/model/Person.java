package springcourse.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // Указывает стратегию с помощью которой генерится id
    private int id;

    @Column(name = "person_name")
    private String person_name;

    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "owner")
    /*Cascade - В данном случае при вызове метода Save на объекте, вместе с ним сохраняются и объекты по внешнему ключу
    * Нам не придётся вручную сохранять связанные items в БД*/
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.REFRESH})// Каскадирование в hibernate. Persist - сохранить
    private List<Item> items;

    public Person() {

    }

    public Person(String person_name, int age) {
        this.person_name = person_name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        if(this.items == null)
            this.items = new ArrayList<>();

        this.items.add(item);
        item.setOwner(this);
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_name='" + person_name + '\'' +
                ", age=" + age +
                '}';
    }
}
