package com.project;
import com.project.service.GiftCardService;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        // 1. Start the Hibernate Service (Connects to Database)
        System.out.println("🔄 Initializing Database Connection...");
        GiftCardService service = new GiftCardService();
        Scanner scanner = new Scanner(System.in);

        // We assume "Vikash" (User ID 1) is logged in for this demo
        int currentUserId = 1;

        while (true) {
            System.out.println("\n==================================");
            System.out.println("  GIFT CARD APP (Hibernate)");
            System.out.println("==================================");
            System.out.println("1. Redeem Gift Card");
            System.out.println("2. Check My Wallet");
            System.out.println("3. [Admin] Check Card Status");
            System.out.println("4. Exit");
            System.out.print("Select Option: ");

            int choice = 0;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Fix for scanner bug (eats the enter key)
            } else {
                scanner.nextLine(); // Clear invalid input
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter 6-digit Claim Code: ");
                    String code = scanner.nextLine();
                    // Call the service to redeem
                    String result = service.redeemCard(currentUserId, code);
                    System.out.println("\n" + result);
                    break;

                case 2:
                    // Call service to show wallet
                    service.showUserWallet(currentUserId);
                    break;

                case 3:
                    System.out.print("Enter Serial No OR Claim Code: ");
                    String input = scanner.nextLine();
                    service.checkCardStatusAdmin(input);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    service.close(); // Close Hibernate connection
                    System.exit(0);
                    break;

                default:
                    System.out.println(" Invalid option. Try again.");
            }
        }
    }
}