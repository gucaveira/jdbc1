package aplicacao;

import db.Db;
import db.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class transacoes {

    public static void main(String[] args) {

        Connection conn = null;
        Statement statement = null;

        try {
            conn = Db.abreConexao();

            conn.setAutoCommit(false);

            statement = conn.createStatement();

            int linhas1 = statement.executeUpdate(
                    "UPDATE vendas SET SalarioBase = 2090 WHERE  DepartamentoId = 1");

            int x = 1;
            if (x < 2) {
                throw new SQLException("ERRO FALSO");
            }

            int linhas2 = statement.executeUpdate(
                    "UPDATE vendas SET SalarioBase = 3090 WHERE  DepartamentoId = 2");

        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbException("Transação não efetuada! Causado por: " + e.getMessage());
            } catch (SQLException ex) {
                throw new DbException("Erro ao voltar a transação! + Causado por: " + ex.getMessage());
            }
        } finally {
            Db.fechandoStatement(statement);
            Db.fechaConexao();
        }
    }
}
