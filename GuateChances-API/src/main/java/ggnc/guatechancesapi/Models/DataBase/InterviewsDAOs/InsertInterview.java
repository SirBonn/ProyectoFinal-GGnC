package ggnc.guatechancesapi.Models.DataBase.InterviewsDAOs;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Interview;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertInterview {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public void insertInterview(Interview interview) throws ErrorOcurredException {

        String SQL_INSERT = "INSERT INTO interviews (application_code, interview_date, inteview_time, interview_state, direction, notes)" +
                " VALUES (?, ?, ?, ?, ?, ?)";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1, interview.getApplication().getIdCode());
            preparedStatement.setString(2, interview.getStringInterviewDate());
            preparedStatement.setString(3, interview.getStringInterviewTime());
            preparedStatement.setInt(4, interview.getInterviewState());
            preparedStatement.setString(5, interview.getDirection());
            preparedStatement.setString(6, interview.getNotes());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ErrorOcurredException("Error al insertar la categoria: " + e.getMessage());
        } finally {
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }
    }
}
