package br.com.yuri.controllers;

import br.com.yuri.data.vo.BookVO;
import br.com.yuri.services.book.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookVO>> all() {
        return ResponseEntity.ok(service.findAll());
    }

}
