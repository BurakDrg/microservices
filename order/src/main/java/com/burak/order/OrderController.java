package com.burak.order;

import com.burak.clients.order.OrderDTO;
import com.burak.clients.response.Response;
import com.burak.clients.response.ResponseError;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
@AllArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        log.info("Get order list");
        List<Order> orders = orderService.getOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping(path = "{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable("orderId") Integer orderId) {
        log.info("Get order... {}", orderId);
        Order order = orderService.getOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping(path = "customer/{customerId}")
    public ResponseEntity<List<OrderDTO>> getOrderByCustomer(@PathVariable("customerId") Integer customerId) {
        log.info("Get orders by customer... {}", customerId);
        List<OrderDTO> orders = orderService.getOrderByCustomer(customerId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> saveOrder(@RequestBody OrderDTO order) {
        log.info("New order... {}", order);
        Response response = orderService.saveOrder(order);

        return new ResponseEntity<>(response,response.getStatus());
    }
}
