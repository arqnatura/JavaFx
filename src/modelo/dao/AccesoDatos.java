package modelo.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


import control.BaseDatos;
import modelo.Jugador;
import modelo.Equipo;

public class AccesoDatos {
	
	// lista de jugadores de un equipo dado

	public HashMap<String, Equipo> getPlayersByTeamMap(String idEquipo){
		HashMap<String, Equipo> listaJugadores = new HashMap<String, Equipo>();
		try {
			BaseDatos bd = new BaseDatos("localhost:3306",  "liga", "root", "1234");
			Connection conexion = bd.getConexion();
			Statement stmt = conexion.createStatement();
			String sql ="select * from jugadores where idEquipo " +		
             " like '" +  idEquipo + "'";
			
			System.out.println(sql);
			ResultSet rS = stmt.executeQuery(sql);
     		
			BufferedReader consulta;
			consulta = new BufferedReader(consulta);
			String registro;
			Equipo equipo = null;
			while  ((registro = consulta.readLine()) != null) {					
				Jugador jugador = new Jugador();
				jugador.setIdJugador(rS.getInt("idJugador"));
				jugador.setNombre(rS.getString("nombre"));
				jugador.setDorsal(rS.getInt("dorsal"));
				jugador.setIdEquipo(rS.getInt("idEquipo"));				
				listaJugadores.put(idEquipo, jugador);			
			}
			
			
			rS.close();
			stmt.close();
			conexion.close();
			return listaJugadores;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
		
	}
	
	// lista de jugadores de un equipo dado

		public static ArrayList<Jugador> getPlayersByTeam(int idEquipo){
			
			ArrayList<Jugador> listaJugadores = new ArrayList<Jugador>();
			try {
				BaseDatos bd = new BaseDatos("localhost:3306",  "liga", "root", "1234");
				Connection conexion = bd.getConexion();
				Statement stmt = conexion.createStatement();
				String sql ="select * from jugadores where idEquipo " +		
	             " like '" +  idEquipo + "'";
				
				System.out.println(sql);
				
				ResultSet rS = stmt.executeQuery(sql);
	     		
					//	+ "(select id from equipos where equipos.nombre like \"" +  equipo +"\" );");
				while(rS.next()) { 					
					Jugador jugador = new Jugador();
					jugador.setIdJugador(rS.getInt("idJugador"));
					jugador.setNombre(rS.getString("nombre"));
					jugador.setDorsal(rS.getInt("dorsal"));
					jugador.setIdEquipo(rS.getInt("idEquipo"));				
					listaJugadores.add(jugador);			
				}			
				rS.close();
				stmt.close();
				conexion.close();
				return listaJugadores;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}catch (NullPointerException e) {
				System.out.println(e.getMessage());
			}
			
			return null;
			
		}
	
		
		
	// CONSULTA DE LAS BASES DE DATOS DISPONIBLES
	
	public static void  showDataBases () {
		
		try {

			BaseDatos bd = new BaseDatos("localhost", "", "root", "1234");	
			Connection conexion = bd.getConexion();
			Statement stmt = conexion.createStatement(); 
			ResultSet rS = stmt.executeQuery ("show databases");
			
            while ( rS.next() ) {
                String lastName = rS.getString(1);
                System.out.println(lastName);
            }

			stmt.close();
			rS.close();	
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

}

	
	// CONSULTA DE LAS TABLAS DE UNA BASE DE DATOS CONCRETA_____________________
	
	public static void  showTables (String bdatos) {

				try {

					BaseDatos bd = new BaseDatos("localhost", bdatos , "root", "1234");	
					Connection conexion = bd.getConexion();
					Statement stmt = conexion.createStatement(); 
					ResultSet rS = stmt.executeQuery ("show tables");
					
		            while ( rS.next() ) {
		                String lastName = rS.getString(1);
		                System.out.println(lastName);
		            }

					stmt.close();
					rS.close();	
						
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
	
	}
	
	
		// LISTADO DE REGISTROS A UNA TABLA CONCRETA_____________________________
	
	public static void recorreCualquierTabla (String bdatos, String tabla) {
			// 1º CONECTAR A LA BASE DE DATOS BBDD.
			// Comenzamos el método conectando y lo cerramos cerrando la conexión.
		
	try {
		BaseDatos bd = new BaseDatos("localhost", bdatos , "root", "1234");		// Conectamos cualquier tabla
		Connection conexion = bd.getConexion();
		Statement stmt = conexion.createStatement();
		ResultSet rS = stmt.executeQuery("SELECT * FROM " + tabla + " WHERE 1");
				
		ResultSetMetaData mD = rS.getMetaData();
		
		for (int i = 1; i < mD.getColumnCount(); i++) {
			System.out.print(i + " -> " + mD.getColumnName(i) + "\t\t");				
		}

		
		while (rS.next()) {
			for (int i = 1; i < mD.getColumnCount(); i++)
				System.out.print(rS.getString(i) + "\t\t");	
				System.out.println();
		}

		rS.close();
		stmt.close();
		conexion.close();
		
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
}
	
	// INSERTAR REGISTROS EN TABLAS___________________________________________
	
	public static void insertaPartidosDesdeFichero(String rutaPartidos) {
		try {
				BufferedReader fichero;
				fichero = new BufferedReader(new FileReader(rutaPartidos));
				String registro;
				
				BaseDatos bd = new BaseDatos("localhost", "liga", "root", "1234");
				Connection conexion = bd.getConexion();
				Statement stmt = conexion.createStatement();
				
				while ((registro = fichero.readLine()) != null) {

					String[] campos = registro.split("#");
					
					int idPartido = Integer.parseInt(campos[0]); //ojo con las comillas en el INSERT!!!
					int jornada = Integer.parseInt(campos[1]);
					String eL = campos [2];
					int gL = Integer.parseInt(campos[3]);
					String eV = campos [4];
					int gV = Integer.parseInt(campos [5]);
					
					String sql ="INSERT INTO partidos (idPartido, jornada, eL, gL, eV, gV) VALUES ";
					sql += "(" + idPartido + ",\"" + jornada + "\"," + "\"" + eL + "\"," + "\"" + gL + "\"," + "\""
					+ eV + "\"," + "\"" + gV + "\")";
					stmt.executeUpdate(sql);
					
					System.out.println(sql);
				}
			
				fichero.close();
				System.out.println("Fin de la lectura del fichero");
			} catch (FileNotFoundException excepcion) {
				System.out.println("fichero no encontrado");
			} catch (IOException e) {
				System.out.println("IO Excepcion");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

	
	public static void insertaJugadoresDesdeFichero(String rutaJugadores) {

		try {
				BufferedReader fichero;
				fichero = new BufferedReader(new FileReader(rutaJugadores));
				String registro;
				
				BaseDatos bd = new BaseDatos("localhost", "liga", "root", "1234");
				Connection conexion = bd.getConexion();
				Statement stmt = conexion.createStatement();
				
				while ((registro = fichero.readLine()) != null) {

					String[] campos = registro.split("#");
					
					int idJugador = Integer.parseInt(campos[0]); //ojo con las comillas en el INSERT!!!
					String nombre = campos [1];
					int dorsal = Integer.parseInt(campos[2]);
					int idEquipo = Integer.parseInt(campos [3]);
					
					String sql ="INSERT INTO jugadores (idJugador, nombre, dorsal, idEquipo) VALUES ";
					sql += "(" + idJugador + ",\"" + nombre + "\"," + "\"" + dorsal + "\"," + "\"" + idEquipo + "\")";
					stmt.executeUpdate(sql);
					
					System.out.println(sql);
				}
			
				fichero.close();
				System.out.println("Fin de la lectura del fichero");
			} catch (FileNotFoundException excepcion) {
				System.out.println("fichero no encontrado");
			} catch (IOException e) {
				System.out.println("IO Excepcion");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

	public static void insertaEquiposDesdeFichero(String rutaEquipos) {

		try {
				BufferedReader fichero;
				fichero = new BufferedReader(new FileReader(rutaEquipos));
				String registro;
				
				BaseDatos bd = new BaseDatos("localhost", "liga", "root", "1234");
				Connection conexion = bd.getConexion();
				Statement stmt = conexion.createStatement();
				

				
				while ((registro = fichero.readLine()) != null) {

					String[] campos = registro.split("#");
					
					int idEquipo = Integer.parseInt(campos[0]); //ojo con las comillas en el INSERT!!!
					String nombreCorto = campos [1];
					String nombre = campos [2];
					
					String sql ="INSERT INTO equipos (idEquipo, nombreCorto, nombre) VALUES ";
					sql += "(" + idEquipo + ",\"" + nombreCorto + "\"," + "\"" + nombre + "\")";
					stmt.executeUpdate(sql);
							//	ResultSetMetaData mD = rS.getMetaData();
					
					System.out.println(sql);
				}
			
				fichero.close();
				System.out.println("Fin de la lectura del fichero");
			} catch (FileNotFoundException excepcion) {
				System.out.println("fichero no encontrado");
			} catch (IOException e) {
				System.out.println("IO Excepcion");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	
}
