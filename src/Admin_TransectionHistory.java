import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin_TransectionHistory {
    private Connection connection;
    public Admin_TransectionHistory(Connection connection) {
        this.connection = connection;
    }
    public void transactionHistory() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter account number: ");
        int choice2 = sc.nextInt();
        String checkQuery = "SELECT COUNT(*) FROM new_user WHERE acc_no = ?";
        String query1 = "SELECT trans_id, credit_amt, debit_amt, curr_bal, created_at FROM transactiontable WHERE acc_no = ? ";
        try {
            PreparedStatement checkStatement = this.connection.prepareStatement(checkQuery);
            checkStatement.setInt(1, choice2);
            ResultSet checkResultSet = checkStatement.executeQuery();
            checkResultSet.next();

            int count = checkResultSet.getInt(1);
            if (count == 0) {
                System.out.println("User does not exist.");
                return;
            }

            PreparedStatement preparedStatement = this.connection.prepareStatement(query1);
            preparedStatement.setInt(1, choice2);

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
