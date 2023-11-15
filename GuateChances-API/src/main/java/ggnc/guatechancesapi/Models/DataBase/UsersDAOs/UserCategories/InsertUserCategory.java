package ggnc.guatechancesapi.Models.DataBase.UsersDAOs.UserCategories;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Category;
import ggnc.guatechancesapi.Models.Domain.User;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertUserCategory {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void insertUserCategory(User user, Category category) throws ErrorOcurredException {
        String SQL_INSERT = "INSERT INTO user_categories (user_code, category_id) VALUES (?, ?)";
        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, user.getIdCode());
            preparedStatement.setInt(2, category.getIdCode());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ErrorOcurredException("Error al insertar la categoria: " +e.getMessage());
        } finally {

            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }
    }

}
