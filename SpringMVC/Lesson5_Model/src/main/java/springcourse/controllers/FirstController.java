package springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller // Тот же бин. Управляет запросами в адресной строке
public class FirstController {

    @GetMapping("/hello") // URL
    public String helloPage(HttpServletRequest request, Model model) { // Get Запрос с параметрами Sin = name=Nikita&surname=Volkov
        String name = request.getParameter("name"); // В виде name
        String surname = request.getParameter("surname"); // И surname

        model.addAttribute("message", "hello, " + name + " " + surname);

        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage(@RequestParam(value = "name", required = false) String name, // Аналогично request но передаёт
                              @RequestParam(value = "surname", required = false) String surname ) { // только параметр

        System.out.println("goodbye, " + name + " " + surname);

        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String Calculator(@RequestParam("a") Integer a,
                             @RequestParam("b") Integer b,
                             @RequestParam("Arithmetic") String Arithmetic,
                             Model model) {

        double result = 0;
        switch (Arithmetic) {

            case("plus") :
                result = a + b;
                break;
            case("-") :
                result = a - b;
                break;
            case("*") :
                result = a * b;
                break;
            case ("/") :
                result = a / (double) b;
                break;
            default:
                result = 0;
                break;

        }
        model.addAttribute("calculator", result);

        return "first/calculator";

    }
}
