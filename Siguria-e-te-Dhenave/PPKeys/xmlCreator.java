package PPKeys;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.math.BigInteger;
import java.nio.file.FileAlreadyExistsException;
import java.util.Base64;

public class xmlCreator {
    public xmlCreator(String path, Document document, BigInteger modulus, BigInteger exponent) throws Exception{
        File xmlFile=new File(path);

        if(xmlFile.exists()) throw new FileAlreadyExistsException(xmlFile.toString());

        Element RSAroot= document.createElement("RSAKeyValue");
        document.appendChild(RSAroot);

        Element modulusElement=document.createElement("Modulus");
        modulusElement.appendChild(document.createTextNode(modulus.toString()));
        RSAroot.appendChild(modulusElement);

        Element exponentElement=document.createElement("Exponent");
        exponentElement.appendChild(document.createTextNode(exponent.toString()));
        RSAroot.appendChild(exponentElement);

        TransformerFactory transformerFactory=TransformerFactory.newInstance();
        Transformer transformer=transformerFactory.newTransformer();
        DOMSource domSource=new DOMSource(document);
        StreamResult streamResult=new StreamResult(xmlFile);

        transformer.transform(domSource,streamResult);
        System.out.println("Eshte krijuar celesi publik '"+path+"'");
    }
    public xmlCreator(String path,Document document,BigInteger modulus,BigInteger publicExponent,BigInteger P,BigInteger Q,BigInteger DP,BigInteger DQ,BigInteger InverseQ,BigInteger privateExponent)
            throws Exception{
        File xmlFile=new File(path);

        if(xmlFile.exists()) throw new FileAlreadyExistsException(xmlFile.toString());
}
