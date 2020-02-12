package baseDatos_NBA;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Scanner sn = new Scanner(System.in);
		BDController controladorBD = new BDController();
		String menu = "";
		do {
			mostrarMenu();
			menu = sc.nextLine();
			switch (menu) {
			case "1":
				System.out.println("Introduce codigo");
				int codigo=sn.nextInt();
				if (controladorBD.existeJugador(codigo)==true) {
					for (int i = 0; i < controladorBD.mostrarEquipos().size(); i++) {
						System.out.println(controladorBD.mostrarEquipos().get(i).toString());
					}
					System.out.println("Escriba el nombre del Equipo A continuacion......");
					String equipo=sc.nextLine();
					if (controladorBD.existeEquipo(equipo)==true) {
						controladorBD.jugadorAlta(equipo, codigo);
					}else {
						System.out.println("Nombre Incorrecto");
					}
				}else {
					System.out.println("Este jugador ya tiene equipo");
				}
				break;
			case "2":
				System.out.println("Introduce codigo");
				codigo=sn.nextInt();
				if (controladorBD.existeJugadorSinNull(codigo)==true) {
					controladorBD.jugadorBaja(codigo);
				}else {
					System.out.println("Codigo introducido inexistente");
				}
				break;
			case "3":
				System.out.println("Introduzca Conferencia: West o East");
				String conferencia=sc.nextLine();
				for (int i = 0; i < controladorBD.mostrarConferencia(conferencia).size(); i++) {
					System.out.println(controladorBD.mostrarConferencia(conferencia).get(i).toString2());
				}
				break;
			case "4":
				System.out.println("Introduzca Division: Atlantic,SouthEast,Central,Pacific,NorhWest,SouthWest");
				String division=sc.nextLine();
				for (int i = 0; i < controladorBD.mostrarDivisiones(division).size(); i++) {
					System.out.println(controladorBD.mostrarDivisiones(division).get(i).toString2());
				}
				break;
			case "5":

				break;
			default:
				break;
			}
		} while (!menu.equalsIgnoreCase("5"));
	}

	public static void mostrarMenu() {
		System.out.println("Pulse 1: Alta jugador");
		System.out.println("Pulse 2: Baja jugador");
		System.out.println("Pulse 3: Listado de equipos por Division");
		System.out.println("Pulse 4: Listado de equipos por conferencia");
		System.out.println("Pulse 5: Salir");
	}
}
