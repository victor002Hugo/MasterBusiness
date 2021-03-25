import controller.*;
import model.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class MasterBusiness {
    public static void main(String[] args) {


        try {


           // Funcionario f = FuncionarioDAO.retreave(6);
          //  f.print();

           //Cliente c = ClienteDAO.retreave(1);
           //c.print();
            // Fornecedor f = FornecedorDAO.retreave(1);
            //f.print();


            for(Funcionario f:FuncionarioDAO.retreaveAll()){
                f.print();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        // Cargo c = new Cargo("Cozinha Quente","Cozinheiro3");

       // Produto p = new Produto("Camarao",2,100);



           // ProdutoDAO.create(p);

            //Produto p1 = ProdutoDAO.retreave(6);

           // System.out.println(p1);




           // ArrayList<Produto> produtos = ProdutoDAO.retreveAll();

           // for(Produto p1:produtos){
           //     System.out.println(p1);
           // }


            //Produto p = ProdutoDAO.retreave(6);

           // p.setNome("Rucula");

            //ProdutoDAO.update(p);


            //ProdutoDAO.delete(6);
    }
}
