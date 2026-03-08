package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EmpConnBuilder implements ConnectionBuilder {

    public EmpConnBuilder() {
        try {
            Class.forName(ConnectionProperty.getProperty("db.driver.class"));
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        String url = ConnectionProperty.getProperty("db.url");
        String login = ConnectionProperty.getProperty("db.login");
        String password = ConnectionProperty.getProperty("db.password");
        return DriverManager.getConnection(url, login, password);
    }
}