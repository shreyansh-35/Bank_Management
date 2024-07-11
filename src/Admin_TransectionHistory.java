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
        String query1 = "SELECT trans_id, credit_amt, debit_amt, curr_bal, created_at FROM transactiontable WHERE acc_no = "+choice2;
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

            PrintTable pt = new PrintTable(query1, connection);
            pt.print();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
