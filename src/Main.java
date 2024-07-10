import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {

    // JDBC Connection
    private static final String url = "jdbc:mysql://localhost:3306/bank_management";
    private static final String userName = "root";
    private static final String password = "Shreyansh@123";

    public static void main(String[] args) {
        try {
            // establish connection
            Connection connection = DriverManager.getConnection(url, userName, password);
            Scanner sc = new Scanner(System.in);

            // Creating objects for other classes
            User user = new User(connection);
            Account account = new Account(connection);

            while (true) {
                System.out.println("*** Welcome to Bank of Java ***");
                System.out.println();
                System.out.println("1. Login");
                System.out.println("2. Open Account");
                System.out.println("3. Exit");
                System.out.print("Enter your Choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        user.login();
                        break;
                    case 2:
                        account.openAccount();
                        break;
                    case 3:
                        System.out.println("Thank You for using Java Bank");
                        System.out.println("Existing System...");
                        return;
                    default:
                        System.out.println("Please enter valid choice...");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
