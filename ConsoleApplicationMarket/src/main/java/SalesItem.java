public class SalesItem {
    private static int id;
    public final int no;
    public final Product product;
    public int amount;
    static {
        id=1000;
    }

    public SalesItem(Product product, int amount) {
        id++;
        this.no = id;
        this.product = product;
        this.amount = amount;
    }
}
