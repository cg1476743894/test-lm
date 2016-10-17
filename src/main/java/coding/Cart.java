package coding;

import java.util.LinkedHashMap;

/**
 * A cart is a collection of Items with an associated quantity.
 */
public class Cart extends LinkedHashMap<Item, Integer> {

    public void add(Item item) {
        Integer quantity = getOrDefault(item, 0);
        put(item, quantity + 1);
    }

}
