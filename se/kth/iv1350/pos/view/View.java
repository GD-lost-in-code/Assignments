package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.DTO.ItemDTO;

/**
 * Simulates the cashier’s UI by hard-coded calls to Controller
 * and prints all relevant information to System.out.
 */
public class View {
    public static void main(String[] args) {
        Controller ctrl = new Controller();

        // 1. Start a new sale
        System.out.println("Start Sale");
        ctrl.startSale();

       // 2. Scan first item
        String itemId1 = "abc123";
        System.out.printf("Add 1 item with item id %s :%n", itemId1);
        ItemDTO item1 = ctrl.registerItem(itemId1);
        printItemInfo(item1, ctrl);
        System.out.println();

        // 3. Scan the same item again
        System.out.printf("Add 1 item with item id %s :%n", itemId1);
        ItemDTO item2 = ctrl.registerItem(itemId1);
        printItemInfo(item2, ctrl);
        System.out.println();

        // 4. Scan a different item
        String itemId2 = "def456";
        System.out.printf("Add 1 item with item id %s :%n", itemId2);
        ItemDTO item3 = ctrl.registerItem(itemId2);
        printItemInfo(item3, ctrl);
        System.out.println();

        // 5. Apply discount for a customer
        String customerId = "CUST001";
        double discount = ctrl.requestDiscount(customerId);
        System.out.printf("Discount applied for customer %s: %.2f SEK%n%n",
                          customerId, discount);

        // 6. End sale (show total line)
        System.out.println("End sale :");
        String totalLine = ctrl.endSale();
        System.out.println(totalLine);
        System.out.println();

        // 7. Pay and print full receipt
        double amountPaid = 100.00;
        System.out.printf("Customer pays: %.2f SEK%n%n", amountPaid);
        String receipt = ctrl.pay(amountPaid);
        System.out.println(receipt);
        System.out.println();

        // 8. Show change explicitly
        double change = amountPaid - 
            Double.parseDouble(totalLine.replaceAll("[^0-9.]", ""));
        System.out.printf("Change to give the customer : %.2f SEK%n", change);
    }
}
