package ggnc.guatechancesapi.Models.DataBase.ApplicationsDAOs;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Application;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeleteApplication {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void deleteApplicationByID(Application app) throws ErrorOcurredException {

        String SQL_DELETE_APPLICATION = "DELETE FROM applications WHERE id_code = ?";
        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_APPLICATION);
            preparedStatement.setInt(1, app.getIdCode());
            preparedStatement.execute ();

        } catch (Exception e) {
            System.out.println("Error al eliminar la aplicacion\n" + e.getMessage());
            throw new ErrorOcurredException("Error en la consulta de application:" + e.getMessage());
        } finally {
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }
    }

}
