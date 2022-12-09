import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class YearlyReport {
    public ArrayList<YearlyItem> yearlyItems = new ArrayList<>();
    int[] sumProfitExpense = new int[2];

    public YearlyReport(String path) {
        String allStrOfFile = readFileContentsOrNull(path);

        if (!(allStrOfFile == null)) {
            String[] lines = allStrOfFile.split("\r?\n");

            for (int i = 1; i < lines.length; i++) {
                String line = lines[i]; //month,amount,is_expense
                String[] parts = line.split(",");
                String month = parts[0];
                int amount = Integer.parseInt(parts[1]);
                boolean isExpense = Boolean.parseBoolean(parts[2]);

                YearlyItem year = new YearlyItem(month, amount, isExpense);
                yearlyItems.add(year);
            }
        } else {
            System.out.println("null point");
        }
    }

    public void printStatisticYerly() {
        for (YearlyItem year : yearlyItems) {
            if (year.isExpense)
                System.out.println("Расходы за месяц №" + year.month + " - " + year.amount);
            else
                System.out.println("Доходы за месяц №" + year.month + " - " + year.amount);
        }
    }

    public int[] profitOrExpense() {

        int sumProfit = 0;
        int sumExpense = 0;

        for (int i = 0; i < yearlyItems.size(); i++) {
            if(yearlyItems.get(i).isExpense) {
                sumExpense += yearlyItems.get(i).amount;
            } else {
                sumProfit += yearlyItems.get(i).amount;
            }
        }
        sumProfitExpense[0] = sumProfit;
        sumProfitExpense[1] = sumExpense;
        return sumProfitExpense;
    }

    public String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }
}
