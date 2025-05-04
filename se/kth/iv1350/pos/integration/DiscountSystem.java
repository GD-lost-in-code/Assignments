package se.kth.iv1350.pos.integration;
import se.kth.iv1350.pos.model.Item;
import java.util.List;

public class DiscountSystem {
    /**
     * @param items --> items in sale
     * @return fixed sum disount
     */
    public double getSumDiscount(List<Item> items){
        return 0.0;
    }

    /**
     * @param total --> sale total before discount
     * @return percentage disount
     */
    public double getTotalPercentDiscount(double total){
        return 0.0;
    }

    /**
     * @param customerId --> the customer's identifier
     * @return percentage disount
     */
    public double getPercentDiscountByCustomer(String customerId) {
        return 0.0;
    }
}
