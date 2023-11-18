package ggnc.guatechancesapi.Models.DataBase.CardDAOs;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Card;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectCard {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public Card searchByNumber(Card card) {

        String SLQ_SELECT_BY_CREDENTIALS = "SELECT * FROM  registered_cards WHERE no_card =?";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SLQ_SELECT_BY_CREDENTIALS);
            preparedStatement.setLong(1, card.getNumber());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Long number = resultSet.getLong("no_card");
                String name = resultSet.getString("titular");

                card = new Card(number, name, 000000);

            }
        } catch (SQLException ex) {
            System.out.println("Error en la consulta de tarjeta\n" + ex);
        } finally {
            DBConectionManager.close(resultSet);
            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);
        }

        return card;
    }

}
