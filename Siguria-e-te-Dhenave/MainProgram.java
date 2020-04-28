public class Program {

	public static void main(String[] args) throws NrOfCommandsNotValidException,MainCommandException,NumberFormatException{
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
							break;
				}
				break;
			        case "create-user":
                    if(args.length>2) throw new NrOfCommandsNotValidException();
                    CreateDeleteUser createDeleteUser=new CreateDeleteUser(args[1]);
                    createDeleteUser.createUser();
                    break;
			default:
				throw new MainCommandException();
					
		}
		}catch(NrOfCommandsNotValidException e) {
			System.err.println(e.getMessage());
		}catch(MainCommandException e) {
			System.err.println(e.getMessage());
		}catch(NumberFormatException e) {
			System.err.println(e.getMessage());
		}
		
		
		
	}
	
}
