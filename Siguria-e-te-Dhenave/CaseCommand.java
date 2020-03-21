public class CaseCommand {
	private String text;
	
	public CaseCommand(){
		
	}
	
	private char charLow(char c) {
		return (char)(c+32);
	}
	private char charUp(char c) {
		return (char)(c-32);
	}
	
	public String lower(String text) {
		this.text=text;
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
	
	public String upper(String text) {
		this.text=text;
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
	
	public String capitalize(String text) {
		this.text=text;
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
	
	public String inverse(String text) {
		this.text=text;
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
	
	public String alternating(String text) {
		this.text=text;
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
	
	
	public String sentence() {
		StringBuilder temp_strBuilder=new StringBuilder();
		StringBuilder alt_sentences=new StringBuilder();
		int index1=0;
		int alt_counter=0;
		for(int i=0;i<this.text.length();i++) {
			temp_strBuilder.append(this.text.charAt(i));
			
			if(this.text.charAt(i)=='.' || this.text.charAt(i)==',' || this.text.charAt(i)=='!' || this.text.charAt(i)=='?') {
				int index2=i;
				String fjalia=temp_strBuilder.toString().substring(index1,index2);
				index1=index2;
				
				CaseCommand cs=new CaseCommand();
				if(alt_counter%5==0)
					alt_sentences.append(cs.lower(fjalia));
				else if(alt_counter%5==1) 
					alt_sentences.append(cs.upper(fjalia));
				else if(alt_counter%5==2) 
					alt_sentences.append(cs.capitalize(fjalia));
				else if(alt_counter%5==3) 
					alt_sentences.append(cs.inverse(fjalia));
				else if(alt_counter%5==4) 
					alt_sentences.append(cs.alternating(fjalia));
				
				alt_counter++;
			}
			else 
				continue;
			
		}
		return alt_sentences.toString();
	}
	
	
}
