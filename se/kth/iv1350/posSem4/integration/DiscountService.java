// ======== integration/DiscountService.java ========
package se.kth.iv1350.posSem4.integration;

import se.kth.iv1350.posSem4.integration.discountStrategy.DiscountStrategy;
import se.kth.iv1350.posSem4.integration.discountStrategy.ItemDiscountStrategy;
import se.kth.iv1350.posSem4.integration.discountStrategy.TotalCostDiscountStrategy;
import se.kth.iv1350.posSem4.model.Sale;
import se.kth.iv1350.posSem4.integration.discountStrategy.CustomerDiscountStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Aggregates all configured DiscountStrategy implementations
 * and computes the total discount for a sale.
 */
public class DiscountService {
    private final List<DiscountStrategy> strategies = new ArrayList<>();

    public DiscountService() {
        // Configure your strategies here:
        strategies.add(new ItemDiscountStrategy("item123", 0.10));       // 10% off item "item123"
        strategies.add(new TotalCostDiscountStrategy(500.0, 0.15));     // 15% off total > 500 SEK
        strategies.add(new CustomerDiscountStrategy("cust001", 0.20));  // 20% off VIP customer
    }

    /**
     * Calculates the sum of all discounts returned by each strategy.
     *
     * @param sale the sale to discount
     * @return total discount amount
     */
    public double calculateDiscount(Sale sale) {
        return strategies.stream()
                .mapToDouble(strategy -> strategy.getDiscount(sale))
                .sum();
    }
}
