package br.com.yuri.services.book;

import br.com.yuri.controllers.BookController;
import br.com.yuri.data.vo.BookVO;
import br.com.yuri.mapper.Mapper;
import br.com.yuri.models.Book;
import br.com.yuri.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

    private final Logger logger = Logger.getLogger(BookService.class.getName());

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookVO> findAll() {
        logger.info("Finding all books");
        List<Book> books = bookRepository.findAll();
        var booksVO = Mapper.parseListObjects(books, BookVO.class);
        booksVO.forEach(b -> b.add(linkTo(methodOn(BookController.class).all()).withSelfRel()));
        return booksVO;
    }


}
