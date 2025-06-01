package se.kth.iv1350.pos.test.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.DTO.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller ctrl;

    @BeforeEach
    void setUp() {
        ctrl = new Controller();
        ctrl.startSale();
    }

    @Test
    void registerInvalidItemReturnsNull() {
        ItemDTO result = ctrl.registerItem("BAD_ID");
        assertNull(result, "Should return null for invalid item");
    }

    @Test
    void registerValidItemReturnsItemDTO() {
        ItemDTO result = ctrl.registerItem("abc123");
        assertNotNull(result, "Valid item ID should return an ItemDTO");
        assertEquals("abc123", result.getId(), "Item ID should match");
    }

    @Test
    void endSaleReflectsRunningTotal() {
        ctrl.registerItem("abc123");
        String totalLine = ctrl.endSale();
        assertTrue(totalLine.contains("Total"), "Should include total in end sale");
    }

    @Test
    void payReturnsReceipt() {
        ctrl.registerItem("abc123");
        ctrl.endSale();
        String receipt = ctrl.pay(100.0);
        assertTrue(receipt.contains("Begin receipt"), "Receipt should start correctly");
        assertTrue(receipt.contains("Change"), "Receipt should include change line");
    }
}
