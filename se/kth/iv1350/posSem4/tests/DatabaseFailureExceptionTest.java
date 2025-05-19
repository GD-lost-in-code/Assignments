package se.kth.iv1350.posSem4.tests;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseFailureExceptionTest {

    @Test
    void testExceptionMessageIsCorrect() {
        String itemId = "dbFail";
        DatabaseFailureException exception = new DatabaseFailureException(itemId);
        assertTrue(exception.getMessage().contains(itemId));
        assertEquals("Database failure accessing item: " + itemId, exception.getMessage());
    }

    @Test
    void testExceptionIsThrownAndCaught() {
        String itemId = "dbFail";
        try {
            throw new DatabaseFailureException(itemId);
        } catch (DatabaseFailureException e) {
            assertEquals("Database failure accessing item: " + itemId, e.getMessage());
        }
    }
}
