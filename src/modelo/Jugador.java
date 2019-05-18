package modelo;

public class Jugador {
	private int idJugador;
	private String nombre;
	private int dorsal;
	private int idEquipo;

	public Jugador() {
		// TODO Auto-generated constructor stub
	}

	public Jugador (String nombre, int idJugador, int dorsal,	int idEq) {
		this.nombre = nombre;
		this.idJugador = idJugador;
		this.dorsal = dorsal;
		this.idEquipo = idEq;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int id) {
		this.idJugador = idJugador;
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

}
