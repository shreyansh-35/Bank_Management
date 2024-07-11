package implementations;

import java.sql.Connection;
import java.sql.SQLException;

public class Admin_DisplayAcc implements interfaces.Admin_DisplayAcc {
    private Connection connection;

    public Admin_DisplayAcc(Connection connection) {
        this.connection = connection;
    }
    public void displayAllAccounts() throws SQLException {
        String query2 = "SELECT acc_no, name, father_name, email, phone_number, address, balance, status FROM new_user";

        PrintTable pt = new PrintTable(query2,connection);
        pt.print();
    }
}
