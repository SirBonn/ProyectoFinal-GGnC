package ggnc.guatechancesapi.Models.DataBase.UsersDAOs;

import ggnc.guatechancesapi.Models.Domain.User;
import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public User SearchByUser(User user) {

        String SLQ_SELECT_BY_CREDENTIALS = "SELECT * FROM users WHERE username = ? AND userpass = ?";

        try {
            preparedStatement = connection.prepareStatement(SLQ_SELECT_BY_CREDENTIALS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int idCode = resultSet.getInt("id_code");
                String forename = resultSet.getString("forename");
                String direction = resultSet.getString("direction");
                String username = resultSet.getString("username");
                String password = resultSet.getString("userpass");
                int CUI = resultSet.getInt("CUI");
                String email = resultSet.getString("email");
                String birthdate = resultSet.getString("birthdate");
                int usertype = resultSet.getInt("usertype");

                user = new User(idCode, forename, direction, username, password, email, CUI, birthdate, usertype);

            }

        } catch (SQLException ex) {
            System.out.println("Error en la consulta de usuario\n" + ex.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
        }

        return user;
    }

    public User getUser(User user){

            String SQL_SELECT = "SELECT * FROM users WHERE id_code = ?";

            try {
                preparedStatement = connection.prepareStatement(SQL_SELECT);
                preparedStatement.setInt(1, user.getIdCode());
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){

                    int idCode = resultSet.getInt("id_code");
                    String forename = resultSet.getString("forename");
                    String direction = resultSet.getString("direction");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("userpass");
                    int CUI = resultSet.getInt("CUI");
                    String email = resultSet.getString("email");
                    String birthdate = resultSet.getString("birthdate");
                    int usertype = resultSet.getInt("usertype");

                    user = new User(idCode, forename, direction, username, password, email, CUI, birthdate, usertype);

                }

            } catch (SQLException ex) {
                System.out.println("Error en la consulta de usuario\n" + ex.getMessage());
            } finally {
                DBConectionManager.close(resultSet);
                DBConectionManager.close(preparedStatement);
            }

            return user;
    }


}

