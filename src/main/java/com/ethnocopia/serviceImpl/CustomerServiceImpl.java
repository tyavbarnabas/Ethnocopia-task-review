package com.ethnocopia.serviceImpl;

import com.ethnocopia.constants.GeneralResponseEnum;
import com.ethnocopia.dto.CustomerRequest;
import com.ethnocopia.dto.CustomerResponse;
import com.ethnocopia.exception.CustomerNotFoundException;
import com.ethnocopia.model.Customer;
import com.ethnocopia.repository.CustomerRepository;
import com.ethnocopia.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerResponse getCustomers() {

        List<Customer> allCustomers = customerRepository.findAll();
        log.info("all customer: {}",allCustomers);

        if(allCustomers.isEmpty()){

            throw new CustomerNotFoundException("customers not found");
        }

        return CustomerResponse.builder()
                .responseCode(GeneralResponseEnum.SUCCESS.getCode())
                .message(GeneralResponseEnum.SUCCESS.getMessage())
                .details(allCustomers)
                .build();

    }


    public CustomerResponse addCustomer(CustomerRequest request) {

       Optional<Customer> savedCustomer = customerRepository.findByEmail(request.email());
        log.info("Saved Customer {} :", savedCustomer);

        if (savedCustomer.isPresent()){

            throw new CustomerNotFoundException("Customer already exist! ");
        }

        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());

        Customer save = customerRepository.save(customer);
        log.info("saved customer to db: {}",save);

        return CustomerResponse.builder()
                .responseCode(GeneralResponseEnum.SUCCESS.getCode())
                .message(GeneralResponseEnum.SUCCESS.getMessage())
                .build();
    }


    public void deleteCustomer(Integer id) {
        Optional<Customer> savedCustomer = customerRepository.findById(id);
        log.info("Customer Retrieved from DB: {}",savedCustomer);

        if (savedCustomer.isEmpty()){

            throw new CustomerNotFoundException("customer with id " + id + " does not exist");
        }

        customerRepository.deleteById(id);
    }


    public CustomerResponse updateCustomer(Integer id,CustomerRequest request) {

        Optional<Customer> customer = customerRepository.findById(id);
        log.info("Customer Retrieved from DB: {}",customer);

        if (customer.isEmpty()){

            throw new CustomerNotFoundException("customer Not Found");
        }

        Customer updatedCustomer =  new Customer();
        updatedCustomer.setName(request.name());
        updatedCustomer.setAge(request.age());
        updatedCustomer.setEmail(request.email());

        Customer save = customerRepository.save(updatedCustomer);
        log.info("Saved Customer {} :", save);

        return CustomerResponse.builder()
                .responseCode(GeneralResponseEnum.UPDATED.getCode())
                .message(GeneralResponseEnum.UPDATED.getMessage())
                .build();
    }
}
