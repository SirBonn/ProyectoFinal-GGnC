package ggnc.guatechancesapi.Models.DataBase.ApplicationsDAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.DataBase.OffersDAOs.SelectOffer;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.SelectSeeker;
import ggnc.guatechancesapi.Models.Domain.Application;
import ggnc.guatechancesapi.Models.Domain.JobSeeker;
import ggnc.guatechancesapi.Models.Domain.Offer;
import ggnc.guatechancesapi.Models.Domain.User;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

public class SelectApplication {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public Application selectApplicationByID(Application app) throws ErrorOcurredException {

        String SQL_SELECT_BY_ID = "SELECT * FROM applications WHERE id_code = ?";
        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setInt(1, app.getIdCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idCode = resultSet.getInt("id_code");
                String idSeeker = resultSet.getString("seeker_code");
                JobSeeker jobSeeker = new JobSeeker(idSeeker);
                int idOffer = resultSet.getInt("offer_code");
                Offer offer = new SelectOffer().getOffer(new Offer(idOffer));
                String seekerMessage = resultSet.getString("seeker_message");
                int state = resultSet.getInt("application_state");

                jobSeeker = new SelectSeeker().getSeekerByID(jobSeeker);
                app = new Application(idCode, jobSeeker, offer, seekerMessage, state);
            }

        } catch (Exception e) {
            System.out.println("Error en la consulta de usuario\n" + e.getMessage());
            throw new ErrorOcurredException("Error en la consulta de application:" + e.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }
        return app;
    }

    public List<Application> getAllApplications() {
        List<Application> applications = new ArrayList<>();
        String SQL_SELECT_ALL = "SELECT * FROM applications";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idCode = resultSet.getInt("id_code");
                String idSeeker = resultSet.getString("seeker_code");
                JobSeeker jobSeeker = new JobSeeker(idSeeker);
                int idOffer = resultSet.getInt("offer_code");
                Offer offer = new SelectOffer().getOffer(new Offer(idOffer));
                String seekerMessage = resultSet.getString("seeker_message");
                int state = resultSet.getInt("application_state");

                jobSeeker = new SelectSeeker().getSeekerByID(jobSeeker);
                applications.add(new Application(idCode, jobSeeker, offer, seekerMessage, state));
            }

        } catch (Exception e) {
            System.out.println("Error en la consulta de usuario\n" + e.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }

        return applications;
    }

    public List<Application> getApplicationsByOffer(Offer offer) throws ErrorOcurredException {

        List<Application> applications = new ArrayList<>();
        String SQL_SELECT_ALL = "SELECT * FROM applications WHERE offer_code = ?";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            preparedStatement.setInt(1, offer.getIdCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idCode = resultSet.getInt("id_code");
                String idSeeker = resultSet.getString("seeker_code");
                JobSeeker jobSeeker = new JobSeeker(idSeeker);
                int idOffer = resultSet.getInt("offer_code");
                offer = new SelectOffer().getOffer(new Offer(idOffer));
                String seekerMessage = resultSet.getString("seeker_message");
                int state = resultSet.getInt("application_state");

                jobSeeker = new SelectSeeker().getSeekerByID(jobSeeker);
                applications.add(new Application(idCode, jobSeeker, offer, seekerMessage, state));
            }

        } catch (Exception e) {
            System.out.println("Error en la consulta de usuario\n" + e.getMessage());
            throw new ErrorOcurredException("Error en la consulta de application:" + e.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }

        return applications;

    }

    public List<Application> getAllApplicationsFromUser(User user) {
        List<Application> applications = new ArrayList<>();
        String SQL_SELECT_ALL = "SELECT * FROM applications WHERE seeker_code = ?";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            preparedStatement.setString(1, user.getIdCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idCode = resultSet.getInt("id_code");
                String idSeeker = resultSet.getString("seeker_code");
                JobSeeker jobSeeker = new JobSeeker(idSeeker);
                int idOffer = resultSet.getInt("offer_code");
                Offer offer = new SelectOffer().getOffer(new Offer(idOffer));
                String seekerMessage = resultSet.getString("seeker_message");
                int state = resultSet.getInt("application_state");

                jobSeeker = new SelectSeeker().getSeekerByID(jobSeeker);
                applications.add(new Application(idCode, jobSeeker, offer, seekerMessage, state));
            }

        } catch (Exception e) {
            System.out.println("Error en la consulta de usuario\n" + e.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }

        return applications;
    }


    private List<Integer> getIdCodesFromList(List<Application> apps) {
        List<Integer> idCodes = new ArrayList<>();
        for (Application app : apps) {
            idCodes.add(app.getIdCode());
        }
        return idCodes;
    }

    public List<Integer> getIdApplications(User user) {
        return getIdCodesFromList(getAllApplicationsFromUser(user));
    }

    public int identifyApplication(User user, Offer offer) {
        int idCode = -1;
        String SQL_SELECT_ALL = "SELECT id_code FROM applications WHERE seeker_code = ? AND offer_code = ?";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            preparedStatement.setString(1, user.getIdCode());
            preparedStatement.setInt(2, offer.getIdCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                idCode = resultSet.getInt("id_code");
            }

        } catch (Exception e) {
            System.out.println("Error en la consulta de usuario\n" + e.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }

        return idCode;
    }


}
