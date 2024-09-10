package br.com.yuri.controllers;

import br.com.yuri.data.vo.BookVO;
import br.com.yuri.models.Book;
import br.com.yuri.services.book.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.yuri.util.MediaType.*;
import static br.com.yuri.util.MediaType.APPLICATION_YML;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping(
            value = "/{id}",
            produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    @Operation(summary = "Find by id book recorded in the database", description = "Find by id book recorded in the database", tags = {"Book Endpoint"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<BookVO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(
            value = "/all",
            produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    @Operation(summary = "Find all book recorded in the database", description = "Find all book recorded in the database", tags = {"Book Endpoint"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = APPLICATION_JSON, array = @ArraySchema(schema = @Schema(implementation = BookVO.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<List<BookVO>> all() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping(
            produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    @Operation(summary = "Adds a new book", description = "Adds a new book by passing in a JSON, XML or YML representation of the book!", tags = {"Book Endpoint"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<BookVO> create(@RequestBody BookVO request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping(
            value = "/{id}",
            produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    @Operation(summary = "Update a book by id", description = "Update a book by id passing in a JSON, XML or YML representation of the book!", tags = {"Book Endpoint"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<BookVO> update(@RequestBody BookVO request, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(request, id));
    }

    @DeleteMapping(
            value = "/{id}")
    @Operation(summary = "Delete a book by id", description = "Delete a book by id!", tags = {"Book Endpoint"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Book deleted");
    }

}
