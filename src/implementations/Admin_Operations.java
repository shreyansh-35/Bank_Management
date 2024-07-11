package implementations;

import interfaces.Admin_OperationsInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin_Operations implements Admin_OperationsInterface {
    private Connection connection;
    private Scanner sc;

    public Admin_Operations(Connection connection) {
        this.connection = connection;
        this.sc = new Scanner(System.in);
    }

    public void close() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter account number: ");
        int ac_num = sc.nextInt();
        String checkQuery = "SELECT COUNT(*) FROM new_user WHERE acc_no = ?";
        String updateQuery = "UPDATE new_user SET status = CASE WHEN status = 'active' THEN 'inactive' ELSE status END WHERE acc_no = ?";
        String selectQuery = "SELECT status FROM new_user WHERE acc_no = ?";
        try {
            PreparedStatement checkStatement = this.connection.prepareStatement(checkQuery);
            checkStatement.setInt(1, ac_num);
            ResultSet checkResultSet = checkStatement.executeQuery();
            checkResultSet.next();

            int count = checkResultSet.getInt(1);
            if (count == 0) {
                System.out.println("Implementations.User does not exist.");
                return;
            }

            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);

            // Set the account number for the select query
            selectStatement.setInt(1, ac_num);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                String status = resultSet.getString("status");
                System.out.println("Now the current status of the user is inactive.");
            } else {
                System.out.println("No user found with the given account number.");
            }
            // Set the account number for the update query
            updateStatement.setInt(1, ac_num);
            int rowsAffected = updateStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void accountInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter account number: ");
        int choice1 = sc.nextInt();
        String checkQuery = "SELECT COUNT(*) FROM new_user WHERE acc_no = ?";
        String query = "SELECT * FROM new_user WHERE acc_no = " + choice1;
        try {
            PreparedStatement checkStatement = this.connection.prepareStatement(checkQuery);
            checkStatement.setInt(1, choice1);
            ResultSet checkResultSet = checkStatement.executeQuery();
            checkResultSet.next();

            int count = checkResultSet.getInt(1);
            if (count == 0) {
                System.out.println("No Transactions Done ");
                return;
            }

            PrintTable pt = new PrintTable(query, connection);
            pt.print();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public void functions() throws SQLException {
        int choice = 0;

        while (choice != 6) {
            System.out.println("1. Update");
            System.out.println("2. Close");
            System.out.println("3. Account Information");
            System.out.println("4. Transaction History");
            System.out.println("5. Display All Accounts");
            System.out.println("6. Logout");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            //sc.nextLine();
            switch (choice) {
                case 1:
                    Admin_Update au = new Admin_Update(connection);
                    au.update();
                    break;
                case 2:
                    close();
                    break;
                case 3:
                    accountInfo();
                    break;
                case 4:
                    Admin_TransectionHistory ath = new Admin_TransectionHistory(connection);
                    ath.transactionHistory();
                    break;
                case 5:
                    Admin_DisplayAcc adc = new Admin_DisplayAcc(connection);
                    adc.displayAllAccounts();
                    break;
                case 6:
                    return;

            }
        }
    }
}