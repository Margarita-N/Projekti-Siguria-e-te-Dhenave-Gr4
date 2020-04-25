

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
}
