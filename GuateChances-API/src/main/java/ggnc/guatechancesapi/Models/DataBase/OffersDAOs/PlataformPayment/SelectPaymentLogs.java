package ggnc.guatechancesapi.Models.DataBase.OffersDAOs.PlataformPayment;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.SelectUser;
import ggnc.guatechancesapi.Models.Domain.PlataformPayment;
import ggnc.guatechancesapi.Models.Domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SelectPaymentLogs {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public List<PlataformPayment> getPaymentLogs() {
        List<PlataformPayment> logs = new ArrayList<>();
        String SQL_SELECT_PAYMENT_LOGS = "SELECT * FROM payment_logs";
        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_PAYMENT_LOGS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int idCode = resultSet.getInt("id_Code");
                String date = resultSet.getString("payment_date");
                String userCode = resultSet.getString("user_code");
                User user = new SelectUser().getUserByID(new User(userCode));
                double payment = resultSet.getDouble("plataformPayment");
                PlataformPayment plataformPayment = new PlataformPayment(idCode, date, user, payment);
                logs.add(plataformPayment);

            }
        } catch (Exception ex) {
            System.out.println("Error en la obtencion de logs de pago\n" + ex.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }

        return logs;
    }


}
