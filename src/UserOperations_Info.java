import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOperations_Info {
    private Connection connection;
    private int accountNumber;

    public UserOperations_Info(int accountNumber, Connection connection) {
        this.connection = connection;
        this.accountNumber = accountNumber;
    }
    public void info() throws SQLException {
        String info_query = "SELECT * FROM new_user WHERE acc_no = "+accountNumber;

        PrintTable pt = new PrintTable(info_query,connection);
        pt.print();
    }
}
