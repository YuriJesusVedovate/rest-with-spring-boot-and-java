package br.com.yuri.controllers;

import br.com.yuri.model.Person;
import br.com.yuri.services.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping(value = "/{id}", produces = "application/json")
    public Person findById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/all")
    public List<Person> all() {
        return service.findAll();
    }
}
