package Inputs;

import BaseClasses.ConsoleColors;

import java.time.LocalDate;
import java.util.Scanner;

public class LocalDateInput {
    public static LocalDate getLocalDate() {
        LocalDate date;
        do {
            System.out.print(ConsoleColors.BLUE+"Zehmet olmasa tarixi teqdim edin((yyyy-mm-dd)):"+ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);

            try {
                date = LocalDate.parse(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println(ConsoleColors.RED+"Tarixi duzgun teyin edin.(yyyy-mm-dd)"+ConsoleColors.RESET);
            }


        } while (true);
        return date;
    }

    public static LocalDate getLocalDate(LocalDate from) {
        LocalDate to=null;
        do{
            System.out.print(ConsoleColors.BLUE+"Zehmet olmasa son tarixi teqdim edin((yyyy-mm-dd)):"+ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);

            try {
                to = LocalDate.parse(scanner.nextLine());
                if (from.isAfter(to)) {
                    System.out.println(ConsoleColors.RED+"Son tarix baslangic tarixden evvel ola bilmez"+ConsoleColors.RESET);
                }
                break;
            } catch (Exception e) {
                System.out.println(ConsoleColors.RED+"Tarixi duzgun teyin edin.(yyyy-mm-dd)"+ConsoleColors.RESET);
            }


        } while (true || from.isAfter(to));
        return to;
    }
}
