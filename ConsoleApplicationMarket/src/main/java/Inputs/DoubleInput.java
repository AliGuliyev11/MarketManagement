package Inputs;

import BaseClasses.ConsoleColors;

import java.util.Scanner;

public class DoubleInput {
    public static double getDoubleValue(String text, String exceptionText) {
        double price = 0;
        do {
            System.out.print(ConsoleColors.BLUE+text+ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);

            try {
                price = scanner.nextDouble();
                if (price<=0){
                    System.out.println(ConsoleColors.RED+"Qiymet 0-dan kicik ve ya 0-a beraber ola bilmez"+ConsoleColors.RESET);
                }else{
                    break;
                }
            } catch (Exception e) {
                System.out.println(ConsoleColors.RED+exceptionText+ConsoleColors.RESET);
            }


        } while (true);
        return price;
    }
    public static double getDoubleValue(double minPrice) {
        double maxPrice = 0;
        do {
            System.out.print(ConsoleColors.BLUE+"Sastisin maximum qiymetini daxil edin:"+ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);

            try {
                maxPrice = scanner.nextDouble();
                if (minPrice > maxPrice) {
                    System.out.println(ConsoleColors.RED+"Minimal qiymet maksimal qiymetden cox ola bilmez"+ConsoleColors.RESET);
                }else{
                    break;
                }
            } catch (Exception e) {
                System.out.println(ConsoleColors.RED+"Satisin qiymetini duzgun qeyd edin"+ConsoleColors.RESET);
            }


        } while (true);
        return maxPrice;
    }
}
