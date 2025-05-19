package se.kth.iv1350.posSem4.view;

import se.kth.iv1350.posSem4.controller.SaleObserver;

/**
 * Observer that logs total revenue to a file.
 */
public class TotalRevenueFileOutput implements SaleObserver {
    private double totalRevenue = 0;
    private final LocalFileLogger logger = new LocalFileLogger();

    @Override
    public void newSale(double saleTotal) {
        totalRevenue += saleTotal;
        logger.log("[TotalRevenueFileOutput] Cumulative revenue: " + totalRevenue + " SEK");
    }
}
