package ggnc.guatechancesapi.Models.DataBase.CategoryDAOs;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Category;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertCategory {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public void insertCategory(Category category) throws ErrorOcurredException {

        String SQL_INSERT = "INSERT INTO categories (cat_name, cat_desc) VALUES (?, ?)";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setString(2, category.getCategoryDesc());
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
