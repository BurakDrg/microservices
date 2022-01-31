package com.burak.order;

import com.burak.clients.book.BookClient;
import com.burak.clients.book.BookDTO;
import com.burak.clients.order.OrderDTO;
import com.burak.clients.response.Response;
import com.burak.clients.response.ResponseError;
import com.burak.clients.response.ResponseSuccess;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookClient bookClient;

    public Response saveOrder(OrderDTO order) {

        BookDTO book = bookClient.getBook(order.book().id()).getBody();

        // Check our book storage
        if (book.amount() >= order.book().amount() && order.book().amount()>0){
            orderRepository.save(
                    Order.builder()
                            .customerId(order.customerId())
                            .bookId(order.book().id())
                            .amount(order.book().amount())
                            .purchased(order.book().amount()*book.price())
                            .startDate(LocalDateTime.now())
                            .endDate(LocalDateTime.now().plusWeeks(1))
                            .build()
            );

            // Update Book repository with new amount
            bookClient.updateBook(
                    book.id(),
                    new BookDTO(
                            book.id(),
                            book.name(),
                            (book.amount() - order.book().amount()),
                            book.price())
            );

            return new ResponseSuccess(HttpStatus.OK);
        }else{
            return new ResponseError(HttpStatus.NOT_ACCEPTABLE,"Not Enough Amount");
        }

    }

    public List<Order> getOrders() {
        return orderRepository.getOrderByDateInterval();
    }

    public Order getOrder(Integer orderId) {
        return orderRepository.getById(orderId);
    }

    public List<OrderDTO> getOrderByCustomer(Integer customerId) {
        return orderRepository.findOrderByCustomerId(customerId)
                    .stream()
                    .map(value -> new OrderDTO(
                            value.getCustomerId(),
                            new BookDTO(bookClient.getBook(value.getBookId()).getBody().id(),
                                    bookClient.getBook(value.getBookId()).getBody().name(),
                                    value.getAmount(),
                                    bookClient.getBook(value.getBookId()).getBody().price()) ,
                            value.getPurchased(),
                            value.getStartDate()))
                    .collect(Collectors.toList());
    }
}
