%Un juego esta formado de 10 frames

secuenciaFrames([]).
secuenciaFrames([frame(Tirada1, Tirada2) | Resto]) :-
	Tirada1 + Tirada2 =< 10,
	secuenciaFrames(Resto).

juego(L) :-
	length(L, 10),
	secuenciaFrames(L).

puntos([], 0).
puntos([frame(T1, T2) | Resto], Puntos) :-
	\+ spare(frame(T1,T2)),
	puntos(Resto, PuntosResto),
	Puntos is T1 + T2 + PuntosResto.

puntos([Frame1, Frame2 | Resto], Puntos) :-
	strike(Frame1),
	puntos([Frame2| Resto], PuntosF2R),
	puntosFrame(Frame2, PuntosFrame2),
	Puntos is 10 + PuntosFrame2 + PuntosF2R.

puntos([Frame1, Frame2, Frame3 | Resto], Puntos) :-
	strike(Frame1),
	strike(Frame2),
	strike(Frame3),
	puntos([Frame2, Frame3 | Resto], PuntosF2F3Resto),
	Puntos is 30 + PuntosF2F3Resto, !.

puntos([frame(T1,T2), frame(T3,T4) | Resto], Puntos) :-
	spare(frame(T1,T2)),
	puntos([frame(T3,T4) | Resto], PuntosResto),
	Puntos is 10 + T3 + PuntosResto.

puntosFrame(frame(10), 10).
puntosFrame(frame(T1, T2), Puntos) :- Puntos is T1 + T2.

strike(frame(10)).
spare(frame(T1, T2)) :- \+ strike(frame(T1, T2)), T1 + T2 =:= 10.
