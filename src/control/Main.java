package control;

import java.util.ArrayList;

import modelo.Jugador;
import modelo.dao.AccesoDatos;

public class Main {

	public static void main(String[] args) {
		
		// Accede a la base de datos y hacer una consulta de los jugadores
		ArrayList<Jugador> jugadoresEquipo = AccesoDatos.getPlayersByTeam(19);
		System.out.println(jugadoresEquipo);
		
		// BaseDatos bd = new BaseDatos ("localhost:3306", "liga", "root", "1234");
				
		// AccesoDatos.showDataBases(); // Accede a la base de datos y hacer una consulta
		// AccesoDatos.showTables("liga"); // Accede a la base de datos y hacer una consulta
		
		// AccesoDatos.recorreCualquierTabla("liga", "equipos"); // Accede al fichero y muestra por consola TODOS LOS EQUIPOS.
		// AccesoDatos.recorreCualquierTabla("liga", "jugadores"); // Accede al fichero y muestra por consola TODOS LOS JUGADORES.
		
				
		// AccesoDatos.insertaJugadoresDesdeFichero("ficheros/jugadores2.txt");  // Inserta jugadores en la tabla jugadores desde el fichero
		// AccesoDatos.insertaEquiposDesdeFichero("ficheros/equipos.txt");  // Inserta equipos en la tabla equipos desde el fichero equipos.txt
		// AccesoDatos.insertaPartidosDesdeFichero("ficheros/partidos.txt");  // Inserta partidos en la tabla partidos desde el fichero

		System.out.println("Fin del programa");
		
	}

}
