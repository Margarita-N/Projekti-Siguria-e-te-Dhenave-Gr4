

public class ReadWriteMessage {
    String emri;
    String mesazhi;
    String path="";

    ReadWriteMessage(String mesazhi){
        this.mesazhi=mesazhi;
    }

    public void setPath(String path){
        this.path=path;
    }

    public String encrypt(String modulus,String exponent){
        try{
            //kthehen ne big integer stringjet e modulit dhe eksponentit
            RSAPublicKeySpec keySpec= new RSAPublicKeySpec(new BigInteger(modulus),new BigInteger(exponent));
            KeyFactory keyFactory=KeyFactory.getInstance("RSA");
            PublicKey celesiPublik=keyFactory.generatePublic(keySpec);

            Cipher cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE,celesiPublik);

            byte[] byteCipher=cipher.doFinal(this.mesazhi.getBytes());
            return Base64.getEncoder().encodeToString(byteCipher);
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
    
    public String decrypt(String teksti,String modulus,String exponent){
        try{
            RSAPrivateKeySpec keySpec=new RSAPrivateKeySpec(new BigInteger(modulus),new BigInteger(exponent));
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
        public String encryptDES(String plaintext,String key,byte[] initialVector){
        try{
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
        }catch(Exception e){
            System.out.println(e.getMessage());
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
}
