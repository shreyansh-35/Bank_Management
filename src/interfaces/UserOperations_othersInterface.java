package interfaces;

import java.sql.SQLException;

public interface UserOperations_othersInterface {
    public void change_password();
    public void transaction_history() throws SQLException;
}
