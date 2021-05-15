import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.DAYS;

public class MarketManagement {
    public static void main(String[] args) {

        int choice;
        boolean check = true;
        Market market = new Market();
        label:
        do {

            do {

                System.out.println(ConsoleColors.YELLOW_BRIGHT + "1.Mehsullar uzerinde emeliyyat aparmaq.");
                System.out.println("2.Satislar uzerinde emeliyyat aparmaq.");
                System.out.println("0.Cixis." + ConsoleColors.RESET);

                try {
                    System.out.print(ConsoleColors.BLUE + "Ededi daxil edin:" + ConsoleColors.RESET);
                    Scanner scanner = new Scanner(System.in);
                    choice = scanner.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println(ConsoleColors.RED + "\nNumber tipinde eded daxil edin." + ConsoleColors.RESET);
                }


            } while (true);

            switch (choice) {
                case 1:
                    System.out.println(ConsoleColors.GREEN + "\nMehsullar sistemine xos gelmisiniz.\n" + ConsoleColors.RESET);
                    while (check) {
                        do {

                            System.out.println(ConsoleColors.YELLOW_BRIGHT + "1.Yeni mehsul elave et.");
                            System.out.println("2.Mehsul uzerinde duzelis et.");
                            System.out.println("3.Mehsulu sil.");
                            System.out.println("4.Butun mehsullari goster.");
                            System.out.println("5.Categoriyasina gore mehsullari goster.");
                            System.out.println("6.Qiymet araligina gore mehsullari goster.");
                            System.out.println("7.Mehsullar arasinda ada gore axtaris et.");
                            System.out.println("8.Geriye kecid et.");
                            System.out.println("0.Sistemden cixis." + ConsoleColors.RESET);

                            try {
                                System.out.print(ConsoleColors.BLUE + "Ededi daxil edin:" + ConsoleColors.RESET);

                                Scanner scanner = new Scanner(System.in);
                                choice = scanner.nextInt();

                                break;
                            } catch (Exception e) {
                                System.out.println("\nNumber tipinde eded daxil edin.");
                            }


                        } while (true);

                        switch (choice) {
                            case 1:
                                AddNewProduct(market);
                                break;
                            case 2:
                                if (market.getProductList().size() == 0) {
                                    System.out.println("\nMarkete mehsul elave olunmayib.\n");
                                } else {
                                    EditProduct(market);
                                }
                                break;
                            case 3:
                                if (market.getProductList().size() == 0) {
                                    System.out.println("\nMarkete mehsul elave olunmayib.\n");
                                } else {
                                    RemoveProduct(market);
                                }
                                break;
                            case 4:
                                if (market.getProductList().size() == 0) {
                                    System.out.println("\nMarkete mehsul elave olunmayib.\n");
                                } else {
                                    GetProductList(market);
                                }
                                break;
                            case 5:
                                if (market.getProductList().size() == 0) {
                                    System.out.println("\nMarkete mehsul elave olunmayib.\n");
                                } else {
                                    GetProductsByCategory(market);
                                }
                                break;
                            case 6:
                                if (market.getProductList().size() == 0) {
                                    System.out.println("\nMarkete mehsul elave olunmayib.\n");
                                } else {
                                    GetProductsByPriceRange(market);
                                }
                                break;
                            case 7:
                                if (market.getProductList().size() == 0) {
                                    System.out.println("\nMarkete mehsul elave olunmayib.\n");
                                } else {
                                    GetProductsByName(market);
                                }
                                break;
                            case 8:
                                continue label;
                            case 0:
                                System.out.println(ConsoleColors.RED + "\nSistemden cixdiniz" + ConsoleColors.RESET);
                                check = false;
                                break;
                            default:
                                System.out.println("\nZehmet olmasa duzgun secim edin");
                                break;

                        }
                    }
                    check = false;
                    break;
                case 2:
                    System.out.println("\nSatislar sistemine xos gelmisiniz.\n");
                    while (check) {
                        do {
                            System.out.println("1.Yeni satis elave etmek.");
                            System.out.println("2.Satisdaki hansisa mehsulun geri qaytarilmasi.");
                            System.out.println("3.Satisin silinmesi.");
                            System.out.println("4.Butun satislarin ekrana cixarilmasi.");
                            System.out.println("5.Verilen tarix araligina gore satislarin gosterilmesi.");
                            System.out.println("6.Verilen mebleg araligina gore satislarin gosterilmesi .");
                            System.out.println("7.Verilmis bir tarixde olan satislarin gosterilmesi.");
                            System.out.println("8.Verilmis nomreye esasen hemin nomreli satisin melumatlarinin gosterilmesi.");
                            System.out.println("9.Geriye kecid ucun.");
                            System.out.println("0.Cixis.");

                            try {
                                System.out.print(ConsoleColors.BLUE + "Ededi daxil edin:" + ConsoleColors.RESET);
                                Scanner scanner = new Scanner(System.in);
                                choice = scanner.nextInt();

                                break;
                            } catch (Exception e) {
                                System.out.println("\nNumber tipinde eded daxil edin.");
                            }


                        } while (true);

                        switch (choice) {
                            case 1:
                                AddNewSales(market);
                                break;
                            case 2:
                                if (market.getSalesList().size() == 0) {
                                    System.out.println("\nSatis yoxdur.\n");
                                } else {
                                    RemoveSalesItemFromSale(market);
                                }
                                break;
                            case 3:
                                if (market.getSalesList().size() == 0) {
                                    System.out.println("\nSatis yoxdur.\n");
                                } else {
                                    RemoveSales(market);
                                }
                                break;
                            case 4:
                                if (market.getSalesList().size() == 0) {
                                    System.out.println("\nSatis yoxdur.\n");
                                } else {
                                    GetSalesList(market);
                                }
                                break;
                            case 5:
                                if (market.getSalesList().size() == 0) {
                                    System.out.println("\nSatis yoxdur.\n");
                                } else {
                                    GetSalesByToFromRange(market);
                                }
                                break;
                            case 6:
                                if (market.getSalesList().size() == 0) {
                                    System.out.println("\nSatis yoxdur.\n");
                                } else {
                                    GetSalesByPriceRange(market);
                                }
                                break;
                            case 7:
                                if (market.getSalesList().size() == 0) {
                                    System.out.println("\nSatis yoxdur.\n");
                                } else {
                                    GetSalesByDate(market);
                                }
                                break;
                            case 8:
                                if (market.getSalesList().size() == 0) {
                                    System.out.println("\nSatis yoxdur.\n");
                                } else {
                                    GetSalesByCode(market);
                                }
                                break;
                            case 9:
                                continue label;
                            case 0:
                                System.out.println(ConsoleColors.RED + "\nSistemden cixdiniz" + ConsoleColors.RESET);

                                check = false;
                                break;
                            default:
                                System.out.println("\nZehmet olmasa duzgun secim edin");
                                break;

                        }
                    }
                    check = false;
                    break;
                case 0:
                    System.out.println(ConsoleColors.RED + "\nSistemden cixdiniz" + ConsoleColors.RESET);
                    check = false;
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Zehmet olmasa duzgun secim edin" + ConsoleColors.RESET);
                    break;
            }


        } while (check);
    }

