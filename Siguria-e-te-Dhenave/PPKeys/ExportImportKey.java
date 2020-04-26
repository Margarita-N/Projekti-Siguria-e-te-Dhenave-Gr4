

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

}
