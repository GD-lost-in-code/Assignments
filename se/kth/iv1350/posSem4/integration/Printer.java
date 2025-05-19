package se.kth.iv1350.posSem4.integration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public static String format(Sale sale, double amountPaid) {
        StringBuilder sb = new StringBuilder();
        sb.append("----- Begin receipt -----\n");
        sb.append("Time of Sale: ")
          .append(LocalDateTime.now().format(TIMESTAMP_FORMAT))
          .append("\n\n");

        sale.getItems().forEach((item, qty) -> {
            double lineNet = item.getPrice() * qty;
            sb.append(String.format("%s %d x %.2f = %.2f SEK\n",
                    item.getName(), qty, item.getPrice(), lineNet));
        });

        double total = sale.getRunningTotal();
        sb.append("\nTotal: ").append(FormatUtil.formatMoney(total)).append("\n");
        sb.append("VAT:   ").append(FormatUtil.formatMoney(sale.getTotalVat())).append("\n");
        sb.append("Cash:  ").append(FormatUtil.formatMoney(amountPaid)).append("\n");
        sb.append("Change:").append(FormatUtil.formatMoney(amountPaid - total)).append("\n");
        sb.append("------ End receipt ------");
        return sb.toString();
    }
}
