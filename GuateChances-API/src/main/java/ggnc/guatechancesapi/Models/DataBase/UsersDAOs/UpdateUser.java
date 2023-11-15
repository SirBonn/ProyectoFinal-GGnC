package ggnc.guatechancesapi.Models.DataBase.UsersDAOs;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.User;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateUser {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void updateUser(User user) throws ErrorOcurredException {
        String SQL_UPDATE_USER = "UPDATE users SET forename=?, direction=?, username=?, email=?, CUI=? WHERE id_code=?";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);

            preparedStatement.setString(1, user.getForename());
            preparedStatement.setString(2, user.getDirection());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getNoCUI());
            preparedStatement.setString(6, user.getIdCode());

            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Error en la actualizacion de usuario\n" + ex.getMessage());
            throw new ErrorOcurredException("Error en la actualizacion de usuario\n" + ex.getMessage());
        } finally {
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }
    }

    public void updateIsActiveUser(User user) throws ErrorOcurredException{

        String SQL_UPDATE_CATEGORY = "UPDATE users SET isActive = ? WHERE id_code = ?";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_CATEGORY);

            if (user.getIsActive() == 1) {
                preparedStatement.setInt(1, 0);
            } else {
                preparedStatement.setInt(1, 1);
            }

            preparedStatement.setString(2, user.getIdCode());
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Error en la actualizacion de usuario\n" + ex.getMessage());
            throw new ErrorOcurredException("Error en la actualizacion de usuario\n" + ex.getMessage());
        } finally {
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }

    }

}
