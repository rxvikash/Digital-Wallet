# Digital Wallet System (Java, Hibernate, MySQL)

A backend **Digital Wallet / Gift Card System** built using **Java, Hibernate ORM, and MySQL**.  
The application simulates functionality similar to **Amazon Gift Cards**, allowing users to redeem gift cards, manage wallet balance, and track transactions.

This project is designed for **learning, practice, and resume purposes**, focusing on clean architecture and backend fundamentals.

---

##  Features

- Redeem gift cards using secure claim codes  
- Wallet balance management per user  
- Prevents duplicate gift card redemption  
- Tracks all redemption transactions  
- Admin option to check gift card status  
- Clean layered architecture using Java packages  
- Hibernate ORM–based database persistence  

---

##  Project Architecture

This structure improves **readability, maintainability, and scalability**.

com.project
├── app
│ └── App.java
├── service
│ └── GiftCardService.java
└── entity
├── User.java
├── GiftCard.java
└── GiftCardTransaction.java



## 🛠 Tech Stack

- **Language:** Java  
- **ORM:** Hibernate  
- **Database:** MySQL  
- **Build Tool:** Maven  
- **Version Control:** Git & GitHub  

---

## 🗄 Database Design

Tables used in this project:

- `users`  
- `gift_cards`  
- `giftcard_transactions`  

The complete database schema is provided as a single SQL file.

---

##  How to Run

### 1. Clone the repository
```bash
git clone https://github.com/rxvikash/project-digitalwallet.git
2. Create the database
sql

CREATE DATABASE digital_wallet;
3. Import the schema
bash

mysql -u root -p digital_wallet < database/digital_wallet_schema.sql
4. Build the project
bash

mvn clean install

5. Run the application
Execute App.java
