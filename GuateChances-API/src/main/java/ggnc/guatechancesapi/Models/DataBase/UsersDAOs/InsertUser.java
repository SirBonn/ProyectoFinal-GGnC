package ggnc.guatechancesapi.Models.DataBase.UsersDAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.User;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

public class InsertUser {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public InsertUser() {
        this.connection = DBConectionManager.getConnection();
    }

    public InsertUser(Connection connection) {
        this.connection = connection;
    }

    public void insertUser(User user) throws ErrorOcurredException{

        String SQL_INSERT = "INSERT INTO users (forename, username, userpass, email, birth_date, userType, id_code, CUI, direction) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, user.getForename());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getFormatedStringDate());
            preparedStatement.setInt(6, user.getUsertype());
            preparedStatement.setString(7, user.getIdCode());
            preparedStatement.setString(8, user.getNoCUI());
            preparedStatement.setString(9, user.getDirection());

            preparedStatement.executeUpdate();

            if(user.getUsertype() == 2){
                insertJobSeeker(user);
            } else if(user.getUsertype() == 1){
                insertEmployer(user);
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar usuario estado" + e.getMessage());
            throw new ErrorOcurredException(""+e.getErrorCode());
        } finally {

            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }
    }

    private void insertJobSeeker(User user){
        String SQL_INSERT = "INSERT INTO jobseekers (id_code) VALUES (?)";
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, user.getIdCode());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al insertar usuario\n" + e.getMessage());
        } finally {

            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }
    }

    private void insertEmployer(User user){
        String SQL_INSERT = "INSERT INTO employers (id_code) VALUES (?)";
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, user.getIdCode());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al insertar usuario\n" + e.getMessage());
        } finally {

            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }
    }
}
