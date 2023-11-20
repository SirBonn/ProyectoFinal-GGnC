package ggnc.guatechancesapi.Models.DataBase.OffersDAOs.PlataformPayment;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.PlataformPayment;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertUpdatePayment {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public void insertLog(PlataformPayment plataformPayment){

        try {
            connection = new DBConectionManager().getConnection();

            new UpdateOfferPayment(connection).updateOfferPayment(plataformPayment.getPayment());

            String SQL_UPDATE_PAYMENT = "INSERT INTO payment_logs(payment_date, user_code, plataformPayment) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(SQL_UPDATE_PAYMENT);
            preparedStatement.setString(1, plataformPayment.getStringDate());
            preparedStatement.setString(2, plataformPayment.getUser().getIdCode());
            preparedStatement.setDouble(3, plataformPayment.getPayment());
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Error en la actualizacion de pago de oferta\n" + ex.getMessage());
        } finally {
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }

    }

}
