package ggnc.guatechancesapi.Models.DataBase;

import java.sql.*;

public class DBConectionManager {

    private static final String USER = "sirRoot";
    private static final String PASSWORD = "sirRoot";
    private static final String URL = "jdbc:mysql://localhost:3306/guatechances_db";

    public static Connection getConnection() {

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {

        }

        return connection;
    }

    public static void close(Connection conecction) {
        try {
            conecction.close();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement preparedStatement) {
        try {
            preparedStatement.close();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

    }

}
