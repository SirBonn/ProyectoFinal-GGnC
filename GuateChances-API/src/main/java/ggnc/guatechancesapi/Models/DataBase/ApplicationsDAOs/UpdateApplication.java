package ggnc.guatechancesapi.Models.DataBase.ApplicationsDAOs;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Application;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateApplication {

    private Connection connection;
    private PreparedStatement preparedStatement;


    public UpdateApplication() {
    }

    public void updateApplicationState(Application application, boolean isSelected) {
        String SQL_UPDATE = "UPDATE applications SET application_state = ? WHERE id_code = ?";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            if (isSelected) {
                preparedStatement.setInt(1, 1);
            } else {
                preparedStatement.setInt(1, 2);
            }
            preparedStatement.setInt(2, application.getIdCode());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }
    }

}
