package UserMethods;

import BaseClasses.ConsoleColors;
import MainMarket.Market;
import Models.Role;
import Models.User;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Inputs.IntegerInput.getRole;
import static Inputs.StringInput.getUsername;
import static SalesMethods.SaleCrud.getSalesPriceOrExchange;

public class UserCRUD {
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    public static void addUser(Market market) {

        int counter = 0;
        boolean check = true;


        do {

            if (counter != 0) {
                check = getSalesPriceOrExchange("User elave etmeye davam etmek issteyirsinizmi?(y/n)");
            }

            if (check) {
                String username;
                do {
                    System.out.print(ConsoleColors.BLUE + "Istifadecinin nameni daxil edin:" + ConsoleColors.RESET);
                    Scanner scanner = new Scanner(System.in);

                    username = scanner.nextLine();
                    if (market.getUserByUsername(username) != null) {
                        System.out.println("Bu istifadeci adi artiq var.");
                    }

                } while (username.length() == 0 || market.getUserByUsername(username) != null);

                String password;
                do {
                    System.out.print(ConsoleColors.BLUE + "Istifadecinin parolunu daxil edin:" + ConsoleColors.RESET);
                    Scanner scanner = new Scanner(System.in);

                    password = scanner.nextLine();
                    if (!isValid(password, PASSWORD_PATTERN)) {
                        System.out.println(ConsoleColors.RED + "1.Parolda hec olmasa 1 reqem olmalidir.");
                        System.out.println(ConsoleColors.RED + "2.Parolda hec olmasa 1 kicik Latin herfi olmalidir.");
                        System.out.println(ConsoleColors.RED + "3.Parolda hec olmasa 1 boyuk Latin herfi olmalidir.");
                        System.out.println(ConsoleColors.RED + "4.Parolda hec olmasa simvol olmalidir.Mes(! @ # & ( )).");
                        System.out.println(ConsoleColors.RED + "5.Parolun minimal uzunlugu 8 ve maximal uzunlugu 20 olmalidir.");
                        System.out.println(ConsoleColors.CYAN + "Asan olsun deye asagidaki parollari da sece bilersiz:" + ConsoleColors.RESET);
                        for (var item : validPasswordProvider().collect(Collectors.toList())) {
                            System.out.println(ConsoleColors.WHITE_BRIGHT + item);
                        }
                    }

                } while (!isValid(password, PASSWORD_PATTERN));

                Role role = getRole();


                User user = new User(username, password, role);
                market.addUser(user);
                System.out.println(ConsoleColors.CYAN + "Istifadeci elave olundu" + ConsoleColors.RESET);
            }
            counter++;
        } while (check);


    }

