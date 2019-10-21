:- ['bowling2.pl'].

test(casoElemental).
test(casoUnFrame).
test(casoVariosStrikesSeguidos).
test(casoSpare).

pass(casoElemental) :- puntos([ frame(3, 2), frame(3, 2), frame(5, 4), frame(8, 1), 
		 frame(5, 2), frame(3, 2), frame(3, 2), frame(5, 4), 
		 frame(8, 1), frame(5, 2) ], 70).

pass(casoUnFrame):- puntos([ frame(3, 2), frame(10), frame(5, 4), frame(8, 1), 
		 frame(5, 2), frame(3, 2), frame(3, 2), frame(5, 4), 
		 frame(8, 1), frame(5, 2) ], 84).

pass(casoVariosStrikesSeguidos):- puntos([ frame(10), frame(10), frame(10), frame(10), 
		 frame(0,0), frame(0,0), frame(0,0), frame(0,0), 
		 frame(0,0), frame(0,0) ], 90).

pass(casoSpare):- puntos([ frame(0,0), frame(0,0), frame(0,0), frame(0,0), frame(8,2), frame(3,5), frame(5,4), frame(5,4), 
		 frame(0,0), frame(0,0) ], 39).

