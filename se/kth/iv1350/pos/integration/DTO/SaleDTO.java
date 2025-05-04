package se.kth.iv1350.pos.integration.DTO;
import java.util.List;


public class SaleDTO {
    private final List<ItemDTO> items;
    private final double totalBeforeDiscount;
    private final double discountApplied;
    private final double totalAfterDiscount;
    private final double totalVat;
    
    /**
     * @param items                  Item details list
     * @param totalBeforeDiscount    Running total before discount
     * @param discountApplied        Total discount applied
     * @param totalAfterDiscount     Final total after discount
     * @param totalVat               The total VAT amount
     */

    public SaleDTO(List<ItemDTO> items, 
                    double totalBeforeDiscount, 
                    double discountApplied, 
                    double totalAfterDiscount, 
                    double totalVat) {

        this.items = items;
        this.totalBeforeDiscount = totalBeforeDiscount;
        this.discountApplied = discountApplied;
        this.totalAfterDiscount = totalAfterDiscount;
        this.totalVat = totalVat;
    }

    /** @return the item identifier. */
    public List<ItemDTO> getItems() { return items; }

    /** @return the item identifier. */
    public double getTotalBeforeDiscount() { return totalBeforeDiscount; }

    /** @return the item description. */
    public double getTotalAfterDiscount() { return totalAfterDiscount; }

    /** @return the item’s net price. */
    public double getDiscountApplied() { return discountApplied; }

    /** @return the item’s VAT rate. */
    public double getTotalVat() { return totalVat; }
}
