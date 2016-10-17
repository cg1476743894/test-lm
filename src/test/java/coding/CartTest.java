package coding;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


public class CartTest {

    @Test
    public void testAdd() {
        Cart cart = new Cart();
        Item book = new Item("a book", Item.Type.BOOK, false, BigDecimal.ONE);
        assertThat(cart.get(book)).isNull();
        cart.add(book);
        assertThat(cart.get(book)).isEqualTo(1);
    }

}
