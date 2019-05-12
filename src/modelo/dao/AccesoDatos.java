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

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.DatabaseMetaData;

import control.BaseDatos;

public class AccesoDatos {
	
	// CONSULTA DE LAS BASES DE DATOS DISPONIBLES
	
	
	public static void  showDataBases () {
		
		DefaultTableModel model;
	
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion = DriverManager.getConnection("localhost","root","1234");
		DatabaseMetaData meta = conexion.getMetaData();
		ResultSet resultSet = meta.getCatalogs();
		while (resultSet.next()) {
		   String db = resultSet.getString("TABLE_CAT");

		model.addRow(new Object[] {db});
		}
		resultSet.close();
		conexion.close();

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
					String nif = campos [1];
					String nombre = campos [2];
					int dorsal = Integer.parseInt(campos[3]);
					String fechaNacimiento = campos[4];
					String sexo = campos [5];
					int numero = Integer.parseInt(campos[6]);
					int idEquipo = Integer.parseInt(campos [7]);
					
					
					String sql ="INSERT INTO jugadores (idJugador, nif, nombre, dorsal, fechaNacimiento, sexo, numero, idEquipo) VALUES ";
					sql += "(" + idJugador + ",\"" + nif  + "\"," + "\"" + nombre  + "\"," + "\"" + dorsal 
							+ "\"," + "\"" + fechaNacimiento  + "\"," + "\"" + sexo  + "\"," + "\"" + numero  + "\"," + "\"" + idEquipo + "\")";
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
