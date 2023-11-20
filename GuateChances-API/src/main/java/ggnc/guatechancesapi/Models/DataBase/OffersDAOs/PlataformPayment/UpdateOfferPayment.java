package ggnc.guatechancesapi.Models.DataBase.OffersDAOs.PlataformPayment;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateOfferPayment {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public UpdateOfferPayment(Connection connection) {
        this.connection = connection;
    }

    public void updateOfferPayment(double payment) {
        String SQL_UPDATE_OFFER_PAYMENT = "ALTER TABLE offers_payments ALTER COLUMN plataformPayment SET DEFAULT ?";

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_OFFER_PAYMENT);
            preparedStatement.setDouble(1, payment);
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Error en la actualizacion de pago de oferta\n" + ex.getMessage());
        } finally {
            DBConectionManager.close(preparedStatement);
        }
    }

}
