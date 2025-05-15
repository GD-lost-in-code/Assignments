package se.kth.iv1350.pos.controller;

import java.util.List;
import java.util.stream.Collectors;

import se.kth.iv1350.pos.integration.AccountingSystem;
import se.kth.iv1350.pos.integration.DiscountSystem;
import se.kth.iv1350.pos.integration.InventorySystem;
import se.kth.iv1350.pos.integration.DTO.ItemDTO;
import se.kth.iv1350.pos.integration.DTO.SaleDTO;
import se.kth.iv1350.pos.model.Receipt;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.Item;
import se.kth.iv1350.pos.util.FormatUtil;

/**
 * Coordinates the point-of-sale use case: scanning, ending sale, and payment.
 */
public class Controller {
    private final InventorySystem inventory = new InventorySystem();
    private final DiscountSystem discountSystem = new DiscountSystem();
    private final AccountingSystem accounting = new AccountingSystem();
    private Sale currentSale;
    /**
     * Starts a brand-new sale session.
     */
    public void startSale() {
        currentSale = new Sale();
    }

    /**
     * Registers one unit of the item with the given ID.
     *
     * @param id the item identifier
     * @return success string with running total, or an error message if not found
     */
    /*public String registerItem(String id) {
        ItemDTO itemInfo = inventory.findItem(id);
        if (itemInfo == null) {
            return "Error: invalid item identifier";
        }

        // Add item to sale
        Item item = new Item(itemInfo);
        currentSale.addItem(item);

        // Display per spec
        String desc = itemInfo.getDescription();
        String priceLine = FormatUtil.formatMoney(itemInfo.getPrice());
        String totalLine = FormatUtil.totalLine(currentSale.getRunningTotal());
        return String.format("%s\nPrice: %s\n%s", desc, priceLine, totalLine);
    }*/

    public ItemDTO registerItem(String id){
        ItemDTO itemInfo = inventory.findItem(id);
        if (itemInfo == null){
            return null;
        }
        Item item = new Item(itemInfo);
        currentSale.addItem(item);
        return itemInfo;
    }

    /**
     * Applies discounts from all sources and returns the discount applied.
     *
     * @param customerId the customer’s ID
     * @return discount amount in SEK
     */
    public double requestDiscount(String customerId) {
        return currentSale.applyDiscount(discountSystem,customerId);
    }

    /**
     * 5. Builds a SaleDTO summarizing the current sale.
     *
     * @return SaleDTO for external systems
     */
    public SaleDTO getSaleInfo() {
        List<ItemDTO> itemDTOs = currentSale.getItems().keySet().stream()
            .map(item -> new ItemDTO(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getVatRate()))
            .collect(Collectors.toList());

        return new SaleDTO(
            itemDTOs,
            currentSale.getRunningTotal(),
            currentSale.getDiscountApplied(),
            currentSale.getTotalAfterDiscount(),
            currentSale.getTotalVat()
        );
    }

    /**
     * Processes the cash payment, notifies external systems, and returns the receipt.
     *
     * @param amountPaid the cash amount provided by the customer
     * @return the full receipt text
     */
    public String pay(double amountPaid) {
        SaleDTO saleDTO = getSaleInfo();
        accounting.recordSale(saleDTO);
        return Receipt.format(currentSale, amountPaid);
    }

    public String printReceipt(Receipt receipt) {
        return Printer.format(receipt);
    }
    
    /*private static void printItemInfo(ItemDTO item, Controller ctrl) {
        if (item == null) {
            System.out.println("Error: invalid item identifier");
        } else {
            System.out.println(item.getDescription());
            System.out.println("Price: " + FormatUtil.formatMoney(item.getPrice()));
            System.out.println(FormatUtil.totalLine(ctrl.getSaleInfo().getRunningTotal()));
        }
    }*/

    /**
     * Ends the sale, returning the total-line for display.
     *
     * @return the “Total (incl VAT): X SEK” line
     */
    public String endSale() {
        return FormatUtil.totalLine(currentSale.getRunningTotal());
    }

}
