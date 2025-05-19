package se.kth.iv1350.posSem4.tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemNotFoundExceptionTest {

    @Test
    void testExceptionMessageIsCorrect() {
        String itemId = "xyz999";
        ItemNotFoundException exception = new ItemNotFoundException(itemId);
        assertTrue(exception.getMessage().contains(itemId));
        assertEquals("Item identifier not found: " + itemId, exception.getMessage());
    }

    @Test
    void testExceptionIsThrownAndCaught() {
        String itemId = "xyz999";
        try {
            throw new ItemNotFoundException(itemId);
        } catch (ItemNotFoundException e) {
            assertEquals("Item identifier not found: " + itemId, e.getMessage());
        }
    }
}
