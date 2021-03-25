package model;

import controller.Cliente;
import controller.Endereco;
import controller.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FornecedorDAO {

    public static Fornecedor retreave(int pk_fornecedor) throws SQLException {

        Connection conn = BancoDados.createConnection();

        ResultSet rs = conn.createStatement().executeQuery(

                "select * from fornecedor where pk_fornecedor="+pk_fornecedor
        );

        Fornecedor f;

        if(rs.next()){

            f = new Fornecedor(
                    pk_fornecedor,
                    rs.getString("nome"),
                    rs.getString("cnpj")
            );

            f.setEnderecos(FornecedorEnderecoDAO.retreaveAll(pk_fornecedor));

        }else {

            throw new RuntimeException("Não existe um fornecedor com a chave "+pk_fornecedor);
        }

        return f;
    }

    public static void create(Fornecedor f) throws SQLException{

        Connection coon = BancoDados.createConnection();

        PreparedStatement stm = coon.prepareStatement(
                "insert into public.fornecedor(nome,cnpj) values (?,?)" ,
                PreparedStatement.RETURN_GENERATED_KEYS);

        stm.setString(1,f.getNome());
        stm.setString(2,f.getCnpj());

        stm.execute();

        ResultSet rs = stm.getGeneratedKeys();
        rs.next();

        f.setPk(rs.getInt(1));//recuperando a chave primaria que acabou de ser gerada durante a inserção e atribuindo
        //a propriedade da classe pessoa/pessoa fisica/funcionario

        for (Endereco e:f.getEnderecos()){
            e.setFk(f.getPk());//atribuindo a chave primaria que acabou de ser inserido na chave estrangeira do endereco
            ClienteEnderecoDAO.create(e);
        }
    }


    public static ArrayList<Fornecedor> retreaveAll() throws SQLException {

        Connection conn = BancoDados.createConnection();

        ResultSet rs = conn.createStatement().executeQuery(

                "select * from fornecedor"
        );

        ArrayList<Fornecedor> forn = new ArrayList<>();


        while(rs.next()){
            Fornecedor f=new Fornecedor(
                    rs.getInt("pk_fornecedor"),
                    rs.getString("nome"),
                    rs.getString("cnpj")

            );

            f.setEnderecos(FornecedorEnderecoDAO.retreaveAll(rs.getInt("pk_fornecedor")));

            forn.add(f);
        }
        return forn;
    }

    public static void update (Fornecedor f) throws SQLException {

        Connection coon = BancoDados.createConnection();

        PreparedStatement stm = coon.prepareStatement(
                "update fornecedor set nome=?,cnpj=? where pk_fornecedor=?"
        );

        stm.setInt(1,f.getPk());
        stm.setString(2,f.getNome());
        stm.setString(3, f.getCnpj());


        stm.execute();

        for(Endereco e : f.getEnderecos()){
            if (!e.isPersistido()) {
                FornecedorEnderecoDAO.update(e);
            }
        }
    }

    public static void delete(Fornecedor f) throws SQLException {
        delete(f.getPk());
        f.setPk(0);
    }

    public static void delete(int pk_fornecedor) throws SQLException {

        Connection coon = BancoDados.createConnection();

        coon.createStatement().execute(
                "delete from fornecedor where pk_fornecedor="+pk_fornecedor
        );
    }
}
