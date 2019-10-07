package aplicacao;

import db.Db;
import db.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletarDados {

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = Db.abreConexao();

            preparedStatement = conn.prepareStatement(
                    "DELETE FROM vendas " +
                            "WHERE " +
                            "Id = ?");
            preparedStatement.setInt(1, 11);

            int LinhasAfetadas = preparedStatement.executeUpdate();
            System.out.println("Feito! Linhas afetadas: " + LinhasAfetadas);

        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            Db.fechandoStatement(preparedStatement);
            Db.fechaConexao();
        }
    }
}
