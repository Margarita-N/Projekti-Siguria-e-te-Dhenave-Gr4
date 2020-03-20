import java.lang.Character;
public class NumericalCommand {
	String text;
	String seperator=" ";
	
	public NumericalCommand() {
	
	}
	
	public void setSeperator(String seperator) {
		this.seperator=seperator;
	}
	
	public String encrypt(String text) {
		this.text=text;
		StringBuilder sb=new StringBuilder();
		int charToNr;
		
		this.text=this.text.replaceAll(" ","~");
		this.text=this.text.toUpperCase();
		
		for(int i=0;i<this.text.length();i++) {
			if(Character.isDigit(this.text.charAt(i)))
				continue;
			else
				charToNr=(int)(this.text.charAt(i)-64);
			
			sb.append(charToNr);
			sb.append(this.seperator);
		}
		
		return sb.toString().trim();
	}
	
	public String decrypt(String text) {
		this.text=text;
		StringBuilder sb=new StringBuilder();
		char c;
		
		String StringArray[]=this.text.split(this.seperator);
		
		for(int i=0;i<StringArray.length;i++) {
			if(Integer.parseInt(StringArray[i])==126)
				c='~';
			c=(char)(Integer.parseInt(StringArray[i])+64);
			
			sb.append(c);
			
		}
		return sb.toString().toLowerCase().replaceAll("~"," ");
	}
}

