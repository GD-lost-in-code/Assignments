// src/test/java/se/kth/iv1350/pos/test/model/SaleTest.java
package se.kth.iv1350.pos.test.model;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.integration.DTO.ItemDTO;
import se.kth.iv1350.pos.model.Item;
import se.kth.iv1350.pos.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Sale.
 */
class SaleTest {
    @Test
    void emptySaleHasZeroRunningTotal() {
        Sale sale = new Sale();
        assertEquals(0.0, sale.getRunningTotal(), 1e-6);
    }

    @Test
    void emptySaleHasZeroVat() {
        Sale sale = new Sale();
        assertEquals(0.0, sale.getTotalVat(), 1e-6);
    }

    @Test
    void addItemIncreasesQuantity() {
        ItemDTO dto = new ItemDTO("test00", "Test item", "desc", 10.0, 0.25);
        Item item = new Item(dto);
        Sale sale = new Sale();
        sale.addItem(item);
        assertEquals(1, sale.getItems().get(item).intValue());
    }

    @Test
    void addItemWithQuantity() {
        ItemDTO dto = new ItemDTO("test01", "Test item", "desc", 10.0, 0.25);
        Item item = new Item(dto);
        Sale sale = new Sale();
        sale.addItem(item, 3);
        assertEquals(3, sale.getItems().get(item).intValue());
    }

    @Test
    void totalVatReflectsVatOnly() {
        ItemDTO dto = new ItemDTO("test03", "Test item", "desc", 10.0, 0.5);
        Item item = new Item(dto);
        Sale sale = new Sale();
        sale.addItem(item, 2);
        // 10*2 * 0.5% =  10
        assertEquals(10, sale.getTotalVat(), 1e-6);
    }

    @Test
    void absoluteDiscountIsAppliedCorrectly() {
        ItemDTO dto = new ItemDTO("test04", "Test item", "desc", 100.0, 0);
        Item item = new Item(dto);
        Sale sale = new Sale();
        sale.addItem(item);
        // apply a fixed discount of 10 SEK
        sale.applyDiscount(10.0);
        // after discount: 100 - 10 = 90
        assertEquals(90.0, sale.getTotalAfterDiscount(), 1e-6);
    }
}
