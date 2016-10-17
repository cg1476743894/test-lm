package coding;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class TaxCalculatorTest {

    private TaxCalculator calc = new TaxCalculator();

    @Test
    public void testCalcReceiptBaseTax() {
        Cart cart = new Cart();
        cart.add(new Item("1", Item.Type.OTHER, false, BigDecimal.ONE));
        cart.add(new Item("2", Item.Type.BOOK, false, BigDecimal.ONE));

        Receipt receipt = calc.receiptFor(cart);

        assertThat(receipt).isNotNull();
        assertThat(receipt.getItemsAmount()).isEqualByComparingTo(BigDecimal.valueOf(2.1));
        assertThat(receipt.getTaxesAmount()).isEqualByComparingTo(BigDecimal.valueOf(0.1));
    }

    @Test
    public void testCalcReceiptImportTax() {
        Cart cart = new Cart();
        cart.add(new Item("1", Item.Type.MED, true, BigDecimal.ONE));
        cart.add(new Item("2", Item.Type.BOOK, true, BigDecimal.ONE));

        Receipt receipt = calc.receiptFor(cart);

        assertThat(receipt).isNotNull();
        assertThat(receipt.getItemsAmount()).isEqualByComparingTo(BigDecimal.valueOf(2.1));
        assertThat(receipt.getTaxesAmount()).isEqualByComparingTo(BigDecimal.valueOf(0.1));
    }

    @Test
    public void testCalcReceiptMixed() {
        Cart cart = new Cart();
        cart.add(new Item("1", Item.Type.MED, true, BigDecimal.ONE));
        cart.add(new Item("2", Item.Type.BOOK, true, BigDecimal.ONE));
        cart.add(new Item("3", Item.Type.OTHER, false, BigDecimal.ONE));
        cart.add(new Item("4", Item.Type.FOOD, false, BigDecimal.ONE));
        cart.add(new Item("5", Item.Type.OTHER, true, BigDecimal.ONE));

        Receipt receipt = calc.receiptFor(cart);

        assertThat(receipt).isNotNull();
        assertThat(receipt.getItemsAmount()).isEqualByComparingTo(BigDecimal.valueOf(5.35));
        assertThat(receipt.getTaxesAmount()).isEqualByComparingTo(BigDecimal.valueOf(0.35));
    }

    @Test
    public void testRound() {
        assertThat(calc.round(BigDecimal.valueOf(1.0))).isEqualByComparingTo(BigDecimal.valueOf(1.0));
        assertThat(calc.round(BigDecimal.valueOf(1.01))).isEqualByComparingTo(BigDecimal.valueOf(1.05));
        assertThat(calc.round(BigDecimal.valueOf(1.06))).isEqualByComparingTo(BigDecimal.valueOf(1.1));
        assertThat(calc.round(BigDecimal.valueOf(1.1))).isEqualByComparingTo(BigDecimal.valueOf(1.1));
    }

    @Test
    public void testIsBaseTaxApplicable() {
        assertThat(calc.isBaseTaxApplicable(new Item("it", Item.Type.OTHER, false, BigDecimal.ONE))).isTrue();
        assertThat(calc.isBaseTaxApplicable(new Item("it", Item.Type.BOOK, true, BigDecimal.ONE))).isFalse();
        assertThat(calc.isBaseTaxApplicable(new Item("it", Item.Type.BOOK, false, BigDecimal.ONE))).isFalse();
    }

    @Test
    public void testIsImportTaxApplicable() {
        assertThat(calc.isImportTaxApplicable(new Item("it", Item.Type.OTHER, false, BigDecimal.ONE))).isFalse();
        assertThat(calc.isImportTaxApplicable(new Item("it", Item.Type.OTHER, true, BigDecimal.ONE))).isTrue();
        assertThat(calc.isImportTaxApplicable(new Item("it", Item.Type.BOOK, true, BigDecimal.ONE))).isTrue();
    }

}
