package ggnc.guatechancesapi.Models.DataBase.InterviewsDAOs;

import ggnc.guatechancesapi.Models.DataBase.ApplicationsDAOs.SelectApplication;
import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.*;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SelectInterview {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public List<Interview> getAllInterviews() throws ErrorOcurredException{

        List<Interview> allInterviews = new ArrayList<>();

        String SQL_SELECT_ALL_INTERVIEWS = "SELECT * FROM interviews";

        try {

            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_INTERVIEWS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idCode = resultSet.getInt("id_code");
                Application application = new Application(resultSet.getInt("application_code"));
                application = new SelectApplication().selectApplicationByID(application);
                String interviewDate = resultSet.getString("interview_date");
                String interviewTime = resultSet.getString("inteview_time");
                int interviewState = resultSet.getInt("interview_state");
                String direction = resultSet.getString("direction");
                String notes = resultSet.getString("notes");

                Interview interview = new Interview(idCode, application, interviewDate, interviewTime, interviewState, direction, notes);
                allInterviews.add(interview);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ErrorOcurredException("Error al obtener todas las entrevistas:" + e.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }
        return allInterviews;

    }

    public Interview getInterviewByCode(Interview interview) throws ErrorOcurredException{


        String SQL_SELECT_INTERVIEW = "SELECT * FROM interviews WHERE id_code = ?";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_INTERVIEW);
            preparedStatement.setInt(1, interview.getIdCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idCode = resultSet.getInt("id_code");
                Application application = new Application(resultSet.getInt("application_code"));
                application = new SelectApplication().selectApplicationByID(application);
                String interviewDate = resultSet.getString("interview_date");
                String interviewTime = resultSet.getString("inteview_time");
                int interviewState = resultSet.getInt("interview_state");
                String direction = resultSet.getString("direction");
                String notes = resultSet.getString("notes");

                interview = new Interview(idCode, application, interviewDate, interviewTime, interviewState, direction, notes);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ErrorOcurredException("Error al obtener todas las entrevistas:" + e.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }

        return interview;
    }

    public Interview getInterviewByApplication(Application app) throws ErrorOcurredException{
        Interview interview = new Interview();

        String SQL_SELECT_INTERVIEW = "SELECT * FROM interviews WHERE application_code = ?";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_INTERVIEW);
            preparedStatement.setInt(1, app.getIdCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idCode = resultSet.getInt("id_code");
                Application application = new Application(resultSet.getInt("application_code"));
                application = new SelectApplication().selectApplicationByID(application);
                String interviewDate = resultSet.getString("interview_date");
                String interviewTime = resultSet.getString("inteview_time");
                int interviewState = resultSet.getInt("interview_state");
                String direction = resultSet.getString("direction");
                String notes = resultSet.getString("notes");

                interview = new Interview(idCode, application, interviewDate, interviewTime, interviewState, direction, notes);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ErrorOcurredException("Error al obtener todas las entrevistas:" + e.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }

        return interview;
    }

    public List<Interview> getInterviewsBySeeker(User user) throws ErrorOcurredException{

        List<Interview> interviews = new ArrayList<>();
        String SQL_SELECT_ALL_INTERVIEWS_BY_USER = "SELECT * FROM interviews JOIN applications " +
                "ON interviews.application_code = applications.id_code WHERE applications.seeker_code = ?";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_INTERVIEWS_BY_USER);
            preparedStatement.setString(1, user.getIdCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idCode = resultSet.getInt("id_code");
                Application application = new Application(resultSet.getInt("application_code"));
                application = new SelectApplication().selectApplicationByID(application);
                String interviewDate = resultSet.getString("interview_date");
                String interviewTime = resultSet.getString("inteview_time");
                int interviewState = resultSet.getInt("interview_state");
                String direction = resultSet.getString("direction");
                String notes = resultSet.getString("notes");

                Interview interview = new Interview(idCode, application, interviewDate, interviewTime, interviewState, direction, notes);
                interviews.add(interview);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ErrorOcurredException("Error al obtener todas las entrevistas:" + e.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }

        return interviews;
    }

    public List<Interview> getInterviewsByOffer(Offer offer) throws ErrorOcurredException{
        List<Interview> interviews = new ArrayList<>();

        String SQL_SELECT_ALL_INTERVIEWS_BY_OFFER = "SELECT * FROM interviews JOIN applications " +
                "ON interviews.application_code = applications.id_code WHERE applications.offer_code = ?";

        try {

            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_INTERVIEWS_BY_OFFER);
            preparedStatement.setInt(1, offer.getIdCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idCode = resultSet.getInt("id_code");
                Application application = new Application(resultSet.getInt("application_code"));
                application = new SelectApplication().selectApplicationByID(application);
                String interviewDate = resultSet.getString("interview_date");
                String interviewTime = resultSet.getString("inteview_time");
                int interviewState = resultSet.getInt("interview_state");
                String direction = resultSet.getString("direction");
                String notes = resultSet.getString("notes");

                Interview interview = new Interview(idCode, application, interviewDate, interviewTime, interviewState, direction, notes);
                interviews.add(interview);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ErrorOcurredException("Error al obtener todas las entrevistas:" + e.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }

        return interviews;
    }

    public List<Interview> getInterviewsByEmployer(Employer employer) throws ErrorOcurredException{
        List<Interview> interviews = new ArrayList<>();

        String SQL_SELECT_ALL_INTERVIEWS_BY_OFFER = "SELECT * FROM interviews JOIN applications ON interviews.application_code = applications.id_code " +
                "JOIN offers ON applications.offer_code = offers.id_code WHERE offers.employer_id = ?";



        try {

            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_INTERVIEWS_BY_OFFER);
            preparedStatement.setString(1, employer.getIdCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idCode = resultSet.getInt("id_code");
                Application application = new Application(resultSet.getInt("application_code"));
                application = new SelectApplication().selectApplicationByID(application);
                String interviewDate = resultSet.getString("interview_date");
                String interviewTime = resultSet.getString("inteview_time");
                int interviewState = resultSet.getInt("interview_state");
                String direction = resultSet.getString("direction");
                String notes = resultSet.getString("notes");

                Interview interview = new Interview(idCode, application, interviewDate, interviewTime, interviewState, direction, notes);
                interviews.add(interview);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ErrorOcurredException("Error al obtener todas las entrevistas:" + e.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }

        return interviews;
    }

    public List<Interview> getInterviewsByDate(String date, String idCode){
        List<Interview> interviews = new ArrayList<>();

        String SQL_SELECT_BY_DATE = "SELECT i.id_code, i.interview_date, i.inteview_time, i.interview_state, i.direction, i.notes " +
                "FROM interviews i JOIN applications a ON i.application_code = a.id_code JOIN offers o ON a.offer_code = o.id_code " +
                "JOIN employers e ON o.employer_id = e.id_code WHERE i.interview_date = ?  AND e.id_code = ? ORDER BY i.interview_date, i.inteview_time;";

        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_DATE);
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, idCode);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Interview interview = new SelectInterview().getInterviewByCode(new Interview(resultSet.getInt("id_code")));
                interviews.add(interview);
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }

        return interviews;
    }

}
