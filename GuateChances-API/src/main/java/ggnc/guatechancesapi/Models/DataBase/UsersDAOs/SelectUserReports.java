package ggnc.guatechancesapi.Models.DataBase.UsersDAOs;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Employer;
import ggnc.guatechancesapi.Models.Domain.EmployerByOffer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SelectUserReports {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public List<EmployerByOffer> getTopEmployersPublisher() {
        List<EmployerByOffer> topEmployers = new ArrayList<>();

        String SQL_SELECT_TOP_EMPLOYERS = "SELECT employers.id_code AS employer_id, COUNT(offers.id_code) AS total_ofertas_publicadas" +
                " FROM employers JOIN offers ON employers.id_code = offers.employer_id GROUP BY employers.id_code ORDER BY total_ofertas_publicadas DESC LIMIT 5";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_TOP_EMPLOYERS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String idCode = resultSet.getString("employer_id");
                Employer employer = new SelectEmployer().getEmployer(new Employer(idCode));
                int totalOffers = resultSet.getInt("total_ofertas_publicadas");
                EmployerByOffer employerByOffer = new EmployerByOffer(employer, totalOffers);
                topEmployers.add(employerByOffer);

            }
        } catch (Exception ex) {
            System.out.println("Error en la obtencion de los empleadores con mas ofertas publicadas\n" + ex.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }
        return topEmployers;
    }

    public List<Employer> getTopEmployersContributedBy(String start, String end) {
        List<Employer> topEmployers = new ArrayList<>();

        String SQL_SELECT_TOP_EMPLOYERS = "SELECT u.id_code AS employer_id, u.forename AS employer_name, SUM(op.plataformPayment) AS total_income " +
                "FROM users u JOIN offers o ON u.id_code = o.employer_id JOIN offers_payments op ON o.id_code = op.offer_code " +
                "WHERE op.payment_date BETWEEN ? AND ? GROUP BY u.id_code, u.forename ORDER BY total_income " +
                "DESC LIMIT 5";

        try {
            this.connection = DBConectionManager.getConnection();

            preparedStatement = connection.prepareStatement(SQL_SELECT_TOP_EMPLOYERS);
            preparedStatement.setString(1, start);
            preparedStatement.setString(2, end);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String idCode = resultSet.getString("employer_id");
                Employer employer = new SelectEmployer().getEmployer(new Employer(idCode));
                double totalOffers = resultSet.getDouble("total_income");
                employer.setPlataformPayments(totalOffers);
                topEmployers.add(employer);

            }
        } catch (Exception ex) {
            System.out.println("Error en la obtencion de los empleadores con mas ofertas publicadas\n" + ex.getMessage());
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }
        return topEmployers;
    }



}
