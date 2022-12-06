import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class YearlyReport {
    public ArrayList<Yerly> yerlyArrayList = new ArrayList<>();
    int[] sumDohRas = new int[2];

    public YearlyReport(String path) {
        String allStrOfFile = readFileContentsOrNull(path);
        String[] lines = allStrOfFile.split("\r?\n");

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i]; //month,amount,is_expense
            String[] parts =line.split(",");
            String month = parts[0];
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            Yerly yerly = new Yerly(month, amount, isExpense);
            yerlyArrayList.add(yerly);

        }

    }

    public void print() {
        for (Yerly yerly : yerlyArrayList) {
            System.out.println(yerly.month + " " + yerly.amount + " " + yerly.isExpense);
        }
    }

    public int[] podschetDohRas() {

        int sumDohod = 0;
        int sumRashod = 0;

        for (int i = 0; i < yerlyArrayList.size(); i++) {
            if(yerlyArrayList.get(i).isExpense) {
                sumRashod += yerlyArrayList.get(i).amount;
            } else {
                sumDohod += yerlyArrayList.get(i).amount;
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
