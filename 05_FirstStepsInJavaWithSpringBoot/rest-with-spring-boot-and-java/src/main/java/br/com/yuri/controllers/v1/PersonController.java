package br.com.yuri.controllers.v1;

import br.com.yuri.data.vo.v1.PersonVO;
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
    public PersonVO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/all")
    public List<PersonVO> all() {
        return service.findAll();
    }

    @PostMapping()
    public PersonVO create(@RequestBody PersonVO person) {
        return service.create(person);
    }

    @PutMapping("/{id}")
    public PersonVO update(@RequestBody PersonVO person, @PathVariable Long id) {
        return service.update(person, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
