package se.kth.iv1350.pos.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import se.kth.iv1350.pos.integration.DiscountSystem;

/**
 * Accumulates items and quantities during a sale.
 */
public class Sale {
    private final DiscountSystem discountSystem = new DiscountSystem();
    private final Map<Item,Integer> items = new LinkedHashMap<>();
    private double discountApplied;
    private double lastDiscountApplied;
    private Sale currentSale;

    /**
     * Registers one unit of the given item.
     *
     * @param item the item to add
     */
    public void addItem(Item item) {
        addItem(item, 1);
    }

    /**
     * Registers a quantity of the given item; increases existing quantity if present.
     *
     * @param item     the item to add
     * @param quantity number of units to add
     */
    public void addItem(Item item, int quantity) {
        int current = items.getOrDefault(item, 0);
        items.put(item, current + quantity);
    }

    /**
     * @return the running total price including VAT for all registered items
     */
    public double getRunningTotal() {
        /* e.getKey() is the Item.
         * e.getValue() is the quantity sold
         * e.getKey().getPrice() is the item’s net unit price
         * e.getKey().getVatRate() is the VAT rate
         * (1 + entry.getKey().getVatRate()) --> price + VAT
         * net price * quantity * (price + VAT)
         */
        return items.entrySet().stream()
            .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue() * (1 + entry.getKey().getVatRate())).sum();
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
     * @return an insertion-ordered map of items to quantities
     */
    public Map<Item,Integer> getItems() {
        return new LinkedHashMap<>(items);
    }
    /**
     * @param amount --> discount amount
     */
    public double applyDiscount(DiscountSystem discountSystem,String customerId){
        List<Item> items = List.copyOf(currentSale.getItems().keySet());
        double before      = currentSale.getRunningTotal();
        double sumDisc     = discountSystem.getSumDiscount(items);
        double rateTotal   = discountSystem.getTotalPercentDiscount(before);
        double rateCust    = discountSystem.getPercentDiscountByCustomer(customerId);
        double combinedRate = rateTotal + rateCust;
        double percentDisc  = before * combinedRate;

        lastDiscountApplied = sumDisc + percentDisc;
        return discountApplied;
    }

    /**
     * @return Applied discount
     */
    public double getDiscountApplied(){
        return discountApplied;
    }

    /**
     * @return The total price of sale after discount
     */
    public double getTotalAfterDiscount(){
        return getRunningTotal() - getDiscountApplied();
    }
}