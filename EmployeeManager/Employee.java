public class Employee {
    private String name;
    private double salary;
    private int workHours;
    private int hireYear;

    public final double MIN_TAX_SALARY = 1000;
    public final double TAX_RATE = .03;

    public final int MIN_WORK_HOURS = 40;
    public final double WORK_HOUR_BONUS = 30;

    public final int[] MIN_WORK_YEAR_LIMIT_FOR_RAISE_ARRAY = {10, 20};
    public final double[] RAISE_RATE_ARRAY = {.05, .1, .15};

    public final int CURRENT_YEAR = 2021;

    public Employee(String name, double salary, int workHours, int hireYear) {
        this.name = name;
        this.salary = salary;
        this.workHours = workHours;
        this.hireYear = hireYear;
    }

    public double tax() {
        if (salary >= MIN_TAX_SALARY) {
            return salary * TAX_RATE;
        }

        return 0;
    }

    public double bonus() {
        if (workHours > MIN_WORK_HOURS) {
            return (workHours - MIN_WORK_HOURS) * WORK_HOUR_BONUS;
        }

        return 0;
    }

    public double raiseSalary() {
        int yearDiff = CURRENT_YEAR - hireYear;

        for (int i = 0; i < MIN_WORK_YEAR_LIMIT_FOR_RAISE_ARRAY.length; i++) {
            if (yearDiff < MIN_WORK_YEAR_LIMIT_FOR_RAISE_ARRAY[i]) {
                return salary * RAISE_RATE_ARRAY[i];
            }
        }

        return salary * RAISE_RATE_ARRAY[RAISE_RATE_ARRAY.length - 1];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getWorkHours() {
        return workHours;
    }

    public void setWorkHours(int workHours) {
        this.workHours = workHours;
    }

    public int getHireYear() {
        return hireYear;
    }

    public void setHireYear(int hireYear) {
        this.hireYear = hireYear;
    }

    @Override
    public String toString() {
        return  "Adı : " + name + "\n" +
                "Maaşı : " + salary + "\n" +
                "Çalışma Saati : " + workHours + "\n" +
                "Başlangıç Yılı : " + hireYear + "\n" +
                "Vergi : " + tax() + "\n" +
                "Bonus : " + bonus() + "\n" +
                "Maaş Artışı : " + raiseSalary() + "\n" +
                "Vergi ve Bonuslar ile birlikte maaş : " + (salary + bonus() - tax()) + "\n" +
                "Toplam Maaş : " + (salary + raiseSalary());
    }
}
