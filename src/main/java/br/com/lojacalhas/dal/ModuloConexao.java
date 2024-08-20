package br.com.lojacalhas.dal;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ModuloConexao {

    // As variáveis abaixo servem para o programa encontrar o caminho do banco de dados
    private static final String url = "jdbc:mysql://localhost:3306/calhas";
    private static final String user = "root";
    private static final String password = "12345";
    
   private static Connection conn ;
   
   
   public static Connection getConexao(){
    
      if (conn == null) {
          try {
              conn = DriverManager.getConnection(url, user, password);
              return conn;
          } catch (SQLException e) {
              System.out.println(e);
              return null;
          }
           }
    return conn;
   }


}   
    

  

