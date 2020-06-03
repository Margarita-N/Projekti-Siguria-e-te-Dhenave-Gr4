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

            System.out.println("User:"+issuer);
            System.out.println("Valid:"+valid);
            System.out.println("Expiration:"+expirationDate.toString());
        }catch(ExpiredJwtException e){
           String issuer=e.getClaims().getIssuer();
            Date expirationDate=e.getClaims().getExpiration();

            System.out.println("User:"+issuer);
            System.out.println("Valid:jo");
            System.out.println("Expiration:"+expirationDate.toString());
        }

    }

   

}
