# Projekti-Siguria-e-te-Dhenave-Gr4
Console programi eshte i krijuar me ndihmen e gjuhes programuese JAVA ne Eclipse-workspace.
Programi ka 3 komanda kryesore per secilen prej tyre eshte krijuar nga nje klase duke perfshire edhe klasat e Exceptions.
Klasat e komandave jane instancuar ne MainProgram(programi kryesor) dhe kemi perdorur switch per shqyrtimin e te gjitha rasteve,
ku secili case paraqet funksion te krijuar ne klasat perkatese.
Gjithashtu per shqyrtimin e gabimeve kemi perdorur try and catch qe programi te mos behet terminate.

1.Udhëzimet për ekzekutimin e programit

Programi ka 3 komanda:

	*rail-fence
	*case
	*numerical
Ne secilen nga rastet argumenti i pare(args[0]) eshte njera nga keto komanda.
*Komanda rail-fence ka nenkomandat encrypt dhe decrypt te cilat mund te thirren ne kete menyre:
	rail-fence encrypt
	rail-fence decrypt
Gjithashtu duhet te jepet numri i rreshtave n:
	Shembull:
	  rail-fence nenkomanda n
Me pas jepet teksti:
	Shembull:
	  rail-fence nenkomanda n "teksti"
Gjithashtu kemi bere opsionin shtese 'show' i cili jepet sipas deshires, por gjithsesi para tekstit, per te shfaqur matricen e enkriptimit ose dekriptimit.
	Shembull:
	  rail-fence nenkomanda n show "teksti"

*Komanda case ka nenkomandat lower, upper, capitalize, inverse dhe alternating, te cilat mund te thirren ne kete menyre:
	case nenkomanda "teksti"
Gjithashtu kemi bere opsionin shtese 'sentence' i cili jepet sipas deshires, por gjithsesi para tekstit, per te shfaqur fjalite e tekstit ne funksione te ndryshme, 
ku ne mes te tyre vendoset nje shenje e pikesimit ('.',',','?','!')
	Shembull:
	  case nenkomanda sentence "teksti"

*Komanda numerical ka nenkomandat encrypt dhe decrypt, te cilat mund te thirren ne kete menyre:
	numerical nenkomanda "teksti"
Gjithashtu kemi bere opsionin shtese 'separator' i cili jepet sipas deshires, por gjithsesi para tekstit, i cili e specifikon karakterin ndarës.Gjithashtu 
ruhen hapesirat e tekstit origjinal permes karakterit '~'.
	Shembull:
	  numerical nenkomanda separator <char> "teksti"
	

2. Përshkrim i shkurtër për secilën komandë
	*rail-fence:
		encrypt-enkripton tekstin permes matrices
		decrypt-dekripton ciphertext-in permes matrices
	Kjo komande nuk paraqet hapesirat mes fjaleve ne matrice, dhe nese shtylla e fundit nuk plotesohet me karaktere, vendet e zbrazeta mbushen me 'w'
	
	*case:
		lower-e kthen tekstin ne shkronja te vogla
		upper-e kthen tekstin ne shkronja te medha
		capitalize-shkronjen e pare te seciles fjale e kthen te madhe
		inverse-shkronjat te cilat ne tekst jane te medha i kthen te vogla, dhe anasjelltas
		alternating-kthen nje shkronje te madhe, nje te vogel e keshtu me rradhe
	
	*numerical:
		encrypt-enkripton tekstin ne numrat perkates te shkronjave ne alfabet
		decrypt-dekripton ciphertext-in ne shkronja
	Kjo komande gjate dekriptimit ruan hapesirat origjinale mes fjaleve ne tekst.

3. Rezultatet e ekzekutimit me nga një shembull për secilën komandë dhe nënkomandë
       *rail-fence:
              argumenti: rail-fence encrypt 3 "Siguria e te dhenave"
              rezultati: Suaeevirednegithaw

              argumenti: rail-fence decrypt 3 "Suaeevirednegithaw"
              rezultati: Siguriaetedhenavew

              argumenti: rail-fence encrypt 3 show "LyraMagiGita"
              rezultati: LagiyMitraGa
                         L a g i 
                         y M i t 
                         r a G a 

              argumenti: rail-fence decrypt 3 show "LagiyMitraGa"
              rezultati: LyraMagiGita
                         L y r 
                         a M a 
                         g i G 
                         i t a 
       
       *numerical:
             argumenti: numerical encrypt "Siguria e te dhenave"
             rezultati: 19 9 7 21 18 9 1 62 5 62 20 5 62 4 8 5 14 1 22 5

             argumenti: numerical decrypt "19 9 7 21 18 9 1 62 5 62 20 5 62 4 8 5 14 1 22 5"
             rezultati: siguria e te dhenave

             argumenti: numerical encrypt seperator = "Siguria e te dhenave"
             rezultati: 19=9=7=21=18=9=1=62=5=62=20=5=62=4=8=5=14=1=22=5=
  
             argumenti: numerical decrypt seperator = "19=9=7=21=18=9=1=62=5=62=20=5=62=4=8=5=14=1=22=5="
             rezultati: siguria e te dhenave

       *case
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

       Dhe nenkomanda sentence cfaredo nenkomande qe merr prape do ta kete vlera te njejta,pra, fjalite do te nderrojne ne renditjen
       lower->upper->capitalize->inverse->alternating dhe vazhdon keshtu ne unaze:
             argumenti: case lower sentence "Siguria e te dhenave.Margarita!Lyra?Magi, grupi 4."
             rezultati: siguria e te dhenave.margarita!lyra?magi, grupi 4.
	                siguria e te dhenave.MARGARITA!Lyra ?mAGI, gRuPi 4
