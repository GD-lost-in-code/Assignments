package se.kth.iv1350.posSem4.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import se.kth.iv1350.posSem4.integration.AccountingSystem;
import se.kth.iv1350.posSem4.integration.DiscountService;
import se.kth.iv1350.posSem4.integration.InventorySystem;
import se.kth.iv1350.posSem4.integration.Printer;
import se.kth.iv1350.posSem4.integration.DTO.ItemDTO;
import se.kth.iv1350.posSem4.integration.DTO.SaleDTO;
import se.kth.iv1350.posSem4.integration.exception.DatabaseFailureException;
import se.kth.iv1350.posSem4.integration.exception.ItemNotFoundException;
import se.kth.iv1350.posSem4.model.Sale;
import se.kth.iv1350.posSem4.util.FormatUtil;

/**
 * Coordinates the point-of-sale use case: scanning, ending sale, and payment.
 */
public class Controller {
    private final InventorySystem inventory = InventorySystem.getInstance();
    private final DiscountService discountService = new DiscountService();
    private final AccountingSystem accounting = new AccountingSystem();
    private final List<SaleObserver> observers = new CopyOnWriteArrayList<>();
    private Sale currentSale;

    /**
     * Starts a brand-new sale session.
     */
    public void startSale() {
        currentSale = new Sale(discountService);
        // No need to push observers into Sale; we'll notify manually
    }

    /**
     * Registers one unit of the item with the given ID.
     * @throws DatabaseFailureException if inventory lookup fails
     * @throws ItemNotFoundException if item ID is invalid
     */
    public ItemDTO registerItem(String id) 
            throws DatabaseFailureException, ItemNotFoundException {
        ItemDTO itemInfo = inventory.findItem(id);
        currentSale.addItem(itemInfo);
        return itemInfo;
    }

    /**
     * Sets the current customer ID for discount strategies.
     */
    public void setCustomerID(String customerId) {
        if (currentSale != null) {
            currentSale.setCustomerID(customerId);
        }
    }

    /**
     * Applies all configured discounts for the current sale.
     * @return total discount applied
     */
    public double requestDiscount(String customerId) {
        setCustomerID(customerId);
        currentSale.applyDiscounts();
        return currentSale.getDiscountApplied();
    }

    /**
     * Ends the sale and returns the total line including VAT formatted for display.
     */
    public String endSale() {
        return FormatUtil.totalLine(currentSale.getRunningTotal());
    }

    /**
     * Returns a SaleDTO representing the current sale.
     */
    public SaleDTO getSaleInfo() {
        return currentSale.toDTO();
    }

    /**
     * Processes payment, records the sale, notifies observers, and returns the receipt.
     */
    public String pay(double amountPaid) {
        SaleDTO saleDTO = currentSale.toDTO();
        accounting.recordSale(saleDTO);

        // Notify observers with final total after discounts
        double finalTotal = saleDTO.getTotalAfterDiscount();
        observers.forEach(obs -> obs.newSale(finalTotal));

        return Printer.format(currentSale, amountPaid);
    }

    /**
     * Registers a new SaleObserver (e.g., TotalRevenueView/FileOutput).
     */
    public void addObserver(SaleObserver observer) {
        observers.add(observer);
    }
}
