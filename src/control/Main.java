package control;

import modelo.dao.AccesoDatos;

public class Main {

	public static void main(String[] args) {
		
		// BaseDatos bd = new BaseDatos ("localhost:3306", "liga", "root", "1234");
		
		AccesoDatos aDatos = new AccesoDatos ();
		
		AccesoDatos.recorreCualquierTabla("liga", "equipos"); // Accede al fichero y muestra por consola TODOS LOS EQUIPOS.

	}

}
