package com.burak.clients.customer;

import com.burak.clients.order.OrderDTO;
import com.burak.clients.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "customer",
        url = "${clients.customer.url}"
)
public interface CustomerClient {

    @GetMapping(path = "customer/order/{customerId}")
    ResponseEntity<List<OrderDTO>> getCustomerOrders(@PathVariable("customerId") Integer customerId);

    @GetMapping(path = "customer/{customerEmail}")
    ResponseEntity<CustomerDTO> getCustomer(@PathVariable("customerEmail") String customerEmail);

    @PostMapping(path = "customer")
    ResponseEntity<Response> saveCustomer(@RequestBody CustomerDTO customerDTO);
}
