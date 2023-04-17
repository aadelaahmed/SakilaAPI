package com.example.sakilaapi.service.customer;

import com.example.sakilaapi.dto.customer.CustomerDto;
import com.example.sakilaapi.dto.PaymentDto;
import com.example.sakilaapi.dto.RentalDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();
    CustomerDto getCustomerById(Integer id);
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(Integer id, CustomerDto customerDto);
    void deleteCustomer(Integer id);
    List<RentalDto> getAllRentalsForCustomer(Integer customerId);
    List<PaymentDto> getAllPaymentsForCustomer(Integer customerId);
}

