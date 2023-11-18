package ggnc.guatechancesapi.Models.DataBase.UsersDAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Employer;
import ggnc.guatechancesapi.Models.Domain.User;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

public class InsertUser {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public InsertUser() {
    }

    public boolean insertUser(User user){

        String SQL_INSERT = "INSERT INTO users (forename, username, userpass, email, birth_date, userType, id_code, CUI, direction) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            this.connection = DBConectionManager.getConnection();
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

            if (user.getUsertype() == 2) {
                insertJobSeeker(user);
            } else if (user.getUsertype() == 1) {
                insertEmployer(user);
            }

            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar usuario estado" + e.getMessage());
            return false;
        } finally {

            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }
    }

    private void insertJobSeeker(User user) throws SQLException {
        String SQL_INSERT = "INSERT INTO jobseekers (id_code) VALUES (?)";

        preparedStatement = connection.prepareStatement(SQL_INSERT);
        preparedStatement.setString(1, user.getIdCode());
        preparedStatement.executeUpdate();

    }

    private void insertEmployer(User user) throws SQLException{
        String SQL_INSERT = "INSERT INTO employers (id_code) VALUES (?)";
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, user.getIdCode());
            preparedStatement.executeUpdate();

    }

}
