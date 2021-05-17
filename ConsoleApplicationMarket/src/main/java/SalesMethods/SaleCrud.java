package SalesMethods;

import BaseClasses.ConsoleColors;
import MainMarket.Market;
import Models.Category;
import Models.Product;
import Models.Sales;
import Models.SalesItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import static Inputs.DoubleInput.getDoubleValue;
import static Inputs.IntegerInput.getAmountInSale;
import static Inputs.IntegerInput.getCategory;
import static Inputs.LocalDateInput.getLocalDate;
import static Inputs.StringInput.getProductInCategory;
import static Inputs.StringInput.getSalesCode;
import static Inputs.voidList.getSalesByCode;
import static Inputs.voidList.getSalesList;
import static java.time.temporal.ChronoUnit.DAYS;

public class SaleCrud {
    public static void RemoveSalesItemFromSale(Market market) {

        String salesCode = getSalesCode(market);
        if (market.getSalesByNo(salesCode).price != market.getSalesItemPrice(market.getSalesByNo(salesCode).salesItem)) {
            System.out.println(ConsoleColors.RED+"Satici:Bu cek artiq istifade olunub.\nMusteri:=)!"+ConsoleColors.RESET);
        } else {
            Sales sales = market.getSalesByNo(salesCode);
            if (sales.date.until(LocalDate.now(), DAYS) > 15) {
                System.out.println(ConsoleColors.RED+"Mehsul 15 gun erzinde deyisdirile ve ya qaytaila biler\nSizin bu mehsulu alis tarixiniz " + sales.date + "-dir"+ConsoleColors.RESET);
            } else if (DAYS.between(sales.date, LocalDate.now()) < 15 && DAYS.between(sales.date, LocalDate.now()) > 1 && haveFoodOrNot(sales.salesItem) != null) {
                if (isAllSalesItemFood(sales.salesItem)) {
                    System.out.println(ConsoleColors.RED+"Mehsullar geri qaytarilmir ve ya deyisdirilmir.Sebeb qaytaraginiz mehsullarin hamisinin qida mehsullari olmasidir.\nBizim magazada qida mehsullarini 1 gun erzinde qaytarmaq ve ya yenisi ile evez etmek mumkundur"+ConsoleColors.RESET);
                } else {

                    removedSaleItems(sales.salesItem.stream().filter(a -> a.product.category != Category.Food).collect(Collectors.toList()), market, sales);
                    System.out.println(ConsoleColors.RED+"Qida mehsullarindan basqa diger mehsullarin qiymeti geri qaytaildi.Sebeb qaytaraginiz mehsullarin icerisinde qida mehsullari olmasidir.\nBizim magazada qida mehsullarini 1 gun erzinde qaytarmaq ve ya yenisi ile evez etmek mumkundur"+ConsoleColors.RESET);
                    System.out.println(ConsoleColors.CYAN+"\nPul geri qaytarildi!\n"+ConsoleColors.RESET);
                }
            } else {
                removedSaleItems(sales.salesItem, market, sales);
                System.out.println(ConsoleColors.CYAN+"\nPul geri qaytarildi!\n"+ConsoleColors.RESET);
            }
        }
    }

