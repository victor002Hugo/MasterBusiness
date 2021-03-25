package model;

import controller.Cliente;
import controller.Endereco;
import controller.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {

    public static void create(Cliente c) throws SQLException{

        Connection coon = BancoDados.createConnection();

        PreparedStatement stm = coon.prepareStatement(
                "insert into public.cliente(nome,cpf) values (?,?)" ,
                PreparedStatement.RETURN_GENERATED_KEYS);

        stm.setString(1,c.getNome());
        stm.setString(2,c.getCpf());

        stm.execute();

        ResultSet rs = stm.getGeneratedKeys();
        rs.next();

        c.setPk(rs.getInt(1));//recuperando a chave primaria que acabou de ser gerada durante a inserção e atribuindo
        //a propriedade da classe pessoa/pessoa fisica/funcionario

        for (Endereco e:c.getEnderecos()){
            e.setFk(c.getPk());//atribuindo a chave primaria que acabou de ser inserido na chave estrangeira do endereco
            ClienteEnderecoDAO.create(e);
        }


    }

    public static Cliente retreave(int pk_cliente) throws SQLException {

        Connection coon = BancoDados.createConnection();

        ResultSet rs = coon.createStatement().executeQuery(

                "select * from cliente where pk_cliente="+pk_cliente
        );

        Cliente c;

        if(rs.next()){

            c = new Cliente(
                    rs.getString("cpf"),
                    rs.getInt("pk_cliente"),
                    rs.getString("nome")
            );

            c.setEnderecos(ClienteEnderecoDAO.retreaveAll(pk_cliente));

        }else{
            throw new RuntimeException("Cliente com a chave "+pk_cliente+" não existe");
        }
        return c;
    }


    public static ArrayList<Cliente> retreaveAll() throws SQLException {

        Connection conn = BancoDados.createConnection();

        ResultSet rs = conn.createStatement().executeQuery(

                "select * from cliente"
        );

        ArrayList<Cliente> cli = new ArrayList<>();


        while(rs.next()){
            Cliente c=new Cliente(
                    rs.getInt("pk_cliente"),
                    rs.getString("nome"),
                    rs.getString("cpf")

            );

            c.setEnderecos(ClienteEnderecoDAO.retreaveAll(rs.getInt("pk_cliente")));

            cli.add(c);
        }
        return cli;
    }

    public static void update (Cliente c) throws SQLException {

        Connection coon = BancoDados.createConnection();

        PreparedStatement stm = coon.prepareStatement(
                "update cliente set nome=?,cpf=? where pk_cliente=?"
        );

        stm.setInt(1,c.getPk());
        stm.setString(2,c.getNome());
        stm.setString(3, c.getCpf());


        stm.execute();

        for(Endereco e : c.getEnderecos()){
            if (!e.isPersistido()) {
                ClienteEnderecoDAO.update(e);
            }
        }
    }

    public static void delete(Cliente c) throws SQLException {
        delete(c.getPk());
        c.setPk(0);
    }

    public static void delete(int pk_cliente) throws SQLException {

        Connection coon = BancoDados.createConnection();

        coon.createStatement().execute(
                "delete from cliente where pk_cliente="+pk_cliente
        );
    }
}
