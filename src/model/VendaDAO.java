package model;

import controller.Endereco;
import controller.Funcionario;
import controller.Venda;

import java.sql.*;
import java.util.ArrayList;

public class VendaDAO {

    public static void create(Venda v) throws SQLException {

        Connection coon = BancoDados.createConnection();

        PreparedStatement stm = coon.prepareStatement(
                "insert into public.venda(fk_cliente,fk_vendedor,numero,data) values(?,?,?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS);

        stm.setInt(1, v.getFkCliente().getPk());
        stm.setInt(2, v.getFkVendedor().getPk());
        stm.setInt(3, v.getNumero());
        stm.setDate(4, (Date) v.getData());

        stm.execute();

        ResultSet rs = stm.getGeneratedKeys();
        rs.next();

        v.setPkVenda(rs.getInt(1));
    }




    public static Venda retreave(int pkVenda) throws SQLException {

        Connection conn = BancoDados.createConnection();

        ResultSet rs = conn.createStatement().executeQuery(

                "select * from venda where pk_venda="+pkVenda
        );

        Venda v;

        if (rs.next()) {

            v = new Venda(
                    pkVenda,
                    ClienteDAO.retreave(rs.getInt("pk_cliente")),
                    FuncionarioDAO.retreave(rs.getInt("pk_funcionario")),
                    rs.getInt("numero"),
                    rs.getDate("data")
            );

        } else {
            throw new RuntimeException("Venda com a chave " + pkVenda + " não existe");
        }
        return v;
    }




    public static ArrayList<Venda> retreaveAll() throws SQLException {

        Connection conn = BancoDados.createConnection();

        ResultSet rs = conn.createStatement().executeQuery(

                "select * from venda"
        );

        ArrayList<Venda> vendas = new ArrayList<>();


        while(rs.next()){
            Venda v=new Venda(
                    rs.getInt("pk_venda"),
                    ClienteDAO.retreave(rs.getInt("pk_cliente")),
                    FuncionarioDAO.retreave(rs.getInt("pk_funcionario")),
                    rs.getInt("numero"),
                    rs.getDate("data")
            );

            vendas.add(v);
        }
        return vendas;
    }



    public static void update (Venda v) throws SQLException {

        Connection coon = BancoDados.createConnection();

        PreparedStatement stm = coon.prepareStatement(
                "update venda set fk_cliente?,fk_vendedor=?,numero=?,data=? where pk_venda=?"
        );

        stm.setInt(1,v.getFkCliente().getPk());
        stm.setInt(2,v.getFkVendedor().getPk());
        stm.setInt(3,v.getNumero());
        stm.setDate(4, (Date) v.getData());
        stm.setInt(5,v.getPkVenda());

        stm.execute();

    }

    public static void delete(Venda v) throws SQLException {
        delete(v.getPkVenda());
        v.setPkVenda(0);
    }

    public static void delete(int pkVenda) throws SQLException {

        Connection coon = BancoDados.createConnection();

        coon.createStatement().execute(
                "delete from venda where pk_venda="+pkVenda
        );


    }

}
