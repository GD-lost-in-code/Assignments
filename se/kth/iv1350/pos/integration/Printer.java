package se.kth.iv1350.pos.integration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import se.kth.iv1350.pos.model.Receipt;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.util.FormatUtil;

public class Printer {
    private static final DateTimeFormatter TIMESTAMP_FORMAT =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Builds and returns the full receipt, including header, line items,
     * totals, VAT, payment and change.
     *
     * @param sale       the completed sale
     * @param amountPaid the cash amount provided by the customer
     * @return the formatted receipt text
     */
    public static String format(Receipt receipt) {
        StringBuilder sb = new StringBuilder();
        sb.append("----- Begin receipt -----\n");
        sb.append("Time of Sale: ")
        .append(LocalDateTime.now().format(TIMESTAMP_FORMAT))
        .append("\n\n");

        receipt.getItems().forEach((item, qty) -> {
            double lineNet = item.getPrice() * qty;
            sb.append(String.format("%s %d x %.2f = %.2f SEK\n",
                    item.getName(), qty, item.getPrice(), lineNet));
        });

        sb.append("\nTotal: ").append(FormatUtil.formatMoney(receipt.getTotalPrice())).append("\n");
        sb.append("VAT:   ").append(FormatUtil.formatMoney(receipt.getTotalVAT())).append("\n");
        sb.append("Cash:  ").append(FormatUtil.formatMoney(receipt.getAmountPaid())).append("\n");
        sb.append("Change:").append(FormatUtil.formatMoney(receipt.getChange())).append("\n");
        sb.append("------ End receipt ------");
        return sb.toString();
    }
}
