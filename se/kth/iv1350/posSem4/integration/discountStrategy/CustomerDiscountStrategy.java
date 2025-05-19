package se.kth.iv1350.posSem4.integration.discountStrategy;

import se.kth.iv1350.posSem4.model.*;

public class CustomerDiscountStrategy implements DiscountStrategy{
    private final String eligibleCustomerId;
    private final double discountRate;

    public CustomerDiscountStrategy(String eligibleCustomerId, double discountRate) {
        this.eligibleCustomerId = eligibleCustomerId;
        this.discountRate = discountRate;
    }

    @Override
    public double getDiscount(Sale sale) {
        if (sale.getCustomerID() != null && sale.getCustomerID().equals(eligibleCustomerId)) {
            return sale.getRunningTotal() * discountRate;
        }
        return 0;
    }

}
