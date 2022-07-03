package springcourse.dao;

import org.springframework.stereotype.Component;
import springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DAO Инкапсуляция источника данных. Проще говоря DAO урпавляет данными переданными из БД и тем самым закрывая потребность напрямую обращатся к БД
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/Person_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "KakoyParol!1";

    private static Connection connection; // Подключение к БД

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Person> index() {
       List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM person"; // Обычный SQL запрос
            ResultSet resultSet = statement.executeQuery(SQL); // Выполняет запрос к базе данных и возращает полученные данные

            while (resultSet.next()) {
                Person person = new Person();

                    person.setId(resultSet.getInt("id"));
                    person.setAge(resultSet.getInt("age"));
                    person.setName(resultSet.getString("name"));
                    person.setEmail(resultSet.getString("email"));

                    people.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return people;
    }

    public Person show(int id) {
        Person person = null;

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Person WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery(); // resultSet записывает всё что было получено по запросу к БД

            resultSet.next();  // Сдвигает указатель на единицу

            person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setEmail(resultSet.getString("email"));
            person.setAge(resultSet.getInt("age"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    public void save(Person person) {

        try {
            // PreparedStatement быстре statement потому что комплируется как SQL запрос всего один раз.
            // Безопаснее, избегаем SQL-иньекций(Ввода SQL команд непосредственно в поле формы)
            // Так же он может кэшироваться в БД
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Person VALUES(1, ?, ?, ?)");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());

            preparedStatement.executeUpdate(); // Execute - выполнить. Обновляет(изменяет) данные в бд
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, Person updatedPerson) {

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Person SET name=?, age =?, email=? WHERE id=?");
            preparedStatement.setString(1,  updatedPerson.getName());
            preparedStatement.setInt(2, updatedPerson.getAge());
            preparedStatement.setString(3, updatedPerson.getEmail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM person WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}