

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

    }
    
    
}
