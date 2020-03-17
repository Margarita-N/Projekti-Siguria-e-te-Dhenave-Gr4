import java.lang.Math;
public class RailFenceCommand {
	String rows;
	String text;

		
		public RailFenceCommand(String rows,String text) {
			this.rows=rows;
			this.text=text;
		}
		public String encrypt() {
			this.text =this.text.replaceAll(" ","");
			StringBuilder sb=new StringBuilder();
			double rows_d=Double.valueOf(this.rows);
			char[][] matrica = new char[(int)rows_d][(int)(Math.ceil(this.text.length()/rows_d))];
			int k=0;
			for (int j=0;j<(int)(Math.ceil(this.text.length()/rows_d));j++) {
				for(int i=0;i<(int)rows_d;i++) {
					if(k==this.text.length()|| k>this.text.length()) {
						matrica[i][j]='w';
						k++;
					}
					else {
						matrica[i][j]=this.text.charAt(k);
						k++;
					}
				}
			}
			for(int i=0;i<(int)rows_d;i++) {
				for(int j=0;j<(int)(Math.ceil(this.text.length()/rows_d));j++) {
					sb.append(matrica[i][j]);
				}
			
		}
			return sb.toString();
		}
		public String decrypt() {
			this.text=this.text.replaceAll(" ",""); 
			StringBuilder str =new StringBuilder(); 
			double rowsindouble=Double.valueOf(this.rows);
			char[][] matrica=new char [(int)rowsindouble][(int)(Math.ceil(this.text.length()/rowsindouble))];
			int k=0;
			for(int i=0;i<(int)rowsindouble;i++) {
				for(int j=0;j<(int)(Math.ceil(this.text.length()/rowsindouble));j++) {
					if(k==this.text.length()|| k>this.text.length()) {
						matrica[i][j]='w';
						k++;
					}
					else {
						matrica[i][j]=this.text.charAt(k);
						k++;
					}
				}
			}
			for(int j=0;j<(int)(Math.ceil(this.text.length()/rowsindouble));j++) {
			for(int i=0;i<(int)rowsindouble;i++) {
	
				
					str.append(matrica[i][j]);
				
				
				}
			
		}
			return str.toString();
		}
		

		public void ShowMatricaEnkriptimi() {
			double rows_d=Double.valueOf(this.rows);
			this.text =this.text.replaceAll(" ","");
			char[][] matrica = new char[(int)rows_d][(int)(Math.ceil(this.text.length()/rows_d))];
			int k=0;
			for (int j=0;j<(int)(Math.ceil(this.text.length()/rows_d));j++) {
				for(int i=0;i<(int)rows_d;i++) {
					if(k==this.text.length()|| k>this.text.length()) {
						matrica[i][j]='w';
						k++;
					}
					else {
						matrica[i][j]=this.text.charAt(k);
						k++;
					}
				}
			}
			for(int i=0;i<(int)rows_d;i++) {
			for (int j=0;j<(int)(Math.ceil(this.text.length()/rows_d));j++) {
				 
					System.out.print(matrica[i][j]+" ");
			}
				
				System.out.println();
			}
		
		}
		public void ShowMatricaDekriptim() {
			this.text=this.text.replaceAll(" ","");
			double rowsindouble=Double.valueOf(this.rows);
			char[][] matrica=new char [(int)rowsindouble][(int)(Math.ceil(this.text.length()/rowsindouble))];
			int k=0;
			for(int i=0;i<(int)rowsindouble;i++) {
				for(int j=0;j<(int)(Math.ceil(this.text.length()/rowsindouble));j++) {
					if(k==this.text.length()|| k>this.text.length()) {
						matrica[i][j]='w';
						k++;
					}
					else {
						matrica[i][j]=this.text.charAt(k);
						k++;
					}
				}
			}
			for(int j=0;j<(int)(Math.ceil(this.text.length()/rowsindouble));j++) {
				for(int i=0;i<(int)rowsindouble;i++) {
					
					}
				System.out.println();
		}

	} 
	}