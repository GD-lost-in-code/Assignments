package se.kth.iv1350.pos.integration;
import se.kth.iv1350.pos.integration.DTO.ItemDTO;

public class DiscountSystem {
    /**
     * @param items --> items in sale
     * @return fixed sum disount
     */
    public double getItemDiscount(ItemDTO item, int quantity){
        if (item.getId().equals("apple") && quantity >= 3) {
            return 5.0; // SEK
        }
        return 0.0;
    }

    /**
     * @param total --> sale total before discount
     * @return percentage disount
     */
     public double getTotalCostDiscount(double total) {
        return total > 500 ? 0.15 : 0.0;
    }

    /**
     * @param customerId --> the customer's identifier
     * @return percentage disount
     */
    public double getCustomerDiscount(String customerId) {
        return customerId.equals("vip123") ? 0.10 : 0.0;
    }
}
