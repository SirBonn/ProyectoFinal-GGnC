package ggnc.guatechancesapi.Models.DataBase.InterviewsDAOs;

import ggnc.guatechancesapi.Models.DataBase.ApplicationsDAOs.SelectApplication;
import ggnc.guatechancesapi.Models.DataBase.ApplicationsDAOs.UpdateApplication;
import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Interview;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertInterview {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public boolean insertInterview(Interview interview) {

        String SQL_INSERT = "INSERT INTO interviews (application_code, interview_date, inteview_time, interview_state, direction, notes)" +
                " VALUES (?, ?, ?, ?, ?, ?)";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);

            if (interview.getApplication().getIdCode() == -1) {
                int appCode = new SelectApplication().identifyApplication(interview.getApplication().getSeeker(), interview.getApplication().getOffer());
                preparedStatement.setInt(1, appCode);
            } else {
                preparedStatement.setInt(1, interview.getApplication().getIdCode());
            }

            preparedStatement.setString(2, interview.getStringInterviewDate());
            preparedStatement.setString(3, interview.getStringInterviewTime());
            preparedStatement.setInt(4, interview.getInterviewState());
            preparedStatement.setString(5, interview.getDirection());
            preparedStatement.setString(6, interview.getNotes());
            preparedStatement.executeUpdate();


            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }
    }

    public void insertInterviewWithUpdate(Interview interview) {

        String SQL_INSERT = "INSERT INTO interviews (application_code, interview_date, inteview_time, interview_state, direction, notes)" +
                " VALUES (?, ?, ?, ?, ?, ?)";

        try {
            new UpdateApplication().updateApplicationState(interview.getApplication(), true); //update aplication state = 1 seleccionado/

            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);

            if (interview.getApplication().getIdCode() == -1) {
                int appCode = new SelectApplication().identifyApplication(interview.getApplication().getSeeker(), interview.getApplication().getOffer());
                preparedStatement.setInt(1, appCode);
            } else {
                preparedStatement.setInt(1, interview.getApplication().getIdCode());
            }

            preparedStatement.setString(2, interview.getStringInterviewDate());
            preparedStatement.setString(3, interview.getStringInterviewTime());
            preparedStatement.setInt(4, interview.getInterviewState());
            preparedStatement.setString(5, interview.getDirection());
            preparedStatement.setString(6, interview.getNotes());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }

    }
}
