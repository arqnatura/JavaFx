package control;

import modelo.dao.AccesoDatos;

public class Main {

	public static void main(String[] args) {
		
		// BaseDatos bd = new BaseDatos ("localhost:3306", "liga", "root", "1234");
		
		
		// AccesoDatos.recorreCualquierTabla("liga", "equipos"); // Accede al fichero y muestra por consola TODOS LOS EQUIPOS.
		
		AccesoDatos.insertaJugadoresDesdeFichero("ficheros/jugadores.txt");  // Inserta jugadores en la tabla jugadores desde el fichero
		// AccesoDatos.insertaEquiposDesdeFichero("ficheros/equipos.txt");  // Inserta equipos en la tabla equipos desde el fichero equipos.txt
		
	}

}
