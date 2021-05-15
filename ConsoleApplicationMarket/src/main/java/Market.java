import java.time.LocalDate;
import java.util.*;

public class Market implements IMarketable {

    public static Map<String, Sales> sales;
    public static Map<String, Product> products;
    public static List<SalesItem> salesItems;

    public Market() {
        sales = new HashMap<>();
        products = new HashMap<>();
        salesItems = new ArrayList<>();
    }

    @Override
    public double getSalesItemPrice(List<SalesItem> item) {
        double prices=item.stream().mapToDouble(value -> value.product.price*value.amount).sum();
        return prices;
    }

    @Override
    public void RemoveProduct(String productCode) {
        products.remove(productCode);
    }

    @Override
    public void RemoveSales(String salesCode) {
        sales.remove(salesCode);
    }

    @Override
    public Map<String, Sales> getSalesList() {
        return sales;
    }

    @Override
    public Map<String, Product> getProductList() {
        return products;
    }

    @Override
    public List<SalesItem> getSalesItemList() {
        return salesItems;
    }

    @Override
    public void addSales(Sales item) {
        sales.put(item.no,item);
    }

    @Override
    public List<Sales> getSalesInFromToDate(LocalDate from, LocalDate to) {
        List<Sales> salesArrayList = new ArrayList<>();

        for (Map.Entry<String, Sales> item : sales.entrySet()) {
            if ((item.getValue().date.isAfter(from) ||item.getValue().date.equals(from)) && (item.getValue().date.isBefore(to) || item.getValue().date.equals(to))) {
                salesArrayList.add(item.getValue());
            }
        }
        return salesArrayList;
    }

    @Override
    public List<Sales> getSalesInOneDate(LocalDate date) {
        List<Sales> salesArrayList = new ArrayList<>();

        for (Map.Entry<String, Sales> item : sales.entrySet()) {
            if (item.getValue().date.equals(date)) {
                salesArrayList.add(item.getValue());
            }
        }
        return salesArrayList;
    }

    @Override
    public List<Sales> getSalesByPrice(double min, double max) {
        List<Sales> salesArrayList = new ArrayList<>();

        for (Map.Entry<String, Sales> item : sales.entrySet()) {
            if (item.getValue().price >= min && item.getValue().price <= max) {
                salesArrayList.add(item.getValue());
            }
        }
        return salesArrayList;
    }

    @Override
    public Sales getSalesByNo(String no) {
        return sales.get(no);
    }

    @Override
    public void addProduct(Product product) {
        products.put(product.productCode, product);
    }


    @Override
    public void updateProduct(String productCode, String name, double price, Category category, int amount) {
        Product product = products.get(productCode);
        if (product != null) {
            product.name = name;
            product.price = price;
            product.category = category;
            product.amount = amount;
        }

    }

    public Product getProduct(String productCode) {
        Product product = products.get(productCode);
        return product;
    }


    @Override
    public List<Product> getProductByCategory(Category category) {
        List<Product> productArrayList = new ArrayList<>();
        for (Map.Entry<String, Product> item : products.entrySet()) {
            if (item.getValue().category == category) {
                productArrayList.add(item.getValue());
            }
        }
        return productArrayList;
    }

    @Override
    public List<Product> getProductByPrice(double min, double max) {
        List<Product> productArrayList = new ArrayList<>();
        for (Map.Entry<String, Product> item : products.entrySet()) {
            if (item.getValue().price >= min && item.getValue().price <= max) {
                productArrayList.add(item.getValue());
            }
        }
        return productArrayList;
    }

    @Override
    public List<Product> searchProductByName(String name) {
        List<Product> productArrayList = new ArrayList<>();
        for (Map.Entry<String, Product> item : products.entrySet()) {
            if (item.getValue().name.toUpperCase().contains(name.toUpperCase())) {
                productArrayList.add(item.getValue());
            }
        }
        return productArrayList;
    }

    public boolean productHaveOrNot(String name) {
        for (Map.Entry<String, Product> item : products.entrySet()) {
            if (item.getValue().name.toUpperCase().equals(name.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean salesItemHaveOrNot(List<SalesItem> items,String name) {
        for (var item : items) {
            if (item.product.name.toUpperCase().equals(name.toUpperCase())) {
                return true;
            }
        }
        return false;
    }


    public void decrementAmountOfTheProduct(String productCode, int amount) {
        Product product = products.get(productCode);
        product.amount = product.amount - amount;
    }
    public void incrementAmountOfTheProduct(String productCode, int amount) {
        Product product = products.get(productCode);
        product.amount = product.amount + amount;
    }
}
