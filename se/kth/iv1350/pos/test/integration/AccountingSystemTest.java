package se.kth.iv1350.pos.test.integration;

import se.kth.iv1350.pos.integration.AccountingSystem;
import se.kth.iv1350.pos.integration.DTO.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Unit tests for AccountingSystem.
 */
class AccountingSystemTest {
    @Test
    void recordSaleStoresTheSaleDTO() {
        AccountingSystem acct = new AccountingSystem();

        // Create a dummy SaleDTO with known values
        SaleDTO dummySale = new SaleDTO(
            List.of(),      // empty item list
            100.0,          // total before discount
            10.0,           // discount applied
            90.0,           // total after discount
            18.0            // total VAT
        );

        acct.recordSale(dummySale);

        // Assert that it was stored
        assertSame(dummySale, acct.getLastRecordedSale(),
                   "recordSale should save the SaleDTO so it can be retrieved");
    }
    @Test
    void recordSaleStoresLastSale() {
        AccountingSystem acct = new AccountingSystem();
        SaleDTO dummy = new SaleDTO(List.of(), 0, 0, 0, 0);
        acct.recordSale(dummy);
        assertSame(dummy, acct.getLastRecordedSale());
    }
}
