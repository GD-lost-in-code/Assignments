package se.kth.iv1350.pos.test.integration;

import se.kth.iv1350.pos.integration.DiscountSystem;
import se.kth.iv1350.pos.integration.DiscountService;
import se.kth.iv1350.pos.integration.DTO.ItemDTO;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for DiscountSystem and DiscountService.
 */
class DiscountSystemTest {

    @Test
    void itemDiscountAppliedForEligibleItem() {
        DiscountService service = new DiscountService(new DiscountSystem());
        ItemDTO apple = new ItemDTO("apple", "Apple", "Juicy red apple", 10.0, 0.12);
        double discount = service.applyItemDiscount(apple, 3); // Should trigger discount
        assertEquals(5.0, discount, 1e-6);
    }

    @Test
    void itemDiscountNotAppliedForLowQuantity() {
        DiscountService service = new DiscountService(new DiscountSystem());
        ItemDTO apple = new ItemDTO("apple", "Apple", "Juicy red apple", 10.0, 0.12);
        double discount = service.applyItemDiscount(apple, 2); // Below threshold
        assertEquals(0.0, discount, 1e-6);
    }

    @Test
    void customerAndTotalDiscountAppliedTogether() {
        DiscountService service = new DiscountService(new DiscountSystem());

        Map<ItemDTO, Integer> dummyItems = new HashMap<>();
        double currentTotal = 600.0;
        String customerId = "vip123"; // 10% discount

        double expected = (0.10 + 0.15) * currentTotal; // 25% of 600 = 150
        double actual = service.calculateFinalDiscount(dummyItems, currentTotal, customerId);

        assertEquals(expected, actual, 1e-6);
    }

    @Test
    void noDiscountsForRegularCustomerAndLowTotal() {
        DiscountService service = new DiscountService(new DiscountSystem());

        Map<ItemDTO, Integer> dummyItems = new HashMap<>();
        double currentTotal = 300.0;
        String customerId = "guest001";

        double expected = 0.0;
        double actual = service.calculateFinalDiscount(dummyItems, currentTotal, customerId);

        assertEquals(expected, actual, 1e-6);
    }
}
