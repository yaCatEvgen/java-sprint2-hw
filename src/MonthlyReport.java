import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MonthlyReport {

    public ArrayList<Month> months = new ArrayList<>();

    int[] profitAndExpense = new int[2];

        public void loadFile(String path) {
        String content = readFileContentsOrNull(path);
        String[] lines = content.split("\r?\n");

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts =line.split(",");
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);

            Month month = new Month(itemName, isExpense, quantity, sumOfOne);
            months.add(month);
        }
       }

       public void printStatisticPerMonth() {
            for (Month month : months) {
                if (month.isExpense) {
                    System.out.println("доходы за товар:" + month.itemName + " в количестве  " + month.quantity + ", цена за штуку " + month.sumOfOne);
                } else {
                    System.out.println("расходы за товар:" + month.itemName + " в количестве - " + month.quantity + ", цена за штуку " + month.sumOfOne);
                }
            }
       }

       public int[] profitOrExpense() {

           int sumProfit = 0;
           int sumExpense = 0;

           for (int i = 0; i < months.size(); i++) {
               if(months.get(i).isExpense) {
                   sumExpense += (months.get(i).quantity * months.get(i).sumOfOne);
               } else {
                   sumProfit += (months.get(i).quantity * months.get(i).sumOfOne);
               }
           }
           profitAndExpense[0] = sumProfit;
           profitAndExpense[1] = sumExpense;
           return profitAndExpense;
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
