import Komandat.*;
import PPKeys.CreateDeleteUser;
import PPKeys.ExportImportKey;
import PPKeys.xmlCreator;
import PPKeys.ReadWriteMessage;

public class MainProgram {
    public static void main(String[] args) throws MainCommandException, NrOfCommandsNotValidException,NumberFormatException {
        try {
            switch(args[0]) {
                case "rail-fence":
                    if(args.length>5)
                        throw new NrOfCommandsNotValidException();
                    RailFenceCommand rf=new RailFenceCommand();
                    switch(args[1]) {
                        case "encrypt":
                            if(args[3].equals("show")) {
                                System.out.println(rf.encrypt(args[2],args[4]));
                                rf.ShowMatricaEnkriptimi(args[2],args[4]);
                            }
                            else {
                                System.out.println(rf.encrypt(args[2],args[3]));
                            }
                            break;
                        case "decrypt":
                            if(args[3].equals("show")) {
                                System.out.println(rf.decrypt(args[2],args[4]));
                                rf.ShowMatricaDekriptim(args[2],args[4]);
                            }
                            else {
                                System.out.println(rf.decrypt(args[2],args[3]));
                            }
                            break;
                        default:
                            System.err.println("Komanda e dhene nuk eshte e vlefshme!"+"\nNenkomandat e vlefshme per komanden rail-fence jane:"+
                                    "\n1.encrypt\n2.decrypt+\nKUJDES madhesine e shkronjave");
                            System.exit(1);
                            break;

                    }
                    break;
                case "case":
                    if(args.length>4)
                        throw new NrOfCommandsNotValidException();
                    CaseCommand cc=new CaseCommand();
                    switch(args[1]) {
                        case "lower":
                            if(args[2].equals("sentence")) {
                                System.out.println(cc.lower(args[3])+"\n"+
                                        cc.sentence());
                            }
                            else {
                                System.out.println(cc.lower(args[2]));
                            }
                            break;
                        case "upper":
                            if(args[2].equals("sentence")) {
                                System.out.println(cc.upper(args[3])+"\n"+
                                        cc.sentence());
                            }
                            else {
                                System.out.println(cc.upper(args[2]));
                            }
                            break;
                        case "capitalize":
                            if(args[2].equals("sentence")) {
                                System.out.println(cc.capitalize(args[3])+"\n"+
                                        cc.sentence());
                            }
                            else {
                                System.out.println(cc.capitalize(args[2]));
                            }
                            break;
                        case "inverse":
                            if(args[2].equals("sentence")) {
                                System.out.println(cc.inverse(args[3])+"\n"+
                                        cc.sentence());
                            }
                            else {
                                System.out.println(cc.inverse(args[2]));
                            }
                            break;
                        case "alternating":
                            if(args[2].equals("sentence")) {
                                System.out.println(cc.alternating(args[3])+"\n"+
                                        cc.sentence());
                            }
                            else {
                                System.out.println(cc.alternating(args[2]));
                            }
                            break;
                        default:
                            System.err.println("Komanda e dhene nuk eshte e vlefshme!"+"\nNenkomandat e vlefshme per komanden case jane:"+
                                    "\n1.lower\n2.upper+\n3.capitalize\n4.inverse\n5.alternating\nKUJDES madhesine e shkronjave");
                            System.exit(1);
                            break;
                    }
                    break;
                case "numerical":
                    if(args.length>5)
                        throw new NrOfCommandsNotValidException();
                    NumericalCommand nc=new NumericalCommand();
                    switch(args[1]) {
                        case "encrypt":
                            if(args[2].equals("seperator")) {
                                nc.setSeperator(args[3]);
                                System.out.println(nc.encrypt(args[4]));
                            }
                            else
                                System.out.println(nc.encrypt(args[2]));
                            break;
                        case "decrypt":
                            if(args[2].equals("seperator")) {
                                nc.setSeperator(args[3]);
                                System.out.println(nc.decrypt(args[4]));
                            }
                            else {
                                for(int i=0;i<args[2].length();i++)
                                {
                                    if(Character.isLetter(args[2].charAt(i)))
                                        throw new NumberFormatException();
                                }
                                System.out.println(nc.decrypt(args[2]));
                            }
                            break;
                        default:
                            System.err.println("Komanda e dhene nuk eshte e vlefshme!"+"\nNenkomandat e vlefshme per komanden numerical jane:"+
                                    "\n1.encrypt\n2.decrypt+\nKUJDES madhesine e shkronjave");
                            System.exit(1);
                            break;
                    }
                    break;
                case "create-user":
                    if(args.length>2) throw new NrOfCommandsNotValidException();
                    CreateDeleteUser createDeleteUser=new CreateDeleteUser(args[1]);
                    createDeleteUser.createUser();
                    break;
                case "delete-user":
                    if(args.length>2) throw new NrOfCommandsNotValidException();
                     System.out.println("Jepni fjalekalimin: ");
                    String passwordi=sc.nextLine();
                    if(passwordi.length()<6){
                        System.out.println("Gabim: Fjalekalimi duhet te permbaje se paku 6 karaktere");
                        System.exit(1);
                        break;
                    }
                    CreateDeleteUser createDeleteUser1=new CreateDeleteUser(args[1]);
                    createDeleteUser1.deleteUser();
                    break;
                case "export-key":
                    if(args.length>4) throw new NrOfCommandsNotValidException();
                    ExportImportKey exportImportKey=new ExportImportKey(args[2]);
                    if(args.length==4) exportImportKey.setPath(args[3]);
                    exportImportKey.exportKey(args[1]);
                    break;
                case "import-key":
                    if(args.length>3) throw new NrOfCommandsNotValidException();
                    ExportImportKey exportImportKey1=new ExportImportKey(args[1]);
                    exportImportKey1.importKey(args[2]);
                    break;
                case "write-message":
                    if(args.length>4) throw new NrOfCommandsNotValidException();
                    ReadWriteMessage readWriteMessage=new ReadWriteMessage(args[2]);
                    if(args.length==4) readWriteMessage.setPath(args[3]);
                    readWriteMessage.writeMessage(args[1]);
                    break;
                case "read-message":
                    if(args.length>2) throw new NrOfCommandsNotValidException();
                    ReadWriteMessage readWriteMessage1=new ReadWriteMessage(args[1]);
                    readWriteMessage1.readMessage();
                    break;

                default:
                    throw new MainCommandException();
            }
        }catch(NrOfCommandsNotValidException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }catch(MainCommandException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }catch(NumberFormatException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }


}
