// src/test/java/se/kth/iv1350/pos/test/integration/DiscountSystemTest.java
package se.kth.iv1350.pos.test.integration;

import se.kth.iv1350.pos.integration.DiscountSystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Unit tests for DiscountSystem.
 */
class DiscountSystemTest {
    @Test
    void defaultSumDiscountIsZero() {
        DiscountSystem ds = new DiscountSystem();
        assertEquals(0.0, ds.getSumDiscount(List.of()), 1e-6);
    }

    @Test
    void defaultPercentDiscountByTotalIsZero() {
        DiscountSystem ds = new DiscountSystem();
        assertEquals(0.0, ds.getTotalPercentDiscount(100.0), 1e-6);
    }

    @Test
    void defaultPercentDiscountByCustomerIsZero() {
        DiscountSystem ds = new DiscountSystem();
        assertEquals(0.0, ds.getPercentDiscountByCustomer("ANY"), 1e-6);
    }
}
