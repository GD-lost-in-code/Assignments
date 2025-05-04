// src/test/java/se/kth/iv1350/pos/test/integration/InventorySystemTest.java
package se.kth.iv1350.pos.test.integration;

import se.kth.iv1350.pos.integration.InventorySystem;
import se.kth.iv1350.pos.integration.DTO.ItemDTO;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for InventorySystem.
 */
class InventorySystemTest {
    @Test
    void getKnownItemReturnsNonNull() {
        InventorySystem inv = new InventorySystem();
        ItemDTO dto = inv.findItem("abc123");
        assertNotNull(dto);
    }

    @Test
    void getKnownItemHasCorrectNameAndDescription() {
        InventorySystem inv = new InventorySystem();
        ItemDTO dto = inv.findItem("abc123");
        assertEquals("abc123", dto.getId());           // id field
        assertEquals("BigWeel Oatmeal", dto.getName());
        assertTrue(dto.getDescription().contains("whole grain"), "Description contains ‘whole grain’");
    }

    @Test
    void getKnownItemHasCorrectPriceAndVat() {
        InventorySystem inv = new InventorySystem();
        ItemDTO dto = inv.findItem("abc123");
        assertEquals(29.90, dto.getPrice(), 1e-6);
        assertEquals(0.06, dto.getVatRate(), 1e-6);
    }

    @Test
    void fetchUnknownItemReturnsNull() {
        InventorySystem inv = new InventorySystem();
        assertNull(inv.findItem("nonexistent"));
    }
}
