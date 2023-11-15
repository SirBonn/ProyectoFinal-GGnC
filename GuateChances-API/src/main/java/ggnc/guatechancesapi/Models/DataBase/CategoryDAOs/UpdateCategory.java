package ggnc.guatechancesapi.Models.DataBase.CategoryDAOs;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Category;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateCategory {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void updateIsActiveCategory(Category category) throws ErrorOcurredException {
        String SQL_UPDATE_CATEGORY = "UPDATE categories SET isActive = ? WHERE id_code = ?";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_CATEGORY);

            if (category.getIsActive() == 1) {
                preparedStatement.setInt(1, 0);
            } else {
                preparedStatement.setInt(1, 1);
            }

            preparedStatement.setInt(2, category.getIdCode());
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Error en la actualizacion de categoria\n" + ex.getMessage());
            throw new ErrorOcurredException("Error en la actualizacion de categoria\n" + ex.getMessage());
        } finally {
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }
    }

}
