package ggnc.guatechancesapi.Models.DataBase.CardDAOs;

import ggnc.guatechancesapi.Models.DataBase.DBConectionManager;
import ggnc.guatechancesapi.Models.Domain.Card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertCard {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public boolean insertEmployerCard(Card card) {

        String SQL_INSERT = "INSERT INTO registered_cards (no_card, titular, csv) VALUES (?, ?, ?)";

        try {
            this.connection = DBConectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setLong(1, card.getNumber());
            preparedStatement.setString(2, card.getTitular());
            preparedStatement.setInt(3, card.getCsv());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar tarjeta\n" + e.getMessage());
            return false;
        } finally {

            DBConectionManager.close(preparedStatement);
            DBConectionManager.close(connection);

        }

    }

}
