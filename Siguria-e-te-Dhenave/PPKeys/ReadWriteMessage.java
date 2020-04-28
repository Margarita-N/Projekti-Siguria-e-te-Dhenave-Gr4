

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
             
    
    
}
