import BaseClasses.ConsoleColors;
import MainMarket.Market;
import Models.Role;
import Models.User;
import ProductMethods.ProductCrud;
import SalesMethods.SaleCrud;
import UserMethods.UserCRUD;

import java.util.Scanner;
import java.util.stream.Collectors;

import static ProductMethods.ProductCrud.*;
import static UserMethods.UserCRUD.*;

public class Menu {
    public static void Menu() {
        int choice;
        boolean check = true;
        Market market = new Market();
        User user = null;

        label:
        do {

            do {

                if (market.getUserList().size() == 0) {
                    System.out.println(ConsoleColors.CYAN + "Sisteme giris ucun istifadeci yoxdur.Ona gore de zehmet olmasa istifadeci daxil edin" + ConsoleColors.RESET);
                    addUser(market);

                    continue label;
                } else {
                    if (user == null) {
                        System.out.println(ConsoleColors.YELLOW_BRIGHT + "Zehmet olmasa login olun:" + ConsoleColors.RESET);
                        user = loginUser(market);
                        if (user == null) {
                            System.out.println(ConsoleColors.RED + "\nSistemden cixdiniz" + ConsoleColors.RESET);
                            System.exit(0);
                        }
                    }

                    System.out.println(ConsoleColors.YELLOW_BRIGHT + "1.Mehsullar uzerinde emeliyyat aparmaq.");
                    System.out.println("2.Satislar uzerinde emeliyyat aparmaq.");
                    System.out.println("3.User uzerinde emeliyyat aparmaq.");
                    System.out.println("4.Login penceresine kecid.");
                    System.out.println("0.Cixis." + ConsoleColors.RESET);

                }

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
                                System.out.println(ConsoleColors.RED + "\nNumber tipinde eded daxil edin." + ConsoleColors.RESET);
                            }


                        } while (true);

                        switch (choice) {
                            case 1:
                                if (user.role.equals(Role.Admin) || user.role.equals(Role.Manager)) {
                                    AddNewProduct(market);
                                } else {
                                    System.out.println(ConsoleColors.RED + "\nYalniz admin ve ya manager bu pencereye kecid ede biler.\n" + ConsoleColors.RESET);
                                }
                                break;
                            case 2:

                                if (user.role.equals(Role.Admin) || user.role.equals(Role.Manager)) {
                                    if (market.getProductList().size() == 0) {
                                        System.out.println(ConsoleColors.RED + "\nMarkete mehsul elave olunmayib.\n" + ConsoleColors.RESET);
                                    } else {
                                        EditProduct(market);
                                    }
                                } else {
                                    System.out.println(ConsoleColors.RED + "\nYalniz admin ve ya manager bu pencereye kecid ede biler.\n" + ConsoleColors.RESET);
                                }

                                break;
                            case 3:
                                if (user.role.equals(Role.Admin) || user.role.equals(Role.Manager)) {
                                    if (market.getProductList().size() == 0) {
                                        System.out.println(ConsoleColors.RED + "\nMarkete mehsul elave olunmayib.\n" + ConsoleColors.RESET);
                                    } else {
                                        ProductCrud.RemoveProduct(market);
                                    }
                                } else {
                                    System.out.println(ConsoleColors.RED + "\nYalniz admin ve ya manager bu pencereye kecid ede biler.\n" + ConsoleColors.RESET);
                                }

                                break;
                            case 4:
                                if (market.getProductList().size() == 0) {
                                    System.out.println(ConsoleColors.RED + "\nMarkete mehsul elave olunmayib.\n" + ConsoleColors.RESET);
                                } else {
                                    ProductCrud.GetProductList(market);
                                }
                                break;
                            case 5:
                                if (market.getProductList().size() == 0) {
                                    System.out.println(ConsoleColors.RED + "\nMarkete mehsul elave olunmayib.\n" + ConsoleColors.RESET);
                                } else {
                                    ProductCrud.GetProductsByCategory(market);
                                }
                                break;
                            case 6:
                                if (market.getProductList().size() == 0) {
                                    System.out.println(ConsoleColors.RED + "\nMarkete mehsul elave olunmayib.\n" + ConsoleColors.RESET);
                                } else {
                                    ProductCrud.GetProductsByPriceRange(market);
                                }
                                break;
                            case 7:
                                if (market.getProductList().size() == 0) {
                                    System.out.println(ConsoleColors.RED + "\nMarkete mehsul elave olunmayib.\n" + ConsoleColors.RESET);
                                } else {
                                    ProductCrud.GetProductsByName(market);
                                }
                                break;
                            case 8:
                                continue label;
                            case 0:
                                System.out.println(ConsoleColors.RED + "\nSistemden cixdiniz" + ConsoleColors.RESET);
                                check = false;
                                break;
                            default:
                                System.out.println(ConsoleColors.RED + "\nZehmet olmasa duzgun secim edin" + ConsoleColors.RESET);
                                break;

                        }
                    }
                    check = false;
                    break;
                case 2:
                    System.out.println(ConsoleColors.GREEN + "\nSatislar sistemine xos gelmisiniz.\n" + ConsoleColors.RESET);
                    while (check) {
                        do {
                            System.out.println(ConsoleColors.YELLOW_BRIGHT + "1.Yeni satis elave etmek.");
                            System.out.println("2.Satisdaki hansisa mehsulun geri qaytarilmasi.");
                            System.out.println("3.Satisin silinmesi.");
                            System.out.println("4.Butun satislarin ekrana cixarilmasi.");
                            System.out.println("5.Verilen tarix araligina gore satislarin gosterilmesi.");
                            System.out.println("6.Verilen mebleg araligina gore satislarin gosterilmesi .");
                            System.out.println("7.Verilmis bir tarixde olan satislarin gosterilmesi.");
                            System.out.println("8.Verilmis nomreye esasen hemin nomreli satisin melumatlarinin gosterilmesi.");
                            System.out.println("9.Geriye kecid ucun.");
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
                                SaleCrud.AddNewSales(market);
                                break;
                            case 2:
                                if (market.getSalesList().size() == 0) {
                                    System.out.println(ConsoleColors.RED + "\nSatis yoxdur.\n" + ConsoleColors.RESET);
                                } else {
                                    SaleCrud.RemoveSalesItemFromSale(market);
                                }
                                break;
                            case 3:
                                if (market.getSalesList().size() == 0) {
                                    System.out.println(ConsoleColors.RED + "\nSatis yoxdur.\n" + ConsoleColors.RESET);
                                } else {
                                    SaleCrud.RemoveSales(market);
                                }
                                break;
                            case 4:
                                if (market.getSalesList().size() == 0) {
                                    System.out.println(ConsoleColors.RED + "\nSatis yoxdur.\n" + ConsoleColors.RESET);
                                } else {
                                    SaleCrud.GetSalesList(market);
                                }
                                break;
                            case 5:
                                if (market.getSalesList().size() == 0) {
                                    System.out.println(ConsoleColors.RED + "\nSatis yoxdur.\n" + ConsoleColors.RESET);
                                } else {
                                    SaleCrud.GetSalesByToFromRange(market);
                                }
                                break;
                            case 6:
                                if (market.getSalesList().size() == 0) {
                                    System.out.println(ConsoleColors.RED + "\nSatis yoxdur.\n" + ConsoleColors.RESET);
                                } else {
                                    SaleCrud.GetSalesByPriceRange(market);
                                }
                                break;
                            case 7:
                                if (market.getSalesList().size() == 0) {
                                    System.out.println(ConsoleColors.RED + "\nSatis yoxdur.\n" + ConsoleColors.RESET);
                                } else {
                                    SaleCrud.GetSalesByDate(market);
                                }
                                break;
                            case 8:
                                if (market.getSalesList().size() == 0) {
                                    System.out.println(ConsoleColors.RED + "\nSatis yoxdur.\n" + ConsoleColors.RESET);
                                } else {
                                    SaleCrud.GetSalesByCode(market);
                                }
                                break;
                            case 9:
                                continue label;
                            case 0:
                                System.out.println(ConsoleColors.RED + "\nSistemden cixdiniz" + ConsoleColors.RESET);

                                check = false;
                                break;
                            default:
                                System.out.println(ConsoleColors.RED + "\nZehmet olmasa duzgun secim edin" + ConsoleColors.RESET);
                                break;

                        }
                    }
                    check = false;
                    break;
                case 3:
                    if (user.role.equals(Role.Admin)) {
                        System.out.println(ConsoleColors.GREEN + "\nUserler sistemine xos gelmisiniz.\n" + ConsoleColors.RESET);

                        if (market.getProductList().size()==0){
                            System.out.println(ConsoleColors.RED+"Marketde mehsul yoxdur.Get ve elave ele."+ConsoleColors.RESET);
                        }else{
                            if (market.getProductList().values().stream().filter(a->a.amount==0).collect(Collectors.toList()).size()!=0){
                                getProducts(market.getProductList().values().stream().filter(a->a.amount==0).collect(Collectors.toList()), "Stokda biten mallar");
                            }
                        }


                        while (check) {
                            do {
                                System.out.println(ConsoleColors.YELLOW_BRIGHT + "1.Yeni user elave etmek.");
                                System.out.println("2.Userin silinmesi.");
                                System.out.println("3.Butun userleri ekrana cixarilmasi.");
                                System.out.println("4.Verilmis username esasen hemin usernameli userin melumatlarinin gosterilmesi.");
                                System.out.println("5.User uzerinde deyisiklik.");
                                System.out.println("6.Geriye kecid ucun.");
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
                                    UserCRUD.addUser(market);
                                    break;
                                case 2:
                                    UserCRUD.removeUser(market);
                                case 3:
                                    UserCRUD.getUserList(market);
                                    break;
                                case 4:
                                        UserCRUD.getUserByUsername(market);
                                    break;
                                case 5:
                                    UserCRUD.updateUser(user.username,market);
                                case 6:
                                    continue label;
                                case 0:
                                    System.out.println(ConsoleColors.RED + "\nSistemden cixdiniz" + ConsoleColors.RESET);
                                    check = false;
                                    break;
                                default:
                                    System.out.println(ConsoleColors.RED + "\nZehmet olmasa duzgun secim edin" + ConsoleColors.RESET);
                                    break;

                            }
                        }
                    } else {
                        System.out.println(ConsoleColors.RED + "\nAncaq adminler daxil ola biler" + ConsoleColors.RESET);
                        continue label;
                    }


                    check = false;
                    break;
                case 4:
                    user = null;
                    continue label;
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
}
