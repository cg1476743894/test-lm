package coding;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A receipt is a formatted presentation of a collection of items.
 */
public class Receipt {

    private LinkedHashMap<String, BigDecimal> taxedItems = new LinkedHashMap<>();

    private BigDecimal itemsAmount = BigDecimal.ZERO;

    private BigDecimal taxesAmount = BigDecimal.ZERO;

    public void add(Integer quantity, String item, BigDecimal price) {
        this.taxedItems.put(String.format("%d x %s", quantity, item), price);
    }

    public LinkedHashMap<String, BigDecimal> getTaxedItems() {
        return taxedItems;
    }

    public BigDecimal getItemsAmount() {
        return itemsAmount;
    }

    public void setItemsAmount(BigDecimal itemsAmount) {
        this.itemsAmount = itemsAmount;
    }

    public BigDecimal getTaxesAmount() {
        return taxesAmount;
    }

    public void setTaxesAmount(BigDecimal taxesAmount) {
        this.taxesAmount = taxesAmount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Integer maxLen = taxedItems.keySet().stream().map(String::length).reduce(Math::max).get();

        for (String item : taxedItems.keySet()) {
            String spaces = Collections.nCopies(maxLen - item.length(), " ").stream().collect(Collectors.joining());
            sb.append(item)
                    .append(spaces).append(" : ")
                    .append(printFormat(taxedItems.get(item)))
                    .append(System.lineSeparator());
        }

        sb.append("Sales Taxes : ").append(printFormat(taxesAmount)).append(System.lineSeparator());
        sb.append("Total       : ").append(printFormat(itemsAmount)).append(System.lineSeparator());

        return sb.toString();
    }

    private static BigDecimal printFormat(BigDecimal amount) {
        return amount.setScale(2, BigDecimal.ROUND_UP);
    }

}
