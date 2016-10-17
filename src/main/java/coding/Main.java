package coding;

import java.math.BigDecimal;

public class Main {

    private static final TaxCalculator calculator = new TaxCalculator();

    public static void main(String[] args) {

        {
            Cart cart = new Cart();

            cart.add(new Item("book", Item.Type.BOOK, false, BigDecimal.valueOf(12.49)));
            cart.add(new Item("music cd", Item.Type.OTHER, false, BigDecimal.valueOf(14.99)));
            cart.add(new Item("chocolate bar", Item.Type.FOOD, false, BigDecimal.valueOf(0.85)));

            System.out.println(calculator.receiptFor(cart));
        }

        {
            Cart cart = new Cart();

            cart.add(new Item("imported box of chocolates", Item.Type.FOOD, true, BigDecimal.valueOf(10.00)));
            cart.add(new Item("imported bottle of perfume", Item.Type.OTHER, true, BigDecimal.valueOf(47.5)));

            System.out.println(calculator.receiptFor(cart));
        }

        {
            Cart cart = new Cart();

            cart.add(new Item("imported bottle of perfume", Item.Type.OTHER, true, BigDecimal.valueOf(27.99)));
            cart.add(new Item("bottle of perfume", Item.Type.OTHER, false, BigDecimal.valueOf(18.99)));
            cart.add(new Item("packet of headache pills", Item.Type.MED, false, BigDecimal.valueOf(9.75)));
            cart.add(new Item("box of imported chocolates", Item.Type.FOOD, true, BigDecimal.valueOf(11.25)));

            System.out.println(calculator.receiptFor(cart));
        }
    }

}
