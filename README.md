PROJEKTI SIGURIA E TE DHENAVE 

Faza1

Udhezime:

1.Forma e pergjithshme per komanden rail-fence 
<rail-fence> <nenkomanda> <numri_i_rreshtave> [show] <teksti> ,
	
2.Forma e pergjithshme per komanden case
<case> <nenkomanda> [sentence] <teksti>,
	
3.Forma e pergjithshme per komanden numerical 
<numerical> <nenkomanda> [seperator] [lloji_i_seperator] <teksti>,

Pershkrimi per komanda:
1.Komanda rail-fence - ben enkriptimin ose dekriptimin e tekstit permes matrices me numrin e caktuar te rreshtave si argument , nenkomanda opsionale "show" shfaq matricen,
	
2.Komanda case - ben modifikimin e tekstit me disa 							nenkomanda:upper,lower,capitalize,inverse,alternating.Nenkomanda opsionale "sentence" paraqet fjalite e tekstit ne formate te ndryshme,

3.Komanda numerical - ben enkodimin dhe dekodimin e nje teksti ku secila shkronje zevendesohet me poziten e saj ne alfabet.Nenkomanda opsionale "seperator" mundeson caktimin e nje simboli per ndarjen e numrave me te.


Rezultatet me shembuj:

	1.Rail-fence
	     argumenti: rail-fence encrypt 3 "Siguria e te dhenave"
             rezultati: Suaeevirednegithaw

             argumenti: rail-fence decrypt 3 "Suaeevirednegithaw"
             rezultati: Siguriaetedhenavew
		
             argumenti: rail-fence encrypt 3 show "LyraMagiGita"
	     argumenti: rail-fence decrypt 3 show "LagiyMitraGa"
	
	2.Case
	     argumenti: case lower "Siguria e te dhenave!"
             rezultati: siguria e te dhenave!

             argumenti: case upper "Siguria e te dhenave!"
             rezultati: SIGURIA E TE DHENAVE!
 
             argumenti: case capitalize "Siguria e te dhenave!"
             rezultati: Siguria E Te Dhenave! 

             argumenti: case inverse "Siguria E tE dhEnaVe!"
             rezultati: sIGURIA e Te DHeNAvE!
 
             argumenti: case alternating "Siguria e te dhenave!"
             rezultati: sIgUrIa e tE DhEnAvE!
		
	3.Numerical
	     argumenti: numerical encrypt "Siguria e te dhenave"
             rezultati: 19 9 7 21 18 9 1 62 5 62 20 5 62 4 8 5 14 1 22 5

             argumenti: numerical decrypt "19 9 7 21 18 9 1 62 5 62 20 5 62 4 8 5 14 1 22 5"
             rezultati: siguria e te dhenave
		
	     argumenti: numerical encrypt seperator = "Siguria e te dhenave"
	     rezultati: 19=9=7=21=18=9=1=62=5=62=20=5=62=4=8=5=14=1=22=5=
  
		
Faza2 

Udhezime:

1.Forma e pergjithshme per komanden create-user 
<create-user> <emri> ,
	
2.Forma e pergjithshme per komanden delete-user
<delete-user> <emri>,
	
3.Forma e pergjithshme per komanden export-key
<export-key> <public/private> <emri> [file],
	
4.Forma e pergjithshme per komanden import-key
<import-key> <emri> <file/url> ,
	
5.Forma e pergjithshme per komanden write-message
<write-message> <emri> <mesazhi> [path]
	
6.Forma e pergjithshme per komanden read-message 
<read-message> <path/mesazhi>
	
	
Pershkrimi per komanda:

1.Create-user krijon celesin publik dhe privat RSA;
	
2.Delete-user fshin celesin ekzitues RSA;
	
3.Export-key eksporton celesin ne file-in e dhene perndryshe paraqitet ne console;
	
4.Import-key importon celesin publik ose private ne direktoriumin e celesave;

5.Write-message shkruan nje mesazh te enkriptuar te dedikuar per nje shfrytezues;

6.Read-message e dekripton dhe e shfaq ne console mesazhin e enkriptuar;


