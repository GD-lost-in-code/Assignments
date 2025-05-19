package se.kth.iv1350.posSem4.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.posSem4.integration.DTO.SaleDTO;

class AccountingSystemTest {
    @Test
    void testRecordAndRetrieveSale() {
        AccountingSystem acct = new AccountingSystem();
        SaleDTO dummy = new SaleDTO(null, 0, 0, 0, 0);
        assertNull(acct.getLastRecordedSale());
        acct.recordSale(dummy);
        assertSame(dummy, acct.getLastRecordedSale());
    }
}
