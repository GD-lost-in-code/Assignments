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
    void getKnownItemReturnsCorrectItem() {
        InventorySystem inv = new InventorySystem();
        ItemDTO dto = inv.findItem("abc123");

        assertNotNull(dto, "Item should be found in catalog");
        assertEquals("abc123", dto.getId());
        assertEquals("BigWheel Oatmeal", dto.getName());
        assertEquals("500g, whole grain oats, high fiber, gluten free", dto.getDescription());
        assertEquals(29.90, dto.getPrice(), 1e-6);
        assertEquals(0.06, dto.getVatRate(), 1e-6);
    }

    @Test
    void getSecondKnownItemReturnsCorrectData() {
        InventorySystem inv = new InventorySystem();
        ItemDTO dto = inv.findItem("def456");

        assertNotNull(dto, "Second item should be found");
        assertEquals("def456", dto.getId());
        assertEquals("YouGoGo Blueberry", dto.getName());
        assertEquals("240g, low sugar yoghurt, blueberry flavour", dto.getDescription());
        assertEquals(14.90, dto.getPrice(), 1e-6);
        assertEquals(0.06, dto.getVatRate(), 1e-6);
    }

    @Test
    void getUnknownItemReturnsNull() {
        InventorySystem inv = new InventorySystem();
        ItemDTO dto = inv.findItem("nonexistent");
        assertNull(dto, "Unknown item ID should return null");
    }
}
