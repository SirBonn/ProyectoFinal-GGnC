package ggnc.guatechancesapi.Models.DataBase.OffersDAOs;

import ggnc.guatechancesapi.Models.DataBase.ApplicationsDAOs.SelectApplication;
import ggnc.guatechancesapi.Models.DataBase.CategoryDAOs.SelectCategory;
import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.SelectEmployer;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.SelectUser;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.UserCategories.SelectUserCategories;
import ggnc.guatechancesapi.Models.Domain.*;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SelectOffer {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public Offer getOffer(Offer offer) {
        String SQL_SELECT_OFFER = "SELECT * FROM offers WHERE id_code = ?";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_OFFER);
            preparedStatement.setInt(1, offer.getIdCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idCode = resultSet.getInt("id_code");
                String offerName = resultSet.getString("offer_name");
                String offerDesc = resultSet.getString("offer_desc");
                String employerCode = resultSet.getString("employer_id");
                Employer employer = new Employer(employerCode);
                int categoryCode = resultSet.getInt("category_id");
                Category category = new Category(categoryCode);
                int offerState = resultSet.getInt("offer_state");
                String publicationDate = resultSet.getString("publication_date");
                String expireDate = resultSet.getString("expiration_date");
                double payment = resultSet.getDouble("payment");
                double plataformPayment = resultSet.getDouble("plataformPayment");
                int modality = resultSet.getInt("modality");
                String direction = resultSet.getString("direction");
                String details = resultSet.getString("details");

                offer.setIdCode(idCode);
                offer.setOfferName(offerName);
                offer.setOfferDesc(offerDesc);
                offer.setEmployer(new SelectEmployer().getEmployer(employer));
                offer.setCategory(new SelectCategory().getCategory(category));
                offer.setOfferState(offerState);
                offer.setPublicationDate(publicationDate);
                offer.setExpireDate(expireDate);
                offer.setPayment(payment);
                offer.setModality(modality);
                offer.setDirection(direction);
                offer.setDetails(details);
            }

        } catch (Exception ex) {
            System.out.println("Error en la consulta de oferta\n" + ex.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }

        return offer;
    }

    public List<Offer> getAllOffers() {

        List<Offer> offers = new ArrayList<>();

        String SQL_SELECT_ALL = "SELECT * FROM offers";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idCode = resultSet.getInt("id_code");
                String offerName = resultSet.getString("offer_name");
                String offerDesc = resultSet.getString("offer_desc");
                String employerCode = resultSet.getString("employer_id");
                Employer employer = new Employer(employerCode);
                int categoryCode = resultSet.getInt("category_id");
                Category category = new Category(categoryCode);
                int offerState = resultSet.getInt("offer_state");
                String publicationDate = resultSet.getString("publication_date");
                String expireDate = resultSet.getString("expiration_date");
                double payment = resultSet.getDouble("payment");
                int modality = resultSet.getInt("modality");
                String direction = resultSet.getString("direction");
                String details = resultSet.getString("details");

                Offer offer = new Offer(idCode, offerName, offerDesc, new SelectEmployer().getEmployer(employer),
                        new SelectCategory().getCategory(category), offerState, publicationDate, expireDate, payment, modality, direction, details);

                offers.add(offer);
            }

        } catch (Exception ex) {
            System.out.println("Error en la consulta de ofertas\n" + ex.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }

        return offers;
    }

    public List<Offer> getOffersByEmployer(Employer employer) {
        List<Offer> offers = new ArrayList<>();

        String SQL_SELECT_OFFERS_BY_EMPLOYER = "SELECT * FROM offers WHERE employer_id = ?";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_OFFERS_BY_EMPLOYER);
            preparedStatement.setString(1, employer.getIdCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idCode = resultSet.getInt("id_code");
                String offerName = resultSet.getString("offer_name");
                String offerDesc = resultSet.getString("offer_desc");
                String employerCode = resultSet.getString("employer_id");
                int categoryCode = resultSet.getInt("category_id");
                Category category = new Category(categoryCode);
                int offerState = resultSet.getInt("offer_state");
                String publicationDate = resultSet.getString("publication_date");
                String expireDate = resultSet.getString("expiration_date");
                double payment = resultSet.getDouble("payment");
                int modality = resultSet.getInt("modality");
                String direction = resultSet.getString("direction");
                String details = resultSet.getString("details");

                Offer offer = new Offer(idCode, offerName, offerDesc, new SelectEmployer().getEmployer(employer),
                        new SelectCategory().getCategory(category), offerState, publicationDate, expireDate, payment, modality, direction, details);

                offers.add(offer);
            }

        } catch (Exception ex) {
            System.out.println("Error en la consulta de ofertas\n" + ex.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }

        return offers;
    }

    private List<Integer> getIdCodesFromList(List<Application> apps) {
        List<Integer> idCodes = new ArrayList<>();
        for (Application app : apps) {
            idCodes.add(app.getOffer().getIdCode());
        }
        return idCodes;
    }

    public List<Integer> getIdCategories(User user) {
        return getIdCodesFromList(new SelectApplication().getAllApplicationsFromUser(user));
    }

    public List<Offer> getOffersByCategories(User user) {
        List<Offer> offers = new ArrayList<>();
        List<Offer> allOffers = getAllOffers();
        List<Integer> categories = new SelectUserCategories().getIdCategories(user);
        List<Integer> appliedOffers = getIdCategories(user);

        for (Offer offer : allOffers) {
            if (categories.contains(offer.getCategory().getIdCode()) && !appliedOffers.contains(offer.getIdCode())) {
                offers.add(offer);
            }
        }

        return offers;
    }

    public List<Offer> getActiveOffers(Employer employer) {
        List<Offer> offers = new ArrayList<>();
        List<Offer> allOffers = getOffersByEmployer(employer);

        for (Offer offer : allOffers) {
            if (offer.getOfferState() == 0) {
                offers.add(offer);
            }
        }
        return offers;
    }

}


