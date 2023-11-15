package ggnc.guatechancesapi.Models.DataBase.UsersDAOs;

import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.PhonebookDAOs.SelectPhoneBook;
import ggnc.guatechancesapi.Models.Domain.User;
import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectUser {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public User SearchByUser(User user) throws ErrorOcurredException {

        String SLQ_SELECT_BY_CREDENTIALS = "SELECT * FROM users WHERE username = ? AND userpass = ?";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SLQ_SELECT_BY_CREDENTIALS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String idCode = resultSet.getString("id_code");
                String forename = resultSet.getString("forename");
                String direction = resultSet.getString("direction");
                String username = resultSet.getString("username");
                String password = resultSet.getString("userpass");
                String CUI = resultSet.getString("CUI");
                String email = resultSet.getString("email");
                String birthdate = resultSet.getString("birth_date");
                int usertype = resultSet.getInt("userType");
                int isActive = resultSet.getInt("isActive");
                List<Long> phones = new SelectPhoneBook().selectPhones(new User(idCode));

                user = new User(idCode, forename, direction, username, password, email, CUI, birthdate, usertype, isActive, phones);

            }
        } catch (SQLException ex) {
            System.out.println("Error en la consulta de usuario\n" + ex);
            throw new ErrorOcurredException("Error al crear el usuario\n" + ex.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }

        return user;
    }

    public User getUserByCUI(User user) throws ErrorOcurredException {

        String SQL_SELECT = "SELECT * FROM users WHERE CUI = ?";
        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setString(1, user.getNoCUI());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String idCode = resultSet.getString("id_code");
                String forename = resultSet.getString("forename");
                String direction = resultSet.getString("direction");
                String username = resultSet.getString("username");
                String password = resultSet.getString("userpass");
                String CUI = resultSet.getString("CUI");
                String email = resultSet.getString("email");
                String birthdate = resultSet.getString("birth_date");
                int usertype = resultSet.getInt("userType");
                int isActive = resultSet.getInt("isActive");
                List<Long> phones = new SelectPhoneBook().selectPhones(user);

                user = new User(idCode, forename, direction, username, password, email, CUI, birthdate, usertype, isActive, phones);

            }

        } catch (SQLException ex) {
            System.out.println("Error en la consulta de usuario\n" + ex.getMessage());
            throw new ErrorOcurredException("Error al crear el usuario\n" + ex.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }

        return user;
    }

    public User getUserByID(User user) throws ErrorOcurredException {

        String SQL_SELECT = "SELECT * FROM users WHERE id_code = ?";
        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setString(1, user.getIdCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String idCode = resultSet.getString("id_code");
                String forename = resultSet.getString("forename");
                String direction = resultSet.getString("direction");
                String username = resultSet.getString("username");
                String password = resultSet.getString("userpass");
                String CUI = resultSet.getString("CUI");
                String email = resultSet.getString("email");
                String birthdate = resultSet.getString("birth_date");
                int usertype = resultSet.getInt("userType");
                int isActive = resultSet.getInt("isActive");
                List<Long> phones = new SelectPhoneBook().selectPhones(user);

                user = new User(idCode, forename, direction, username, password, email, CUI, birthdate, usertype, isActive, phones);

            }

        } catch (SQLException ex) {
            System.out.println("Error en la consulta de usuario\n" + ex.getMessage());
            throw new ErrorOcurredException("Error al crear el usuario\n" + ex.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }

        return user;
    }

    public List<User> getAllUsers() {

        List<User> allUsers = new ArrayList<>();

        String SQL_SELECT_ALL = "SELECT * FROM users";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String idCode = resultSet.getString("id_code");
                String forename = resultSet.getString("forename");
                String direction = resultSet.getString("direction");
                String username = resultSet.getString("username");
                String CUI = resultSet.getString("CUI");
                String email = resultSet.getString("email");
                String birthdate = resultSet.getString("birth_date");
                int usertype = resultSet.getInt("userType");
                int isActive = resultSet.getInt("isActive");
                List<Long> phones = new SelectPhoneBook().selectPhones(new User(idCode));

                User user = new User(idCode, forename, direction, username,
                        "encrypted_password_no_salt", email, CUI, birthdate, usertype, isActive, phones);

                allUsers.add(user);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ErrorOcurredException e) {
            e.printStackTrace(System.out);
        } finally {

            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }

        return allUsers;
    }

}

