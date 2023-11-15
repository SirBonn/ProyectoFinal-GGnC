package ggnc.guatechancesapi.Models.DataBase.CategoryDAOs;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SelectCategory {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public Category getCategory(Category category) {
        String SQL_SELECT_CATEGORY = "SELECT * FROM categories WHERE id_code = ?";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_CATEGORY);
            preparedStatement.setInt(1, category.getIdCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idCode = resultSet.getInt("id_code");
                String categoryName = resultSet.getString("cat_name");
                String categoryDesc = resultSet.getString("cat_desc");
                int isActive = resultSet.getInt("isActive");

                category.setIdCode(idCode);
                category.setCategoryName(categoryName);
                category.setCategoryDesc(categoryDesc);
                category.setIsActive(isActive);
            }

        } catch (Exception ex) {
            System.out.println("Error en la consulta de categoria\n" + ex.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }

        return category;
    }

    public List<Category> getAllCategories() {

        List<Category> allCategories = new ArrayList<>();
        String SQL_SELECT_ALL_CATEGORIES = "SELECT * FROM categories";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_CATEGORIES);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idCode = resultSet.getInt("id_code");
                String categoryName = resultSet.getString("cat_name");
                String categoryDesc = resultSet.getString("cat_desc");
                int isActive = resultSet.getInt("isActive");

                Category category = new Category(idCode, categoryName, categoryDesc, isActive);
                allCategories.add(category);
            }

        } catch (Exception ex) {
            System.out.println("Error en la consulta de categoria\n" + ex.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }

        return allCategories;

    }

}
