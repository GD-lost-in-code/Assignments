package se.kth.iv1350.posSem4.integration.DTO;
import java.util.LinkedHashMap;
import java.util.Map;


public class SaleDTO {
    private final LinkedHashMap<ItemDTO,Integer> itemsWithQty;
    private final double totalBeforeDiscount;
    private final double discountApplied;
    private final double totalAfterDiscount;
    private final double totalVat;

    public SaleDTO(LinkedHashMap<ItemDTO,Integer> itemsWithQty,
                   double totalBeforeDiscount,
                   double discountApplied,
                   double totalAfterDiscount,
                   double totalVat) {
        this.itemsWithQty        = new LinkedHashMap<>(itemsWithQty);
        this.totalBeforeDiscount = totalBeforeDiscount;
        this.discountApplied     = discountApplied;
        this.totalAfterDiscount  = totalAfterDiscount;
        this.totalVat            = totalVat;
    }

    /** @return the itemDTO with quantity. */
     public Map<ItemDTO,Integer> getItemsWithQty() {
        return new LinkedHashMap<>(itemsWithQty);
    }

    /** @return the item identifier. */
    public double getTotalBeforeDiscount() { return totalBeforeDiscount; }

    /** @return the item description. */
    public double getTotalAfterDiscount() { return totalAfterDiscount; }

    /** @return the item’s net price. */
    public double getDiscountApplied() { return discountApplied; }

    /** @return the item’s VAT rate. */
    public double getTotalVat() { return totalVat; }
}
