package baseDatos_NBA.P7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BDController {
	private Connection conexion;
	BDController() {
		try {
			// Iniciamos la conexion con la config de acceso
			this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/nba", "root", "");

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error en constructor BDController: " + e.getMessage());
		}
	}
	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
	/*METODOS EXTRAS*/
	/*EJERCICIO 4*/
	public ArrayList<Equipo>mostrarDivisiones(String division){
		ArrayList<Equipo>equipos=new ArrayList<Equipo>();
		try {
			Statement myStatement=this.conexion.createStatement();
			String cadenaSQL="select nombre,ciudad from equipos where division='"+division+"'";
			ResultSet rs = myStatement.executeQuery(cadenaSQL);
			while (rs.next()) {
				equipos.add(new Equipo(rs.getString("nombre"),rs.getString("ciudad")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return equipos;
	}
	/*EJERCICIO 3*/
	public ArrayList<Equipo>mostrarConferencia(String conferencia){
		ArrayList<Equipo>equipos=new ArrayList<Equipo>();
		try {
			Statement myStatement=this.conexion.createStatement();
			String cadenaSQL="select nombre,ciudad from equipos where conferencia='"+conferencia+"'";
			ResultSet rs = myStatement.executeQuery(cadenaSQL);
			while (rs.next()) {
				equipos.add(new Equipo(rs.getString("nombre"),rs.getString("ciudad")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return equipos;
	}
	/*EJERCICIO 2*/
	public boolean existeJugadorSinNull(int cod) {
		boolean encontrado=false;
		try {
			Statement myStatement=this.conexion.createStatement();
			String cadenaSQL="select*from jugadores where  codigo="+cod;
			ResultSet rs = myStatement.executeQuery(cadenaSQL);
			while (rs.next()) {
				encontrado=true;
			}
			myStatement.close();
			rs.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("ERROR JEJE");
		}
		return encontrado;
	}
	public void jugadorBaja(int cod) {
		String cadenaSql="delete from jugadores where codigo="+cod;
		try {
			Statement miStatement = this.conexion.createStatement();
			miStatement.executeUpdate(cadenaSql);
			miStatement.close();
			System.out.println("Se borro correctamente");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/*EJERCICIO 1*/
	public void jugadorAlta(String date,int cod) {
		String cadenaSQL="update jugadores set nombre_equipo='"+date+"'"+" where codigo="+cod;
		try {
			Statement miStatement = this.conexion.createStatement();
			miStatement.executeUpdate(cadenaSQL);
			miStatement.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public boolean existeEquipo(String date) {
		boolean encontrado=false;
		try {
			Statement myStatement=this.conexion.createStatement();
			String cadenaSQL="select*from equipos where nombre='"+date+"'";
			ResultSet rs = myStatement.executeQuery(cadenaSQL);
			while (rs.next()) {
				encontrado=true;
			}
			myStatement.close();
			rs.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("ERROR JEJE");
		}
		return encontrado;
	}
	
	public ArrayList<Equipo>mostrarEquipos() {
		ArrayList<Equipo>equipos=new ArrayList<Equipo>();
		try {
			Statement myStatement=this.conexion.createStatement();
			String cadenaSQL="select*from equipos";
			ResultSet rs = myStatement.executeQuery(cadenaSQL);
			while (rs.next()) {
				equipos.add(new Equipo(rs.getString("nombre"),rs.getString("ciudad"),rs.getString("conferencia"),rs.getString("division")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR JEJE");
		}
		return equipos;
	}
	public boolean existeJugador(int cod) {
		boolean encontrado=false;
		try {
			Statement myStatement=this.conexion.createStatement();
			String cadenaSQL="select*from jugadores where  nombre_equipo is null and codigo="+cod;
			ResultSet rs = myStatement.executeQuery(cadenaSQL);
			while (rs.next()) {
				encontrado=true;
			}
			myStatement.close();
			rs.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("ERROR JEJE");
		}
		return encontrado;
	}
}
