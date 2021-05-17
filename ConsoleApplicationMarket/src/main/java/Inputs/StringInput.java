package Inputs;

import BaseClasses.ConsoleColors;
import MainMarket.Market;
import Models.Product;
import Models.SalesItem;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StringInput {
    public static String stringInput(String text) {
        String productName;
        do {
            System.out.print(ConsoleColors.BLUE + text+ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);

            productName = scanner.nextLine();

        } while (productName.length() == 0);
        return productName;
    }

    public static String addProductCode(Market market) {
        String productCode;
        do {
            System.out.print(ConsoleColors.BLUE+"Productun kodunu elave edin:"+ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);

            productCode = scanner.nextLine();

            if (market.getProduct(productCode) == null) {
                System.out.println(ConsoleColors.RED+"Bele bir kodda mehsul tapilmadi"+ConsoleColors.RESET);
            }

        } while (productCode.length() == 0 || market.getProduct(productCode) == null);
        return productCode;
    }

    public static String getProductInCategory(List<Product> products, Market market) {
        String productInCategory;
        int i = 0;
        do {
            i = 1;
            for (var item : products.stream().filter(product -> product.amount != 0).collect(Collectors.toUnmodifiableList())) {
                System.out.println(i + "." + ConsoleColors.CYAN + "Mehusulun adi:" + item.name + ConsoleColors.RED_UNDERLINED + " Mehsulun qiymeti:" + item.price + ConsoleColors.RESET);
                i++;
            }
            System.out.print(ConsoleColors.BLUE+"Productun adini daxil edin:"+ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);
            productInCategory = scanner.nextLine();

            if (!market.productHaveOrNot(productInCategory)) {
                System.out.println(ConsoleColors.RED+"Marketde bu adda ya mehsul yoxdur ya da siz duzgun daxil elemirsiz."+ConsoleColors.RESET);
            }

        } while (productInCategory.length() == 0 || !market.productHaveOrNot(productInCategory));
        return productInCategory;
    }

    public static String getProductInCategory(Market market,List<SalesItem> salesItemList) {
        String productInCategory;
        int i = 0;
        do {
            i = 1;
            for (var item : salesItemList) {
                System.out.println(i + "." + ConsoleColors.CYAN + "Mehusulun adi:" + item.product.name + ConsoleColors.RED_UNDERLINED + " Mehsulun qiymeti:" + item.product.price + " Sayi:" + item.amount + ConsoleColors.RESET);
                i++;
            }
            System.out.print(ConsoleColors.BLUE+"Productun adini daxil edin:"+ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);
            productInCategory = scanner.nextLine();

            if (!market.salesItemHaveOrNot(salesItemList, productInCategory)) {
                System.out.println(ConsoleColors.RED+"Marketde bu adda ya mehsul yoxdur ya da siz duzgun daxil elemirsiz."+ConsoleColors.RESET);
            }

        } while (productInCategory.length() == 0 || !market.salesItemHaveOrNot(salesItemList, productInCategory));
        return productInCategory;
    }

    public static String getSalesCode(Market market) {
        String salesCode;
        do {
            System.out.print(ConsoleColors.BLUE+"Zehmet olmasa ceki teqdim edin:"+ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);

            salesCode = scanner.nextLine();

            if (market.getSalesByNo(salesCode) == null) {
                System.out.println(ConsoleColors.RED+"Satici:Ceki oxumur.\nMusteri:Bir daha nezerden kecirin!"+ConsoleColors.RESET);
            }

        } while (salesCode.length() == 0 || market.getSalesByNo(salesCode) == null);

        return salesCode;
    }
    public static String getUsername(Market market){
        String username;
        do {
            System.out.print(ConsoleColors.BLUE + "Istifadecinin nameni daxil edin:" + ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);

            username = scanner.nextLine();
            if (market.getUserByUsername(username) == null) {
                System.out.println(ConsoleColors.RED + "Bu adda istifadeci yoxdur." + ConsoleColors.RESET);
            }

        } while (username.length() == 0 || market.getUserByUsername(username) == null);
        return username;
    }
}
