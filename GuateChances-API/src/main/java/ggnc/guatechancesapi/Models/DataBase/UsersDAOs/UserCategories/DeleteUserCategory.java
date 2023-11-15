package ggnc.guatechancesapi.Models.DataBase.UsersDAOs.UserCategories;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Category;
import ggnc.guatechancesapi.Models.Domain.User;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeleteUserCategory {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void deleteUserCategory(User user, Category category) throws ErrorOcurredException {
        String SQL_DELETE = "DELETE FROM user_categories WHERE user_code = ? AND category_id = ?";
        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setString(1, user.getIdCode());
            preparedStatement.setInt(2, category.getIdCode());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ErrorOcurredException("Error al borrar la categoria: " +e.getMessage());
        } finally {
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }
    }

}
