package com.example.sakilaapi.service.customer;

import com.example.sakilaapi.dto.rental.RentalDto;
import com.example.sakilaapi.dto.customer.CustomerDto;
import com.example.sakilaapi.dto.PaymentDto;
import com.example.sakilaapi.dto.customer.CustomerSummaryDto;
import com.example.sakilaapi.dto.rental.RentalSummaryDto;
import com.example.sakilaapi.mapper.customer.CustomerMapper;
import com.example.sakilaapi.mapper.PaymentMapper;
import com.example.sakilaapi.mapper.customer.CustomerSummaryMapper;
import com.example.sakilaapi.model.Customer;
import com.example.sakilaapi.repository.*;
import com.example.sakilaapi.service.BaseService;
import com.example.sakilaapi.util.Database;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class CustomerService extends BaseService<Customer, CustomerDto> {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;
    PaymentMapper paymentMapper;
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        super(customerRepository, customerMapper);
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.paymentMapper = PaymentMapper.INSTANCE;
    }

    @Override
    protected Class<Customer> getEntityClass() {
        return Customer.class;
    }

    @Override
    protected Class<CustomerDto> getDtoClass() {
        return CustomerDto.class;
    }
    public CustomerDto getCustomerById(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.getById(id);
        if (!optionalCustomer.isPresent())
            throw new EntityNotFoundException("Can't fetch customer with id: " + id);
        else {
            System.out.println("get the customer object successfully in customer service -> " + optionalCustomer.get());
            return customerMapper.toDto(optionalCustomer.get());
        }
    }
    public List<CustomerSummaryDto> getAllCustomers() {
        List<CustomerSummaryDto> summaries = customerRepository.getCustomerSummaries();
        return summaries;
    }
    public CustomerSummaryDto getCustomerSummariesById(Integer customerId) {
        return customerRepository.getCustomerSummariesById(customerId);
    }

    public List<RentalSummaryDto> getRentalsByCustomerId(Integer categoryId) {
        List<RentalSummaryDto> rentals = customerRepository.getRentalsByCustomerId(categoryId);
        return rentals;
    }


    public List<PaymentDto> getPaymentsByCustomerId(Integer categoryId) {
        List<PaymentDto> payments = customerRepository.getPaymentsByCustomerId(categoryId);
        System.out.println("start map the payment of category id to payment dtos");
        return payments;
    }



}

