package dev.evgeni.peopleapi.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    @GetMapping(value = "/dummy")
    private String dummy() {
        return "We added CI/CD";
    }

}
