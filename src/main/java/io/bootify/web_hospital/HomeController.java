package io.bootify.web_hospital;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "home/index";
    }

    @GetMapping("/prueba1")
    public String index2() {
        return "home/index1";
    }

}
