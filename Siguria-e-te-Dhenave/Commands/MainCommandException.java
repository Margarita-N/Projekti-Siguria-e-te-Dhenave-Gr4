package Komandat;

public class MainCommandException extends Exception{
    public MainCommandException() {
        super("Komanda e dhene eshte e pavlefshme!"+
                "\nKomandat e vlefshme jane keto:"+
                "\n1.rail-fence\n2.case\n3.numerical\n4.create-user\n5.delete-user\n6.export-key\n7.import-key\n8.write-message\n9.read-message"+
                "\nKUJDES madhesine e shkronjave!");

    }
}
