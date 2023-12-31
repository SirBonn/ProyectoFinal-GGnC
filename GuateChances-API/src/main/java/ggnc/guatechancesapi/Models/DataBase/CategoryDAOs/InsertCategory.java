package ggnc.guatechancesapi.Models.DataBase.CategoryDAOs;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Category;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertCategory {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public boolean insertCategory(Category category) {

        String SQL_INSERT = "INSERT INTO categories (cat_name, cat_desc) VALUES (?, ?)";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setString(2, category.getCategoryDesc());
            preparedStatement.executeUpdate();
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
