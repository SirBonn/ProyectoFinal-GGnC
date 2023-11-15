package ggnc.guatechancesapi.Models.DataBase.UsersDAOs.UserCategories;

import ggnc.guatechancesapi.Models.DataBase.CategoryDAOs.SelectCategory;
import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Category;
import ggnc.guatechancesapi.Models.Domain.User;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SelectUserCategories {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public List<Category> selectCategoriesByUser(User user) throws ErrorOcurredException {
        List<Category> categories = new ArrayList<>();

        String SQL_SELECT = "SELECT * FROM user_categories WHERE user_code = ?";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setString(1, user.getIdCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Category category = new Category(resultSet.getInt("category_id"));
                category = new SelectCategory().getCategory(category);
                categories.add(category);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ErrorOcurredException("Error al obtener las categorias del usuario: " + e.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }
        return categories;
    }

    public List<Category> selectDiffCategories(User user) throws ErrorOcurredException {
        List<Category> categories = new ArrayList<>();
        List<Integer> userCategories = getIdCodesFromList(selectCategoriesByUser(user));
        String SQL_SELECT = "SELECT * FROM categories";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Category category = new Category(resultSet.getInt("id_code"));
                category = new SelectCategory().getCategory(category);
                if (!userCategories.contains(category.getIdCode())) {
                    categories.add(category);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ErrorOcurredException("Error al obtener las categorias del usuario: " + e.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }
        return categories;
    }

    private List<Integer> getIdCodesFromList(List<Category> categories) {
        List<Integer> idCodes = new ArrayList<>();
        for (Category category : categories) {
            idCodes.add(category.getIdCode());
        }
        return idCodes;
    }

    public List<Integer> getIdCategories(User user) {
        try {
            return getIdCodesFromList(selectCategoriesByUser(user));
        } catch (ErrorOcurredException e) {
            return null;
        }
    }

}
