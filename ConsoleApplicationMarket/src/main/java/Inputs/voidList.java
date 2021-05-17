package Inputs;

import BaseClasses.ConsoleColors;
import MainMarket.Market;
import Models.Sales;

import java.util.List;

import static Inputs.StringInput.getSalesCode;

public class voidList {
    public static void getSalesList(List<Sales> sales,String text) {
        System.out.println(ConsoleColors.PURPLE_BRIGHT+text+ConsoleColors.RESET);
        for (var item : sales) {
            System.out.println(ConsoleColors.WHITE_BRIGHT+"\nSatisi nomresi:" + item.no + "\nSatisin umumi qiymeti:" + item.price + "\nSatisin tarixi:" + item.date);
            for (var salesItems : item.salesItem) {
                System.out.println("\nMehsulun adi:" + salesItems.product.name + "\nMehsulun sayi:" + salesItems.amount);
            }
            System.out.println(ConsoleColors.RESET+ConsoleColors.PURPLE_BRIGHT+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+ConsoleColors.RESET);

        }


    }

    public static void getSalesByCode(Sales sales) {
        System.out.println(ConsoleColors.PURPLE_BRIGHT+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ " + sales.no + " nomreli satisin siyahisi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE_BRIGHT+"\nSatisi nomresi:" + sales.no + "\nSatisin umumi qiymeti:" + sales.price + "\nSatisin tarixi:" + sales.date);
        for (var salesItems : sales.salesItem) {
            System.out.println("\nMehsulun adi:" + salesItems.product.name + "\nMehsulun sayi:" + salesItems.amount+ConsoleColors.RESET);
        }
        System.out.println(ConsoleColors.PURPLE_BRIGHT+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+ConsoleColors.RESET);
    }
}
