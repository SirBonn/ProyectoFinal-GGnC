package ggnc.guatechancesapi.Models.DataBase.UsersDAOs;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Employer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateEmployer {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

     public void updateEmployer(Employer employer) {
        String SQL_UPDATE_EMPLOYER = "UPDATE employers SET vision=?, mision=? WHERE id_code=?";

        try {
            new UpdateUser().updateUser(employer);
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_EMPLOYER);

            preparedStatement.setString(1, employer.getVision());
            preparedStatement.setString(2, employer.getMision());
            preparedStatement.setString(3, employer.getIdCode());

            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Error en la actualizacion de empleador\n" + ex.getMessage());
        } finally {
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }
    }
}
