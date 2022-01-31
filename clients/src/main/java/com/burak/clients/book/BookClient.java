package com.burak.clients.book;

import com.burak.clients.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "book",
        url = "${clients.book.url}"
)
public interface BookClient {

    @GetMapping(path = "book/{bookId}")
    ResponseEntity<BookDTO> getBook(@PathVariable("bookId") Integer bookId);

    @PutMapping(path = "book/{bookId}")
    void updateBook(@PathVariable("bookId") Integer bookId,@RequestBody BookDTO bookDTO);

    @PostMapping
    ResponseEntity<Response> saveBook(@RequestBody BookDTO bookDTO);
}
