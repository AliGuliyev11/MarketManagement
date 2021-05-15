import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IMarketable {
    public static final List<Sales> sales=new ArrayList<>();
    public static final List<Product> product=new ArrayList<>();
    public static final List<SalesItem> salesItem=new ArrayList<>();

    Map<String, Sales> getSalesList();
    public void RemoveSales(String salesCode);
    Map<String,Product> getProductList();
    List<SalesItem> getSalesItemList();

    void addSales(Sales item);

    double getSalesItemPrice(List<SalesItem> item);

    List<Sales> getSalesInFromToDate(LocalDate from, LocalDate to);

    List<Sales> getSalesInOneDate(LocalDate date);

    List<Sales> getSalesByPrice(double min, double max);

    Sales getSalesByNo(String no);

    void RemoveProduct(String productCode);
    void addProduct(Product product);
    void updateProduct(String productCode,String name, double price, Category category, int amount);
    List<Product> getProductByCategory(Category category);
    List<Product> getProductByPrice(double min, double max);
    List<Product> searchProductByName(String name);

}
