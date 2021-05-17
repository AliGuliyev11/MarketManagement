package ProductMethods;

import BaseClasses.ConsoleColors;
import Inputs.StringInput;
import MainMarket.Market;
import Models.Category;
import Models.Product;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import static Inputs.DoubleInput.getDoubleValue;
import static Inputs.IntegerInput.getCategory;
import static Inputs.IntegerInput.getIntegerValue;
import static Inputs.StringInput.addProductCode;
import static Inputs.StringInput.stringInput;

public class ProductCrud {
    public static void RemoveProduct(Market market) {
        String productCode=addProductCode(market);
        market.RemoveProduct(productCode);
        System.out.println(ConsoleColors.CYAN+"\nMehsul silindi!\n"+ConsoleColors.RESET);
    }

    public static void GetProductsByPriceRange(Market market) {
        double minPrice=getDoubleValue("Productun minimum qiymetini daxil edin:","Productun qiymetini duzgun qeyd edin");

        double maxPrice = 0;
        do {
            System.out.print(ConsoleColors.BLUE+"Productun maximum qiymetini daxil edin:"+ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);

            try {
                maxPrice = scanner.nextDouble();
                if (minPrice > maxPrice) {
                    System.out.println(ConsoleColors.RED+"Minimal qiymet maksimal qiymetden c0x ola bilmez"+ConsoleColors.RESET);
                }else{
                    break;
                }
            } catch (Exception e) {
                System.out.println(ConsoleColors.RED+"Productun qiymetini duzgun qeyd edin"+ConsoleColors.RESET);
            }


        } while (true);


        getProducts(market.getProductByPrice(minPrice, maxPrice), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Verilmis qiymetdeki mehsullarin siyahisi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }

    public static void getProducts(List<Product> productMap, String text) {
        System.out.println(ConsoleColors.PURPLE_BRIGHT+text+ConsoleColors.RESET);
        for (var item : productMap) {
            System.out.println(ConsoleColors.WHITE_BRIGHT+"\nMehsulun kodu:" + item.productCode + "\nMehusulun adi:" + item.name + "\nMehsulun qiymeti:" + item.price + "\nMehsulun kateqoriyasi:" + item.category.name() + "\nMehsulun sayi:" + item.amount+ConsoleColors.RESET);
        }
        System.out.println(ConsoleColors.PURPLE_BRIGHT+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+ConsoleColors.RESET);

    }

    public static void EditProduct(Market market) {
        String productCode=addProductCode(market);
        String productName = stringInput("Productun adini daxil edin:");
        double price=getDoubleValue("Productun qiymetini daxil edin:","Productun qiymetini duzgun qeyd edin");
        int category = getCategory();
        int amount=getIntegerValue("Productun sayini daxil edin:","Productun sayini duzgun qeyd edin");
        market.updateProduct(productCode, productName, price, Category.values()[category - 1], amount);
        System.out.println(ConsoleColors.CYAN+"\nMehsul ugurla yenilendi!\n"+ConsoleColors.RESET);
    }

    public static void GetProductsByCategory(Market market) {
        int category = getCategory();

        if (market.getProductByCategory(Category.values()[category - 1]).size() == 0) {
            System.out.println("Axtaris neticesi tapilmadi");
        } else {
            getProducts(market.getProductByCategory(Category.values()[category - 1]), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Terkibinde " + Category.values()[category - 1] + " olan mehsullarin siyahisi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }

    }

    public static void GetProductsByName(Market market) {
        String productName = stringInput("Productun axtarisi ucun bir sey yazin:");

        if (market.searchProductByName(productName).size() == 0) {
            System.out.println("Axtaris neticesi tapilmadi");
        } else {
            getProducts(market.searchProductByName(productName), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Terkibinde " + productName + " olan mehsullarin siyahisi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }

    }

    public static void GetProductList(Market market) {
        getProducts(market.getProductList().values().stream().collect(Collectors.toList()), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Mehsullarin siyahisi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }


    public static void AddNewProduct(Market market) {

        if (market.getProductList().size() == 0) {
            addProduct(market);
        } else {
            String yesOrNot;
            do {
                System.out.print(ConsoleColors.BLUE_UNDERLINED+"Yeni bir mehsul elave etmekk isteyirsizse(y),marketde olan mehsulu elave etmek isteyirsizse(n)?y/n:"+ConsoleColors.RESET);
                Scanner scanner = new Scanner(System.in);
                yesOrNot = scanner.nextLine();
            } while (!yesOrNot.toLowerCase().contentEquals("y") && !yesOrNot.toLowerCase().contentEquals("n"));

            switch (yesOrNot.toLowerCase()) {
                case "y":
                    addProduct(market);
                    break;
                case "n":
                    editProductAmount(market);
                    break;
                default:
                    System.out.println("Seciminizi duzgun daxil edin.(y(yes) ve ya n(no))\n");
                    break;

            }
        }


    }

    public static void editProductAmount(Market market) {
        String productCode=addProductCode(market);
        Product product = market.getProduct(productCode);
        int amount=getIntegerValue("Productun sayini daxil edin:","Productun sayini duzgun qeyd edin");;
        product.amount = product.amount + amount;
        System.out.println(ConsoleColors.CYAN + "\nMagazada olan mehsulun miqdari yenilendi!\n" + ConsoleColors.RESET);
    }

    public static void addProduct(Market market) {
        String productName=stringInput( "Productun adini daxil edin:");

        double price=getDoubleValue("Productun qiymetini daxil edin:","Productun qiymetini duzgun qeyd edin");

        int category = getCategory();
        int amount=getIntegerValue("Productun sayini daxil edin:","Productun sayini duzgun qeyd edin");

        market.addProduct(new Product(productName, price, Category.values()[category - 1], amount));
        System.out.println(ConsoleColors.CYAN + "\nProduct elave olundu!\n" + ConsoleColors.RESET);
    }


}