    public static void removedSaleItems(List<SalesItem> salesItemList, Market market, Sales sales) {
        int i;
        int counter = 0;
        boolean checkIsContinue = true;
        do {

            String continueOrNot;
            if (counter >= 1) {
                do {
                    System.out.print(ConsoleColors.BLUE+"Davam etmek isteyirsinizmi?y/n:"+ConsoleColors.RESET);
                    Scanner scanner = new Scanner(System.in);
                    continueOrNot = scanner.nextLine();
                } while (!continueOrNot.toLowerCase().contentEquals("y") && !continueOrNot.toLowerCase().contentEquals("n"));

                switch (continueOrNot.toLowerCase()) {
                    case "y":
                        break;
                    case "n":
                        checkIsContinue = false;
                        break;
                    default:
                        System.out.println("Seciminizi duzgun daxil edin.(y(yes) ve ya n(no))\n");
                        break;
                }

            }

            if (checkIsContinue) {


                String productInCategory = getProductInCategory(market, salesItemList);

                String finalProductInCategory = productInCategory;
                SalesItem salesItem = salesItemList.stream().filter(a -> a.product.name.equals(finalProductInCategory)).findFirst().orElse(null);
                int amount = getAmountInSale(salesItem);

                market.incrementAmountOfTheProduct(salesItem.product.productCode, amount);
                sales.price = sales.price - (salesItem.product.price * amount);
                salesItem.amount = salesItem.amount - amount;

                if (salesItem.amount == 0) {
                    salesItemList.remove(salesItem);
                }
                counter++;

                if (salesItemList.size() == 0) {
                    checkIsContinue = false;
                }

            }


        } while (checkIsContinue);

    }

    public static void GetSalesByToFromRange(Market market) {
        LocalDate from = getLocalDate();
        LocalDate to = getLocalDate(from);
        showSales(market.getSalesInFromToDate(from, to));
    }

    public static void GetSalesByDate(Market market) {
        LocalDate date = getLocalDate();
        showSales(market.getSalesInOneDate(date));
    }

