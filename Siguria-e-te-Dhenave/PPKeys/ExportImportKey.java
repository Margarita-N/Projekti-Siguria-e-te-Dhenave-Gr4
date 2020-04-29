package PPKeys;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ExportImportKey {
    String option;
    String emri;
    String path="";
    String externalPath;

    public ExportImportKey(String emri){
        this.emri=emri;
    }
    public void setPath(String path){
        this.path=path;
    }

    public void exportKey(String option){
        this.option=option;
        try{
            switch(this.option){
                case "private":
                    File xmlFile=new File("src/PPKeys/keys/"+this.emri+".xml");

                    if(!xmlFile.exists()) throw new FileNotFoundException();
                    if(this.path=="") throw new InvalidPathException(this.path,"empty");

                    Path moved= Files.move(Paths.get("src/PPKeys/keys/"+this.emri+".xml"),Paths.get(this.path));
                    if(moved != null) System.out.println("Celesi privat u ruajt ne file-in "+this.path);
                    else throw new InvalidPathException(this.path,"invalid");

                    break;
                case "public":
                    File xmlFilePublic=new File("src/PPKeys/keys/"+this.emri+".pub.xml");

                    if(!xmlFilePublic.exists()) throw new FileNotFoundException();
                    if(this.path=="") throw new InvalidPathException(this.path,"empty");

                    Path movePublic=Files.move(Paths.get("src/PPKeys/keys/"+this.emri+".pub.xml"),Paths.get(this.path));
                    if(movePublic != null) System.out.println("Celesi publik u ruajt ne file-in "+this.path);
                    else throw new InvalidPathException(this.path,"invalid");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + this.option);
            }
        }catch(FileNotFoundException e){
            System.out.println("Gabim:Celesi '"+this.emri+"' nuk ekziston");
        }catch(InvalidPathException e){
            try{
                switch(this.option){
                    case "private":
                        StringBuilder sb=new StringBuilder();
                        Scanner read=new Scanner(new File("src/PPKeys/keys/"+this.emri+".xml"));
                        while(read.hasNext()) {
                            sb.append(read.next());
                        }
                        System.out.println(sb.toString());
                        break;
                    case "public":
                        StringBuilder sbPublic=new StringBuilder();
                        Scanner readPublic=new Scanner(new File("src/PPKeys/keys/"+this.emri+".pub.xml"));
                        while(readPublic.hasNext()) {
                            sbPublic.append(readPublic.next());
                        }
                        System.out.println(sbPublic.toString());
                        break;
                }
            }catch(FileNotFoundException m){
                System.out.println("Gabim:Celesi '"+this.emri+"' nuk ekziston");
                System.exit(1);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public boolean validateURL(String adresa){
        try{
            URL url=new URL(adresa);
            url.toURI();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public void importKey(String externalPath){
        //Path i jashtem eshte per vendodhjen e file per importim eshte i domosdoshem
        this.externalPath=externalPath;

        try{
            //Shikohet a ekziston nje file ne pathin e dhene qofte url apo path i zakonshem
            boolean exists;
            if(validateURL(this.externalPath)){
                //Fillimisht shkohet a ekziston nje file i tille
                URL pathUrl=new URL(externalPath);
                HttpURLConnection httpURLConnection=(HttpURLConnection)pathUrl.openConnection();
                int response=httpURLConnection.getResponseCode();

                exists=(response == 200);
                if(!exists) throw new InvalidPathException(this.externalPath,"invalid");

                URL url = new URL(this.externalPath);
                StringBuilder stringBuilder=new StringBuilder();
                Scanner s = new Scanner(url.openStream());
                while(s.hasNext()){
                    stringBuilder.append(s.next());
                }

                this.externalPath="C:\\Users\\Lenovo\\Desktop\\Testim\\external.xml";
                File fileExternal=new File(this.externalPath);
                Files.write(Paths.get(this.externalPath),stringBuilder.toString().getBytes());

            }

            File externalFile=new File(this.externalPath);
            exists=externalFile.exists();

            if(!exists) throw new InvalidPathException(externalPath,"invalid");

            //Krijohet nje dokument per te lexuar vlerat nga xml file
            DocumentBuilder db= DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document=db.parse(externalFile);

            //Krijohet nje dokument per krijuar xml filein ne pathin e caktuar
            Document documentPublic=db.newDocument();
            xmlCreator xmlPublic=new xmlCreator("src/PPKeys/keys/"+this.emri+".pub.xml",documentPublic,document.getElementsByTagName("Modulus").item(0).getTextContent(),
                    document.getElementsByTagName("Exponent").item(0).getTextContent());

            //Krijohet nje dokument per te krijuar xml filein privat dhe gjuan exception nese nuk ekzistojne elementet
            Document documentPrivate=db.newDocument();
            xmlCreator xmlPrivate=new xmlCreator("src/PPKeys/keys/"+this.emri+".xml",documentPrivate, document.getElementsByTagName("Modulus").item(0).getTextContent(),
                    document.getElementsByTagName("Exponent").item(0).getTextContent(),document.getElementsByTagName("P").item(0).getTextContent(),document.getElementsByTagName("Q").item(0).getTextContent(),
                    document.getElementsByTagName("DP").item(0).getTextContent(),document.getElementsByTagName("DQ").item(0).getTextContent(),document.getElementsByTagName("InverseQ").item(0).getTextContent(),
                    document.getElementsByTagName("D").item(0).getTextContent());

            externalFile.delete();


        }catch(InvalidPathException e){
            System.out.println(e.getMessage());
            //System.out.println("Gabim:File i dhene nuk eshte celes valid");
            System.exit(1);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
