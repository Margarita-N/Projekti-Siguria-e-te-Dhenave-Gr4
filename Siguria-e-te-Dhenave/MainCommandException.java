public class MainCommandException extends Exception {
	public MainCommandException() {
		super("Komanda e dhene eshte e pavlefshme!"+
			 "\nKomandat e vlefshme jane keto:"+
			 "\n1.rail-fence\n2.case\n3.numerical"+
			 "\nKUJDES madhesine e shkronjave!");
		
	}
}