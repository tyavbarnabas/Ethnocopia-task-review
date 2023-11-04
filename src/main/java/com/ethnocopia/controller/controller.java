package com.ethnocopia.controller;

import com.ethnocopia.dto.CustomerRequest;
import com.ethnocopia.dto.CustomerResponse;
import com.ethnocopia.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
public class controller {

    private final CustomerService customerService;

    @GetMapping("/all-customers")
    public CustomerResponse getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping("/add")
    public CustomerResponse addCustomer(@RequestBody CustomerRequest request) {
        return customerService.addCustomer(request);
    }

    @PutMapping("/update/{customerId}")
    public CustomerResponse updateCustomer(@PathVariable("customerId") Integer id,@RequestBody CustomerRequest request) {
        return customerService.updateCustomer(id,request);
    }


    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        customerService.deleteCustomer(id);
    }
}
