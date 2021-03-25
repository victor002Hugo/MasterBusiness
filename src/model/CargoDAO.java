package model;

import controller.Cargo;

import java.sql.*;
import java.util.ArrayList;

//Data access Object da classe controller.cargo, realizando o mapeamento objeto relacional
public class CargoDAO {
    public static void create(Cargo c) throws SQLException {

        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO public.cargo(nome, descricao) VALUES (?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        );

        stm.setString(1, c.getNome());
        stm.setString(2, c.getDescricao());

        stm.execute();

        ResultSet rs = stm.getGeneratedKeys();//retorna a chave primária gerada
        rs.next();

        c.setPkCargo(rs.getInt(1));

        stm.close();
    }


    public static Cargo retreave(int pkCargo) throws SQLException {
        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement(
                "select * from cargo where pk_cargo = ?"
        );

        stm.setInt(1, pkCargo);
        //ResultSet  = Tabela do Banco de Dados
        ResultSet rs = stm.executeQuery();
        rs.next();


        return new Cargo(
                rs.getString("descricao"),
                rs.getString("nome"),
                rs.getInt("pk_cargo")
        );
    }

    public static ArrayList<Cargo> retreaveAll() throws SQLException {
        Connection conn = BancoDados.createConnection();

        ResultSet rs = conn.createStatement().executeQuery("select * from cargo");

        ArrayList<Cargo> aux = new ArrayList<>();

        while (rs.next()) {
            aux.add(
                    new Cargo(
                            rs.getString("descricao"),
                            rs.getString("nome"),
                            rs.getInt("pk_cargo")
                    ));

        }

        return aux;
    }

    public static void update(Cargo c) throws SQLException {

        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement(
                "update cargo set nome = ?, descricao = ? where pk_cargo = ?"
        );

        stm.setString(1, c.getNome());
        stm.setString(2, c.getDescricao());
        stm.setInt(3, c.getPkCargo());

        stm.execute();
        stm.close();
    }

    public static void delete(Cargo c) throws SQLException {
        delete(c.getPkCargo());
        c.setPkCargo(0);
    }

    public static void delete(int pk_cargo) throws SQLException {

        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement(
                "delete from cargo where pk_cargo = ?"
        );

        stm.setInt(1, pk_cargo);

        stm.execute();
        stm.close();
    }
}