    private static void RemoveSalesItemFromSale(Market market) {
        String salesCode;
        do {
            System.out.print("Zehmet olmasa ceki teqdim edin:");
            Scanner scanner = new Scanner(System.in);

            salesCode = scanner.nextLine();

            if (market.getSalesByNo(salesCode) == null) {
                System.out.println("Satici:Ceki oxumur.\nMusteri:Bir daha nezerden kecirin!");
            }

        } while (salesCode.length() == 0 || market.getSalesByNo(salesCode) == null);

        if (market.getSalesByNo(salesCode).price != market.getSalesItemPrice(market.getSalesByNo(salesCode).salesItem)) {
            System.out.println("Satici:Bu cek artiq istifade olunub.\nMusteri:=)!");
        } else {
            Sales sales = market.getSalesByNo(salesCode);
            if (sales.date.until(LocalDate.now(), DAYS) > 15) {
                System.out.println("Mehsul 15 gun erzinde deyisdirile ve ya qaytaila biler\nSizin bu mehsulu alis tarixiniz " + sales.date + "-dir");
            } else if (DAYS.between(sales.date, LocalDate.now()) < 15 && DAYS.between(sales.date, LocalDate.now()) > 1 && haveFoodOrNot(sales.salesItem) != null) {
                if (isAllSalesItemFood(sales.salesItem)) {
                    System.out.println("Mehsullar geri qaytarilmir ve ya deyisdirilmir.Sebeb qaytaraginiz mehsullarin hamisinin qida mehsullari olmasidir.\nBizim magazada qida mehsullarini 1 gun erzinde qaytarmaq ve ya yenisi ile evez etmek mumkundur");
                } else {

                    removedSaleItems(sales.salesItem.stream().filter(a -> a.product.category != Category.Food).collect(Collectors.toList()), market, sales);
                    System.out.println("Qida mehsullarindan basqa diger mehsullarin qiymeti geri qaytaildi");
                    System.out.println("\nPul geri qaytarildi!\n");
                }
            } else {
                removedSaleItems(sales.salesItem, market, sales);
                System.out.println("\nPul geri qaytarildi!\n");
            }
        }
    }

