
public class Program {

	public static void main(String[] args) throws NrOfCommandsNotValidException,MainCommandException,NumberFormatException{
		try {
			switch(args[0]) {
			case "rail-fence":
				if(args.length>4)
					throw new NrOfCommandsNotValidException();
				RailFenceCommand rf=new RailFenceCommand(args[2],args[3]);
				switch(args[1]) {
					case "encrypt":
						System.out.println(rf.encrypt());
						break;
					case "decrypt":
						System.out.println(rf.decrypt());
						break;
					default:
						System.err.println("Komanda e dhene nuk eshte e vlefshme!"+"\nNenkomandat e vlefshme per komanden rail-fence jane:"+
						"\n1.encrypt\n2.decrypt+\nKUJDES madhesine e shkronjave");
						break;
				}
				break;
			case "case":
				if(args.length>3)
					throw new NrOfCommandsNotValidException();
				CaseCommand cc=new CaseCommand(args[2]);
				switch(args[1]) {
				case "lower":
					System.out.println(cc.lower());
					break;
				case "upper":
					System.out.println(cc.upper());
					break;
				case "capitalize":
					System.out.println(cc.capitalize());
					break;
				case "inverse":
					System.out.println(cc.inverse());
					break;
				case "alternating":
					System.out.println(cc.alternating());
					break;
				default:
					System.err.println("Komanda e dhene nuk eshte e vlefshme!"+"\nNenkomandat e vlefshme per komanden case jane:"+
							"\n1.lower\n2.upper+\n3.capitalize\n4.inverse\n5.alternating\nKUJDES madhesine e shkronjave");
							break;
				}
				break;
			case "numerical":
				if(args.length>3)
					throw new NrOfCommandsNotValidException();
				NumericalCommand nc=new NumericalCommand(args[2]);
				switch(args[1]) {
				case "encrypt":
					System.out.println(nc.encrypt());
					break;
				case "decrypt":
					for(int i=0;i<args[2].length();i++)
					{
						if(Character.isLetter(args[2].charAt(i)))
							throw new NumberFormatException();
					}
					System.out.println(nc.decrypt());
					break;
				default:
					System.err.println("Komanda e dhene nuk eshte e vlefshme!"+"\nNenkomandat e vlefshme per komanden numerical jane:"+
							"\n1.encrypt\n2.decrypt+\nKUJDES madhesine e shkronjave");
							break;
				}
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
