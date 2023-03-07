public enum ListOfDrinks {
    MILK("цена - 50, введите - <1>"),
    WATER("цена - 20, введите - <2>"),
    SODA("цена - 30, введите - <3>"),
    COFFEE("цена - 100, введите - <4>");

    String memo;

    private ListOfDrinks(String memo) {
        this.memo = memo;
    }
}
