public class Main {
    public static void main(String[] args) {
        Employee employee1 = new Employee("Kemal", 2000, 40, 1985);
        System.out.println(employee1.toString() + "\n");

        Employee employee2 = new Employee("Hayrettin", 1000, 55, 2020);
        System.out.println(employee2.toString() + "\n");

        Employee employee3 = new Employee("Pınar", 3000, 45, 2010);
        System.out.println(employee3.toString());
    }
}