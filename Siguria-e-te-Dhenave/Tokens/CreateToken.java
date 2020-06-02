package Tokens;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

public class CreateToken {
    private String issuer;

    public CreateToken(String issuer){
        this.issuer=issuer;
    }

    public String generateToken(String issuer) {
        try {
            File xmlFile = new File("C:\\Users\\HP\\IdeaProjects\\jwt-excercises\\src\\main\\java\\PPKeys\\keys\\"+issuer+".xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            String modulusDecoded = new String(Base64.getDecoder().decode(document.getElementsByTagName("Modulus").item(0).getTextContent().getBytes()));
            String exponentDecoded = new String(Base64.getDecoder().decode(document.getElementsByTagName("D").item(0).getTextContent().getBytes()));
            String publicexponentDecoded = new String(Base64.getDecoder().decode(document.getElementsByTagName("Exponent").item(0).getTextContent().getBytes()));
            //kthehen ne big integer stringjet e modulit dhe eksponentit
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(new BigInteger(modulusDecoded), new BigInteger(exponentDecoded));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey celesi = keyFactory.generatePrivate(keySpec);
            
        } catch (FileNotFoundException e) {
            System.out.println("Gabim:Nuk ekziston celesi privat " + issuer + ".xml");
            return "";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
}
