package ggnc.guatechancesapi.Models.DataBase.OffersDAOs;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Offer;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertOffer {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void insertOffer(Offer offer) throws ErrorOcurredException {

        String SQL_INSERT = "INSERT INTO offers (offer_name, offer_desc, employer_id, category_id, publication_date, " +
                "expiration_date, payment, modality, direction, details) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, offer.getOfferName());
            preparedStatement.setString(2, offer.getOfferDesc());
            preparedStatement.setString(3, offer.getEmployer().getIdCode());
            preparedStatement.setInt(4, offer.getCategory().getIdCode());
            preparedStatement.setString(5, offer.getStringPublicationDate());
            preparedStatement.setString(6, offer.getStringExpireDate());
            preparedStatement.setDouble(7, offer.getPayment());
            preparedStatement.setInt(8, offer.getModality());
            preparedStatement.setString(9, offer.getDirection());
            preparedStatement.setString(10, offer.getDetails());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ErrorOcurredException("Error al insertar la oferta: " +e.getMessage());
        } finally {
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }


    }
}
