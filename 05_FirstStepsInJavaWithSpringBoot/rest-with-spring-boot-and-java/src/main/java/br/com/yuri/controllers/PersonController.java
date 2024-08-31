package br.com.yuri.controllers;

import br.com.yuri.data.vo.PersonVO;
import br.com.yuri.services.person.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.yuri.util.MediaType.*;

@RestController
@RequestMapping("/api/person")
@Tag(name = "Person Endpoint", description = "Endpoints for Managing People")
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
    @Operation(summary = "Find all people recorded in the database", description = "Find all people recorded in the database", tags = {"Person Endpoint"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = APPLICATION_JSON)

            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
    })
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
