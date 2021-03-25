package model;

import controller.Cargo;
import controller.Endereco;
import controller.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuncionarioDAO {

   public static void create(Funcionario f) throws SQLException{

       Connection coon = BancoDados.createConnection();

       if(f.getCargo()==null || f.getCargo().getPkCargo()==0){
           throw new RuntimeException("ERRO: Cargo não esta cadastrado");
       }

       PreparedStatement stm = coon.prepareStatement(
               "insert into public.funcionario(fk_cargo,nome,cpf) values (?,?,?)" ,
               PreparedStatement.RETURN_GENERATED_KEYS);

       stm.setInt(1,f.getCargo().getPkCargo());
       stm.setString(2,f.getNome());
       stm.setString(3,f.getCpf());

       stm.execute();

       ResultSet rs = stm.getGeneratedKeys();
       rs.next();

       f.setPk(rs.getInt(1));//recuperando a chave primaria que acabou de ser gerada durante a inserção e atribuindo
                                        //a propriedade da classe pessoa/pessoa fisica/funcionario

       for (Endereco e:f.getEnderecos()){
           e.setFk(f.getPk());//atribuindo a chave primaria que acabou de ser inserido na chave estrangeira do endereco
            FuncionarioEnderecoDAO.create(e);
       }


   }




    public static Funcionario retreave(int pk_funcionario) throws SQLException {

        Connection conn = BancoDados.createConnection();

       ResultSet rs = conn.createStatement().executeQuery(

                "select * from funcionario where pk_funcionario="+pk_funcionario
        );

       Funcionario f;

       if(rs.next()){

           f=new Funcionario(CargoDAO.retreave(rs.getInt("fk_cargo")),// nome na coluna no banco de dados
                   rs.getString("cpf"),
                   pk_funcionario,
                   rs.getString("nome")
           );

           f.setEnderecos(FuncionarioEnderecoDAO.retreveAll(pk_funcionario));
       }else{
           throw new RuntimeException("Funcionario com a chave "+pk_funcionario+" não existe");
       }


        return f;
    }


    public static ArrayList<Funcionario> retreaveAll() throws SQLException {

        Connection conn = BancoDados.createConnection();

        ResultSet rs = conn.createStatement().executeQuery(

                "select * from funcionario"
        );

        ArrayList<Funcionario> funcs = new ArrayList<>();


        while(rs.next()){
            Funcionario f=new Funcionario(CargoDAO.retreave(rs.getInt("fk_cargo")),// nome na coluna no banco de dados
                    rs.getString("cpf"),
                    rs.getInt("pk_funcionario"),
                    rs.getString("nome")
            );

            f.setEnderecos(FuncionarioEnderecoDAO.retreveAll(rs.getInt("pk_funcionario")));

            funcs.add(f);
        }
        return funcs;
    }

    public static void update (Funcionario f) throws SQLException {

       Connection coon = BancoDados.createConnection();

       PreparedStatement stm = coon.prepareStatement(
               "update funcionario set fk_cargo=?,nome=?,cpf=? where pk_funcionario=?"
       );

       stm.setInt(1,f.getCargo().getPkCargo());
       stm.setString(2,f.getNome());
       stm.setString(3, f.getCpf());
       stm.setInt(4,f.getPk());

       stm.execute();

       for(Endereco e : f.getEnderecos()){
           if (!e.isPersistido()) {
               FuncionarioEnderecoDAO.update(e);
           }
       }
    }


    public static void delete(Funcionario f) throws SQLException {
       delete(f.getPk());
       f.setPk(0);
    }

    public static void delete(int pk_funcionario) throws SQLException {

        Connection coon = BancoDados.createConnection();

        coon.createStatement().execute(
                "delete from funcionario where pk_funcionario="+pk_funcionario
        );


    }
}
