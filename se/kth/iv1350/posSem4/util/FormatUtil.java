package se.kth.iv1350.posSem4.util;

/**
 * Utility methods for formatting monetary amounts and total lines.
 */
public class FormatUtil {
    /**
     * Formats a monetary amount with two decimals and “SEK”.
     *
     * @param amount the value to format
     * @return a string like “29.90 SEK”
     */
    public static String formatMoney(double amount) {
        return String.format("%.2f SEK", amount);
    }

    /**
     * Builds the “Total (incl VAT): X SEK” line for display.
     *
     * @param totalInclVat the total amount including VAT
     * @return a string like “Total (incl VAT): 59.80 SEK”
     */
    public static String totalLine(double totalInclVat) {
        return "Total (incl VAT): " + formatMoney(totalInclVat);
    }
}
