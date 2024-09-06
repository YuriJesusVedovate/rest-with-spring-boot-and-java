package br.com.yuri.services.book;

import br.com.yuri.controllers.BookController;
import br.com.yuri.data.vo.BookVO;
import br.com.yuri.exceptions.ResourceNotFoundException;
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
        booksVO.forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getId())).withSelfRel()));
        return booksVO;
    }

    public BookVO findById(Long id) {
        logger.info("Finding book by id: " + id);

        Book entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id: " + id));

        BookVO result = Mapper.parseObject(entity, BookVO.class);
        result.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return result;
    }

    public BookVO create(BookVO request) {
        logger.info("Creating book: " + request.getTitle());
        Book book = Mapper.parseObject(request, Book.class);
        book = bookRepository.save(book);
        BookVO result = Mapper.parseObject(book, BookVO.class);
        result.add(linkTo(methodOn(BookController.class).findById(book.getId())).withSelfRel());
        return result;
    }

    public BookVO update(BookVO request, Long id) {
        logger.info("Updating book: " + request.getTitle());
        Book book = Mapper.parseObject(request, Book.class);
        book.setId(id);
        book = bookRepository.save(book);
        BookVO result = Mapper.parseObject(book, BookVO.class);
        result.add(linkTo(methodOn(BookController.class).findById(book.getId())).withSelfRel());
        return result;
    }

    public void delete(Long id) {
        logger.info("Deleting book by id: " + id);
        bookRepository.deleteById(id);
    }


}
