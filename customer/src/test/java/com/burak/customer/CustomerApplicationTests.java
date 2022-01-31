package com.burak.customer;

import com.burak.clients.customer.CustomerDTO;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerApplicationTests {

    @Autowired
    private CustomerService customerService;

    @Test
    void contextLoads() {
        CustomerDTO customerDTO = new CustomerDTO("test", "test", "test@email.com");
        customerService.saveCustomer(customerDTO);
        customerService.getCustomer(customerDTO.email());
        customerService.getCustomerList();
    }
}
