package se.kth.iv1350.posSem4.integration;

import java.util.Map;

import se.kth.iv1350.pos.integration.DTO.ItemDTO;

public class DiscountService {
    private final DiscountSystem discountDB;

    public DiscountService(DiscountSystem discountDB) {
        this.discountDB = discountDB;
    }

    public double applyItemDiscount(ItemDTO item, int quantity) {
        return discountDB.getItemDiscount(item, quantity); // Returns fixed SEK value
    }

    public double calculateFinalDiscount(Map<ItemDTO, Integer> items, double currentTotal, String customerId) {
        double discount = 0;
        discount += discountDB.getCustomerDiscount(customerId) * currentTotal;
        discount += discountDB.getTotalCostDiscount(currentTotal) * currentTotal;
        return discount;
    }
}
