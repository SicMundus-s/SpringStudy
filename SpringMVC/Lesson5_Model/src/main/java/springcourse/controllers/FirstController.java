package springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller // Тот же бин. Управляет запросами в адресной строке
public class FirstController {

    @GetMapping("/hello") // URL
    public String helloPage(HttpServletRequest request) { // Get Запрос с параметрами Sin = name=Nikita&surname=Volkov
        String name = request.getParameter("name"); // В виде name
        String surname = request.getParameter("surname"); // И surname

        System.out.println("hello, " + name + " " + surname);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage(@RequestParam(value = "name", required = false) String name, // Аналогично request но передаёт
                              @RequestParam(value = "surname", required = false) String surname) { // только параметры

        System.out.println("goodbye, " + name + " " + surname);

        return "first/goodbye";
    }
}
