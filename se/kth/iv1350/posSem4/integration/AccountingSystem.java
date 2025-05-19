package se.kth.iv1350.posSem4.integration;


import se.kth.iv1350.pos.integration.DTO.SaleDTO;

/**
 * Simulates an external accounting system; no real persistence.
 */
public class AccountingSystem {
    private SaleDTO lastRecordedSale;
    /**
     * Records the completed sale for accounting purposes.
     *
     * @param saleDTO summary of a completed sale
     */
    public void recordSale(SaleDTO saleDTO) {
        this.lastRecordedSale = saleDTO;
    }

    /**
     * @return the last SaleDTO that was recorded, or null if none
     */
    public SaleDTO getLastRecordedSale() {
        return lastRecordedSale;
    }
}
