package com.burak.book;

import com.burak.clients.book.BookDTO;
import com.burak.clients.response.Response;
import com.burak.clients.response.ResponseError;
import com.burak.clients.response.ResponseSuccess;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Response saveBook(BookDTO bookDTO) {

        if(bookDTO.price() <= 0) new ResponseError(HttpStatus.NOT_ACCEPTABLE, "Price has to higher then 0");

        bookRepository.save(
                Book.builder()
                        .name(bookDTO.name())
                        .amount(bookDTO.amount())
                        .price(bookDTO.price())
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        return new ResponseSuccess(HttpStatus.OK);

    }

    public Response updateBook(Integer bookId, BookDTO bookDTO) {
        Book book = Optional.ofNullable(bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found"))).get();

        book.setName(bookDTO.name());
        book.setAmount(bookDTO.amount());
        book.setPrice(bookDTO.price());

        bookRepository.save(book);

        return new ResponseSuccess(HttpStatus.OK);
    }

    public BookDTO getBook(Integer bookId) {
        Optional<Book> book = bookRepository.findById(bookId);

        return new BookDTO(book.get().getId(),
                            book.get().getName(),
                            book.get().getAmount(),
                            book.get().getPrice());
    }
}
