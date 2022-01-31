package com.burak.book;


import com.burak.clients.book.BookDTO;
import com.burak.clients.customer.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookApplicationTests {

    @Autowired
    private BookService bookService;

    @Test
    void contextLoads() {
        BookDTO bookDTO = new BookDTO(1,"test", 5, 10.0);
        bookService.saveBook(bookDTO);
        bookService.getBook(1);
        bookService.updateBook(1,bookDTO);
    }
}
