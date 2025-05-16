package se.kth.iv1350.pos.model;

import java.util.LinkedHashMap;
import java.util.Map;
import se.kth.iv1350.pos.integration.DTO.ItemDTO;
import se.kth.iv1350.pos.integration.DTO.SaleDTO;

/**
 * Represents a completed sale and its associated receipt data.
 */
public class Receipt {
    private final Map<ItemDTO, Integer> items;
    private final double totalPrice;
    private final double totalVAT;
    private final double amountPaid;
    private final double change;

    public Receipt(SaleDTO sale, double amountPaid) {
        this.items = sale.getItemsWithQty();
        this.totalPrice = sale.getTotalAfterDiscount();
        this.totalVAT = sale.getTotalVat();
        this.amountPaid = amountPaid;
        this.change = amountPaid - totalPrice;
    }

    public Map<ItemDTO, Integer> getItems() {
        return new LinkedHashMap<>(items);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getTotalVAT() {
        return totalVAT;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getChange() {
        return change;
    }
}
