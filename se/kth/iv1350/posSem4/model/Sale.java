package se.kth.iv1350.posSem4.model;

import se.kth.iv1350.pos.integration.DTO.ItemDTO;
import se.kth.iv1350.pos.integration.DTO.SaleDTO;

import java.util.LinkedHashMap;
import java.util.Map;

import se.kth.iv1350.pos.integration.DiscountService;

/**
 * Accumulates items and quantities during a sale.
 */
public class Sale {
    private final Map<ItemDTO,Integer> items = new LinkedHashMap<>();
    private double runningTotal = 0; // VAT inclussive
    private double itemDiscountTotal = 0;
    private double finalDiscountTotal = 0;
    private final DiscountService discountHandler;

    public Sale(DiscountService discountHandler) {
        this.discountHandler = discountHandler;
    }

    /**
     * Registers one unit of the given item.
     *
     * @param item the item to add
     */
    public void addItem(ItemDTO item) {
        addItem(item, 1);
    }

    /**
     * Registers a quantity of the given item; increases existing quantity if present.
     *
     * @param item     the item to add
     * @param quantity number of units to add
     */
    public void addItem(ItemDTO item, int quantity) {
        items.merge(item, quantity, Integer::sum);

        // Correct per‑batch VAT calculation (no double‑count):
        double net     = item.getPrice() * quantity;
        double vat     = net * item.getVatRate();
        double batch   = net + vat;

        runningTotal +=batch;

        // Apply item-based discount immediately
        double discount = discountHandler.applyItemDiscount(item, quantity);
        itemDiscountTotal += discount;
        runningTotal -= discount;
    }

    /**
     * @return the running total price including VAT for all registered items
     */
    public double getRunningTotal() {
        return runningTotal;
    }

    /**
     * @return the total VAT amount for all registered items
     */
    public double getTotalVat() {
        /* e.getKey() is the Item.
         * e.getValue() is the quantity sold
         * e.getKey().getPrice() is the item’s net unit price
         * e.getKey().getVatRate() is the VAT rate
         * net price * quantity * VAT rate
         */
        return items.entrySet().stream()
            .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue() * entry.getKey().getVatRate()).sum();
    }

    /**
     * @param customerID --> customer identification
     */
    public void applyFinalDiscounts(String customerId) {
        finalDiscountTotal = discountHandler.calculateFinalDiscount(items, runningTotal, customerId);
        runningTotal -= finalDiscountTotal;
    }

    /**
     * @return Applied discount
     */
    public double getDiscountApplied(){
        return finalDiscountTotal+itemDiscountTotal;
    }

    /**
     * @return Method returns the items and their quantity
     */
    public Map<ItemDTO, Integer> getItems() {
        return new LinkedHashMap<>(items);
    }

    /**
     * @return This method converts the internal Sale object into a SaleDTO
     */
    public SaleDTO toDTO() {
        return new SaleDTO(
            new LinkedHashMap<>(items), // Deep copy of items
            runningTotal + getDiscountApplied(), // Total before discount
            getDiscountApplied(),               // Total discount
            runningTotal,                       // Final price after discount
            getTotalVat()                       // Total VAT
        );
    }

}