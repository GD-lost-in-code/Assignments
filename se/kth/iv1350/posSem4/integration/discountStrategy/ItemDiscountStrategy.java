package se.kth.iv1350.posSem4.integration.discountStrategy;

import se.kth.iv1350.posSem4.model.*;

public class ItemDiscountStrategy implements DiscountStrategy {
    private final String discountItemId;
    private final double discountRate;

    public ItemDiscountStrategy(String discountItemId, double discountRate) {
        this.discountItemId = discountItemId;
        this.discountRate = discountRate;
    }

    @Override
    public double getDiscount(Sale sale) {
        return sale.getItems().keySet().stream()
        .filter(item -> item.getId().equals(discountItemId))
        .mapToDouble(item -> item.getPrice() * discountRate * sale.getItems().get(item))
        .sum();

    }
}