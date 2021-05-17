package Inputs;

import BaseClasses.ConsoleColors;
import Models.Category;
import Models.Product;
import Models.Role;
import Models.SalesItem;

import java.util.Scanner;

public class IntegerInput {
    public static int getCategory() {
        int category;
        int i;
        do {
            i = 1;
            for (var item : Category.values()) {
                System.out.println(ConsoleColors.BLUE+i + "." + item.name()+ConsoleColors.RESET);
                i++;
            }
            System.out.print(ConsoleColors.BLUE+"Productun kateqoriyasini daxil edin:"+ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);
            try {
                category = scanner.nextInt();

                if (Category.values()[category - 1] == null) {
                    System.out.println(ConsoleColors.RED+"Productun kateqoriyasini duzgun qeyd edin"+ConsoleColors.RESET);
                }
                break;
            } catch (Exception e) {
                System.out.println(ConsoleColors.RED+"Productun kateqoriyasini duzgun qeyd edin"+ConsoleColors.RESET);
            }


        } while (true);

        return category;
    }
    public static Role getRole() {
        int role;
        int i;
        do {
            i = 1;
            for (var item : Role.values()) {
                System.out.println(ConsoleColors.BLUE+i + "." + item.name()+ConsoleColors.RESET);
                i++;
            }
            System.out.print(ConsoleColors.BLUE+"Istifadecinin rolunu daxil edin:"+ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);
            try {
                role = scanner.nextInt();

                if (Role.values()[role - 1] == null) {
                    System.out.println(ConsoleColors.RED+"Istifadecinin rolunu duzgun qeyd edin"+ConsoleColors.RESET);
                }
                break;
            } catch (Exception e) {
                System.out.println(ConsoleColors.RED+"Istifadecinin rolunu duzgun qeyd edin"+ConsoleColors.RESET);
            }


        } while (true);

        return Role.values()[role-1];
    }


    public static int getIntegerValue(String text,String exceptionText){
        int amount;
        do {
            System.out.print(ConsoleColors.BLUE+text+ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);

            try {
                amount = scanner.nextInt();
                if (amount<=0){
                    System.out.println(ConsoleColors.RED+"Say menfi ve ya 0 ola bilmez"+ConsoleColors.RESET);
                }else{
                    break;
                }
            } catch (Exception e) {
                System.out.println(ConsoleColors.RED+exceptionText+ConsoleColors.RESET);
            }

        } while (true);
        return amount;
    }
    public static int getAmountInSale(Product product){
        int amount;
        do {
            System.out.print(ConsoleColors.BLUE+"Productun sayini daxil edin:"+ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);
                try {
                    amount = scanner.nextInt();
                    if (product.amount < amount) {
                        System.out.println(ConsoleColors.BLUE+"Mehsuldan daxil etdiyiniz say qeder yoxdur" + " daxil ede bileceyiniz maksimum say " +ConsoleColors.RED_UNDERLINED+product.amount+ConsoleColors.RESET);
                    }else{
                        break;
                    }
                } catch (Exception e) {
                    System.out.println(ConsoleColors.RED+"Productun sayini duzgun qeyd edin"+ConsoleColors.RESET);
                }


        } while (true);
        return amount;
    }
    public static int getAmountInSale(SalesItem salesItem){
        int amount = 0;

        do {
            System.out.print(ConsoleColors.BLUE+"Productun sayini daxil edin:"+ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);
                try {
                    amount = scanner.nextInt();
                    if (salesItem.amount < amount) {
                        System.out.println(ConsoleColors.BLUE+"Mehsuldan daxil etdiyiniz say qeder yoxdur" + " daxil ede bileceyiniz maksimum say " +ConsoleColors.RED_UNDERLINED+ salesItem.amount+ConsoleColors.RESET);
                    }else{
                        break;
                    }
                } catch (Exception e) {
                    System.out.println(ConsoleColors.RED+"Productun sayini duzgun qeyd edin"+ConsoleColors.RESET);
                }
        } while (true);
        return amount;
    }
}
