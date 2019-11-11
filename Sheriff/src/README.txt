Aluchenesei Cosmin Florin
Grupa 322, Seria CA


Data inceperii temei:7.11.2019
Data finalizarii temei:10.11.2019


Probleme intampinate:
	Intelegerea in totalitate a cerintei, consecinte obersvabile in cateva teste.


Algoritumul utilizat:
	Pasul1: Se creeaza clasele sugestive rezolvarii, clasa Player ce urmeaza sa fie extinsa de Basic,
 		iar aceasta mai departe de Bribed si Greedy(in anumite circumstante ei vor juca precum un BasicPlayer).
		Conform cerintei fiecare Player are metodele sale cu care isi alege cartea(ex: sortari pt dupa frecventa,
		profit, ID, etc...). Avem o clasa pentru pachet si cateva metode specifice pentru impartirea cartilor.

	Pasul2: Metodele playAsSheriff si playAsVendor vor fi abstracte asa incat urmeaza a fi extinse de fiecare tip de jucator in parte.
		Clasa Player va contine toate metodele ce faciliteaza rezolvarea in ceea ce priveste interactiunea intre playeri.
 
	Pasul3: Dupa ce jucatorii joaca conform strategiei si sunt verificati bunurile cu care au ramas se baga pe taraba,
		aceasta urmand sa fie actualizata si cu bunurile ilegale aditionale in main inainte de calcularea
		clasamentului king si queen bonus.

	Pasul4: Se face punctajul final pentru fiecare player si un se facea afisarea clasamentului final in consola.

P.S: Codul are metode cu nume suficient de sugestiv si pe alocuri comentarii.
