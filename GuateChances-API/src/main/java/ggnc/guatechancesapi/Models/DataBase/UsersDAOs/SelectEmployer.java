package ggnc.guatechancesapi.Models.DataBase.UsersDAOs;

import ggnc.guatechancesapi.Models.DataBase.CardDAOs.SelectCard;
import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Card;
import ggnc.guatechancesapi.Models.Domain.Employer;
import ggnc.guatechancesapi.Models.Domain.User;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectEmployer {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public Employer getEmployer(Employer employer) {
        String SQL_SELECT_EMP = "SELECT * FROM employers WHERE  id_code = ? ";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_EMP);
            preparedStatement.setString(1, employer.getIdCode());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String idCode = resultSet.getString("id_code");
                String vision = resultSet.getString("vision");
                String mision = resultSet.getString("mision");
                int noCard = resultSet.getInt("no_card");
                User user = new SelectUser().getUserByID(new User(idCode));
                employer.setVision(vision);
                employer.setMision(mision);
                employer.setCard(new SelectCard().searchByNumber(new Card(noCard)));
                employer.setUserOnEmployer(user);
            }

        } catch (SQLException | ErrorOcurredException ex) {
            System.out.println("Error en la consulta de usuario\n" + ex.getMessage());
        } finally {

            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }

        return employer;
    }

    public List<Employer> getAllEmployers() {
        List<User> allUsers = new SelectUser().getAllUsers();
        List<Employer> allEmployees = new ArrayList<>();

        for (User user : allUsers) {
            if (user.getUsertype() == 2) {
                Employer employee = new Employer(user);
                allEmployees.add(getEmployer(employee));
            }
        }

        return allEmployees;
    }

}
