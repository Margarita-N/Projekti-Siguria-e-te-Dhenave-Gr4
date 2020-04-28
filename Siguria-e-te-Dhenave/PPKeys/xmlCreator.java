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

public class xmlCreator {
    public xmlCreator(String path, Document document, String modulus, String exponent) throws Exception{
        File xmlFile=new File(path);

        if(xmlFile.exists()) throw new FileAlreadyExistsException(xmlFile.toString());

        Element RSAroot= document.createElement("RSAKeyValue");
        document.appendChild(RSAroot);

        Element modulusElement=document.createElement("Modulus");
        modulusElement.appendChild(document.createTextNode(modulus));
        RSAroot.appendChild(modulusElement);

        Element exponentElement=document.createElement("Exponent");
        exponentElement.appendChild(document.createTextNode(exponent));
        RSAroot.appendChild(exponentElement);

        TransformerFactory transformerFactory=TransformerFactory.newInstance();
        Transformer transformer=transformerFactory.newTransformer();
        DOMSource domSource=new DOMSource(document);
        StreamResult streamResult=new StreamResult(xmlFile);

        transformer.transform(domSource,streamResult);
        System.out.println("Eshte krijuar celesi publik '"+path+"'");
    }

    public xmlCreator(String path,Document document,String modulus,String publicExponent,String P,String Q,String DP,String DQ,String InverseQ,String privateExponent)
            throws Exception{
        File xmlFile=new File(path);

        if(xmlFile.exists()) throw new FileAlreadyExistsException(xmlFile.toString());

        Element RSAroot= document.createElement("RSAKeyValue");
        document.appendChild(RSAroot);

        Element modulusElement=document.createElement("Modulus");
        modulusElement.appendChild(document.createTextNode(modulus));
        RSAroot.appendChild(modulusElement);

        Element publicExponentElement=document.createElement("Exponent");
        publicExponentElement.appendChild(document.createTextNode(publicExponent));
        RSAroot.appendChild(publicExponentElement);

        Element elementP=document.createElement("P");
        elementP.appendChild(document.createTextNode(P));
        RSAroot.appendChild(elementP);

        Element elementQ=document.createElement("Q");
        elementQ.appendChild(document.createTextNode(Q));
        RSAroot.appendChild(elementQ);

        Element elementDP=document.createElement("DP");
        elementDP.appendChild(document.createTextNode(DP));
        RSAroot.appendChild(elementDP);

        Element elementDQ=document.createElement("DQ");
        elementDQ.appendChild(document.createTextNode(DQ));
        RSAroot.appendChild(elementDQ);

        Element inverseQElement=document.createElement("InverseQ");
        inverseQElement.appendChild(document.createTextNode(InverseQ));
        RSAroot.appendChild(inverseQElement);

        Element elementD=document.createElement("D");
        elementD.appendChild(document.createTextNode(privateExponent));
        RSAroot.appendChild(elementD);

        TransformerFactory transformerFactory=TransformerFactory.newInstance();
        Transformer transformer=transformerFactory.newTransformer();
        DOMSource domSource=new DOMSource(document);
        StreamResult streamResult=new StreamResult(xmlFile);

        transformer.transform(domSource,streamResult);
        System.out.println("Eshte krijuar celesi privat '"+path+"'");
    }
}
