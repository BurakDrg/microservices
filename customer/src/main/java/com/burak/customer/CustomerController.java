package com.burak.customer;

import com.burak.clients.customer.CustomerDTO;
import com.burak.clients.order.OrderClient;
import com.burak.clients.order.OrderDTO;
import com.burak.clients.response.Response;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final OrderClient orderClient;

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        log.info("Get customer list");
        return new ResponseEntity<>(customerService.getCustomerList(), HttpStatus.OK);
    }

    @GetMapping(path = "order/{customerId}")
    public ResponseEntity<List<OrderDTO>> getCustomerOrders(@PathVariable("customerId") Integer customerId) {
        log.info("Get customer order list");
        return orderClient.getOrderByCustomer(customerId);
    }

    @GetMapping(path = "{customerEmail}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("customerEmail") String customerEmail) {
        log.info("Get customer by email {}",customerEmail);
        return new ResponseEntity<>(customerService.getCustomer(customerEmail),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        log.info("Save new customer {}", customerDTO);
        Response response = customerService.saveCustomer(customerDTO);

        return new ResponseEntity<>(response,response.getStatus());
    }
}
