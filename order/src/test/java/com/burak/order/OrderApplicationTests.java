package com.burak.order;

import com.burak.clients.book.BookClient;
import com.burak.clients.book.BookDTO;
import com.burak.clients.customer.CustomerDTO;
import com.burak.clients.order.OrderDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class OrderApplicationTests {

    @Autowired
    private BookClient bookClient;

    @Autowired
    private OrderService orderService;


    @Test
    void contextLoads() {
        BookDTO bookDTO = new BookDTO(1,"test", 5, 10.0);
        bookClient.saveBook(bookDTO);
        OrderDTO orderDTO = new OrderDTO(1,bookDTO,5.0, LocalDateTime.now());

        orderService.saveOrder(orderDTO);
        orderService.getOrder(1);
        orderService.getOrderByCustomer(1);
    }
}
