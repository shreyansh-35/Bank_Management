import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin_DisplayAcc {
    private Connection connection;

    public Admin_DisplayAcc(Connection connection) {
        this.connection = connection;
    }
    public void displayAllAccounts() {
        String query2 = "SELECT acc_no, name, father_name, email, phone_number, address, balance, status FROM new_user";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query2);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String accNumber = resultSet.getString("acc_no");
                String userName = resultSet.getString("name");
                String fatherName = resultSet.getString("father_name");
                String email = resultSet.getString("email");
                String phoneNum = resultSet.getString("phone_number");
                String address = resultSet.getString("address");
                int balance = resultSet.getInt("balance");
                String status = resultSet.getString("status");

                System.out.println("Account Number: " + accNumber);
                System.out.println("User Name: " + userName);
                System.out.println("Father Name: " + fatherName);
                System.out.println("Email: " + email);
                System.out.println("Phone Number: " + phoneNum);
                System.out.println("Address: " + address);
                System.out.println("Balance: " + balance);
                System.out.println("Status: " + status);
                System.out.println("----------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
