public class DemoCoffeeMachine {
    public DemoCoffeeMachine() {
    }

    public static void main(String[] args) {
        CoffeeMachine cm = new CoffeeMachine();
        cm.greeting();

        try {
            Thread.sleep(3000L);
        } catch (InterruptedException var4) {
            var4.printStackTrace();
        }

        cm.showMenu();
        if (!cm.depositMoney() || !cm.buyDrink()) {
            cm.exit();

            try {
                Thread.sleep(3000L);
            } catch (InterruptedException var3) {
                var3.printStackTrace();
            }

            for(int i = 0; i < 100; ++i) {
                System.out.println();
            }

        }
    }
}
