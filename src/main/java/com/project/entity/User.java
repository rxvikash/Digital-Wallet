package com.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "total_balance")
    private double totalBalance;

    // We treat these as simple strings for now as per your design
    @Column(name = "redeemed_gift_cards")
    private String redeemedGiftCards;

    // Getters and Setters
    public int getUserId() { return userId; }
    public String getUserName() { return userName; }
    public double getTotalBalance() { return totalBalance; }
    public String getRedeemedGiftCards() { return redeemedGiftCards; }

    public void setTotalBalance(double balance) { this.totalBalance = balance; }
    public void setRedeemedGiftCards(String cards) { this.redeemedGiftCards = cards; }
}