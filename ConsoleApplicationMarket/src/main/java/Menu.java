import java.util.Scanner;

public class Menu {
//    public static void Menu(){
//        int choice;
//        boolean check = true;
//        Market market = new Market();
//        label:
//        do {
//
//            do {
//
//                System.out.println(ConsoleColors.YELLOW_BRIGHT + "1.Mehsullar uzerinde emeliyyat aparmaq.");
//                System.out.println("2.Satislar uzerinde emeliyyat aparmaq.");
//                System.out.println("0.Cixis." + ConsoleColors.RESET);
//
//                try {
//                    System.out.print(ConsoleColors.BLUE + "Ededi daxil edin:" + ConsoleColors.RESET);
//                    Scanner scanner = new Scanner(System.in);
//                    choice = scanner.nextInt();
//                    break;
//                } catch (Exception e) {
//                    System.out.println(ConsoleColors.RED + "\nNumber tipinde eded daxil edin." + ConsoleColors.RESET);
//                }
//
//
//            } while (true);
//
//            switch (choice) {
//                case 1:
//                    System.out.println(ConsoleColors.GREEN + "\nMehsullar sistemine xos gelmisiniz.\n" + ConsoleColors.RESET);
//                    while (check) {
//                        do {
//
//                            System.out.println(ConsoleColors.YELLOW_BRIGHT + "1.Yeni mehsul elave et.");
//                            System.out.println("2.Mehsul uzerinde duzelis et.");
//                            System.out.println("3.Mehsulu sil.");
//                            System.out.println("4.Butun mehsullari goster.");
//                            System.out.println("5.Categoriyasina gore mehsullari goster.");
//                            System.out.println("6.Qiymet araligina gore mehsullari goster.");
//                            System.out.println("7.Mehsullar arasinda ada gore axtaris et.");
//                            System.out.println("8.Geriye kecid et.");
//                            System.out.println("0.Sistemden cixis." + ConsoleColors.RESET);
//
//                            try {
//                                System.out.print(ConsoleColors.BLUE + "Ededi daxil edin:" + ConsoleColors.RESET);
//
//                                Scanner scanner = new Scanner(System.in);
//                                choice = scanner.nextInt();
//
//                                break;
//                            } catch (Exception e) {
//                                System.out.println("\nNumber tipinde eded daxil edin.");
//                            }
//
//
//                        } while (true);
//
//                        switch (choice) {
//                            case 1:
//                                AddNewProduct(market);
//                                break;
//                            case 2:
//                                if (market.getProductList().size() == 0) {
//                                    System.out.println("\nMarkete mehsul elave olunmayib.\n");
//                                } else {
//                                    EditProduct(market);
//                                }
//                                break;
//                            case 3:
//                                if (market.getProductList().size() == 0) {
//                                    System.out.println("\nMarkete mehsul elave olunmayib.\n");
//                                } else {
//                                    RemoveProduct(market);
//                                }
//                                break;
//                            case 4:
//                                if (market.getProductList().size() == 0) {
//                                    System.out.println("\nMarkete mehsul elave olunmayib.\n");
//                                } else {
//                                    GetProductList(market);
//                                }
//                                break;
//                            case 5:
//                                if (market.getProductList().size() == 0) {
//                                    System.out.println("\nMarkete mehsul elave olunmayib.\n");
//                                } else {
//                                    GetProductsByCategory(market);
//                                }
//                                break;
//                            case 6:
//                                if (market.getProductList().size() == 0) {
//                                    System.out.println("\nMarkete mehsul elave olunmayib.\n");
//                                } else {
//                                    GetProductsByPriceRange(market);
//                                }
//                                break;
//                            case 7:
//                                if (market.getProductList().size() == 0) {
//                                    System.out.println("\nMarkete mehsul elave olunmayib.\n");
//                                } else {
//                                    GetProductsByName(market);
//                                }
//                                break;
//                            case 8:
//                                continue label;
//                            case 0:
//                                System.out.println(ConsoleColors.RED + "\nSistemden cixdiniz" + ConsoleColors.RESET);
//                                check = false;
//                                break;
//                            default:
//                                System.out.println("\nZehmet olmasa duzgun secim edin");
//                                break;
//
//                        }
//                    }
//                    check = false;
//                    break;
//                case 2:
//                    System.out.println("\nSatislar sistemine xos gelmisiniz.\n");
//                    while (check) {
//                        do {
//                            System.out.println("1.Yeni satis elave etmek.");
//                            System.out.println("2.Satisdaki hansisa mehsulun geri qaytarilmasi.");
//                            System.out.println("3.Satisin silinmesi.");
//                            System.out.println("4.Butun satislarin ekrana cixarilmasi.");
//                            System.out.println("5.Verilen tarix araligina gore satislarin gosterilmesi.");
//                            System.out.println("6.Verilen mebleg araligina gore satislarin gosterilmesi .");
//                            System.out.println("7.Verilmis bir tarixde olan satislarin gosterilmesi.");
//                            System.out.println("8.Verilmis nomreye esasen hemin nomreli satisin melumatlarinin gosterilmesi.");
//                            System.out.println("9.Geriye kecid ucun.");
//                            System.out.println("0.Cixis.");
//
//                            try {
//                                System.out.print(ConsoleColors.BLUE + "Ededi daxil edin:" + ConsoleColors.RESET);
//                                Scanner scanner = new Scanner(System.in);
//                                choice = scanner.nextInt();
//
//                                break;
//                            } catch (Exception e) {
//                                System.out.println("\nNumber tipinde eded daxil edin.");
//                            }
//
//
//                        } while (true);
//
//                        switch (choice) {
//                            case 1:
//                                AddNewSales(market);
//                                break;
//                            case 2:
//                                if (market.getSalesList().size() == 0) {
//                                    System.out.println("\nSatis yoxdur.\n");
//                                } else {
//                                    RemoveSalesItemFromSale(market);
//                                }
//                                break;
//                            case 3:
//                                if (market.getSalesList().size() == 0) {
//                                    System.out.println("\nSatis yoxdur.\n");
//                                } else {
//                                    RemoveSales(market);
//                                }
//                                break;
//                            case 4:
//                                if (market.getSalesList().size() == 0) {
//                                    System.out.println("\nSatis yoxdur.\n");
//                                } else {
//                                    GetSalesList(market);
//                                }
//                                break;
//                            case 5:
//                                if (market.getSalesList().size() == 0) {
//                                    System.out.println("\nSatis yoxdur.\n");
//                                } else {
//                                    GetSalesByToFromRange(market);
//                                }
//                                break;
//                            case 6:
//                                if (market.getSalesList().size() == 0) {
//                                    System.out.println("\nSatis yoxdur.\n");
//                                } else {
//                                    GetSalesByPriceRange(market);
//                                }
//                                break;
//                            case 7:
//                                if (market.getSalesList().size() == 0) {
//                                    System.out.println("\nSatis yoxdur.\n");
//                                } else {
//                                    GetSalesByDate(market);
//                                }
//                                break;
//                            case 8:
//                                if (market.getSalesList().size() == 0) {
//                                    System.out.println("\nSatis yoxdur.\n");
//                                } else {
//                                    GetSalesByCode(market);
//                                }
//                                break;
//                            case 9:
//                                continue label;
//                            case 0:
//                                System.out.println(ConsoleColors.RED + "\nSistemden cixdiniz" + ConsoleColors.RESET);
//
//                                check = false;
//                                break;
//                            default:
//                                System.out.println("\nZehmet olmasa duzgun secim edin");
//                                break;
//
//                        }
//                    }
//                    check = false;
//                    break;
//                case 0:
//                    System.out.println(ConsoleColors.RED + "\nSistemden cixdiniz" + ConsoleColors.RESET);
//                    check = false;
//                    break;
//                default:
//                    System.out.println(ConsoleColors.RED + "Zehmet olmasa duzgun secim edin" + ConsoleColors.RESET);
//                    break;
//            }
//
//
//        } while (check);
//    }
}
