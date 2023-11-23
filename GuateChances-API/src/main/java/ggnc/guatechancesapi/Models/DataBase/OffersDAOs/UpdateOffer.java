package ggnc.guatechancesapi.Models.DataBase.OffersDAOs;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.DataBase.OffersDAOs.PlataformPayment.InsertOfferPayment;
import ggnc.guatechancesapi.Models.Domain.JobSeeker;
import ggnc.guatechancesapi.Models.Domain.Offer;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateOffer {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private boolean closeConnection = true;

    public UpdateOffer() {
    }

    public UpdateOffer(Connection connection) {
        this.connection = connection;
        this.closeConnection = false;
    }

    public void updateOfferState(Offer offer) {

        String SQL_UPDATE_OFFER_STATE = "UPDATE offers SET offer_state = ? WHERE id_code = ?";

        try {
            if (this.connection == null) {
                this.connection = DBConectionManager.getConnection();
            }
            preparedStatement = connection.prepareStatement(SQL_UPDATE_OFFER_STATE);
            preparedStatement.setInt(1, offer.getOfferState());
            preparedStatement.setInt(2, offer.getIdCode());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en la actualizacion de estado de oferta\n" + e.getMessage());
        } finally {
            if (closeConnection) {
                DBConectionManager.close(preparedStatement);
                DBConectionManager.close(connection);
            }
        }

    }

    public void setSeekerSelected(JobSeeker seeker, Offer offer) {

        String SQL_UPDATE_OFFER_SEEKER = "UPDATE offers SET user_selected = ? WHERE id_code = ?";

        try {
            if (this.connection == null) {
                this.connection = DBConectionManager.getConnection();
            }
            preparedStatement = connection.prepareStatement(SQL_UPDATE_OFFER_SEEKER);
            preparedStatement.setString(1, seeker.getIdCode());
            preparedStatement.setInt(2, offer.getIdCode());

            preparedStatement.executeUpdate();
            new InsertOfferPayment().insertOfferPayment(offer);
        } catch (Exception e) {
            System.out.println("Error en la actualizacion de estado de oferta " + e.getMessage());
        } finally {
            if (closeConnection) {
                DBConectionManager.close(preparedStatement);
                DBConectionManager.close(connection);
            }
        }

    }

}
