package com.burak.clients.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "order",
        url = "${clients.order.url}"
)
public interface OrderClient {

    @GetMapping(path = "order/customer/{customerId}")
    ResponseEntity<List<OrderDTO>> getOrderByCustomer(@PathVariable("customerId") Integer customerId);
}
