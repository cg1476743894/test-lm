package coding;

import java.math.BigDecimal;

/**
 * An item is a simple value-object with a name and a price,
 * plus some fields to ease tax rules application.
 */
public class Item {

    enum Type {
        BOOK, FOOD, MED, OTHER;
    }

    private String name;

    private Type type;

    private boolean imported;

    private BigDecimal price;

    public Item(String name, Type type, boolean imported, BigDecimal price) {
        this.name = name;
        this.type = type;
        this.imported = imported;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (imported != item.imported) return false;
        if (!name.equals(item.name)) return false;
        if (type != item.type) return false;
        return price.compareTo(item.price) == 0;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (imported ? 1 : 0);
        result = 31 * result + price.hashCode();
        return result;
    }

}
