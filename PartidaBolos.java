package v3;

import java.util.Scanner;

public class PartidaBolos {

	public static final int NUMERO_DE_RONDAS = 10;
	public static int record = 0;
	public static String nom_record = "";
	public static int HOFPuntuaciones[] = new int[3];
	public static String HOFNombres[] = {"","",""};

	public static void main(String[] args) {
		String respuesta = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("  Bienvenidos a Java Bowling"+ "\n"
				+ "          *****              "+ "\n"
				+ "         *     *             "+ "\n"
				+ "         *     *             "+ "\n"
				+ "          *****              "+ "\n"
				+ "           ***               "+ "\n"
				+ "           ***              "+ "\n"
				+ "           ***               "+ "\n"
				+ "          *****              "+ "\n"
				+ "        *********            "+ "\n"
				+ "           ***             "+ "\n"
				+ "                            "+ "\n"
				+ "         OOOOOOO            "+ "\n"
				+ "       OO       OO          "+ "\n"
				+ "     OO   O   O   OO         "+ "\n"
				+ "    OO     O   O   OO      "+ "\n"
				+ "     OO   O   O   OO        "+ "\n"
				+ "       OO       OO           "+ "\n"
				+ "         OOOOOOO             "+ "\n");
		System.out.println("Presione ENTER para continuar");
		sc.nextLine();
		System.out.println("Cargando...");
		esperar(3);
		System.out.println("Cargando...");
		esperar(2);
		//usamos un do-while para que se repita el juego con jugadores distintos mientras se dé la condición (respuesta="s").
		do {
			

			System.out.println("¡Hola! , bienvenidos a la bolera!, ¿cuál es tu nombre?");

			Jugador jugador1 = new Jugador(sc.nextLine());

			System.out.println("¡Estupendo! Dime cómo se llama tu contrincante y podréis" + "\n" + "empezar a jugar a los bolos");

			Jugador jugador2 = new Jugador(sc.nextLine());

			for (int i = 0; i < (NUMERO_DE_RONDAS - 1); i++) {
				System.out.println("-----RONDA" + (i + 1) + "-----");
				jugador1.jugarTurno(i);

				jugador2.jugarTurno(i);
				sc.nextLine();
			}
			jugador1.jugarTurnoFinal();
			jugador2.jugarTurnoFinal();

			System.out.println("\n" + "La tabla de puntuación de " + jugador1.getNombre() + " es la siguiente:");
			jugador1.mostrarPuntuacion();
			System.out.println("La puntuación de " + jugador1.getNombre() + " es " + jugador1.calcularPuntuacion() + " puntos");

			System.out.println();

			System.out.println("\n" + "La tabla de puntuación de " + jugador2.getNombre() + " es la siguiente:");
			jugador2.mostrarPuntuacion();
			System.out.println("La puntuación de " + jugador2.getNombre() + " es " + jugador2.calcularPuntuacion() + " puntos");

			if (jugador1.calcularPuntuacion() > jugador2.calcularPuntuacion()) {
				System.out.println("\n" + "¡" + jugador1.getNombre() + " ha ganado!");
				comprobarRecord(jugador1);
			} else {
				System.out.println("\n" + "¡" + jugador2.getNombre() + " ha ganado!");
				comprobarRecord(jugador2);
			}
			comprobarHallOfFame(jugador1, jugador2);
			
			System.out.println("\n" + "¿Queréis jugar otra vez?(s/n)");

			respuesta = sc.nextLine();

			

			

		} while (respuesta.equals("s"));
		
		sc.close();

		System.out.println("***GRACIAS POR JUGAR***");
	}
	/**
	 * Este método compara el método calcularPuntuacion con el atributo record. Si es superior, lo actualiza con dicha cantidad
	 * y el nombre obtenido mediante getNombre.
	 * @param jugadorRecord. objeto de la clase jugador.
	 */
	public static void comprobarRecord(Jugador jugadorRecord) {
		if (jugadorRecord.calcularPuntuacion() > record) {
			record = jugadorRecord.calcularPuntuacion();
			nom_record = jugadorRecord.getNombre();
			System.out.println("El record es de " + jugadorRecord.getNombre() + " con "
					+ jugadorRecord.calcularPuntuacion() + " puntos"+"\n");

		}
	}

	/**
	 * El método recorre el array de HOFPuntuaciones desde la posición 0 y compara la puntuación total (calcularPuntuacion) para saber
	 * Si es mayo
	 * @param jugador1
	 * @param jugador2
	 */
	public static void comprobarHallOfFame(Jugador jugador1, Jugador jugador2) {
		System.out.println("---HALL OF FAME__");
		for (int i = 0; i < HOFNombres.length; i++) {
			if (jugador1.calcularPuntuacion() > HOFPuntuaciones[i]) {
				for (int j = 2; j > i; j--) {
					HOFPuntuaciones[j] = HOFPuntuaciones[j - 1];
					HOFNombres[j] = HOFNombres[j - 1];

				}
				HOFPuntuaciones[i] = jugador1.calcularPuntuacion();
				HOFNombres[i] = jugador1.getNombre();
				break;
			}
		}
		for (int i = 0; i < HOFNombres.length; i++) {
			if (jugador2.calcularPuntuacion() > HOFPuntuaciones[i]) {
				for (int j = 2; j > i; j--) {
					HOFPuntuaciones[j] = HOFPuntuaciones[j - 1];
					HOFNombres[j] = HOFNombres[j - 1];

				}
				HOFPuntuaciones[i] = jugador2.calcularPuntuacion();
				HOFNombres[i] = jugador2.getNombre();
				break;
			}
		}
		for (int i = 0; i < HOFNombres.length; i++) {
			System.out.println("Nº" + (i + 1) + ": " + HOFNombres[i] + " " + HOFPuntuaciones[i]);
		}
	}



/**El método detiene la ejecución del programa durante el tiempo que se le indique.
 * No ejecuta el catch porque el try no produce una excepción, pero el método Thread.sleep()
 * solo se puede ejecutar dentro de un try-catch de manejo de excepciones.
 * @param segundos Los segundos que se detendrá el programa.
 */
public static void esperar(int segundos){
    try {
        Thread.sleep(segundos * 1000);
     } catch (Exception e) {
        System.out.println(e);
     }
}   

}
