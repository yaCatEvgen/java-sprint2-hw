public class Checker {
    public MonthlyReport monthlyReport;
    public YearlyReport yearlyReport;

    public Checker(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
    }

    public boolean check() {
        boolean check = false;
        int[] profitMonth = monthlyReport.profitOrExpense();
        int[] profitYear = yearlyReport.profitOrExpense();

        if ((profitMonth[0] == profitYear[0]) && (profitMonth[1] == profitYear[1])) {
            System.out.println("Поздравляем, всё сошлось");
            check = true;
        } else if (profitMonth[0] == profitYear[0]) {
            System.out.println("доходы не сходятся");
            check = false;
        } else if (profitMonth[1] == profitYear[1]) {
            System.out.println("расходы не сходятся");
            check = false;
        } else {
            System.out.println("Ошибка! Ни расход, ни доход не сходятся.");
            check = false;
        }
        return check;
    }
}
