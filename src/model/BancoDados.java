package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoDados {

    private static Connection conn;

    public static Connection createConnection(){

        if(conn==null){
            try {
                //instancia a classe driver do banco de dados utilizado, neste caso o postgresql,
                //para a implementação da classe java.sql.driver.
                Class.forName(BancoDadosConfig.DRIVER);

                //estabelece uma conexão com o banco de dados informado.
                //url é o endereço do banco de dados (especifico para cada tipo de banco de dados).
                //User: usuario de conexão com o banco de dados.
                //Pass: senha para conexão com o banco de dados.
                conn= DriverManager.getConnection(
                        BancoDadosConfig.URL,
                        BancoDadosConfig.USER,
                        BancoDadosConfig.PWD
                );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}
