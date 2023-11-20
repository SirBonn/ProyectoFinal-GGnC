package ggnc.guatechancesapi.Models.DataBase.OffersDAOs.PlataformPayment;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectOfferPayment {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public double getOfferPayment() {
        String SQL_SELECT_OFFER_PAYMENT = "SELECT COLUMN_NAME, COLUMN_DEFAULT FROM information_schema.columns " +
                "WHERE TABLE_SCHEMA='guatechances_db' AND TABLE_NAME='offers_payments' AND COLUMN_NAME='plataformPayment'";
        double payment = 0;
        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_OFFER_PAYMENT);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                payment = resultSet.getDouble("COLUMN_DEFAULT");

            }
            return payment;
        } catch (Exception ex) {
            System.out.println("Error en la obtencion de pago de oferta\n" + ex.getMessage());
            return payment;
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }
    }

}
