package aplicacao;

import db.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AtualizarDados {

    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement st = null;

        try {
            connection = Db.abreConexao();

            st = connection.prepareStatement(
                    "UPDATE vendas "
                            + "SET SalarioBase=SalarioBase + ?  "
                            + "WHERE  "
                            + "(DepartamentoId = ?)");

            st.setDouble(1, 200);
            st.setInt(2, 2);

            int LinhaAfetadas = st.executeUpdate();

            System.out.println("Feito Linhas Afetadas" + LinhaAfetadas);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Db.fechandoStatement(st);
            Db.fechaConexao();
        }
    }
}
