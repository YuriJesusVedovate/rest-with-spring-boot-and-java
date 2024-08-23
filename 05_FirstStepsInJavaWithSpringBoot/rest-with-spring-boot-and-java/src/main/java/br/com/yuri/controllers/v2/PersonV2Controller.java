package br.com.yuri.controllers.v2;

import br.com.yuri.data.vo.v2.PersonVOV2;
import br.com.yuri.services.person.PersonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person/v2")
public class PersonV2Controller {

    private final PersonService service;

    public PersonV2Controller(PersonService service) {
        this.service = service;
    }

    @PostMapping()
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
        return service.createV2(person);
    }

    @PutMapping("/{id}")
    public PersonVOV2 updateV2(@RequestBody PersonVOV2 person, @PathVariable Long id) {
        return service.updateV2(person, id);
    }
}