Rezultatet me shembuj:
		
		1.Create-user
		Argumenti: create-user User
		Rezultati: Krijohet celesi publik dhe privat i perdoruesit me emer User ne direktoriumin e celesave
		
		2.Delete-user User
		Argumenti: delete-user User
		Rezultati: Fshin celesin ekzistues te perdoruesit me emer User
		
		3.Export-key 
		Argumenti: export-key public User 
		Rezultati: Celesi publik i shfrytezuesit User shfaqet ne console
		
		Argumenti: export-key public User C:\Users\Lenovo\Desktop\Testim\Celesi.xml
		Rezultati: Eksportohet celesi ne folderin Testim 
		
		4.Import-key
		Argumenti: import-key User1 https://pastebin.com/raw/568vxV7i
		Rezultati: Importohet celesi(nese eshte privat krijohen nje file per publik nje per private) i shfrytezuesit User1 i 
		cili merret nga permbajtja URL.
		
		Argumenti: import-key User1 C:\Users\Lenovo\Desktop\Testim\Celesi1.xml
		Rezultati: Celesin ne file-in Celesi1.xml e importon ne direktoriumin e celesave.
		
		5.Write-message
		Argumenti: write-message User Pershendetje 
		Rezultati: Shfaq mesazhin e shfrytezuesit te enkriptuar me celesin publik te user ne console
		
		Argumenti: write-message User Pershendetje C:\Users\Lenovo\Desktop\Testim\Teksti.txt
		Rezultati: E ruan mesazhin e enkriptuar ne file-in Teksti.txt
		
		6.Read-message
		Argumenti: read-message mesazhi_i_enkriptuar
		Rezultati: Lexon marresin e mesazhit dhe mesazhin e dekriptuar
		
		Argumenti: read-message C:\Users\Lenovo\Desktop\Testim\Teksti.txt
		Rezultati: Lexon marresin e mesazhit dhe mesazhin e dekriptuar nga file-i Teksti.txt
		

Faza3

Udhezime:

1.Forma e pergjithshme per komanden create-user <create-user> <emri>
	
2.Forma e pergjithshme per komanden delete-user
<delete-user> <emri>,
	
3.Forma e pergjithshme per komanden login 
<login> <emri>
	
4.Forma e pergjithshme per komanden status
<status> <token>
	
5.Forma e pergjithshme per komanden  write-message
<write-message> <marresi> <mesazhi> [path] <token>

6.Forma e pergjithshme per komanden read-message
<read-message> <mesazhi>
	


Pershkrimi per komanda:


1.Create-user krijon celesin publik dhe privat RSA te perdoruesit, por per krijimin e tyre kerkohet nje fjalekalim i cili eshte valid, fjalekalimi ruhet salted dhe hashed ne databaze(MYSQL) se bashku me emrin e perdoruesit i cili jepet si argument.

2.Delete-user fshin celesin ekzistues te atij perdoruesi , ku gjithashtu e fshin perdoruesin (emrin dhe fjalekalimin e tij) edhe nga databaza.

3.Login fillimisht teston nese ekziston ai perdorues dhe e kerkon fjalekalimin e tij , pastaj nese keto vertetohen atehere leshohet nje token i nenshkruar.

4.Status na tregon ne qofte se tokeni eshte valid, pra jep informate ne qofte se ai ka skaduar ,nenshkrimi nuk eshte valid apo nuk ekzisotn ai shfrytezues

5.Write-message ku pervec qe shkruan nje mesazh te enkriptuar te dedikuar per nje shfrytezues e bashkangjet edhe emrin dhe nenshkrimin e shfrytezuesit 

6.Read-message e dekripton dhe e shfaq ne console mesazhin e enkriptuar dhe nese figuron pjesa e derguesit/nenshkrimit atehere behet edhe verifikimi i atij nenshkrimi , ne te kundert jep vetem marresin, mesazhin ,derguesin.


Rezultatet me shembuj:

		1.Create-user
		Argumenti: create-user User
		Jepni fjalekalimin:
		Perseritni fjalekalimin:
		Rezultati: Krijohet celesi publik dhe privat i perdoruesit me emer User ne direktoriumin e celesave dhe ruhet emri dhe fjalekalimi i tij ne databaze
		
		2.Delete-user
		Argmunenti: delete-user User
		Rezultati: Fshin celesin ekzistues te perdoruesit me emer User dhe largon te dhenat e tij nga databaza
		
		3.Login
		Argumenti: login User
		Jepni fjalekalimin:
		Rezultati: Leshohet nje token i nenshkruar ne qofte se emri i user dhe fjalekalimi pershtaten 
		
		4.Status
		Argumenti: status eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJNY....
		Rezultati: Tregon emrin e user pastaj tregon nese  tokeni i dhene eshte valid apo jo dhe kohen e skadimit
		
		5.Write-message
		Argumenti: write-message User Pershendetje eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJNY....
		Rezultati: Jep mesazhin e enkriptuar ne qofte se tokeni eshte valid
		
		6.Read-message
		Argumenti: read-message mesazhi_i_enkriptuar 
		Rezultati: Shfaq ne console marresin , mesazhin e dekriptuar ,derguesin, dhe nese jepet celesi publik i derguesit atehere tregon se nenshkrimi eshte valid , ne te kundert tek nenshkrimi tregon se mungon ai celes.
		


	

Referencat:

Krijimi i XML files:"https://examples.javacodegeeks.com/core-java/xml/parsers/documentbuilderfactory/create-xml-file-in-java-using-dom-parser-example/"

Materiali kryesor i shfrytezuar per krijimin e RSA celesave:"https://www.devglan.com/java8/rsa-encryption-decryption-java"

Materiali kryesor per krijimin e DES celesave,si dhe enkriptimi dhe dekriptimi me DES:"https://www.journaldev.com/1309/java-des-algorithm-program"

Per shkruarjen, leximin dhe zhvendosjen e files jane perdorur ushtrimet e ass.Vigan nga lenda Programimi i Orientuar ne Objekte
