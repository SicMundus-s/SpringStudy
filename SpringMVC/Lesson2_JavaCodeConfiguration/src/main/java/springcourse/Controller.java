package springcourse;


import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/hello-world")
    public String Hello() {
        return "hello_world";
    }
}
