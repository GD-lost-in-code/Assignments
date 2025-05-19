package se.kth.iv1350.posSem4.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.posSem4.integration.DiscountService;
import se.kth.iv1350.posSem4.integration.DTO.ItemDTO;
import se.kth.iv1350.posSem4.model.Sale;

class DiscountServiceTest {
    private DiscountService discounts;
    private Sale sale;

    @BeforeEach
    void setUp() {
        discounts = new DiscountService();
        sale = new Sale(discounts);
        // add 3 units of abc123 (10% off strategy)
        sale.addItem(new ItemDTO("abc123","","",100,0), 3);
        // add another item to push total over 500
        sale.addItem(new ItemDTO("def456","","",200,0), 2);
        sale.setCustomerID("vip123");
    }

    @Test
    void testCombinedDiscount() {
        double discount = discounts.calculateDiscount(sale);
        // item: 3*100*0.10 = 30
        // total: (3*100 + 2*200) = 700 → 700*0.15 = 105
        // customer: 700*0.20 = 140
        assertEquals(30 + 105 + 140, discount, 0.0001);
    }
}
