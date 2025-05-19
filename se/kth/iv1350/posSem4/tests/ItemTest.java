package se.kth.iv1350.posSem4.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.posSem4.model.Item;
import se.kth.iv1350.posSem4.integration.DTO.ItemDTO;

class ItemTest {
    @Test
    void testConstructorAndGetters() {
        ItemDTO dto = new ItemDTO("id1", "Name", "Desc", 123.45, 0.12);
        Item item = new Item(dto);

        assertEquals("id1",      item.getId());
        assertEquals("Name",     item.getName());
        assertEquals("Desc",     item.getDescription());
        assertEquals(123.45,     item.getPrice(),   0.0001);
        assertEquals(0.12,       item.getVatRate(), 0.0001);
    }

    @Test
    void testEqualsAndHashCode() {
        ItemDTO dto1 = new ItemDTO("idX", "A", "D", 1.0, 0.05);
        ItemDTO dto2 = new ItemDTO("idX", "A", "D", 1.0, 0.05);
        Item item1 = new Item(dto1);
        Item item2 = new Item(dto2);

        // Underlying DTOs are equal by ID
        assertEquals(item1, item2);
        assertEquals(item1.hashCode(), item2.hashCode());
    }
}
