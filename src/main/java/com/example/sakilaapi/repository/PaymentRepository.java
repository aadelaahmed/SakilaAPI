package com.example.sakilaapi.repository;

import com.example.sakilaapi.model.Payment;

public class PaymentRepository extends BaseRepository<Payment,Integer> {
    public PaymentRepository() {
        super(Payment.class);
    }
}