    public static void showSales(List<Sales> salesItem) {

        if (salesItem.size() == 0) {
            System.out.println("Bu tarixde satis olmayib");
        } else {
            getSalesList(salesItem, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Satislarin siyahisi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }

    }

    public static void GetSalesByPriceRange(Market market) {
        double minPrice = getDoubleValue("Sastisin minimum qiymetini daxil edin:", "Sastisin qiymetini duzgun qeyd edin");
        double maxPrice = getDoubleValue(minPrice);
        getSalesList(market.getSalesByPrice(minPrice, maxPrice), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Verilmis qiymetdeki satislarin siyahisi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void GetSalesByCode(Market market) {
        String salesCode = getSalesCode(market);
        Sales sales = market.getSalesByNo(salesCode);
        getSalesByCode(sales);
    }

    public static void RemoveSales(Market market) {
        String salesCode = getSalesCode(market);
        Sales sales = market.getSalesByNo(salesCode);

        if (sales.price != market.getSalesItemPrice(sales.salesItem) || sales.price==0) {
            System.out.println(ConsoleColors.RED+"Satici:Bu cek artiq istifade olunub.\nMusteri:=)!"+ConsoleColors.RESET);
        } else {
            if (sales.date.until(LocalDate.now(), DAYS) > 15) {
                System.out.println(ConsoleColors.RED_UNDERLINED+"Mehsul 15 gun erzinde deyisdirile ve ya qaytaila biler\nSizin bu mehsulu alis tarixiniz " + sales.date + "-dir"+ConsoleColors.RESET);
            } else if (DAYS.between(sales.date, LocalDate.now()) < 15 && DAYS.between(sales.date, LocalDate.now()) > 1 && haveFoodOrNot(sales.salesItem) != null) {
                if (isAllSalesItemFood(sales.salesItem)) {
                    System.out.println(ConsoleColors.RED+"Mehsullar geri qaytarilmir ve ya deyisdirilmir.Sebeb qaytaraginiz mehsullarin hamisinin qida mehsullari olmasidir.\nBizim magazada qida mehsullarini 1 gun erzinde qaytarmaq ve ya yenisi ile evez etmek mumkundur"+ConsoleColors.RESET);
                } else {
                    if (getSalesPriceOrExchange("Pulunuzu geri isteyirsiz(y),yoxsa hemin deyerde alis-veris(n) ?y/n:")) {
                        System.out.println(ConsoleColors.RED_UNDERLINED+"Qida mehsullarindan basqa diger mehsullarin qiymeti geri qaytaildi.Sebeb qaytaraginiz mehsullarin icinde qida mehsullari olmasidir.\nBizim magazada qida mehsullarini 1 gun erzinde qaytarmaq ve ya yenisi ile evez etmek mumkundur"+ConsoleColors.RESET);
                        sales.price = sales.salesItem.stream().filter(a -> a.product.category == Category.Food).mapToDouble(a -> a.product.price * a.amount).sum();
                        getProductsBack(sales.salesItem.stream().filter(a -> a.product.category != Category.Food).collect(Collectors.toList()), market);
                        System.out.println(ConsoleColors.CYAN+"\nPul geri qaytarildi!\n"+ConsoleColors.RESET);
                    } else {
                        sales.price = sales.salesItem.stream().filter(a -> a.product.category == Category.Food).mapToDouble(a -> a.product.price * a.amount).sum();
                        exchangeProduct(sales.price, market);
                        sales.price = 0;
                        getProductsBack(sales.salesItem, market);
                    }
                }
            } else {
                if (getSalesPriceOrExchange("Pulunuzu geri isteyirsiz(y),yoxsa hemin deyerde alis-veris(n) ?y/n:")) {
                    sales.price = 0;
                    getProductsBack(sales.salesItem, market);
                    System.out.println(ConsoleColors.CYAN+"\nPul geri qaytarildi!\n"+ConsoleColors.RESET);
                } else {
                    exchangeProduct(sales.price, market);
                    sales.price = 0;
                    getProductsBack(sales.salesItem, market);
                }
            }
        }


    }

    public static void exchangeProduct(double price, Market market) {
        List<SalesItem> salesItems = new ArrayList<>();
        int i;

        int counter = 0;
        boolean checkIsContinue = true;
        do {

            String continueOrNot;
            if (counter >= 1) {
                do {
                    System.out.print(ConsoleColors.BLUE_UNDERLINED+"Davam etmek isteyirsinizmi?y/n:"+ConsoleColors.RESET);
                    Scanner scanner = new Scanner(System.in);
                    continueOrNot = scanner.nextLine();
                } while (!continueOrNot.toLowerCase().contentEquals("y") && !continueOrNot.toLowerCase().contentEquals("n"));

                switch (continueOrNot.toLowerCase()) {
                    case "y":
                        break;
                    case "n":
                        checkIsContinue = false;
                        break;
                    default:
                        System.out.println("Seciminizi duzgun daxil edin.(y(yes) ve ya n(no))\n");
                        break;

                }

            }

            if (checkIsContinue) {

                int category = getCategory();
                List<Product> products = market.getProductByCategory(Category.values()[category - 1]).stream().filter(product -> product.amount != 0).collect(Collectors.toUnmodifiableList());
                if (products.size() == 0) {
                    System.out.println("Axtaris neticesi tapilmadi");
                } else {
                    String productInCategory = getProductInCategory(products, market);

                    String finalProductInCategory = productInCategory;
                    Product product = products.stream().filter(a -> a.name.toUpperCase().equals(finalProductInCategory.toUpperCase())).findFirst().orElse(null);
                    int amount = getAmountInSale(product);

                    if (price >= product.price * amount) {
                        market.decrementAmountOfTheProduct(product.productCode, amount);
                        SalesItem salesItem = salesItems.stream().filter(a -> a.product.name.equals(product.name)).findFirst().orElse(null);
                        if (salesItem != null) {
                            salesItem.amount = salesItem.amount + amount;
                        } else {
                            salesItems.add(new SalesItem(product, amount));
                        }
                        counter++;
                        price = price - product.price * amount;
                    } else {
                        if (getSalesPriceOrExchange("Daxil etdiyin mehsulu deyisdirecek qeder pulunuz yoxdur.Davam etmek isteyirsizmi?(y/n)") == true) {
                            counter++;
                        } else {
                            checkIsContinue = false;
                            break;
                        }
                    }

                }


            }


        } while (checkIsContinue);

        if (salesItems.size() != 0) {
            double salePrice = market.getSalesItemPrice(salesItems);
            Sales mySales = new Sales(salesItems, salePrice);
            market.addSales(mySales);

        } else {
            System.out.println("Hec bir mehsul deyisdirilmedi ve musteri pulu goturub getdi.");
        }
    }

    public static boolean isAllSalesItemFood(List<SalesItem> salesItems) {
        List<SalesItem> salesItemsList = salesItems.stream().filter(a -> a.product.category == Category.Food).collect(Collectors.toList());
        if (salesItemsList.size() == salesItems.size()) {
            return true;
        }
        return false;
    }

    public static SalesItem haveFoodOrNot(List<SalesItem> salesItems) {
        return salesItems.stream().filter(a -> a.product.category == Category.Food).collect(Collectors.toList()).stream().findAny().orElse(null);
    }

    public static void getProductsBack(List<SalesItem> salesItems, Market market) {
        for (var item : salesItems) {
            market.incrementAmountOfTheProduct(item.product.productCode, item.amount);
        }
    }

    public static boolean getSalesPriceOrExchange(String text) {
        String exchangeOrNot;
        do {
            System.out.print(ConsoleColors.BLUE_UNDERLINED+text+ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);
            exchangeOrNot = scanner.nextLine();
        } while (!exchangeOrNot.contentEquals("y") && !exchangeOrNot.toLowerCase().contentEquals("n"));

        switch (exchangeOrNot.toLowerCase()) {
            case "y":
                return true;
        }
        return false;
    }

    public static void GetSalesList(Market market) {
        getSalesList(market.getSalesList().values().stream().collect(Collectors.toList()), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Satislarin siyahisi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void AddNewSales(Market market) {

        List<SalesItem> salesItems = new ArrayList<>();
        int i;

        int counter = 0;
        boolean checkIsContinue = true;
        do {
            String continueOrNot;
            if (counter >= 1) {
                do {
                    System.out.print(ConsoleColors.BLUE_UNDERLINED+"Davam etmek isteyirsinizmi?y/n:"+ConsoleColors.RESET);
                    Scanner scanner = new Scanner(System.in);
                    continueOrNot = scanner.nextLine();
                } while (!continueOrNot.toLowerCase().contentEquals("y") && !continueOrNot.toLowerCase().contentEquals("n"));

                switch (continueOrNot.toLowerCase()) {
                    case "y":
                        break;
                    case "n":
                        checkIsContinue = false;
                        break;
                    default:
                        System.out.println("Seciminizi duzgun daxil edin.(y(yes) ve ya n(no))\n");
                        break;

                }

            }

            if (checkIsContinue) {
                int category = getCategory();
                List<Product> products = market.getProductByCategory(Category.values()[category - 1]).stream().filter(a -> a.amount != 0).collect(Collectors.toList());
                if (products.size() == 0) {
                    System.out.println(ConsoleColors.RED+"Axtaris neticesi tapilmadi."+ConsoleColors.RESET);
                    break;
                } else {
                    String productInCategory = getProductInCategory(products, market);

                    String finalProductInCategory = productInCategory;
                    Product product = products.stream().filter(a -> a.name.toUpperCase().equals(finalProductInCategory.toUpperCase())).findFirst().orElse(null);
                    int amount = getAmountInSale(product);

                    market.decrementAmountOfTheProduct(product.productCode, amount);
                    SalesItem salesItem = salesItems.stream().filter(a -> a.product.name.equals(product.name) && a.product.category == Category.values()[category - 1]).findFirst().orElse(null);
                    if (salesItem != null) {
                        salesItem.amount = salesItem.amount + amount;
                    } else {
                        salesItems.add(new SalesItem(product, amount));
                    }
                    counter++;
                }
            }
        } while (checkIsContinue);


        double price = market.getSalesItemPrice(salesItems);
        Sales mySales = new Sales(salesItems, price);
        market.addSales(mySales);

    }


}
