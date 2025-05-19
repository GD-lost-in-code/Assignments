package se.kth.iv1350.posSem4.controller;

import java.util.List;
import java.util.stream.Collectors;

import se.kth.iv1350.pos.integration.AccountingSystem;
import se.kth.iv1350.pos.integration.DiscountSystem;
import se.kth.iv1350.pos.integration.DiscountService;
import se.kth.iv1350.pos.integration.InventorySystem;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.integration.DTO.ItemDTO;
import se.kth.iv1350.pos.integration.DTO.SaleDTO;
import se.kth.iv1350.pos.model.Receipt;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.util.FormatUtil;

/**
 * Coordinates the point-of-sale use case: scanning, ending sale, and payment.
 */
public class Controller {
    private final InventorySystem inventory = new InventorySystem();
    private final DiscountSystem discountSystem = new DiscountSystem();
    private final DiscountService discountService = new DiscountService(discountSystem);
    private final AccountingSystem accounting = new AccountingSystem();
    private Sale currentSale;

    /**
     * Starts a brand-new sale session.
     */
    public void startSale() {
        currentSale = new Sale(discountService);
    }

    /**
     * Registers one unit of the item with the given ID.
     *
     * @param id the item identifier
     * @return the ItemDTO if found, otherwise null
     */
    public ItemDTO registerItem(String id) {
        ItemDTO itemInfo = inventory.findItem(id);
        if (itemInfo == null) {
            return null;
        }
        currentSale.addItem(itemInfo);
        return itemInfo;
    }

    /**
     * Applies discounts for a given customer.
     *
     * @param customerId the customer’s ID
     * @return the total discount amount applied in SEK
     */
    public double requestDiscount(String customerId) {
        currentSale.applyFinalDiscounts(customerId);
        return currentSale.getDiscountApplied();
    }

    /**
     * Returns a SaleDTO representing the current sale, for external systems.
     *
     * @return SaleDTO summarizing the sale
     */
    public SaleDTO getSaleInfo() {
        return currentSale.toDTO();
    }

    /**
     * Processes the payment, notifies external systems, and returns the receipt text.
     *
     * @param amountPaid the amount of cash provided by the customer
     * @return the formatted receipt string
     */
    public String pay(double amountPaid) {
        SaleDTO saleDTO = currentSale.toDTO();
        accounting.recordSale(saleDTO);
        return Printer.format(currentSale, amountPaid);
    }

    /**
     * Ends the sale and returns the total line including VAT formatted for display.
     *
     * @return formatted total line string
     */
    public String endSale() {
        return FormatUtil.totalLine(currentSale.getRunningTotal());
    }
}
