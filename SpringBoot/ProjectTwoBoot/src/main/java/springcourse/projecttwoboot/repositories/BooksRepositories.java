package springcourse.projecttwoboot.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springcourse.projecttwoboot.models.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepositories extends JpaRepository<Book, Integer> {

    Optional<Book> findByTitle(String title);
    List<Book> findByTitleStartingWith(String title); // Метод ищет книги по заданным символам с помощью LIKE

}
