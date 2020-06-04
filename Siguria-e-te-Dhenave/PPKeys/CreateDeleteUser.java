package PPKeys;

import org.w3c.dom.Document;

import javax.naming.NamingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.util.Base64;

public class CreateDeleteUser {
    String emri;
    String passwordi;
    public CreateDeleteUser(String emri){
        this.emri=emri;
    }

    private void validimi(String emri) throws NamingException {
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

    public void createUser(String passwordi){
        this.passwordi=passwordi;
        //Krijimi i celsave publik dhe privat
        try{
            validimi(this.emri);
            
            DBConnection connectionClass = new DBConnection();
            Connection connection = connectionClass.getConnection();
            
            SecureRandom secureRandom=new SecureRandom();
            byte[] salt=new byte[24];
            secureRandom.nextBytes(salt);
            String salt_encoded= Base64.getEncoder().encodeToString(salt);


            KeyPair dyshjaCelesave=keyGenerator();
            PublicKey celesiPublik=dyshjaCelesave.getPublic();
            PrivateKey celesiPrivat=dyshjaCelesave.getPrivate();

            KeyFactory kf=KeyFactory.getInstance("RSA");
            RSAPrivateCrtKeySpec rsaPrivate=kf.getKeySpec(celesiPrivat,RSAPrivateCrtKeySpec.class);

            DocumentBuilderFactory documentFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder=documentFactory.newDocumentBuilder();
            Document documentPrivate=documentBuilder.newDocument();
            Document documentPublic=documentBuilder.newDocument();

            String privateFile="src/PPKeys/keys/"+this.emri+".xml";
            String publicFile="src/PPKeys/keys/"+this.emri+".pub.xml";

            String modulusEncoded= Base64.getEncoder().encodeToString(rsaPrivate.getModulus().toString().getBytes());
            String exponentEncoded=Base64.getEncoder().encodeToString(rsaPrivate.getPublicExponent().toString().getBytes());
            String PrimeP=Base64.getEncoder().encodeToString(rsaPrivate.getPrimeP().toString().getBytes());
            String PrimeQ=Base64.getEncoder().encodeToString(rsaPrivate.getPrimeQ().toString().getBytes());
            String DP=Base64.getEncoder().encodeToString(rsaPrivate.getPrimeExponentP().toString().getBytes());
            String DQ=Base64.getEncoder().encodeToString(rsaPrivate.getPrimeExponentQ().toString().getBytes());
            String InverseQ=Base64.getEncoder().encodeToString(rsaPrivate.getCrtCoefficient().toString().getBytes());
            String privateExponent=Base64.getEncoder().encodeToString(rsaPrivate.getPrivateExponent().toString().getBytes());

            xmlCreator xml =new xmlCreator(privateFile,documentPrivate,modulusEncoded,exponentEncoded,PrimeP,PrimeQ,DP,DQ,InverseQ,privateExponent);
            xmlCreator xmlPublic=new xmlCreator(publicFile,documentPublic,modulusEncoded,exponentEncoded);

        } catch(NamingException e){
            System.out.println("Emri i dhene nuk eshte valid\nEmri duhet te permbaje vetem shkronja,numra apo '_'");
            System.exit(1);
        } catch(FileAlreadyExistsException e){
            System.out.println("File "+this.emri+" ekziston paraprakisht");
            System.exit(1);
        } catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    //duhet me bo funksion me i fshi perdoruesit
    public void deleteUser(){
        Path pathPrivate= Path.of("src/PPKeys/keys/"+this.emri+".xml");
        Path pathPublic=Path.of("src/PPKeys/keys/"+this.emri+".pub.xml");

        try{
            validimi(this.emri);
            if(Files.exists(pathPrivate)) {
                Files.delete(pathPrivate);
                System.out.println("Eshte larguar celesi privat '"+this.emri+"'");
            }
            if(Files.exists(pathPublic)) {
                Files.delete(pathPublic);
                System.out.println("Eshte larguar celesi public '"+this.emri+"'");
            }
            else throw new FileNotFoundException();
        }catch(NamingException e){
            System.out.println("Emri i dhene nuk eshte valid\nEmri duhet te permbaje vetem shkronja,numra apo '_'");
            System.exit(1);
        }catch(Exception e){
            System.out.println("Gabim:Celesi '"+this.emri+"' nuk ekziston");
            System.exit(1);
        }
    }

}
