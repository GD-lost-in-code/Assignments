package se.kth.iv1350.posSem4.model;

import se.kth.iv1350.posSem4.integration.DiscountService;
import se.kth.iv1350.posSem4.integration.DTO.ItemDTO;
import se.kth.iv1350.posSem4.integration.DTO.SaleDTO;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Accumulates items and quantities during a sale.
 */
public class Sale {
    private final Map<ItemDTO,Integer> items = new LinkedHashMap<>();
    private double runningTotal = 0; // VAT inclusive
    private double itemDiscountTotal = 0;
    private double finalDiscountTotal = 0;
    private String customerID;
    private final DiscountService discountService;

    /**
     * Creates a new Sale with its DiscountService.
     * @param discountService is an object of the DiscountService class, allowing access to the multiple discounts
     */
    public Sale(DiscountService discountService) {
        this.discountService = discountService;
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

        double net = item.getPrice() * quantity;
        double vat = net * item.getVatRate();
        double batch = net + vat;
        runningTotal += batch;

        // Immediate item-based discount if any
        double itemDiscount = 0; 
        itemDiscountTotal += itemDiscount;
        runningTotal -= itemDiscount;
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

    public void addObserver(SaleObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (SaleObserver observer : observers) {
            observer.newSale(runningTotal); // or use finalTotal if you prefer
        }
    }

    public void applyDiscounts() {
        finalDiscountTotal = discountService.calculateDiscount(this);
        runningTotal -= finalDiscountTotal;
        notifyObservers();
    }

    /**
     * @return Applied discount
     */
    public double getDiscountApplied(){
        return finalDiscountTotal+itemDiscountTotal;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerID() {
        return customerID;
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