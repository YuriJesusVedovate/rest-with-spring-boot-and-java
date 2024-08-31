package br.com.yuri.controllers;

import br.com.yuri.data.vo.PersonVO;
import br.com.yuri.services.person.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.yuri.util.MediaType.*;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping(
            value = "/{id}",
            produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
            consumes = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    public ResponseEntity<PersonVO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(
            value = "/all",
            produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
            consumes = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    public ResponseEntity<List<PersonVO>> all() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping(
            produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
            consumes = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    public ResponseEntity<PersonVO> create(@RequestBody PersonVO person) {
        return ResponseEntity.ok(service.create(person));
    }

    @PutMapping(
            value = "/{id}",
            produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
            consumes = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    public ResponseEntity<PersonVO> update(@RequestBody PersonVO person, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(person, id));
    }

    @DeleteMapping(
            value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
