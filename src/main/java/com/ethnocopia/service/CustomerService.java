package com.ethnocopia.service;

import com.ethnocopia.dto.CustomerRequest;
import com.ethnocopia.dto.CustomerResponse;

public interface CustomerService {

    CustomerResponse getCustomers();
    CustomerResponse addCustomer(CustomerRequest request);
    void deleteCustomer(Integer id);
    CustomerResponse updateCustomer(Integer id,CustomerRequest request);
}
