package se.kth.iv1350.posSem4.view;

import se.kth.iv1350.posSem4.controller.SaleObserver;

/**
 * Observer that prints total revenue to the console.
 */
public class TotalRevenueView implements SaleObserver {
    private double totalRevenue = 0;

    @Override
    public void newSale(double saleTotal) {
        totalRevenue += saleTotal;
        System.out.println("[TotalRevenueView] Cumulative revenue: " + totalRevenue + " SEK");
    }
}