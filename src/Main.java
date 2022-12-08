import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userInput = -1;
        MonthlyReport monthlyReport = null;
        YearlyReport yearlyReport = null;

        while (userInput != 0) {
            System.out.println("1 - Считать все месячные отчёты\n" + "2 - Считать годовой отчёт\n" + "3 - Сверить отчёты\n" + "4 - Вывести информацию о всех месячных отчётах\n" + "5 - Вывести информацию о годовом отчёте");
            userInput = scanner.nextInt();
            if (userInput == 1) {
                monthlyReport = new MonthlyReport();
                for (int i = 1; i < 4; i++) {
                    monthlyReport.loadFile("resources/m.20210" + i + ".csv");
                }
                System.out.println("все месячные отчёты cформированы");
            } else if (userInput == 2) {
                yearlyReport = new YearlyReport("resources/y.2021.csv");
                System.out.println("годовой отчёт cформирован");
            } else if (userInput == 3) {
                if (monthlyReport.equals(null) && yearlyReport.equals(null)) {
                    System.out.println("Объекта нет! null");
                } else {
                    Checker checker = new Checker(monthlyReport, yearlyReport);
                    System.out.println(checker.check());
                }
            } else if (userInput == 4) {
                if (monthlyReport.equals(null)) {
                    System.out.println("Объекта нет! null");
                } else {
                    monthlyReport.printStatisticPerMonth();
                }
            } else if (userInput == 5) {
                if (monthlyReport.equals(null)) {
                    System.out.println("Объекта нет! null");
                }
                yearlyReport.printStatisticYerly();
            } else if (userInput == 0) {
                break;
            } else {
                System.out.println("Такой команды нет, введите 0 для завершения");
            }
        }
    }
}

