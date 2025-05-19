package se.kth.iv1350.posSem4.view;

import se.kth.iv1350.posSem4.controller.Controller;
import se.kth.iv1350.posSem4.integration.DTO.ItemDTO;
import se.kth.iv1350.posSem4.integration.exception.DatabaseFailureException;
import se.kth.iv1350.posSem4.integration.exception.ItemNotFoundException;
import se.kth.iv1350.posSem4.util.FormatUtil;

public class View {
    private final Controller ctrl;
    private final LocalFileLogger logger = new LocalFileLogger();

    public View(Controller ctrl) {
        this.ctrl = ctrl;
        ctrl.addObserver(new TotalRevenueView());
        ctrl.addObserver(new TotalRevenueFileOutput());
    }

    public void Execution() {
        // 1. Start a new sale
        System.out.println("Start Sale");
        ctrl.startSale();

        // Utility to scan an item and handle exceptions
        java.util.function.Consumer<String> scanItem = id -> {
            System.out.printf("Add 1 item with item id %s :%n", id);
            try {
                ItemDTO item = ctrl.registerItem(id);
                printItemInfo(item);
            } catch (ItemNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
                logger.logException("ItemNotFoundException during item registration", e);
            } catch (DatabaseFailureException e) {
                System.out.println("Error: " + e.getMessage());
                logger.logException("DatabaseFailureException during item registration", e);
            }
            System.out.println();
        };

        // 2. Scan first item
        scanItem.accept("abc123");

        // 3. Scan the same item again
        scanItem.accept("abc123");

        // 4. Scan a different item
        scanItem.accept("def456");

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

    private void printItemInfo(ItemDTO item) {
        System.out.println(item.getDescription());
        System.out.println("Price: " + FormatUtil.formatMoney(item.getPrice()));
        System.out.println(FormatUtil.totalLine(ctrl.getSaleInfo().getTotalAfterDiscount()));
    }
}