    private static void removedSaleItems(List<SalesItem> salesItemList, Market market, Sales sales) {
        List<SalesItem> salesItems = new ArrayList<>();
        int i;

        int counter = 0;
        boolean checkIsContinue = true;
        do {

            String continueOrNot;
            if (counter >= 1) {
                do {
                    System.out.print("Davam etmek isteyirsinizmi?y/n:");
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


                String productInCategory;
                do {
                    i = 1;
                    for (var item : salesItemList) {
                        System.out.println(i + "." + ConsoleColors.CYAN + "Mehusulun adi:" + item.product.name + ConsoleColors.RED_UNDERLINED + " Mehsulun qiymeti:" + item.product.price + " Sayi:" + item.amount + ConsoleColors.RESET);
                        i++;
                    }
                    System.out.print("Productun adini daxil edin:");
                    Scanner scanner = new Scanner(System.in);
                    productInCategory = scanner.nextLine();

                    if (!market.salesItemHaveOrNot(salesItemList, productInCategory)) {
                        System.out.println("Marketde bu adda ya mehsul yoxdur ya da siz duzgun daxil elemirsiz.");
                    }

                } while (productInCategory.length() == 0 || !market.salesItemHaveOrNot(salesItemList, productInCategory));

                int amount = 0;
                String finalProductInCategory = productInCategory;
                SalesItem salesItem = salesItemList.stream().filter(a -> a.product.name.equals(finalProductInCategory)).findFirst().orElse(null);
                do {
                    System.out.print("Productun sayini daxil edin:");
                    Scanner scanner = new Scanner(System.in);
                    do {
                        try {
                            amount = scanner.nextInt();
                            break;
                        } catch (Exception e) {
                            System.out.println("Productun sayini duzgun qeyd edin");
                        }
                    } while (true);

                    if (salesItem.amount < amount) {
                        System.out.println("Mehsuldan daxil etdiyiniz say qeder yoxdur" + " daxil ede bileceyiniz maksimum say " + salesItem.amount);
                    }
                } while (salesItem.amount < amount);

                market.incrementAmountOfTheProduct(salesItem.product.productCode, amount);
                sales.price = sales.price - (salesItem.product.price * amount);
                salesItem.amount = salesItem.amount - amount;

                if (salesItem.amount == 0) {
                    salesItemList.remove(salesItem);
                }
                counter++;

                if (sales.salesItem.size() == 0) {
                    checkIsContinue = false;
                }

            }


        } while (checkIsContinue);

    }

    private static void GetSalesByToFromRange(Market market) {
        LocalDate from;
        do {
            System.out.print("Zehmet olmasa baslangic tarixi teqdim edin((yyyy-mm-dd)):");
            Scanner scanner = new Scanner(System.in);

            try {
                from = LocalDate.parse(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Tarixi duzgun teyin edin.(yyyy-mm-dd)");
            }


        } while (true);

        LocalDate to = null;
        do {
            System.out.print("Zehmet olmasa son tarixi teqdim edin((yyyy-mm-dd)):");
            Scanner scanner = new Scanner(System.in);

            try {
                to = LocalDate.parse(scanner.nextLine());
                if (from.isAfter(to)) {
                    System.out.println("Son tarix baslangic tarixden evvel ola bilmez");
                }
                break;
            } catch (Exception e) {
                System.out.println("Tarixi duzgun teyin edin.(yyyy-mm-dd)");
            }


        } while (true || from.isAfter(to));


        showSales(market.getSalesInFromToDate(from, to));

    }

    private static void GetSalesByDate(Market market) {
        LocalDate date;
        do {
            System.out.print("Zehmet olmasa tarixi teqdim edin((yyyy-mm-dd)):");
            Scanner scanner = new Scanner(System.in);

            try {
                date = LocalDate.parse(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Tarixi duzgun teyin edin.(yyyy-mm-dd)");
            }


        } while (true);

        showSales(market.getSalesInOneDate(date));


    }

    private static void showSales(List<Sales> salesItem) {

        if (salesItem.size() == 0) {
            System.out.println("Bu tarixde satis olmayib");
        } else {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Satislarin siyahisi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            for (var item : salesItem) {
                System.out.println("\nSatisi nomresi:" + item.no + "\nSatisin umumi qiymeti:" + item.price + "\nSatisin tarixi:" + item.date);
                for (var salesItems : item.salesItem) {
                    System.out.println("\nMehsulun adi:" + salesItems.product.name + "\nMehsulun sayi:" + salesItems.amount);

                }
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }

    }

    private static void GetSalesByPriceRange(Market market) {
        double minPrice;
        do {
            System.out.print("Sastisin minimum qiymetini daxil edin:");
            Scanner scanner = new Scanner(System.in);

            try {
                minPrice = scanner.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Sastisin qiymetini duzgun qeyd edin");
            }

        } while (true);

        double maxPrice = 0;
        do {
            System.out.print("Sastisin maximum qiymetini daxil edin:");
            Scanner scanner = new Scanner(System.in);

            try {
                maxPrice = scanner.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Sastisin qiymetini duzgun qeyd edin");
            }

            if (minPrice > maxPrice) {
                System.out.println("Minimal qiymet maksimal qiymetden cix ola bilmez");
            }
        } while (minPrice > maxPrice || true);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Verilmis qiymetdeki satislarin siyahisi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        for (var item : market.getSalesByPrice(minPrice, maxPrice)) {

            System.out.println("\nSatisi nomresi:" + item.no + "\nSatisin umumi qiymeti:" + item.price + "\nSatisin tarixi:" + item.date);
            for (var salesItems : item.salesItem) {
                System.out.println("\nMehsulun adi:" + salesItems.product.name + "\nMehsulun sayi:" + salesItems.amount);

            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }

    private static void GetSalesByCode(Market market) {
        String salesCode;
        do {
            System.out.print("Zehmet olmasa ceki teqdim edin:");
            Scanner scanner = new Scanner(System.in);

            salesCode = scanner.nextLine();

            if (market.getSalesByNo(salesCode) == null) {
                System.out.println("Ceki oxumur.!");
            }

        } while (salesCode.length() == 0 || market.getSalesByNo(salesCode) == null);
        Sales sales = market.getSalesByNo(salesCode);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ " + sales.no + " nomreli satisin siyahisi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\nSatisi nomresi:" + sales.no + "\nSatisin umumi qiymeti:" + sales.price + "\nSatisin tarixi:" + sales.date);
        for (var salesItems : sales.salesItem) {
            System.out.println("\nMehsulun adi:" + salesItems.product.name + "\nMehsulun sayi:" + salesItems.amount);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }

    private static void RemoveSales(Market market) {
        String salesCode;
        do {
            System.out.print("Zehmet olmasa ceki teqdim edin:");
            Scanner scanner = new Scanner(System.in);

            salesCode = scanner.nextLine();

            if (market.getSalesByNo(salesCode) == null) {
                System.out.println("Satici:Ceki oxumur.\nMusteri:Bir daha nezerden kecirin!");
            }

        } while (salesCode.length() == 0 || market.getSalesByNo(salesCode) == null);

        if (market.getSalesByNo(salesCode).price != market.getSalesItemPrice(market.getSalesByNo(salesCode).salesItem)) {
            System.out.println("Satici:Bu cek artiq istifade olunub.\nMusteri:=)!");
        } else {
            Sales sales = market.getSalesByNo(salesCode);
            if (sales.date.until(LocalDate.now(), DAYS) > 15) {
                System.out.println("Mehsul 15 gun erzinde deyisdirile ve ya qaytaila biler\nSizin bu mehsulu alis tarixiniz " + sales.date + "-dir");
            } else if (DAYS.between(sales.date, LocalDate.now()) < 15 && DAYS.between(sales.date, LocalDate.now()) > 1 && haveFoodOrNot(sales.salesItem) != null) {
                if (isAllSalesItemFood(sales.salesItem)) {
                    System.out.println("Mehsullar geri qaytarilmir ve ya deyisdirilmir.Sebeb qaytaraginiz mehsullarin hamisinin qida mehsullari olmasidir.\nBizim magazada qida mehsullarini 1 gun erzinde qaytarmaq ve ya yenisi ile evez etmek mumkundur");
                } else {
                    if (getSalesPriceOrExchange("Pulunuzu geri isteyirsiz(y),yoxsa hemin deyerde alis-veris(n) ?y/n:")) {
                        System.out.println("Qida mehsullarindan basqa diger mehsullarin qiymeti geri qaytaildi");
                        sales.price = sales.salesItem.stream().filter(a -> a.product.category == Category.Food).mapToDouble(a -> a.product.price * a.amount).sum();
                        getProductsBack(sales.salesItem.stream().filter(a -> a.product.category != Category.Food).collect(Collectors.toList()), market);
                        System.out.println("\nPul geri qaytarildi!\n");
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
                    System.out.println("\nPul geri qaytarildi!\n");
                } else {
                    exchangeProduct(sales.price, market);
                    sales.price = 0;
                    getProductsBack(sales.salesItem, market);
                }
            }
        }


    }

    private static void exchangeProduct(double price, Market market) {
        List<SalesItem> salesItems = new ArrayList<>();
        int i;

        int counter = 0;
        boolean checkIsContinue = true;
        do {

            String continueOrNot;
            if (counter >= 1) {
                do {
                    System.out.print("Davam etmek isteyirsinizmi?y/n:");
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

                int cateory = getCategory();
                List<Product> products = market.getProductByCategory(Category.values()[cateory - 1]).stream().filter(product -> product.amount != 0).collect(Collectors.toUnmodifiableList());
                if (products.size() == 0) {
                    System.out.println("Axtaris neticesi tapilmadi");
                } else {
                    String productInCategory;
                    do {
                        i = 1;
                        for (var item : products.stream().filter(product -> product.amount != 0).collect(Collectors.toUnmodifiableList())) {
                            System.out.println(i + "." + ConsoleColors.CYAN + "Mehusulun adi:" + item.name + ConsoleColors.RED_UNDERLINED + " Mehsulun qiymeti:" + item.price + ConsoleColors.RESET);
                            i++;
                        }
                        System.out.print("Productun adini daxil edin:");
                        Scanner scanner = new Scanner(System.in);
                        productInCategory = scanner.nextLine();

                        if (!market.productHaveOrNot(productInCategory)) {
                            System.out.println("Marketde bu adda ya mehsul yoxdur ya dac siz duzgun daxil elemirsiz.");
                        }

                    } while (productInCategory.length() == 0 || !market.productHaveOrNot(productInCategory));

                    int amount = 0;
                    String finalProductInCategory = productInCategory;
                    Product product = products.stream().filter(a -> a.name.toUpperCase().equals(finalProductInCategory.toUpperCase())).findFirst().orElse(null);
                    do {
                        System.out.print("Productun sayini daxil edin:");
                        Scanner scanner = new Scanner(System.in);
                        do {
                            try {
                                amount = scanner.nextInt();
                                break;
                            } catch (Exception e) {
                                System.out.println("Productun sayini duzgun qeyd edin");
                            }
                        } while (true);

                        if (product.amount < amount) {
                            System.out.println("Mehsuldan daxil etdiyiniz say qeder yoxdur" + " daxil ede bileceyiniz maksimum say " + product.amount);
                        }
                    } while (product.amount < amount || amount == 0);

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

    private static boolean isAllSalesItemFood(List<SalesItem> salesItems) {
        List<SalesItem> salesItemsList = salesItems.stream().filter(a -> a.product.category == Category.Food).collect(Collectors.toList());
        if (salesItemsList.size() == salesItems.size()) {
            return true;
        }
        return false;
    }

    private static SalesItem haveFoodOrNot(List<SalesItem> salesItems) {
        return salesItems.stream().filter(a -> a.product.category == Category.Food).collect(Collectors.toList()).stream().findAny().orElse(null);
    }

    private static void getProductsBack(List<SalesItem> salesItems, Market market) {
        for (var item : salesItems) {
            market.incrementAmountOfTheProduct(item.product.productCode, item.amount);
        }
    }

    private static boolean getSalesPriceOrExchange(String text) {
        String exchangeOrNot;

        do {
            System.out.print(text);
            Scanner scanner = new Scanner(System.in);
            exchangeOrNot = scanner.nextLine();
        } while (!exchangeOrNot.contentEquals("y") && !exchangeOrNot.toLowerCase().contentEquals("n"));

        switch (exchangeOrNot.toLowerCase()) {
            case "y":
                return true;
        }
        return false;
    }

    private static void GetSalesList(Market market) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Satislarin siyahisi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for (var item : market.getSalesList().values()) {
            System.out.println("\nSatisi nomresi:" + item.no + "\nSatisin umumi qiymeti:" + item.price + "\nSatisin tarixi:" + item.date);
//            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Hemin bu satisa aiddir:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            for (var salesItems : item.salesItem) {
                System.out.println("\nMehsulun adi:" + salesItems.product.name + "\nMehsulun sayi:" + salesItems.amount);

            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        }


    }

    private static int getCategory() {
        int cateory;
        int i;

        do {
            i = 1;
            for (var item : Category.values()) {
                System.out.println(i + "." + item.name());
                i++;
            }
            System.out.print("Productun kateqoriyasini daxil edin:");
            Scanner scanner = new Scanner(System.in);
            try {
                cateory = scanner.nextInt();

                if (Category.values()[cateory - 1] == null) {
                    System.out.println("Productun kateqoriyasini duzgun qeyd edin");
                }
                break;
            } catch (Exception e) {
                System.out.println("Productun kateqoriyasini duzgun qeyd edin");
            }


        } while (true);

        return cateory;
    }

    private static void AddNewSales(Market market) {

        List<SalesItem> salesItems = new ArrayList<>();
        int i;

        int counter = 0;
        boolean checkIsContinue = true;
        do {

            String continueOrNot;
            if (counter >= 1) {
                do {
                    System.out.print("Davam etmek isteyirsinizmi?y/n:");
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


                int cateory = getCategory();
                List<Product> products = market.getProductByCategory(Category.values()[cateory - 1]).stream().filter(a -> a.amount != 0).collect(Collectors.toList());
                if (products.size() == 0) {
                    System.out.println("Axtaris neticesi tapilmadi.");
                } else {
                    String productInCategory;
                    do {
                        i = 1;
                        for (var item : products.stream().filter(product -> product.amount != 0).collect(Collectors.toUnmodifiableList())) {
                            System.out.println(i + "." + ConsoleColors.CYAN + "Mehusulun adi:" + item.name + ConsoleColors.RED_UNDERLINED + " Mehsulun qiymeti:" + item.price + ConsoleColors.RESET);
                            i++;
                        }
                        System.out.print("Productun adini daxil edin:");
                        Scanner scanner = new Scanner(System.in);
                        productInCategory = scanner.nextLine();

                        if (!market.productHaveOrNot(productInCategory)) {
                            System.out.println("Marketde bu adda ya mehsul yoxdur ya dac siz duzgun daxil elemirsiz.");
                        }

                    } while (productInCategory.length() == 0 || !market.productHaveOrNot(productInCategory));

                    int amount = 0;
                    String finalProductInCategory = productInCategory;
                    Product product = products.stream().filter(a -> a.name.toUpperCase().equals(finalProductInCategory.toUpperCase())).findFirst().orElse(null);
                    do {
                        System.out.print("Productun sayini daxil edin:");
                        Scanner scanner = new Scanner(System.in);
                        do {
                            try {
                                amount = scanner.nextInt();
                                break;
                            } catch (Exception e) {
                                System.out.println("Productun sayini duzgun qeyd edin");
                            }
                        } while (true);

                        if (product.amount < amount) {
                            System.out.println("Mehsuldan daxil etdiyiniz say qeder yoxdur" + " daxil ede bileceyiniz maksimum say " + product.amount);
                        }
                    } while (product.amount < amount);

                    market.decrementAmountOfTheProduct(product.productCode, amount);
                    SalesItem salesItem = salesItems.stream().filter(a -> a.product.name.equals(product.name) && a.product.category == Category.values()[cateory - 1]).findFirst().orElse(null);
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


    /**
     * Products start
     **/
    private static void RemoveProduct(Market market) {
        String productCode;
        do {
            System.out.print("Productun kodunu elave edin:");
            Scanner scanner = new Scanner(System.in);

            productCode = scanner.nextLine();

            if (market.getProduct(productCode) == null) {
                System.out.println("Bele bir kodda mehsul tapilmadi");
            }

        } while (productCode.length() == 0 || market.getProduct(productCode) == null);

        market.RemoveProduct(productCode);
        System.out.println("\nMehsul silindi!\n");
    }

    private static void GetProductsByPriceRange(Market market) {
        double minPrice;
        do {
            System.out.print("Productun minimum qiymetini daxil edin:");
            Scanner scanner = new Scanner(System.in);

            try {
                minPrice = scanner.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Productun qiymetini duzgun qeyd edin");
            }

        } while (true);

        double maxPrice = 0;
        do {
            System.out.print("Productun maximum qiymetini daxil edin:");
            Scanner scanner = new Scanner(System.in);

            try {
                maxPrice = scanner.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Productun qiymetini duzgun qeyd edin");
            }

            if (minPrice > maxPrice) {
                System.out.println("Minimal qiymet maksimal qiymetden cix ola bilmez");
            }
        } while (minPrice > maxPrice || true);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Verilmis qiymetdeki mehsullarin siyahisi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        for (var item : market.getProductByPrice(minPrice, maxPrice)) {
            System.out.println("\nMehsulun kodu:" + item.productCode + "\nMehusulun adi:" + item.name + "\nMehsulun qiymeti:" + item.price + "\nMehsulun kateqoriyasi:" + item.category.name() + "\nMehsulun sayi:" + item.amount);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


    }

    private static void EditProduct(Market market) {
        String productCode;
        do {
            System.out.print("Productun kodunu elave edin:");
            Scanner scanner = new Scanner(System.in);

            productCode = scanner.nextLine();

            if (market.getProduct(productCode) == null) {
                System.out.println("Bele bir kodda mehsul tapilmadi");
            }

        } while (productCode.length() == 0 || market.getProduct(productCode) == null);
        String productName;
        do {
            System.out.print("Productun adini daxil edin:");
            Scanner scanner = new Scanner(System.in);

            productName = scanner.nextLine();

        } while (productName.length() == 0);

        double price;
        do {
            System.out.print("Productun qiymetini daxil edin:");
            Scanner scanner = new Scanner(System.in);

            try {
                price = scanner.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Productun qiymetini duzgun qeyd edin");
            }

        } while (true);

        int cateory = 0;
        int i;

        do {
            i = 1;
            for (var item : Category.values()) {
                System.out.println(i + "." + item.name());
                i++;
            }
            System.out.print("Productun kateqoriyasini daxil edin:");
            Scanner scanner = new Scanner(System.in);
            try {
                cateory = scanner.nextInt();

                if (Category.values()[cateory - 1] == null) {
                    System.out.println("Iscinin vezifesini duzgun qeyd edin");
                }
                break;
            } catch (Exception e) {
                System.out.println("Productun kateqoriyasini duzgun qeyd edin");
            }


        } while (true);

        int amount;
        do {
            System.out.print("Productun sayini daxil edin:");
            Scanner scanner = new Scanner(System.in);

            try {
                amount = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Productun sayini duzgun qeyd edin");
            }

        } while (true);

        market.updateProduct(productCode, productName, price, Category.values()[cateory - 1], amount);
        System.out.println("\nMehsul ugurla yenilendi!\n");


    }

    private static void GetProductsByCategory(Market market) {
        int cateory = 0;
        int i;

        do {
            i = 1;
            for (var item : Category.values()) {
                System.out.println(i + "." + item.name());
                i++;
            }
            System.out.print("Productun kateqoriyasini daxil edin:");
            Scanner scanner = new Scanner(System.in);
            try {
                cateory = scanner.nextInt();

                if (Category.values()[cateory - 1] == null) {
                    System.out.println("Iscinin vezifesini duzgun qeyd edin");
                }
                break;
            } catch (Exception e) {
                System.out.println("Productun kateqoriyasini duzgun qeyd edin");
            }


        } while (true);

        if (market.getProductByCategory(Category.values()[cateory - 1]).size() == 0) {
            System.out.println("Axtaris neticesi tapilmadi");
        } else {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Terkibinde " + Category.values()[cateory - 1] + " olan mehsullarin siyahisi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            for (var item : market.getProductByCategory(Category.values()[cateory - 1])) {

                System.out.println("\nMehsulun kodu:" + item.productCode + "\nMehusulun adi:" + item.name + "\nMehsulun qiymeti:" + item.price + "\nMehsulun kateqoriyasi:" + item.category.name() + "\nMehsulun sayi:" + item.amount);
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        }

    }

    private static void GetProductsByName(Market market) {
        String productName;
        do {
            System.out.print("Productun axtarisi bir sey yazin edin:");
            Scanner scanner = new Scanner(System.in);

            productName = scanner.nextLine();

        } while (productName.length() == 0);

        if (market.searchProductByName(productName).size() == 0) {
            System.out.println("Axtaris neticesi tapilmadi");
        } else {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Terkibinde " + productName + " olan mehsullarin siyahisi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            for (var item : market.searchProductByName(productName)) {

                System.out.println("\nMehsulun kodu:" + item.productCode + "\nMehusulun adi:" + item.name + "\nMehsulun qiymeti:" + item.price + "\nMehsulun kateqoriyasi:" + item.category.name() + "\nMehsulun sayi:" + item.amount);
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        }

    }

    private static void GetProductList(Market market) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Mehsullarin siyahisi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for (var item : market.getProductList().values()) {
            System.out.println("\nMehsulun kodu:" + item.productCode + "\nMehusulun adi:" + item.name + "\nMehsulun qiymeti:" + item.price + "\nMehsulun kateqoriyasi:" + item.category.name() + "\nMehsulun sayi:" + item.amount);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }


    private static void AddNewProduct(Market market) {

        if (market.getProductList().size() == 0) {
            addProduct(market);
        } else {
            String yesOrNot;
            do {
                System.out.print("Yeni bir mehsul elave etmekk isteyirsizse(y),marketde olan mehsulu elave etmek isteyirsizse(n)?y/n:");
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

    private static void editProductAmount(Market market) {
        String productCode;
        do {
            System.out.print("Productun kodunu elave edin:");
            Scanner scanner = new Scanner(System.in);

            productCode = scanner.nextLine();

            if (market.getProduct(productCode) == null) {
                System.out.println("Bele bir kodda mehsul tapilmadi");
            }

        } while (productCode.length() == 0 || market.getProduct(productCode) == null);

        Product product = market.getProduct(productCode);

        int amount;
        do {
            System.out.print("Productun sayini daxil edin:");
            Scanner scanner = new Scanner(System.in);

            try {
                amount = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Productun sayini duzgun qeyd edin");
            }

        } while (true);

        product.amount = product.amount + amount;

        System.out.println(ConsoleColors.CYAN + "\nMagazada olan mehsulun miqdari yenilendi!\n" + ConsoleColors.RESET);


    }

    private static void addProduct(Market market) {
        String productName;
        do {
            System.out.print(ConsoleColors.BLUE + "Productun adini daxil edin:");
            Scanner scanner = new Scanner(System.in);

            productName = scanner.nextLine();

        } while (productName.length() == 0);

        double price;
        do {
            System.out.print("Productun qiymetini daxil edin:");
            Scanner scanner = new Scanner(System.in);

            try {
                price = scanner.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Productun qiymetini duzgun qeyd edin");
            }

        } while (true);

        int cateory = 0;
        int i;

        do {
            i = 1;
            for (var item : Category.values()) {
                System.out.println(i + "." + item.name());
                i++;
            }
            System.out.print("Productun kateqoriyasini daxil edin:");
            Scanner scanner = new Scanner(System.in);
            try {
                cateory = scanner.nextInt();

                if (Category.values()[cateory - 1] == null) {
                    System.out.println("Iscinin vezifesini duzgun qeyd edin");
                }
                break;
            } catch (Exception e) {
                System.out.println("Productun kateqoriyasini duzgun qeyd edin");
            }


        } while (true);

        int amount;
        do {
            System.out.print("Productun sayini daxil edin:");
            Scanner scanner = new Scanner(System.in);

            try {
                amount = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Productun sayini duzgun qeyd edin");
            }

        } while (true);


        market.addProduct(new Product(productName, price, Category.values()[cateory - 1], amount));
        System.out.println(ConsoleColors.CYAN + "\nProduct elave olundu!\n" + ConsoleColors.RESET);
    }


    /** Products end  **/
}
