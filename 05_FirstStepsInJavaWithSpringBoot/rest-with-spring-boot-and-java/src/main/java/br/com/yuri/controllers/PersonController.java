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

    @GetMapping(value = "/{id}")
    public Person findById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/all")
    public List<Person> all() {
        return service.findAll();
    }

    @PostMapping()
    public Person create(@RequestBody Person person) {
        return service.Create(person);
    }

    @PutMapping("/{id}")
    public Person update(@RequestBody Person person, @PathVariable String id) {
        return service.Update(person, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        service.Delete(id);
    }

}
