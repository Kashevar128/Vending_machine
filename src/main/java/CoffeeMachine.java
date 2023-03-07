import java.util.InputMismatchException;
import java.util.Scanner;

public class CoffeeMachine {
    private static int moneyAmount;
    private static int[] numberCommand;
    private static int[] numberSpecialCommand;
    private static int[] drinkPrices;
    private static String com_1 = "Ввести сумму";
    private static String com_2 = "Ввести комманду";

    CoffeeMachine() {
        moneyAmount = 0;
        numberCommand = new int[]{1, 2, 3, 4};
        numberSpecialCommand = new int[]{0, 777};
        drinkPrices = new int[]{50, 20, 30, 100};
    }

    public static int getMoneyAmount() {
        return moneyAmount;
    }

    public void exit() {
        int change = getMoneyAmount();
        System.out.println("\nВаша сдача: " + change + "\nСпасибо, что воспользовались нашими услугами!");
    }

    public static void memos() {
        ListOfDrinks[] var0 = ListOfDrinks.values();
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            ListOfDrinks drink = var0[var2];
            System.out.print("" + drink + ": " + drink.memo + " //");
        }

        System.out.println("Выход - <0> // Пополнить счет - <777>");
    }

    public static int run(ListOfDrinks drink, int money, int prices) {
        int change = money - prices;
        if (change < 0) {
            change = money;
            System.out.println("\nНедостаточно средств на счете!");
            System.out.println("Остаток средств: " + money);
        } else {
            System.out.println("\nВы купили: " + drink + "\nОстаток средств: " + change);
            System.out.println("Хотите что-то еще? Введите номер напитка или введите <0> для выхода из режима выдачи продукта и получения сдачи.\nДля того чтобы пополнить сумму введите <777>.\n");
        }

        memos();
        return change;
    }

    public static int input(int num, String com) {
        boolean flag = false;

        while(!flag) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("\n" + com + ": ");
                num = Math.abs(scanner.nextInt());
                System.out.print("----------------------------------------------");
                flag = true;
            } catch (InputMismatchException var4) {
                System.out.println("\nНеверный формат данных, можно вводить только цифры, \nпопробуйте еще раз.\n");
            }
        }

        return num;
    }

    public static ListOfDrinks interpreter(int num) {
        int count = 0;
        ListOfDrinks[] var2 = ListOfDrinks.values();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            ListOfDrinks drink = var2[var4];
            if (num == numberCommand[count]) {
                return drink;
            }

            ++count;
        }

        System.out.println("\nНеопознонная команда, попробуйте еще раз.");
        System.out.println();
        memos();
        return null;
    }

    public void greeting() {
        System.out.println();
        System.out.println("Здравствуйте! Добро пожаловать! Для приобретения желаемого продукта внесите требуемую сумму.");
        System.out.println();
    }

    public void showMenu() {
        System.out.println("ПРЕЙСКУРАНТ:");
        System.out.println();
        int count = 0;
        ListOfDrinks[] var2 = ListOfDrinks.values();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            ListOfDrinks drink = var2[var4];
            System.out.println("Напиток: " + drink + "\nСтоимость: " + drinkPrices[count] + "\nЧтобы купить, нажмите: " + numberCommand[count]);
            System.out.println();
            ++count;
        }

    }

    public boolean depositMoney() {
        System.out.println("Введите сумму, которую хотите положить на\nсвой депозит, для выхода из режима покупки введите <0>");
        moneyAmount = input(moneyAmount, com_1);
        if (moneyAmount == numberSpecialCommand[0]) {
            return false;
        } else {
            System.out.println("\nВы внесли: " + moneyAmount);
            return true;
        }
    }

    public boolean buyDrink() {
        int num = 0;
        System.out.println("Введите номер напитка, который хотите приобрести или\nвведите команду <777> для пополнения своего депозита.\nДля выхода из режима покупки введите команду <0>");

        while(true) {
            while(true) {
                try {
                    while(true) {
                        num = input(num, com_2);
                        if (num == numberSpecialCommand[0]) {
                            return false;
                        }

                        if (num != numberSpecialCommand[1]) {
                            switch (interpreter(num)) {
                                case MILK:
                                    moneyAmount = run(ListOfDrinks.MILK, moneyAmount, drinkPrices[0]);
                                    break;
                                case WATER:
                                    moneyAmount = run(ListOfDrinks.WATER, moneyAmount, drinkPrices[1]);
                                    break;
                                case SODA:
                                    moneyAmount = run(ListOfDrinks.SODA, moneyAmount, drinkPrices[2]);
                                    break;
                                case COFFEE:
                                    moneyAmount = run(ListOfDrinks.COFFEE, moneyAmount, drinkPrices[3]);
                            }
                        } else {
                            moneyAmount += input(moneyAmount, com_1);
                            System.out.println("\nВаш баланс: " + moneyAmount + "\n");
                            memos();
                        }
                    }
                } catch (NullPointerException var3) {
                }
            }
        }
    }
}
