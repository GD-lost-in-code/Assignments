package se.kth.iv1350.posSem4.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.posSem4.integration.exception.DatabaseFailureException;
import se.kth.iv1350.posSem4.integration.exception.ItemNotFoundException;
import se.kth.iv1350.posSem4.integration.InventorySystem;
import se.kth.iv1350.posSem4.integration.DTO.ItemDTO;

class InventorySysTest {
    private InventorySystem inventory;

    @BeforeEach
    void setUp() {
        inventory = InventorySystem.getInstance();
    }

    @Test
    void testFindValidItem() throws Exception {
        ItemDTO item = inventory.findItem("abc123");
        assertNotNull(item);
        assertEquals("abc123", item.getId());
        assertEquals(29.90, item.getPrice());
    }

    @Test
    void testItemNotFoundThrows() {
        assertThrows(ItemNotFoundException.class, () -> 
            inventory.findItem("nonexistent"));
    }

    @Test
    void testDatabaseFailureThrows() {
        assertThrows(DatabaseFailureException.class, () ->
            inventory.findItem("dbFail"));
    }
}
