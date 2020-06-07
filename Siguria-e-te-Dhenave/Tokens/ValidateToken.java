package Tokens;

import io.jsonwebtoken.*;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

public class ValidateToken {
    private String token;

    public ValidateToken(String token){
        this.token=token;
    }

    public void status(){
        try{
            String[] tokenArray=this.token.split("\\.");
            String unsignedToken=tokenArray[0]+"."+tokenArray[1]+".";

            Jwt<Header,Claims> result=Jwts.parser().parse(unsignedToken);
            Claims claims= result.getBody();
            String issuer=claims.getIssuer();
            Date expirationDate=claims.getExpiration();
            String valid=validate(issuer,expirationDate)? "po":"jo";

            String dateToFormat=expirationDate.toString().replace("CEST","");

            System.out.println("User:"+issuer);
            System.out.println("Valid:"+valid);
            System.out.println("Expiration:"+formatDate(dateToFormat));
        }catch(ExpiredJwtException e){
            String issuer=e.getClaims().getIssuer();
            Date expirationDate=e.getClaims().getExpiration();

            String dateToFormat=expirationDate.toString().replace("CEST","");

            System.out.println("User:"+issuer);
            System.out.println("Valid:jo");
            System.out.println("Expiration:"+formatDate(dateToFormat));
        }

    }

    public boolean validate(String issuer,Date expirationTime) {
        try {
            //XML file i celesit publik te derguesit
            File xmlFile = new File("C:\\Users\\HP\\IdeaProjects\\jwt-excercises\\src\\main\\java\\PPKeys\\keys\\" + issuer + ".pub.xml");
            if (!xmlFile.exists()) throw new FileNotFoundException();

            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            String modulusDecoded = new String(Base64.getDecoder().decode(document.getElementsByTagName("Modulus").item(0).getTextContent().getBytes()));
            String exponentDecoded = new String(Base64.getDecoder().decode(document.getElementsByTagName("Exponent").item(0).getTextContent().getBytes()));

            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(new BigInteger(modulusDecoded), new BigInteger(exponentDecoded));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey celesi = keyFactory.generatePublic(keySpec);

            Jws<Claims> jws = Jwts.parser().setSigningKey(celesi).parseClaimsJws(this.token);

            Instant now = Instant.now();
            if (Date.from(now).compareTo(expirationTime) > 0) return false;
            else return true;
        } catch(FileNotFoundException e){
            System.out.println("Gabim:Nuk ekziston celesi publik "+issuer+".pub.xml");
            return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    private String formatDate(String date){
        try{
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
            Date dateP=simpleDateFormat.parse(date);
            String sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dateP);
            return sdf;
        }catch(Exception e){
            System.out.print(e.getMessage());
            return "";
        }

    }

}
