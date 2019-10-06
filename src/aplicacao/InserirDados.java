package aplicacao;

import db.Db;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InserirDados {

    public static final int VALOR_DE_UMA_COLUNA = 1;

    public static void main(String[] args) {

        Connection connection;
        PreparedStatement preparedStatement = null;
        SimpleDateFormat dataFormatada = new SimpleDateFormat("DD/MM/YYYY");

        try {
            connection = Db.abreConexao();

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO VENDAS" +
                            "(Nome, Email, Aniversario, SalarioBase, DepartamentoId)" +
                            "VALUES" +
                            "(?, ?, ?, ?, ?)" ,Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, "Carl Purple");
            preparedStatement.setString(2, "Carl@gmail.com");
            preparedStatement.setDate(3, new java.sql.Date(
                    dataFormatada.parse("22/04/1985").getTime()));
            preparedStatement.setDouble(4, 3000.0);
            preparedStatement.setInt(5, 4);

            int linhaExecutada = preparedStatement.executeUpdate();

           if (linhaExecutada > 0){
               ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
               while (generatedKeys.next()){
                   int id = generatedKeys.getInt(VALOR_DE_UMA_COLUNA);
                   System.out.println("Feito! Id = " +  id);
               }
           }else {
               System.out.println("Nem uma linha alterada");
           }

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ParseException e) {
            e.printStackTrace();

        } finally {
            Db.fechandoStatement(preparedStatement);
            Db.fechaConexao();
        }
    }
}
