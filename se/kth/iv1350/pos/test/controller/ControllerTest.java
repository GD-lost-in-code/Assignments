package se.kth.iv1350.pos.test.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.controller.Controller;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller ctrl;

    @BeforeEach
    void setUp() {
        ctrl = new Controller();
        ctrl.startSale();
    }

    @Test
    void registerInvalidItemReturnsError() {
        String msg = ctrl.registerItem("BAD_ID");
        assertTrue(msg.startsWith("Error:"), "Should return error message");
    }

    @Test
    void registerValidItemUpdatesTotal() {
        String out = ctrl.registerItem("abc123");
        assertTrue(out.contains("Price:"), "Should show price line");
        assertTrue(out.contains("Total"), "Should show total line");
    }

    @Test
    void endSaleReflectsRunningTotal() {
        ctrl.registerItem("abc123");
        String totalLine = ctrl.endSale();
        assertTrue(totalLine.contains("Total"));
    }

    @Test
    void payReturnsReceipt() {
        ctrl.registerItem("abc123");
        ctrl.endSale();
        String receipt = ctrl.pay(100.0);
        assertTrue(receipt.contains("Begin receipt"));
        assertTrue(receipt.contains("Change"));
    }
}
