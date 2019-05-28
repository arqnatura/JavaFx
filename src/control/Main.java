package control;

import java.util.ArrayList;

import modelo.Jugador;
import modelo.Equipo;
import modelo.dao.AccesoDatos;

public class Main {

	public static void main(String[] args) {
		
		
		
//GENERA CLASIFICACION e INSERTA LOS DATOS DE LA CLASIFICACION EN UNA TABLA BBDD
		// ArrayList<Equipo> clasificacion = AccesoDatos.generaClasificacionBD();
		// AccesoDatos.insertaEquiposDesdeLista(clasificacion);
		
//ACCEDE a la base de datos y hacer una consulta de los jugadores
		// ArrayList<Jugador> jugadoresEquipo = AccesoDatos.getPlayersByTeam(19);
		// System.out.println(jugadoresEquipo);
		
		// BaseDatos bd = new BaseDatos ("localhost:3306", "liga", "root", "1234");

//CONSULTAS MYSQL A BASES DE DATOS				
		// AccesoDatos.showDataBases(); // Accede a la base de datos y hacer una consulta
		// AccesoDatos.showTables("liga"); // Accede a la base de datos y hacer una consulta
		// AccesoDatos.selectClasificacion("liga", "clasificacion"); // Accede a la base de datos y hacer una consulta
		// AccesoDatos.getAllTeams();
		
//RECORRE CUALQUIER TABLA		
		// AccesoDatos.recorreCualquierTabla("liga", "equipos"); // Accede al fichero y muestra por consola TODOS LOS EQUIPOS.
		// AccesoDatos.recorreCualquierTabla("liga", "jugadores"); // Accede al fichero y muestra por consola TODOS LOS JUGADORES.
		
//INSERTA DATOS DESDE FICHEROS EN TABLAS DE LA BASE DE DATOS			
		// AccesoDatos.insertaJugadoresDesdeFichero("ficheros/jugadores2.txt");  // Inserta jugadores en la tabla jugadores desde el fichero
		// AccesoDatos.insertaEquiposDesdeFichero("ficheros/equipos.txt");  // Inserta equipos en la tabla equipos desde el fichero equipos.txt
		// AccesoDatos.insertaPartidosDesdeFichero("ficheros/partidos.txt");  // Inserta partidos en la tabla partidos desde el fichero
		// AccesoDatos.insertaPartidosDesdeFichero2("ficheros/partidos.txt");
		
		System.out.println("Fin del programa");
		
	}

}
