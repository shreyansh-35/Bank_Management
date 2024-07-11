import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


public class PrintTable {
    private String q;
    private Connection connection;

    PrintTable(String q,Connection connection) {
        this.q = q;
        this.connection = connection;
    }

    public void print() throws SQLException {
        DisplayInterface di = (q1 -> {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(q1);

            // Get metadata to retrieve column names and count
            int columnCount = resultSet.getMetaData().getColumnCount();

            // Print table header
            System.out.println("\nAccounts Data ");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-22s", resultSet.getMetaData().getColumnName(i));
            }
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");

            // Print table rows
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.printf("%-22s", resultSet.getString(i));
                }
                System.out.println();
            }
        });
        di.printTable(q);
    }
}
