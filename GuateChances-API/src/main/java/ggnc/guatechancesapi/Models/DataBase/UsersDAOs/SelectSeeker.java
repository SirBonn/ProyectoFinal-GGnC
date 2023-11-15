package ggnc.guatechancesapi.Models.DataBase.UsersDAOs;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.JobSeeker;
import ggnc.guatechancesapi.Models.Domain.User;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectSeeker {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    public JobSeeker getSeekerByID(JobSeeker seeker){

        String SQL_SELECT_SEEKER = "SELECT * FROM jobSeekers WHERE id_code = ?";
        try {
            User user = new SelectUser().getUserByID(seeker);
            seeker.setUserOnSeeker(user);
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_SEEKER);
            preparedStatement.setString(1, seeker.getIdCode());
            resultSet = preparedStatement.executeQuery();

//            while (resultSet.next()) {
//                InputStream curriculumPDF = resultSet.getBinaryStream("curriculumPDF");
//                seeker.setCv(curriculumPDF);
//            }

        } catch (Exception ex) {
            System.out.println("Error en la consulta de usuario\n" + ex.getMessage());
        } finally {

            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }

        return seeker;
    }
    public JobSeeker getCompleteSeekerByID(JobSeeker seeker){

        String SQL_SELECT_SEEKER = "SELECT * FROM jobSeekers WHERE id_code = ?";
        try {
            User user = new SelectUser().getUserByID(seeker);
            seeker.setUserOnSeeker(user);
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_SEEKER);
            preparedStatement.setString(1, seeker.getIdCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                InputStream curriculumPDF = resultSet.getBinaryStream("curriculumPDF");
                seeker.setCv(curriculumPDF);
            }

        } catch (Exception ex) {
            System.out.println("Error en la consulta de usuario\n" + ex.getMessage());
        } finally {

            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }

        return seeker;
    }

}
