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
