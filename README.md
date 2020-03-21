# Projekti-Siguria-e-te-Dhenave-Gr4

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
