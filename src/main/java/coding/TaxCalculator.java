package coding;

import java.math.BigDecimal;
import java.util.Map;

public class TaxCalculator {

    private static final BigDecimal PERCENT_5 = BigDecimal.valueOf(5);
    private static final BigDecimal PERCENT_10 = BigDecimal.TEN;

    private static final BigDecimal ROUNDING_CENTS = BigDecimal.valueOf(0.05);
    private static final BigDecimal BIGDECIMAL_100 = BigDecimal.valueOf(100);

    /**
     * Calculate a receipt for a given cart.
     */
    public static Receipt receiptFor(Cart cart) {

        Receipt receipt = new Receipt();

        BigDecimal totalItemsAmount = BigDecimal.ZERO;
        BigDecimal totalTaxesAmount = BigDecimal.ZERO;

        for (Map.Entry<Item, Integer> cartItem : cart.entrySet()) {

            Item item = cartItem.getKey();
            Integer quantity = cartItem.getValue();

            BigDecimal itemPrice = item.getPrice().multiply(BigDecimal.valueOf(quantity));
            BigDecimal taxes = BigDecimal.ZERO;

            if (isBaseTaxApplicable(item)) {
                taxes = taxes.add(taxAmount(itemPrice, PERCENT_10));
            }

            if (isImportTaxApplicable(item)) {
                taxes = taxes.add(taxAmount(itemPrice, PERCENT_5));
            }

            itemPrice = itemPrice.add(taxes);
            totalItemsAmount = totalItemsAmount.add(itemPrice);
            totalTaxesAmount = totalTaxesAmount.add(taxes);

            receipt.add(quantity, item.getName(), itemPrice);
        }

        receipt.setItemsAmount(totalItemsAmount);
        receipt.setTaxesAmount(totalTaxesAmount);

        return receipt;
    }

    @VisibleForTesting
    static BigDecimal taxAmount(BigDecimal itemPrice, BigDecimal percentage) {
        return round(itemPrice.multiply(percentage).divide(BIGDECIMAL_100));
    }

    @VisibleForTesting
    static BigDecimal round(BigDecimal price) {
        return price.divide(ROUNDING_CENTS, 0, BigDecimal.ROUND_UP).multiply(ROUNDING_CENTS);
    }

    @VisibleForTesting
    static boolean isBaseTaxApplicable(Item item) {
        return item.getType() == Item.Type.OTHER;
    }

    @VisibleForTesting
    static boolean isImportTaxApplicable(Item item) {
        return item.isImported();
    }

}
