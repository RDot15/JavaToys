import java.io.FileWriter;
import java.util.Scanner;
import java.io.*;


public class UserView implements View {
    private ControllerToys controller;

    @Override
    public int promptInt(String msg) {
        Scanner in = new Scanner(System.in);
        int key = -1;
        try {
            System.out.print(msg);
            key = in.nextInt();
        } catch (Exception ex) {
            System.out.println(" Вводите только целый числа");

        }
        return key;
    }

    @Override
    public String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    @Override
    public void run() {
        Shop shop = new Shop();
        shop.addProduct(new Toys("Bear", 2, 564));
        shop.addProduct(new Toys("Kitten", 3, 23));
        shop.addProduct(new Toys("Dog", 4, 237));
        System.out.println("\033\143");
        boolean flag = true;
        while (flag) {

            System.out.println("\nМеню: \n1. Добавить товар в магазин \n2. Добавить товар в ЛОТ \n" +
                    "3. Посмотреть список товаров \n4. Посмотреть список ЛОТов\n5. Запустить! )\n6. Выход\n");


            int key = promptInt("Введите: ");
            switch (key) {
                case 1:
                    String[] arr = new String[] { " имя ", " количество ", " вес " };
                    String[] temp = new String[3];
                    for (int i = 0; i < arr.length; i++) {
                        temp[i] = prompt("Введите" + arr[i] + ": ");}
                    shop.addProduct(new Toys(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
                    break;
                case 2:
                    System.out.println("\033\143" + "\n Добавить товар в ЛОТ");
                    for (Toys line : shop.getProductList()) {
                        System.out.println(line);
                    }
                    int k = promptInt("Введите ID: ");
                    shop.addLotteryProduct(k);
                    break;
                case 3:
                    System.out.println("\033\143" + "\n Посмотреть список товаров");
                    for (Toys line : shop.getProductList()) {
                        System.out.println(line);
                    }
                    break;
                case 4:
                    System.out.println("\033\143" + "\n Посмотреть список ЛОТов");
                    if (shop.getLotteryList().size() == 0) {
                        System.out.println("Пусто! Добавьте продукт!");
                    } else {
                        for (Toys line : shop.getLotteryList()) {
                            System.out.println(line);
                        }
                    }
                    break;
                case 5:
                    System.out.println("\033\143" + "\n Запустить!");
                    if (shop.getLotteryList().size() == 0) {
                        System.out.println("ЛОТ пуст. Добавьте товар!");
                    } else {
                        ControllerToys controller = new ControllerToys(shop.getLotteryList());
                        String text = "WINNER: " + controller.LotteryTime();
                        System.out.println(text);
                        try (FileWriter writer = new FileWriter("WinnerList.txt", true)) {
                            writer.write(text + "\n");
                        } catch (Exception ex) {
                            System.out.println("Exception" + ex);
                        }
                    }
                    break;
                case 6:
                    flag = false;
            }

        }
    }

}