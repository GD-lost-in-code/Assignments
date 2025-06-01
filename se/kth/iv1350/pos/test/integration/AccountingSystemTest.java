package se.kth.iv1350.pos.test.integration;

import se.kth.iv1350.pos.integration.AccountingSystem;
import se.kth.iv1350.pos.integration.DTO.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;

/**
 * Unit tests for AccountingSystem.
 */
class AccountingSystemTest {

    @Test
    void recordSaleStoresTheSaleDTO() {
        AccountingSystem acct = new AccountingSystem();

        // Create a dummy SaleDTO with correct constructor
        LinkedHashMap<ItemDTO, Integer> dummyItems = new LinkedHashMap<>();
        SaleDTO dummySale = new SaleDTO(
            dummyItems,
            100.0,    // totalBeforeDiscount
            10.0,     // discountApplied
            90.0,     // totalAfterDiscount
            18.0      // totalVat
        );

        acct.recordSale(dummySale);

        assertSame(dummySale, acct.getLastRecordedSale(),
                   "recordSale should save the SaleDTO so it can be retrieved");
    }

    @Test
    void recordSaleStoresLastSale() {
        AccountingSystem acct = new AccountingSystem();

        SaleDTO dummySale = new SaleDTO(
            new LinkedHashMap<>(), 0.0, 0.0, 0.0, 0.0
        );

        acct.recordSale(dummySale);
        assertSame(dummySale, acct.getLastRecordedSale());
    }
}
