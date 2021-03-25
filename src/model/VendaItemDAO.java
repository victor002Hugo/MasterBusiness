package model;

import controller.Venda;
import controller.VendaItem;

import javax.lang.model.element.ModuleElement;
import java.sql.*;
import java.util.ArrayList;

public class VendaItemDAO {


    public static void create(VendaItem vd) throws SQLException {

        Connection coon = BancoDados.createConnection();

        PreparedStatement stm = coon.prepareStatement(
                "insert into public.venda_item(fk_venda,fk_produto,qtd,valor_unitario) values(?,?,?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS);

        stm.setInt(1, vd.getFkVenda().getPkVenda());
        stm.setInt(2, vd.getFkProduto().getPkProduto());
        stm.setInt(3, vd.getQtd());
        stm.setDouble(4,vd.getValorUnitario());

        stm.execute();

        ResultSet rs = stm.getGeneratedKeys();
        rs.next();

        vd.setPkItem(rs.getInt(1));
    }


    public static VendaItem retreave(int pkVendaItem) throws SQLException {

        Connection conn = BancoDados.createConnection();

        ResultSet rs = conn.createStatement().executeQuery(

                "select * from venda_item where pk_item="+pkVendaItem
        );

        VendaItem vd;

        if (rs.next()) {

            vd = new VendaItem(
                    pkVendaItem,
                    VendaDAO.retreave(rs.getInt("pk_venda")),
                    ProdutoDAO.retreave(rs.getInt("pk_produto")),
                    rs.getInt("qtd"),
                    rs.getDouble("valor_unitario")
            );

        } else {
            throw new RuntimeException("Venda com a chave " + pkVendaItem + " não existe");
        }
        return vd;
    }



    public static ArrayList<VendaItem> retreaveAll() throws SQLException {

        Connection conn = BancoDados.createConnection();

        ResultSet rs = conn.createStatement().executeQuery(

                "select * from venda_item"
        );

        ArrayList<VendaItem> vendasItem = new ArrayList<>();


        while(rs.next()){
            VendaItem vd=new VendaItem(
                    rs.getInt("pk_item"),
                    VendaDAO.retreave(rs.getInt("pk_venda")),
                    ProdutoDAO.retreave(rs.getInt("pk_produto")),
                    rs.getInt("qtd"),
                    rs.getDouble("valor_unitario")
            );

            vendasItem.add(vd);
        }
        return vendasItem;
    }


    public static void update (VendaItem vd) throws SQLException {

        Connection coon = BancoDados.createConnection();

        PreparedStatement stm = coon.prepareStatement(
                "update venda_item set fk_venda=?,fk_produto=?,qtd=?,valor_unitario=? where pk_item=?"
        );

        stm.setInt(1,vd.getFkVenda().getPkVenda());
        stm.setInt(2,vd.getFkProduto().getPkProduto());
        stm.setInt(3,vd.getQtd());
        stm.setDouble(4,vd.getValorUnitario());
        stm.setInt(5,vd.getPkItem());

        stm.execute();

    }

    public static void delete(VendaItem vd) throws SQLException {
        delete(vd.getPkItem());
        vd.setPkItem(0);
    }

    public static void delete(int pkVendaItem) throws SQLException {

        Connection coon = BancoDados.createConnection();

        coon.createStatement().execute(
                "delete from venda_item where pk_item="+pkVendaItem
        );


    }

}
