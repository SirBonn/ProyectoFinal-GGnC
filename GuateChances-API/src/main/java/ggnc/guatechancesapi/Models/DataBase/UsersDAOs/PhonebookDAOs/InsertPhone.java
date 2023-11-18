package ggnc.guatechancesapi.Models.DataBase.UsersDAOs.PhonebookDAOs;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.PhoneBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertPhone {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public boolean insertPhone(PhoneBook phoneBook) {
        String SQL_INSERT_PHONE = "INSERT INTO phonebook (telephone, user_code) VALUES (?, ?)";

        try {
            this.connection = DBConectionManager.getConnection();
            for (long telephone: phoneBook.getTelephones()) {
                preparedStatement = connection.prepareStatement(SQL_INSERT_PHONE);
                preparedStatement.setLong(1, telephone);
                preparedStatement.setString(2, phoneBook.getUserCode());
                preparedStatement.executeUpdate();
            }

            return true;

        } catch (SQLException e) {
            System.out.println("Error en la insercion de telefonos\n" + e.getMessage());
            return false;
        } finally {
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }
    }
}
