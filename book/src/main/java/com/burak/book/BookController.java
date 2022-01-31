package com.burak.book;

import com.burak.clients.book.BookDTO;
import com.burak.clients.response.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
@AllArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    @GetMapping(path = "{bookId}")
    public ResponseEntity<BookDTO> getBook(@PathVariable("bookId") Integer bookId) {
        log.info("Get book request for id {}", bookId);

        return new ResponseEntity<>(bookService.getBook(bookId), HttpStatus.OK);
    }

    @PutMapping(path = "{bookId}")
    public ResponseEntity<Response> updateBook(@PathVariable("bookId") Integer bookId, @RequestBody BookDTO bookDTO) {
        log.info("book update {}", bookDTO);
        Response response = bookService.updateBook(bookId,bookDTO);

        return new ResponseEntity<>(response,response.getStatus());
    }

    @PostMapping
    public ResponseEntity<Response> saveBook(@RequestBody BookDTO bookDTO) {
        log.info("new book registration {}", bookDTO);
        Response response = bookService.saveBook(bookDTO);

        return new ResponseEntity<>(response,response.getStatus());
    }
}
