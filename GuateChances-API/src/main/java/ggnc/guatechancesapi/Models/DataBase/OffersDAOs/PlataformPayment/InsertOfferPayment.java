package ggnc.guatechancesapi.Models.DataBase.OffersDAOs.PlataformPayment;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Offer;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertOfferPayment {

    private Connection connection = new DBConectionManager().getConnection();
    private PreparedStatement preparedStatement;

    public void insertOfferPayment(Offer offer) {
        try {
            String SQL_INSERT_OFFER_PAYMENT = "INSERT INTO offers_payments (offer_code, payment_date) VALUES (?)";

            preparedStatement = connection.prepareStatement(SQL_INSERT_OFFER_PAYMENT);
            preparedStatement.setDouble(1, offer.getIdCode());
            preparedStatement.setDate(2, offer.setNowDate());
            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Error en la insercion de pago de oferta\n" + ex.getMessage());
        } finally {
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }
    }

}
