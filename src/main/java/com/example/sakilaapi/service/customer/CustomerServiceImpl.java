/*

package com.example.sakilaapi.service.customer;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final RentalRepository rentalRepository;
    private final PaymentRepository paymentRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, RentalRepository rentalRepository, PaymentRepository paymentRepository) {
        this.customerRepository = customerRepository;
        this.rentalRepository = rentalRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream().map(CustomerDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(int id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        return customer != null ? CustomerDto.fromEntity(customer) : null;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.save(Customer.fromDto(customerDto));
        return CustomerDto.fromEntity(customer);
    }

    @Override
    public CustomerDto updateCustomer(int id, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customer.updateFromDto(customerDto);
            customerRepository.save(customer);
            return CustomerDto.fromEntity(customer);
        }
        return null;
    }

    @Override
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<RentalDto> getRentalsByCustomerId(int id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        return customer != null ? rentalRepository.findAllByCustomer(customer).stream().map(RentalDto::fromEntity).collect(Collectors.toList()) : null;
    }

    @Override
    public List<PaymentDto> getPaymentsByCustomerId(int id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        return customer != null ? paymentRepository.findAllByCustomer(customer).stream().map(PaymentDto::fromEntity).collect(Collectors.toList()) : null;
    }
}

*/
