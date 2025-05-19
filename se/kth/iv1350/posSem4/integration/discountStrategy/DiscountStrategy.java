package se.kth.iv1350.posSem4.integration.discountStrategy;

import se.kth.iv1350.posSem4.model.*;

public interface DiscountStrategy {
    double getDiscount(Sale sale);
}