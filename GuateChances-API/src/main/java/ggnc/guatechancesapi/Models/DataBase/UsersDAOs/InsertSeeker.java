package ggnc.guatechancesapi.Models.DataBase.UsersDAOs;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.JobSeeker;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertSeeker {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public void insertPDFtoSeeker(JobSeeker jobSeeker) throws ErrorOcurredException {

        String SQL_INSERT = "UPDATE jobSeekers SET curriculumPDF = ? WHERE id_code = ?";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setBlob(1, jobSeeker.getCv());
            preparedStatement.setString(2, jobSeeker.getIdCode());

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al insertar pdf: " + e.getMessage());
            throw new ErrorOcurredException("Error al insertar pdf: "+e.getMessage());
        } finally {

                DBConectionManager.close(preparedStatement);
                DBConectionManager.close(connection);
        }

    }

}