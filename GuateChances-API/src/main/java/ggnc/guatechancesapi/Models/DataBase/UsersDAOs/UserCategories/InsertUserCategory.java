package ggnc.guatechancesapi.Models.DataBase.UsersDAOs.UserCategories;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Category;
import ggnc.guatechancesapi.Models.Domain.User;
import ggnc.guatechancesapi.Models.Domain.UserCategories;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertUserCategory {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public boolean insertUserCategory(UserCategories userCategories){
        String SQL_INSERT = "INSERT INTO user_categories (user_code, category_id) VALUES (?, ?)";
        try {
            this.connection = DBConectionManager.getConnection();
            for (int category : userCategories.getCatgories()) {
                preparedStatement = connection.prepareStatement(SQL_INSERT);
                preparedStatement.setString(1, userCategories.getUserCode());
                preparedStatement.setInt(2, category);
                preparedStatement.executeUpdate();
            }

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {

            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }
    }

}
