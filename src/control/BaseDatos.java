package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDatos {

	private String host;
	private String dbName;
	private String dbUser;
	private String dbPass;
	private Connection conexion;
		
			// CREAMOS UNA CADENA DE CONEXIÓN A CUALQUIER BASE DE DATOS

	public BaseDatos(String host, String dbName, String dbUser, String dbPass) {
		super();
		this.host = host;
		this.dbName = dbName;
		this.dbUser = dbUser;
		this.dbPass = dbPass;

        	try {
				Class.forName("com.mysql.cj.jdbc.Driver");

			this.conexion = DriverManager.getConnection("jdbc:mysql://" + host + "/" +
					dbName + "?&user=" + dbUser + "&password=" + dbPass + "&serverTimezone=UTC");
			
			System.out.println("Conectado...");				
				
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
	}
	
	
	public BaseDatos() {
		super();
	}
		
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPass() {
		return dbPass;
	}

	public void setDbPass(String dbPass) {
		this.dbPass = dbPass;
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

}

