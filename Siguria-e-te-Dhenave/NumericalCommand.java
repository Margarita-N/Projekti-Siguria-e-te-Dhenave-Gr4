public class NumericalCommand{
	String text;
	
	public NumericalCommand(String text) {
		this.text=text;
	}
	
	public String encrypt() {
		StringBuilder sb=new StringBuilder();
		int charToNr;
		
		this.text=this.text.replaceAll(" ", "");
		this.text=this.text.toUpperCase();
		
		for (int i=0;i<this.text.length();i++) {
			if(Character.isDigit(this.text.charAt(i)))
				continue;
			else
				charToNr=(int)(this.text.charAt(i)-64);
	
			sb.append(charToNr);
			sb.append(" ");
		}
		
		return sb.toString().trim();
	}
	
	public String decrypt() {
		StringBuilder sb=new StringBuilder();
		char c;
		
		String StringArray[]=this.text.split(" ");
		
		for(int i=0;i<StringArray.length;i++) {
			c=(char)(Integer.parseInt(StringArray[i])+64);
			
			sb.append(c);
			
		}
		return sb.toString().toLowerCase();
	}
}
