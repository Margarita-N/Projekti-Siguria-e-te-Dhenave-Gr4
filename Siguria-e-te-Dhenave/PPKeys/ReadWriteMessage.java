package PPKeys;

import org.w3c.dom.Document;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.math.BigInteger;
import java.nio.charset.CharacterCodingException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class ReadWriteMessage {
    private String emri;
    private String mesazhi;
    private String path="";

    public ReadWriteMessage(String mesazhi){
        this.mesazhi=mesazhi;
    }

    public void setPath(String path){
        this.path=path;
    }

    public String encrypt(String teksti,String modulus,String exponent){
        try{
            //Duhet te dekodohet moduli dhe eksponenti
            String modulusDecoded=new String(Base64.getDecoder().decode(modulus.getBytes()));
            String exponentDecoded=new String(Base64.getDecoder().decode(exponent.getBytes()));
            //kthehen ne big integer stringjet e modulit dhe eksponentit
            RSAPublicKeySpec keySpec= new RSAPublicKeySpec(new BigInteger(modulusDecoded),new BigInteger(exponentDecoded));
            KeyFactory keyFactory=KeyFactory.getInstance("RSA");
            PublicKey celesiPublik=keyFactory.generatePublic(keySpec);

            Cipher cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE,celesiPublik);

            byte[] byteCipher=cipher.doFinal(teksti.getBytes());
            return Base64.getEncoder().encodeToString(byteCipher);
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public String decrypt(String teksti,String modulus,String exponent){
        try{
            //Dekodimi nga base64
            String modulusDecoded=new String(Base64.getDecoder().decode(modulus.getBytes()));
            String exponentDecoded=new String(Base64.getDecoder().decode(exponent.getBytes()));
            RSAPrivateKeySpec keySpec=new RSAPrivateKeySpec(new BigInteger(modulusDecoded),new BigInteger(exponentDecoded));
            KeyFactory keyFactory=KeyFactory.getInstance("RSA");
            PrivateKey celesiPrivat=keyFactory.generatePrivate(keySpec);

            byte[] ciphertextDecoded=Base64.getDecoder().decode(teksti.getBytes());
            Cipher cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE,celesiPrivat);

            byte[] plaintexti=cipher.doFinal(ciphertextDecoded);
            return new String(plaintexti);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "";
        }

    }

    public String encryptDES(String plaintext,String key,byte[] initialVector){
        try{
            //Nxjerrja e celesit nga String
            DESKeySpec desKeySpec=new DESKeySpec(key.getBytes());
            SecretKeyFactory skf=SecretKeyFactory.getInstance("DES");
            SecretKey celesi=skf.generateSecret(desKeySpec);

            Cipher ciphertext=Cipher.getInstance("DES/CBC/PKCS5Padding");
            AlgorithmParameterSpec initial=new IvParameterSpec(initialVector);
            ciphertext.init(Cipher.ENCRYPT_MODE,celesi,initial);

            byte[] cipherByte=ciphertext.doFinal(plaintext.getBytes());
            return Base64.getEncoder().encodeToString(cipherByte);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "";
        }
    }

    public String decryptDES(String ciphertext,String key,byte[] initialVector){
        try{
            byte[] cipherDecoded=Base64.getDecoder().decode(ciphertext);
            DESKeySpec desKeySpec=new DESKeySpec(key.getBytes());
            SecretKeyFactory skf=SecretKeyFactory.getInstance("DES");
            SecretKey celesi=skf.generateSecret(desKeySpec);

            Cipher plaintext=Cipher.getInstance("DES/CBC/PKCS5Padding");
            AlgorithmParameterSpec initial=new IvParameterSpec(initialVector);
            plaintext.init(Cipher.DECRYPT_MODE,celesi,initial);
            byte[] plaintextByte=plaintext.doFinal(cipherDecoded);
            return new String(plaintextByte);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "";
        }

    }
    private String getSender(String token){
        String issuer;
        try{
            String[] tokenArray=token.split("\\.");
            String unsignedToken=tokenArray[0]+"."+tokenArray[1]+".";

            Jwt<Header, Claims> result= Jwts.parser().parse(unsignedToken);
            Claims claims= result.getBody();
            issuer=claims.getIssuer();
            return issuer;
        }
        catch(ExpiredJwtException e){
            issuer=e.getClaims().getIssuer();
            return issuer;
        }
    }
      private Date getExpirationDate(String token){
        Date expirationDate;
        try{
            String[] tokenArray=token.split("\\.");
            String unsignedToken=tokenArray[0]+"."+tokenArray[1]+".";

            Jwt<Header, Claims> result= Jwts.parser().parse(unsignedToken);
            Claims claims= result.getBody();
            expirationDate=claims.getExpiration();
            return expirationDate;
        }catch(ExpiredJwtException e){
            expirationDate=e.getClaims().getExpiration();
            return expirationDate;

        }
      }
    public void writeMessage(String marresi){
        this.emri=marresi;
        try{
            //Shikohet a ekziston fillimisht celesi
            File file=new File("src/PPKeys/keys/"+this.emri+".pub.xml");
            if(!file.exists()) throw new FileNotFoundException();

            //Krijimi i dokument objekteve per te lexuar vlerat nga xml file
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db=dbf.newDocumentBuilder();
            Document document=db.parse(file);

            //Gjenerimi i DES celesave
            KeyGenerator keyGenerator=KeyGenerator.getInstance("DES");
            SecretKey celesi=keyGenerator.generateKey();

            //Gjenerimi i nje initial vektori
            byte[] initialVector=new byte[8];
            for(int i=0;i<initialVector.length;i++){
                initialVector[i]=(byte)((Math.random()*((99-10)+1))+10);
            }


            StringBuilder stringBuilder=new StringBuilder();

            stringBuilder.append(Base64.getEncoder().encodeToString(marresi.getBytes("UTF8")));
            stringBuilder.append(".");
            stringBuilder.append(Base64.getEncoder().encodeToString(initialVector));
            stringBuilder.append(".");
            String celesiEnkriptuar=encrypt(celesi.toString(),document.getElementsByTagName("Modulus").item(0).getTextContent(),document.getElementsByTagName("Exponent").item(0).getTextContent());
            stringBuilder.append(Base64.getEncoder().encodeToString(celesiEnkriptuar.getBytes()));
            stringBuilder.append(".");
            String mesazhiDES=encryptDES(this.mesazhi,celesi.toString(),initialVector);
            stringBuilder.append(Base64.getEncoder().encodeToString(mesazhiDES.getBytes()));

            if(path.equals("")) System.out.println(stringBuilder.toString());
            else{
                File fileExternal=new File(path);
                FileWriter myWriter = new FileWriter(path);
                myWriter.write(stringBuilder.toString());
                myWriter.close();
            }
        }catch(FileNotFoundException e){
            System.out.println("Gabim:Celesi publik '"+this.emri+"' nuk ekziston");
            System.exit(1);
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
    
    public void writeMessageToken(String marresi,String token){
        this.emri=marresi;
        try{
            //Shikohet a ekziston fillimisht celesi
            File file=new File("C:\\Users\\HP\\IdeaProjects\\jwt-excercises\\src\\main\\java\\PPKeys\\keys\\"+marresi+".pub.xml");
            if(!file.exists()) throw new FileNotFoundException();

            //Krijimi i dokument objekteve per te lexuar vlerat nga xml file
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db=dbf.newDocumentBuilder();
            Document document=db.parse(file);
            
             //Gjenerimi i DES celesave
            KeyGenerator keyGenerator=KeyGenerator.getInstance("DES");
            SecretKey celesi=keyGenerator.generateKey();
            
            //Gjenerimi i nje initial vektori
            byte[] initialVector=new byte[8];
            for(int i=0;i<initialVector.length;i++){
                initialVector[i]=(byte)((Math.random()*((99-10)+1))+10);
            }

        }catch(FileNotFoundException e){
            System.out.println("Gabim:Celesi publik '"+this.emri+"' nuk ekziston");
            System.exit(1);
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public void messageDecoder(String message) throws Exception{
        String[] messaageArray=message.split("\\.");

        byte[] marresiBytes=Base64.getDecoder().decode(messaageArray[0].getBytes());
        String marresi=new String(marresiBytes);

        File privateFile=new File("src/PPKeys/keys/"+marresi+".xml");
        if(!privateFile.exists()) throw new FileNotFoundException();

        DocumentBuilder db=DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document=db.parse(privateFile);

        String iv=new String(Base64.getDecoder().decode(messaageArray[1]));
        String keyEncrypted=new String(Base64.getDecoder().decode(messaageArray[2].getBytes()));
        String key=decrypt(keyEncrypted,document.getElementsByTagName("Modulus").item(0).getTextContent(),document.getElementsByTagName("D").item(0).getTextContent());
        String messageEncrypted=new String(Base64.getDecoder().decode(messaageArray[3].getBytes()));
        String messageDecrypted=decryptDES(messageEncrypted,key,iv.getBytes());

        System.out.println("Marresi:"+marresi+"\nMesazhi:"+messageDecrypted);

    }

    public void readMessage(){
        try{
            File file=new File(this.mesazhi);
            if(file.exists()){
                StringBuilder sb=new StringBuilder();
                Scanner read=new Scanner(file);
                while(read.hasNext()) {
                    sb.append(read.next());
                }
                messageDecoder(sb.toString());
            }
            else{
                messageDecoder(this.mesazhi);
            }

        }catch(FileNotFoundException e){
            System.out.println("Gabim:Celesi privat nuk ekziston");
            System.exit(1);
        }catch(CharacterCodingException e){
            System.out.println("Gabim:Mesazhi i dhene nuk eshte i vlefshem");
            System.exit(1);
        }
        catch (Exception e){
            System.out.println("Nje file i tille nuk ekziston");
            System.exit(1);
        }
    }

}
