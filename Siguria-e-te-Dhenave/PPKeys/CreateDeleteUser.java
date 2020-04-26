import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.naming.NamingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.spec.RSAPrivateCrtKeySpec;

public class CreateDeleteUser {
  String emri;
    CreateDeleteUser(String emri){
        this.emri=emri;
    }

    private void validimi(String emri) throws NamingException{
        char c;
        for(int i=0;i<emri.length();i++){
            c=emri.charAt(i);
            if((c>=48 && c<=57)||(c>=65 && c<=90)||(c>=97 && c<=122)|| c==95) continue;
            else throw new NamingException();
        }
    }
  //duhet mi gjeneru celsat per me i ru n files
    private KeyPair keyGenerator() throws Exception{
            KeyPairGenerator celsat=KeyPairGenerator.getInstance("RSA");
            celsat.initialize(1024);

            return celsat.generateKeyPair();
    }
  
  public void createUser(){
        //Krijimi i celsave publik dhe privat
        try{
            validimi(this.emri);

            KeyPair dyshjaCelesave=keyGenerator();
            PublicKey celesiPublik=dyshjaCelesave.getPublic();
            PrivateKey celesiPrivat=dyshjaCelesave.getPrivate();
            
            KeyFactory kf=KeyFactory.getInstance("RSA");
            RSAPrivateCrtKeySpec rsaPrivate=kf.getKeySpec(celesiPrivat,RSAPrivateCrtKeySpec.class);

            DocumentBuilderFactory documentFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder=documentFactory.newDocumentBuilder();
            Document documentPrivate=documentBuilder.newDocument();
            Document documentPublic=documentBuilder.newDocument();

            
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
