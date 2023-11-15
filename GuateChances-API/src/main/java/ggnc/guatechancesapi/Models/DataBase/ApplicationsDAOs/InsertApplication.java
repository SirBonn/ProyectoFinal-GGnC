package ggnc.guatechancesapi.Models.DataBase.ApplicationsDAOs;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Application;
import ggnc.guatechancesapi.Models.Domain.User;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertApplication {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void insertApplication(Application application) throws ErrorOcurredException {
        String SQL_INSERT = "INSERT INTO applications (seeker_code, offer_code, seeker_message) VALUES (?, ?, ?)";
        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, application.getSeeker().getIdCode());
            preparedStatement.setInt(2, application.getOffer().getIdCode());
            preparedStatement.setString(3, application.getSeekerMessage());

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
