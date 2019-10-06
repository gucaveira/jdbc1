package aplicacao;

import db.Db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RecuperarDados {

    public static void main(String[] args) {

        Connection connection;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Db.abreConexao();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from departamento");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("Id") + "," + resultSet.getString("Nome"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Db.fechandoResultSet(resultSet);
            Db.fechandoStatement(statement);
            Db.fechaConexao();
        }
    }
}
