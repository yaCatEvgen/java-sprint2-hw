import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MonthlyReport {

    public ArrayList<Monthly> monthlies = new ArrayList<>();

    int[] sumDohRas = new int[2];

        public void loadFile(String path) {
        String content = readFileContentsOrNull(path);
        String[] lines = content.split("\r?\n");

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i]; //item_name,is_expense,quantity,sum_of_one
            String[] parts =line.split(",");
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);

            Monthly monthly = new Monthly(itemName, isExpense, quantity, sumOfOne);
            monthlies.add(monthly);

        }

       }

       public void print() {
            for (Monthly monthly : monthlies) {
                System.out.println(monthly.itemName + " " + monthly.isExpense + " " + monthly.quantity + " " + monthly.sumOfOne);
            }

       }

       public int[] podschetDohRas() {

           int sumDohod = 0;
           int sumRashod = 0;

           for (int i = 0; i < monthlies.size(); i++) {
               if(monthlies.get(i).isExpense) {
                   sumRashod += (monthlies.get(i).quantity * monthlies.get(i).sumOfOne);
               } else {
                   sumDohod += (monthlies.get(i).quantity * monthlies.get(i).sumOfOne);
               }

           }
           sumDohRas[0] = sumDohod;
           sumDohRas[1] = sumRashod;
           return sumDohRas;

       }


    public String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            //return Collections.emptyList();
            return null;
        }
    }


}
