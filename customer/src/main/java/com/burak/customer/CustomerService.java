package com.burak.customer;

import com.burak.clients.customer.CustomerDTO;
import com.burak.clients.response.Response;
import com.burak.clients.response.ResponseError;
import com.burak.clients.response.ResponseSuccess;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getCustomerList(){
        Optional<List<Customer>> customers = Optional.ofNullable(customerRepository.findAll());

        customers.stream().findAny().orElseThrow(() -> new EntityNotFoundException("Customer list not found"));

        return customers.get();
    }

    public CustomerDTO getCustomer(String customerEmail){
        Customer customer = customerRepository.findCustomerByEmail(customerEmail);


        return new CustomerDTO(customer.getFirstName(), customer.getLastName(), customer.getEmail());
    }

    public Response saveCustomer(CustomerDTO customerDTO) {
        if(!getCustomerList().stream().map(c -> c.getEmail()).collect(Collectors.toList()).contains(customerDTO.email())){

            Customer customer = Customer.builder()
                    .firstName(customerDTO.firstName())
                    .lastName(customerDTO.lastName())
                    .email(customerDTO.email())
                    .build();

            customerRepository.save(customer);

            return new ResponseSuccess(HttpStatus.OK);
        }else{
            return new ResponseError(HttpStatus.NOT_ACCEPTABLE,"Email address is already taken");
        }
    }
}
