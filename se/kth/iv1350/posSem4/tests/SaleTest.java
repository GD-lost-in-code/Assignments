package se.kth.iv1350.posSem4.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.posSem4.model.Sale;
import se.kth.iv1350.posSem4.integration.DiscountService;
import se.kth.iv1350.posSem4.integration.DTO.ItemDTO;

class SaleTest {
    private Sale sale;

    @BeforeEach
    void setUp() {
        sale = new Sale(new DiscountService());
    }

    @Test
    void testAddAndVat() {
        ItemDTO item = new ItemDTO("x","","",100.0,0.25);
        sale.addItem(item, 2);
        // net=200, vat=50, total=250
        assertEquals(250, sale.getRunningTotal(), 0.0001);
        assertEquals(50, sale.getTotalVat(), 0.0001);
    }

    @Test
    void testDiscountApplication() {
        sale.addItem(new ItemDTO("abc123","","",100.0,0), 3);
        sale.setCustomerID("vip123");
        sale.applyDiscounts();
        double expectedItemDiscount = 3 * 100 * 0.10;      // 10% of each abc123
        double expectedTotalDiscount = (300) * 0.20;       // 20% VIP
        assertEquals(expectedItemDiscount + expectedTotalDiscount,
                     sale.getDiscountApplied(), 0.0001);
    }

    @Test
    void testToDTO() {
        sale.addItem(new ItemDTO("a","","",50,0), 1);
        sale.setCustomerID("none");
        sale.applyDiscounts();
        var dto = sale.toDTO();
        assertEquals(sale.getRunningTotal(), dto.getTotalAfterDiscount(), 0.0001);
        assertEquals(sale.getDiscountApplied(), dto.getDiscountApplied(), 0.0001);
        assertEquals(sale.getTotalVat(),       dto.getTotalVat(), 0.0001);
    }
}
