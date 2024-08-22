package br.com.yuri.controllers;

import br.com.yuri.models.Person;
import br.com.yuri.services.person.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public Person findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/all")
    public List<Person> all() {
        return service.findAll();
    }

    @PostMapping()
    public Person create(@RequestBody Person person) {
        return service.create(person);
    }

    @PutMapping("/{id}")
    public Person update(@RequestBody Person person, @PathVariable Long id) {
        return service.update(person, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
