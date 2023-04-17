package com.example.sakilaapi.repository;

import com.example.sakilaapi.dto.PaymentDto;
import com.example.sakilaapi.dto.customer.CustomerSummaryDto;
import com.example.sakilaapi.mapper.customer.CustomerSummaryMapper;
import com.example.sakilaapi.mapper.PaymentMapper;
import com.example.sakilaapi.model.*;
import com.example.sakilaapi.util.Database;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class CustomerRepository extends BaseRepository<Customer, Integer> {
    public CustomerRepository() {
        super(Customer.class);
    }

    public Store getStoreByCustomerId(Integer customerId) {
        //already checked that the customer is existed in db.
        return Database.doInTransaction(entityManager -> entityManager.find(Customer.class,customerId).getStore());
    }

    public List<PaymentDto> getPaymentsByCustomerId(Integer categoryId) {
        return Database.doInTransaction(
                entityManager -> {
                    Category category = entityManager.find(Category.class,categoryId);
                    if (category == null)
                        throw new EntityNotFoundException("Can't get category with id: "+categoryId);
                    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
                    CriteriaQuery<Payment> query = builder.createQuery(Payment.class);
                    Root<Payment> paymentRoot = query.from(Payment.class);
                    Predicate customerIdPredicate = builder.equal(paymentRoot.get("customer").get("id"), categoryId);
                    query.where(customerIdPredicate);
                    List<Payment> payments = entityManager.createQuery(query).getResultList();
                    PaymentMapper paymentMapper = PaymentMapper.INSTANCE;
                    //PaymentMapperImpl paymentMapper1 = new PaymentMapperImpl();
                    return paymentMapper.toDto(payments);
                }
        );
    }

    public List<CustomerSummaryDto> getCustomerSummaries() {
        return Database.doInTransaction(
                entityManager -> {
                    List<Customer> customers = getAll(entityManager);
                    CustomerSummaryMapper customerSummaryMapper = CustomerSummaryMapper.INSTANCE;
                    return customerSummaryMapper.toDto(customers);
                }
        );
    }

    /*public List<Rental> getRentalsByCustomerId(Integer categoryId) {

    }*/
}