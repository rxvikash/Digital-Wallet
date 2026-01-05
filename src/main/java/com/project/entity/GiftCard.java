package com.project.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gift_cards")
public class GiftCard {

    @Id
    @Column(name = "serial_no")
    private String serialNo;

    @Column(name = "claim_code", unique = true, nullable = false)
    private String claimCode;

    @Column(name = "total_amount", nullable = false)
    private double amount;

    @Column(name = "is_redeemed")
    private boolean isRedeemed;

    @Column(name = "purchase_user_id")
    private Integer purchaseUserId; // Integer allows nulls (if no one bought it yet)

    @Column(name = "purchase_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDate;

    // ============================
    // CONSTRUCTORS
    // ============================

    // Default constructor (Required by Hibernate)
    public GiftCard() {}

    // Constructor for creating a new card easily
    public GiftCard(String serialNo, String claimCode, double amount) {
        this.serialNo = serialNo;
        this.claimCode = claimCode;
        this.amount = amount;
        this.isRedeemed = false; // Default to false
    }

    // ============================
    // GETTERS AND SETTERS
    // ============================

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getClaimCode() {
        return claimCode;
    }

    public void setClaimCode(String claimCode) {
        this.claimCode = claimCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isRedeemed() {
        return isRedeemed;
    }

    public void setRedeemed(boolean redeemed) {
        isRedeemed = redeemed;
    }

    public Integer getPurchaseUserId() {
        return purchaseUserId;
    }

    public void setPurchaseUserId(Integer purchaseUserId) {
        this.purchaseUserId = purchaseUserId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}