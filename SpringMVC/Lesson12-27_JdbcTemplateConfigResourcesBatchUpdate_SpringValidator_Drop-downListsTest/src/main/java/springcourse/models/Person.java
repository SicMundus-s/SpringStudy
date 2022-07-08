package springcourse.models;


import javax.validation.constraints.*;

public class Person {
    private int id;

    @NotEmpty(message = "Name should not be empty") // Валидация. Не может быть пустым
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters") // мин/макс размер. Message выводиться при не выполнение условий
    private String name;

    @Min(value = 0, message = "Age should be greater than 0")
    private int age;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid") // Использует регулярные выражения для проверки ввода email(sdfsd@afaf.ru)
    private String email;
    //Структура - Страна, Город, Индекс( 6 цифр)
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "your address should be in this format: County, City, Postal Code (6 digits") // Паттерн - представляет шаблон строки адресс(какой она должна быть)
    private String address;
    public Person() {

    }

    public Person(int id, String name, int age, String email, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}