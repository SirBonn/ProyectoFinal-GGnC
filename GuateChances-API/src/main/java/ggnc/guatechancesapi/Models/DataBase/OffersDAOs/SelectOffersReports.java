package ggnc.guatechancesapi.Models.DataBase.OffersDAOs;

import com.mysql.cj.jdbc.ConnectionGroupManager;
import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Offer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SelectOffersReports {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public List<Offer> getOffersByTimes(String start, String end, String idCode) {
        List<Offer> offers = new ArrayList<>();

        String SQL_SELECT_BY_TIMES = "SELECT id_code, publication_date FROM offers " +
                "WHERE employer_id= ? publication_date BETWEEN ? AND ? ORDER BY publication_date";
        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_TIMES);
            preparedStatement.setString(1, idCode);
            preparedStatement.setString(2, start);
            preparedStatement.setString(3, end);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Offer offer = new SelectOffer().getOffer(new Offer(resultSet.getInt("id_code")));
                offers.add(offer);
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }

        return offers;
    }

    public double getTotalPayments(String idCode){
        double total = 0;

        String SQL_SELECT_TOTAL_PAYMENTS = " SELECT e.id_code AS employer_id, e.forename AS employer_name, SUM(op.plataformPayment) AS total_cost " +
                "FROM users e JOIN offers o ON e.id_code = o.employer_id JOIN offers_payments op ON o.id_code = op.offer_code " +
                "WHERE o.offer_state = 2 AND e.id_code = ? GROUP BY e.id_code, e.forename ORDER BY total_cost DESC";
        try {
            connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_TOTAL_PAYMENTS);
            preparedStatement.setString(1, idCode);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                total = resultSet.getDouble("total_cost");
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }

        return total;
    }

}
