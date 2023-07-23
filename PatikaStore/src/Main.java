import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.TreeSet;
import java.util.Scanner;

interface Address {
    String getAddress(String address);
}

class HomeAddress implements Address {
    @Override
    public String getAddress(String address) {
        return "Home Address: " + address;
    }
}

class BusinessAddress implements Address {

    @Override
    public String getAddress(String address) {
        return "Business Address: " + address;
    }
}

class AddressManager {
    public static void addAddress(User user, Address address) {
        user.getAddresses().add(address);
    }

    public static void removeAddress(User user, Address address) {
        user.getAddresses().remove(address);
    }
}

abstract class Insurance {
    private String name;
    private double price;
    private Date startDate;
    private Date endDate;

    public Insurance(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = calculate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    public abstract double calculate();
}

class HealthInsurance extends Insurance {
    public HealthInsurance(String name, Date startDate, Date endDate) {
        super(name, startDate, endDate);
    }

    @Override
    public double calculate() {
        return (this.getEndDate().getTime() - this.getStartDate().getTime()) / 86400000 * 5;
    }
}

class ResidenceInsurance extends Insurance {
    public ResidenceInsurance(String name, Date startDate, Date endDate) {
        super(name, startDate, endDate);
    }

    @Override
    public double calculate() {
        return (this.getEndDate().getTime() - this.getStartDate().getTime()) / 86400000 * 3.8;
    }
}

class TravelInsurance extends Insurance {
    public TravelInsurance(String name, Date startDate, Date endDate) {
        super(name, startDate, endDate);
    }

    @Override
    public double calculate() {
        return (this.getEndDate().getTime() - this.getStartDate().getTime()) / 86400000 * 7.5;
    }
}

class CarInsurance extends Insurance {
    public CarInsurance(String name, Date startDate, Date endDate) {
        super(name, startDate, endDate);
    }

    @Override
    public double calculate() {
        return (this.getEndDate().getTime() - this.getStartDate().getTime()) / 86400000 * 12.45;
    }
}

class User {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String occupation;
    private int age;
    private ArrayList<Address> addresses;
    private Date lastLoginDate;

    public User(String name, String surname, String email, String password, String occupation, int age) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.occupation = occupation;
        this.age = age;
        this.addresses = new ArrayList<>();
        this.lastLoginDate = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public final String  showUserInfo() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", occupation='" + occupation + '\'' +
                ", age=" + age +
                ", addresses=" + addresses +
                ", lastLoginDate=" + lastLoginDate +
                '}';
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }
}

class Account implements Comparable<Account> {
    private AuthenticationStatus authenticationStatus;
    private User user;
    private ArrayList<Insurance> insurances;

    public Account(User user) {
        this.user = user;
        this.authenticationStatus = AuthenticationStatus.FAIL;
        this.insurances = new ArrayList<>();
    }

    @Override
    public int compareTo(Account o) {
        return this.user.getName().compareTo(o.getUser().getName());
    }

    enum AuthenticationStatus {
        SUCCESS, FAIL
    }

    public void login(String email, String password) throws InvalidAuthenticationException {
        if (this.user.getEmail().equals(email) && this.user.getPassword().equals(password)) {
            this.authenticationStatus = AuthenticationStatus.SUCCESS;
        } else {
            System.out.println("Invalid email or password");
        }
    }

    public AuthenticationStatus getAuthenticationStatus() {
        return authenticationStatus;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Insurance> getInsurances() {
        return insurances;
    }

    public void addInsurance(Insurance insurance) {
        getInsurances().add(insurance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "authenticationStatus=" + authenticationStatus +
                ", user=" + user +
                ", insurances=" + insurances +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return authenticationStatus == account.authenticationStatus && Objects.equals(user, account.user) && Objects.equals(insurances, account.insurances);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authenticationStatus, user, insurances);
    }
}

class AccountManager {
    private TreeSet<Account> accounts;

    public AccountManager() {
        accounts = new TreeSet<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public Account login(String email, String password) throws InvalidAuthenticationException {

        for (Account account : accounts) {
            try {
                account.login(email, password);
                System.out.println(account.getAuthenticationStatus());
                if (account.getAuthenticationStatus().equals(Account.AuthenticationStatus.SUCCESS))
                    return account;

            } catch (InvalidAuthenticationException e) {
                continue;
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        User user1 = new User("John", "Doe", "u", "1234", "Doctor", 30);
        Account individualAccount = new Account(user1);

        AccountManager accountManager = new AccountManager();
        accountManager.addAccount(individualAccount);

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("E-mail: ");
            String email = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            Account loggedInAccount = accountManager.login(email, password);

            if (loggedInAccount != null) {
                HealthInsurance healthInsurance = new HealthInsurance("Sağlık",
                        new Date(2020, 1, 2), new Date(2028, 1, 2));
                loggedInAccount.addInsurance(healthInsurance);
                System.out.println("Login successful for user: " + loggedInAccount.getUser().showUserInfo());
                System.out.println(loggedInAccount.getInsurances());
            } else {
                System.out.println("Login failed. Invalid email or password.");
            }
        } catch (InvalidAuthenticationException e) {
            System.out.println("Login failed. Invalid email or password.");
        }
    }
}
