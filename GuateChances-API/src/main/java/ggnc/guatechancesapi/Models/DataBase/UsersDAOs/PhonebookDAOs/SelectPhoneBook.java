package ggnc.guatechancesapi.Models.DataBase.UsersDAOs.PhonebookDAOs;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.User;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SelectPhoneBook {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public List<Long> selectPhones(User user) throws ErrorOcurredException {
        List<Long> phones = new ArrayList<>();

        String SQL_SELECT_PHONES = "SELECT * FROM phonebook WHERE user_code = ?";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_PHONES);
            preparedStatement.setString(1, user.getIdCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long phone = resultSet.getLong("telephone");
                phones.add(phone);
            }

        } catch (Exception e) {
            System.out.println("Error en la consulta de telefonos\n" + e.getMessage());
            throw new ErrorOcurredException("Error en la consulta de telefonos\n" + e.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }

        return phones;
    }


}
