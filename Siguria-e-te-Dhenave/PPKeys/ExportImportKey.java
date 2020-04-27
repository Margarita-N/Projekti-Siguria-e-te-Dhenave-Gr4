

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
        
            
}
