import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserOperations_Info {
    private Connection connection;
    private int accountNumber;

    public UserOperations_Info(int accountNumber, Connection connection) {
        this.connection = connection;
        this.accountNumber = accountNumber;
    }
    public void info() {
        String info_query = "SELECT * FROM new_user WHERE acc_no = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(info_query);
            preparedStatement.setInt(1, accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String fathersName = resultSet.getString("father_name");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phone_number");
                String address = resultSet.getString("address");
                int accno = resultSet.getInt("acc_no");
                double balance = resultSet.getDouble("balance");
                String accstatus = resultSet.getString("status");
                System.out.println("Account Number : " + accno);
                System.out.println("Name: " + name);
                System.out.println("Father's Name : " + fathersName);
                System.out.println("Email: " + email);
                System.out.println("Phone Number: " + phoneNumber);
                System.out.println("Address: " + address);
                System.out.println("Balance: " + balance);
                System.out.println("Account Status : " + accstatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