    public static boolean isValid(String password, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    static Stream<String> validPasswordProvider() {
        return Stream.of(
                "AAAbbbccc@123",
                "1111aaaA@",
                "123Aa$Aa"
        );
    }

    public static User loginUser(Market market) {
        int tryPass = 0;
        boolean wrongPass = true;

        String username = getUsername(market);
        User user = market.getUserByUsername(username);
        String password;
        do {
            tryPass++;
            System.out.print(ConsoleColors.BLUE + "Istifadecinin sifresini daxil edin:" + ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);

            password = scanner.nextLine();
            if (!user.password.equals(password)) {
                System.out.println(ConsoleColors.BLUE + "Istifadecinin sifresi yanlisdir.Qalan cehdlerin sayi:" + ConsoleColors.RED_UNDERLINED + (3 - tryPass));
            }
            if (tryPass == 3 && !user.password.equals(password)) {
                System.out.println(ConsoleColors.RED + "3 defe yanlis cehd elediyinz ucun sistemden sizi atmaq mecburiyyetindeyik" + ConsoleColors.RESET);
                wrongPass = false;
            }

        } while (tryPass != 3 && (password.length() == 0 || !user.password.equals(password)));

        return wrongPass ? user : null;

    }

    public static void getUserList(Market market) {
        System.out.println(ConsoleColors.PURPLE_BRIGHT + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Userlerin listi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ConsoleColors.RESET);
        for (var item : market.getUserList().values()) {
            System.out.println(ConsoleColors.WHITE_BRIGHT + "Username:" + item.username + " Role:" + item.role.name() + ConsoleColors.RESET);
        }
        System.out.println(ConsoleColors.PURPLE_BRIGHT + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ConsoleColors.RESET);

    }

    public static void getUserByUsername(Market market) {
        String username = getUsername(market);

        User user = market.getUserByUsername(username);

        System.out.println(ConsoleColors.WHITE_BRIGHT + "Username:" + user.username + " Role:" + user.role.name() + ConsoleColors.RESET);


    }


    public static void removeUser(Market market) {
        String username = getUsername(market);
        market.removeUser(username);
    }

    public static void updateUser(String adminUsername,Market market) {
        String username = getUsername(market);
        if (!username.equals(adminUsername)){
            int choice;
            do {

                System.out.println(ConsoleColors.YELLOW_BRIGHT + "1.Usernameni deyismek.");
                System.out.println("2.Rolunu deyismek.");
                System.out.println("3.Parolunu deyismek.");
                System.out.println("4.Her ucunu deyismek.");

                try {
                    System.out.print(ConsoleColors.BLUE + "Ededi daxil edin:" + ConsoleColors.RESET);
                    Scanner scanner = new Scanner(System.in);
                    choice = scanner.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println(ConsoleColors.RED + "\nNumber tipinde eded daxil edin." + ConsoleColors.RESET);
                }

            } while (true);
            switchCase4UpdateUser(choice, username,market);
        }else{
            System.out.println(ConsoleColors.RED_BOLD+"Admin,oz uzerinde deyisiklik ede bilmersen"+ConsoleColors.RESET);
        }


    }

    public static void switchCase4UpdateUser(int choice, String username,Market market) {
        switch (choice) {
            case 1:

                updateUsername(username,market);
                break;
            case 2:
                updateRole(username,market);
                break;
            case 3:
                updatePassword(username,market);
                break;
            case 4:
                updateUsernameRoleAndPass(username,market);
                break;
        }
    }

    private static void updatePassword(String username, Market market) {
        User user=market.getUserByUsername(username);
        String password;
        do {
            System.out.print(ConsoleColors.BLUE + "Istifadecinin hazirki sifresini daxil edin:" + ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);

            password = scanner.nextLine();
            if (!user.password.equals(password)) {
                System.out.println(ConsoleColors.BLUE + "Istifadecinin sifresi yanlisdir");
            }

        } while (password.length() == 0 || !user.password.equals(password));

        String newPassword;
        do {
            System.out.print(ConsoleColors.BLUE + "Istifadecinin yeni parolunu daxil edin:" + ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);

            newPassword = scanner.nextLine();
            if (!isValid(newPassword, PASSWORD_PATTERN)) {
                System.out.println(ConsoleColors.RED + "1.Parolda hec olmasa 1 reqem olmalidir.");
                System.out.println(ConsoleColors.RED + "2.Parolda hec olmasa 1 kicik Latin herfi olmalidir.");
                System.out.println(ConsoleColors.RED + "3.Parolda hec olmasa 1 boyuk Latin herfi olmalidir.");
                System.out.println(ConsoleColors.RED + "4.Parolda hec olmasa simvol olmalidir.Mes(! @ # & ( )).");
                System.out.println(ConsoleColors.RED + "5.Parolun minimal uzunlugu 8 ve maximal uzunlugu 20 olmalidir.");
                System.out.println(ConsoleColors.CYAN + "Asan olsun deye asagidaki parollari da sece bilersiz:" + ConsoleColors.RESET);
                for (var item : validPasswordProvider().collect(Collectors.toList())) {
                    System.out.println(ConsoleColors.WHITE_BRIGHT + item);
                }
            }

        } while (!isValid(newPassword, PASSWORD_PATTERN));
        user.password=newPassword;
        System.out.println(ConsoleColors.CYAN+"Istifadecinin passwordu deyisdi."+ConsoleColors.RESET);
        market.updateUser(username,user.username,user.role,user.password);

    }

    private static void updateUsernameRoleAndPass(String username, Market market) {
        User user = market.getUserByUsername(username);

        Role role=getRole();
        String newUsername;
        do {
            System.out.print(ConsoleColors.BLUE + "Istifadecinin yeni nameni daxil edin:" + ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);

            newUsername = scanner.nextLine();
            if (market.getUserByUsername(newUsername) != null) {
                System.out.println(ConsoleColors.RED+"Bu istifadeci adi artiq var."+ConsoleColors.RESET);
            }

        } while (newUsername.length() == 0 || market.getUserByUsername(newUsername) != null);

        String password;
        do {
            System.out.print(ConsoleColors.BLUE + "Istifadecinin hazirki sifresini daxil edin:" + ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);

            password = scanner.nextLine();
            if (!user.password.equals(password)) {
                System.out.println(ConsoleColors.BLUE + "Istifadecinin sifresi yanlisdir.");
            }

        } while (password.length() == 0 || !user.password.equals(password));

        String newPassword;
        do {
            System.out.print(ConsoleColors.BLUE + "Istifadecinin yeni parolunu daxil edin:" + ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);

            newPassword = scanner.nextLine();
            if (!isValid(newPassword, PASSWORD_PATTERN)) {
                System.out.println(ConsoleColors.RED + "1.Parolda hec olmasa 1 reqem olmalidir.");
                System.out.println(ConsoleColors.RED + "2.Parolda hec olmasa 1 kicik Latin herfi olmalidir.");
                System.out.println(ConsoleColors.RED + "3.Parolda hec olmasa 1 boyuk Latin herfi olmalidir.");
                System.out.println(ConsoleColors.RED + "4.Parolda hec olmasa simvol olmalidir.Mes(! @ # & ( )).");
                System.out.println(ConsoleColors.RED + "5.Parolun minimal uzunlugu 8 ve maximal uzunlugu 20 olmalidir.");
                System.out.println(ConsoleColors.CYAN + "Asan olsun deye asagidaki parollari da sece bilersiz:" + ConsoleColors.RESET);
                for (var item : validPasswordProvider().collect(Collectors.toList())) {
                    System.out.println(ConsoleColors.WHITE_BRIGHT + item);
                }
            }

        } while (!isValid(newPassword, PASSWORD_PATTERN));

        user.password=newPassword;
        user.username=newUsername;
        user.role=role;
        market.updateUser(username,user.username,user.role,user.password);
    }

    private static void updateRole(String username,Market market) {
        Role role = getRole();
        User user = market.getUserByUsername(username);
        user.role=role;
        System.out.println(ConsoleColors.CYAN+"Istifadecinin rolu deyisdi."+ConsoleColors.RESET);
        market.updateUser(username,user.username,user.role,user.password);

    }

    private static void updateUsername(String username,Market market) {
        String newUsername;
        do {
            System.out.print(ConsoleColors.BLUE + "Istifadecinin yeni nameni daxil edin:" + ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);

            newUsername = scanner.nextLine();
            if (market.getUserByUsername(newUsername) != null) {
                System.out.println(ConsoleColors.RED+"Bu istifadeci adi artiq var."+ConsoleColors.RESET);
            }

        } while (newUsername.length() == 0 || market.getUserByUsername(newUsername) != null);

        User user = market.getUserByUsername(username);
        user.username=newUsername;
        market.updateUser(username,newUsername,user.role,user.password);
        System.out.println(ConsoleColors.CYAN+"Istifadecinin adi deyisdi."+ConsoleColors.RESET);

    }

}
