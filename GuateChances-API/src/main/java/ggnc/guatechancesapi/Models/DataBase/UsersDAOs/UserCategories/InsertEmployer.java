package ggnc.guatechancesapi.Models.DataBase.UsersDAOs.UserCategories;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.InsertUser;
import ggnc.guatechancesapi.Models.Domain.Employer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertEmployer {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public boolean insertEmployer(Employer user){
        String SQL_INSERT = "UPDATE employers SET no_card=?, vision=?, mision= ? WHERE id_code = ?";

        try {
            new InsertUser().insertUser(user);
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setLong(1, user.getCard().getNumber());
            preparedStatement.setString(2, user.getVision());
            preparedStatement.setString(3, user.getMision());
            preparedStatement.setString(4, user.getIdCode());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar usuario\n" + e.getMessage());
            return false;
        } finally {

            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }
    }
}
