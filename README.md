PROJEKTI SIGURIA E TE DHENAVE 

Faza1

Udhezime
	1.Forma e pergjithshme per komanden rail-fence 
	 	<komanda> <nenkomanda> <numri_i_rreshtave> [show] <teksti> 
	2.Forma e pergjithshme per komanden case
		<komanda> <nenkomanda> [sentence] <teksti>
	3.Forma e pergjithshme per komanden numerical 
		<komanda> <nenkomanda> [seperator] [lloji_i_seperator] <teksti>

Pershkrimi per komanda
	1.Komanda rail-fence - ben enkriptimin ose dekriptimin e tekstit permes matrices me numrin e     	caktuar te rreshtave si argument , nenkomanda opsionale "show" shfaq matricen.
	2.Komanda case - ben modifikimin e tekstit me disa 							nenkomanda:upper,lower,capitalize,inverse,alternating.Nenkomanda opsionale "sentence" paraqet 		fjalite e tekstit ne formate te ndryshme
	3.Komanda numerical - ben enkodimin dhe dekodimin e nje teksti ku secila shkronje zevendesohet 		me poziten e saj ne alfabet.Nenkomanda opsionale "seperator" mundeson caktimin e nje simboli per 	 ndarjen e numrave me te.

Rezultatet me shembuj
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
  
		
		



