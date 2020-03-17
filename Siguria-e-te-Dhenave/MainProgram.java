
public class Program {

	public static void main(String[] args) {
		switch(args[0]) {
			case "rail-fence":
				RailFenceCommand rf=new RailFenceCommand(args[2],args[3]);
				switch(args[1]) {
					case "encrypt":
						System.out.println(rf.encrypt());
						break;
					case "decrypt":
						System.out.println(rf.decrypt());
						break;
					default:
						System.out.println("Komanda e dhene nuk eshte e vlefshme!");
						break;
				}
				break;
			case "case":
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
					System.out.println("Komanda e dhene nuk eshte e vlefshme!");
					break;
				}
				break;
			case "numerical":
				NumericalCommand nc=new NumericalCommand(args[2]);
				switch(args[1]) {
				case "encrypt":
					System.out.println(nc.encrypt());
					break;
				case "decrypt":
					System.out.println(nc.decrypt());
					break;
				default:
					System.out.println("Komanda e dhene nuk eshte e vlefshme!");
					break;
				}
				break;
			default:
				System.out.println("Komanda e dhene nuk eshte e vlefshme!");
				break;
					
		}
		
		
		
	}
}