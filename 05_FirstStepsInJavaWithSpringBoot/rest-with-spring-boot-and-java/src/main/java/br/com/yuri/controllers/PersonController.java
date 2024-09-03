package br.com.yuri.controllers;

import br.com.yuri.data.vo.PersonVO;
import br.com.yuri.services.person.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Operation(summary = "Find by id person recorded in the database", description = "Find by id person recorded in the database", tags = {"Person Endpoint"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = PersonVO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<PersonVO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(
            value = "/all",
            produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
            consumes = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    @Operation(summary = "Find all people recorded in the database", description = "Find all people recorded in the database", tags = {"Person Endpoint"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = APPLICATION_JSON, array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<List<PersonVO>> all() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping(
            produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
            consumes = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    @Operation(summary = "Adds a new person", description = "Adds a new Person by passing in a JSON, XML or YML representation of the person!", tags = {"Person Endpoint"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = PersonVO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<PersonVO> create(@RequestBody PersonVO person) {
        return ResponseEntity.ok(service.create(person));
    }

    @PutMapping(
            value = "/{id}",
            produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
            consumes = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    @Operation(summary = "Update a person by id", description = "Update a Person by id passing in a JSON, XML or YML representation of the person!", tags = {"Person Endpoint"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = PersonVO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<PersonVO> update(@RequestBody PersonVO person, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(person, id));
    }

    @DeleteMapping(
            value = "/{id}")
    @Operation(summary = "Delete a person by id", description = "Delete a Person by id!", tags = {"Person Endpoint"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
