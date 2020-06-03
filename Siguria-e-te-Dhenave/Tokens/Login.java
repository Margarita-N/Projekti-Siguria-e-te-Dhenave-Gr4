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

            String salt=null;
            String hashedPassword=null;
            while(rs.next()){
                salt=rs.getString("salt");
                hashedPassword=rs.getString("hashedPassword");
            }

            if(salt==null || hashedPassword==null)
                throw new Exception("Perdoruesi nuk ekziston!");

            MessageDigest messageDigest=MessageDigest.getInstance("SHA-512");
            messageDigest.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes=messageDigest.digest(this.password.getBytes(StandardCharsets.UTF_8));
            String encodedHash= Base64.getEncoder().encodeToString(bytes);

            if(encodedHash.equals(hashedPassword)){
                CreateToken createToken=new CreateToken(this.username);
                String token=createToken.generateToken(this.username);
                System.out.println("Token:"+token);
            }
            else throw new Exception("Gabim:Shfrytezuesi ose fjalekalimi i gabuar!");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }


    }
}
