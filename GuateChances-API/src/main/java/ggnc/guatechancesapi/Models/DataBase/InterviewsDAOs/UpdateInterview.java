package ggnc.guatechancesapi.Models.DataBase.InterviewsDAOs;

import ggnc.guatechancesapi.Models.DataBase.ApplicationsDAOs.UpdateApplication;
import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.DataBase.OffersDAOs.UpdateOffer;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.UpdateEmployer;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.UpdateUser;
import ggnc.guatechancesapi.Models.Domain.Interview;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateInterview {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public void updateStateInterview(Interview interview, boolean isContract){

        String SQL_UPDATE = "UPDATE interviews SET interview_state = ?, notes = ? WHERE id_code = ?";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setInt(1, interview.getInterviewState());
            preparedStatement.setString(2, interview.getNotes());
            preparedStatement.setInt(3, interview.getIdCode());
            preparedStatement.executeUpdate();

            new UpdateApplication().updateApplicationState(interview.getApplication(), isContract); //update aplication state = 1 seleccionado/rechazado

            if(isContract){

                new UpdateUser().contractUser(interview.getApplication().getSeeker()); //set user isActive = 2 //contratado
                new UpdateOffer(this.connection).updateOfferState(interview.getApplication().getOffer()); //finalize offer
                new UpdateOffer(this.connection).setSeekerSelected(interview.getApplication().getSeeker(), interview.getApplication().getOffer()); //set seeker selected
                new UpdateEmployer(this.connection).payOffer(interview.getApplication().getOffer().getEmployer()); //pay offer

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }

    }
}
