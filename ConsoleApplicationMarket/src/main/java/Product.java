import java.util.Locale;

public class Product {
    public String name;
    public double price;
    public Category category;
    public int amount;
    private static int id;
    public final String productCode;

    static {
        id=1000;
    }

    public Product(String name, double price, Category category, int amount) {
        id++;
        this.name = name;
        this.price = price;
        this.category = category;
        this.amount = amount;
        this.productCode = name.substring(0,1).toUpperCase()+ category.name().substring(0,1).toUpperCase()+id;

    }
}
