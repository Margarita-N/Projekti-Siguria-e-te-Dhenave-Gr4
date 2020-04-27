

public class ExportImportKey {
  public void exportKey(String option){
        this.option=option;
        try{
            switch(this.option){
                case "private":
                    File xmlFile=new File("src/PPKeys/keys/"+this.emri+".xml");

                    if(!xmlFile.exists()) throw new FileNotFoundException();
                    if(this.path=="") throw new InvalidPathException(this.path,"empty");

                    Path moved=Files.move(Paths.get("src/PPKeys/keys/"+this.emri+".xml"),Paths.get(this.path));
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
        }
    catch(InvalidPathException e){
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
                }
            }catch(Exception e){
            System.err.println(e.getMessage());
        }
        
        public void importKey(String externalPath){
        //Path i jashtem eshte per vendodhjen e file per importim eshte i domosdoshem
        this.externalPath=externalPath;

        try{
            //Shikohet a ekziston nje file ne pathin e dhene qofte url apo path i zakonshem
            boolean exists;
            if(externalPath.contains("http")){
                //Fillimisht shkohet a ekziston nje file i tille
                URL pathUrl=new URL(externalPath);
                HttpURLConnection httpURLConnection=(HttpURLConnection)pathUrl.openConnection();
                int response=httpURLConnection.getResponseCode();

                exists=(response == 200);
                if(!exists) throw new InvalidPathException(externalPath,"invalid");
            }
            
        }catch(InvalidPathException e){
            System.out.println(e.getMessage());
            //System.out.println("Gabim:File i dhene nuk eshte celes valid");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
}
