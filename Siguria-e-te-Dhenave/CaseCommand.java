
public class CaseCommand {
	private String text;
	
	CaseCommand(String text){
		this.text=text;
	}
	
	private char charLow(char c) {
		return (char)(c+32);
	}
	private char charUp(char c) {
		return (char)(c-32);
	}
	
	public String lower() {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<this.text.length();i++) {
			if(this.text.charAt(i)>='A' && this.text.charAt(i)<='Z') {
				sb.append(charLow(this.text.charAt(i)));
			}
			else {
				sb.append(this.text.charAt(i));
			}
		}
		
		return sb.toString();
	}
	
	public String upper() {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<this.text.length();i++) {
			if(this.text.charAt(i)>='a' && this.text.charAt(i)<='z') {
				sb.append(charUp(this.text.charAt(i)));
			}
			else {
				sb.append(this.text.charAt(i));
			}
		}
		
		return sb.toString();
	}
	
	public String capitalize() {
		StringBuilder sb=new StringBuilder();
		String[] arrayOfText=this.text.split(" ");
		
		for(int i=0;i<arrayOfText.length;i++) {
			for(int j=0;j<arrayOfText[i].length();j++) {
				if(j==0 && (arrayOfText[i].charAt(j)>='a' && arrayOfText[i].charAt(j)<='z')) {
					sb.append(charUp(arrayOfText[i].charAt(j)));
				}
				else {
					sb.append(arrayOfText[i].charAt(j));
				}
			}
			sb.append(" ");
		}
		
		return sb.toString();
	}
	
	public String inverse() {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<this.text.length();i++) {
			if(this.text.charAt(i)>='A' && this.text.charAt(i)<='Z') {
				sb.append(charLow(this.text.charAt(i)));
			}
			else if(this.text.charAt(i)>='a' && this.text.charAt(i)<='z') {
				sb.append(charUp(this.text.charAt(i)));
			}
			else 
				sb.append(this.text.charAt(i));
			
		}
		return sb.toString();
	}
	
	public String alternating() {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<this.text.length();i++) {
			if(i%2==0) {
				if(this.text.charAt(i)>='A' && this.text.charAt(i)<='Z') {
					sb.append(charLow(this.text.charAt(i)));
				}
				else 
					sb.append(this.text.charAt(i));
			}
			else {
				if(this.text.charAt(i)>='a' && this.text.charAt(i)<='z') {
					sb.append(charUp(this.text.charAt(i)));
				}
				else 
					sb.append(this.text.charAt(i));
			}
		}
		
		
		return sb.toString();
	}
	
	
}