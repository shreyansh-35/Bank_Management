package db_connection;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseConnection {
    private Connection connection;

    public DatabaseConnection() throws FileNotFoundException, SQLException {
        File file = new File("E:\\Bank Management System\\src\\DB_Details.txt");
        Scanner obj = new Scanner(file);

        String url = obj.nextLine();
        String userName = obj.nextLine();
        String password = obj.nextLine();

        this.connection = DriverManager.getConnection(url, userName, password);
    }

    public Connection getConnection() {
        return connection;
    }
}

