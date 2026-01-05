package com.project.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "giftcard_transactions")
public class GiftCardTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transaction_id;

    @Column(name = "serial_id")
    private String serialId;

    @Column(name = "transaction_amount")
    private double amount;

    @Column(name = "redeemed_by_user_id")
    private int userId;

    @Column(name = "redemption_date")
    private Date date;

    // Constructor to easily create new transactions
    public GiftCardTransaction(String serialId, double amount, int userId) {
        this.serialId = serialId;
        this.amount = amount;
        this.userId = userId;
        this.date = new Date(); // Current time
    }

    public GiftCardTransaction() {

    }
}