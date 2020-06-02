package Tokens;

import DBConnecion.DbConnection;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

public class Login {
    private String username;
    private String password;

    public Login(String username,String password){
        this.username=username;
        this.password=password;
    }

    public void loginGenerateToken(){
        try{
            DbConnection connectionClass = new DbConnection();
            Connection connection = connectionClass.getConnection();
            String query="SELECT * FROM perdoruesit WHERE username='"+this.username+"'";

            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            stmt.executeUpdate(query);

            String salt=null;
            String hashedPassword=null;
            while(rs.next()){
                salt=rs.getString("salt");
                hashedPassword=rs.getString("hashedPassword");
            }

            


    }
}
