package aplicacao;

import db.Db;

import java.sql.Connection;

public class Programa {

    public static void main(String[] args) {

        Connection conn = Db.abreConexao();
        Db.fechaConexao();
    }
}
