import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class UserOperations_others {
    private Connection connection;
    private int accountNumber;

    public UserOperations_others(int accountNumber, Connection connection) {
        this.connection = connection;
        this.accountNumber = accountNumber;
    }
    public void change_password() {
        Scanner scanner = new Scanner(System.in);
        Validation vd = new Validation();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your current password: ");
        String currentPassword = scanner.nextLine();
        currentPassword = vd.doHashing(currentPassword);

        System.out.print("Enter your new password: ");
        String newPassword = scanner.nextLine();

        System.out.print("Enter confirm password: ");
        String confirmPassword = scanner.nextLine();

        if (!Objects.equals(newPassword, confirmPassword)) {
            System.out.println("Both passwords are not matching. Try again.");
            return;
        }
        newPassword = vd.doHashing(newPassword);

        // Query to update password
        String updatePasswordQuery = "UPDATE new_user SET password = ? WHERE email = ? AND password = ?";
        try {
            PreparedStatement updateStatement = connection.prepareStatement(updatePasswordQuery);
            updateStatement.setString(1, newPassword);
            updateStatement.setString(2, email);
            updateStatement.setString(3, currentPassword);
            int rowsUpdated = updateStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Password changed successfully.");
            } else {
                System.out.println("Failed to change password. Please check your email and current password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void transaction_history() {
        String query1 = "SELECT trans_id, credit_amt, debit_amt, curr_bal, created_at " +
                "FROM transactiontable WHERE acc_no = ? ";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query1);
            preparedStatement.setInt(1, accountNumber);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int transactionId = resultSet.getInt("trans_id");
                int creditAmount = resultSet.getInt("credit_amt");
                int debitAmount = resultSet.getInt("debit_amt");
                int currentBalance = resultSet.getInt("curr_bal");
                String dateTime = resultSet.getString("created_at");

                System.out.println("Transaction ID: " + transactionId);
                System.out.println("Credited Amount: " + creditAmount);
                System.out.println("Debited Amount: " + debitAmount);
                System.out.println("Current Balance: " + currentBalance);
                System.out.println("Time Stamp: " + dateTime);
                System.out.println("----------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
