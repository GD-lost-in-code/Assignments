package se.kth.iv1350.posSem4.integration.discountStrategy;

import se.kth.iv1350.posSem4.model.*;

public class TotalCostDiscountStrategy implements DiscountStrategy{
    private final double threshold;
    private final double discountRate;

    public TotalCostDiscountStrategy(double threshold, double discountRate){
        this.threshold = threshold;
        this.discountRate = discountRate;
    }

    @Override
    public double getDiscount(Sale sale){
        if (sale.getRunningTotal() > threshold) {
            return sale.getRunningTotal() * discountRate;
        }
        return 0;
    }
}
