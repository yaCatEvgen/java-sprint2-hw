public class Checker {
    public MonthlyReport monthlyReport;
    public YearlyReport yearlyReport;

    public Checker (MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
    }

    public boolean check() {
        boolean check = false;
        int[] newMasM = monthlyReport.podschetDohRas();
        int[] newMasY = yearlyReport.podschetDohRas();

        if((newMasM[0] == newMasY[0]) && (newMasM[1] == newMasY[1])) {
            System.out.println("поздравляем всё сошлось");
            check =  true;
        } else if (newMasM[0] == newMasY[0]) {
            System.out.println("доходы не сходяться");
            check =  false;
        } else if (newMasM[1] == newMasY[1]) {
            System.out.println("расходы не сходяться");
            check =  false;
        } else {
            System.out.println(" не сходиться ни расход ни доход");
            check =  false;
        }
        return check;


    }
}
