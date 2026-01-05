package com.project.service;

import com.project.entity.GiftCard;
import com.project.entity.GiftCardTransaction;
import com.project.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class GiftCardService {

    private SessionFactory factory;

    // Constructor: Sets up the connection
    public GiftCardService() {
        try {
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(GiftCard.class)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(GiftCardTransaction.class) // Make sure this matches your file name
                    .buildSessionFactory();
        } catch (Exception e) {
            System.out.println(" Database Connection Failed!");
            e.printStackTrace();
        }
    }

    // --- METHOD 1: REDEEM CARD (Fixes 'redeemCard' error) ---
    public String redeemCard(int userId, String code) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // 1. Find Card
            Query<GiftCard> q = session.createQuery("FROM GiftCard WHERE claimCode = :code", GiftCard.class);
            q.setParameter("code", code);
            GiftCard card = q.uniqueResult();

            if (card == null) return "Invalid Code.";
            if (card.isRedeemed()) return "Already Redeemed.";

            // 2. Get User
            User user = session.find(User.class, userId);

            // 3. Update Data
            card.setRedeemed(true);
            user.setTotalBalance(user.getTotalBalance() + card.getAmount());

            // Update Text List
            String currentList = user.getRedeemedGiftCards();
            String newList = (currentList == null || currentList.isEmpty()) ? card.getSerialNo() : currentList + "," + card.getSerialNo();
            user.setRedeemedGiftCards(newList);

            session.merge(card);
            session.merge(user);

            // 4. Create Transaction Record
            GiftCardTransaction transRecord = new GiftCardTransaction(card.getSerialNo(), card.getAmount(), userId);
            session.persist(transRecord);

            tx.commit();
            return "Success! ₹" + card.getAmount() + " added.";

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return "Error: " + e.getMessage();
        } finally {
            session.close();
        }
    }

    // --- METHOD 2: SHOW WALLET (Fixes 'showUserWallet' error) ---
    public void showUserWallet(int userId) {
        Session session = factory.openSession();
        try {
            User user = session.find(User.class, userId);
            if (user != null) {
                System.out.println("\n--- 👤 MY WALLET ---");
                System.out.println("User: " + user.getUserName());
                System.out.println("Balance: ₹" + user.getTotalBalance());
                System.out.println("Redeemed Cards: " + user.getRedeemedGiftCards());
            } else {
                System.out.println("User not found.");
            }
        } finally {
            session.close();
        }
    }

    // --- METHOD 3: ADMIN CHECK ---
    public void checkCardStatusAdmin(String input) {
        Session session = factory.openSession();
        try {
            Query<GiftCard> q = session.createQuery("FROM GiftCard WHERE serialNo = :val OR claimCode = :val", GiftCard.class);
            q.setParameter("val", input);
            GiftCard card = q.uniqueResult();
            if (card != null) {
                System.out.println("\n--- 🕵️ ADMIN CHECK ---");
                System.out.println("Serial: " + card.getSerialNo());
                System.out.println("Amount: ₹" + card.getAmount());
                System.out.println("Status: " + (card.isRedeemed() ? "USED" : "ACTIVE"));
            } else {
                System.out.println("Card not found.");
            }
        } finally {
            session.close();
        }
    }

    // --- METHOD 4: CLOSE (Fixes 'close' error) ---
    public void close() {
        if (factory != null) {
            factory.close();
        }
    }
}