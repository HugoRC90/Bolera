/**
 * 
 */
package v3;

/**
 * 
 */
public class Jugador {

	// atributos
	private String nombre;
	private int[] puntuacion;
	private String[] resultados;

	// constructor nombre por parámetro
	public Jugador(String nombre) {
		this.nombre = nombre;
		puntuacion = new int[PartidaBolos.NUMERO_DE_RONDAS];
		resultados = new String[PartidaBolos.NUMERO_DE_RONDAS];

	}

	// getters y setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int[] getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int[] puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String[] getResultados() {
		return resultados;
	}

	public void setResultados(String[] resultados) {
		this.resultados = resultados;
	}

	/**
	 * Este método rellena el array de puntuación con un número rándom del 0 al 10 y, en base a este
	 * resultado, rellena el array de resultados con "-", "/ " o "X ". Incluye una segunda tirada cuyo
	 * intervalo de probabilidad se ve afectado por la primera tirada.
	 * Necesita un for para ejecutarse en todo el rango de array.
	 * @param turno el turno que se está jugando.
	 */
	public void jugarTurno(int turno) {
		// bolos tirada 1
		puntuacion[turno] = (int) (Math.random() * 11);
		System.out.println("Tirada 1 de " + nombre + ": " + puntuacion[turno]);

		if (puntuacion[turno] == 10) {
			System.out.print("¡PLENO! ");
			resultados[turno] = "X ";//ojo con el espacio, importante para método calcularPuntuacion
		}
		
		if (puntuacion[turno] < 10) {
			// bolos tirada 2
			int bolos2 = (int) (Math.random() * (11 - puntuacion[turno]));
			System.out.println("Tirada 2 de " + nombre + ": " + bolos2);
			puntuacion[turno] = puntuacion[turno] + bolos2;
			if (puntuacion[turno] < 10) {
				resultados[turno] = "-";
			} else {
				System.out.print("¡SEMIPLENO! ");
				resultados[turno] = "/ ";//ojo con el espacio, importante para método calcularPuntuacion

			}

		}

		System.out.println(nombre + " ha tirado " + puntuacion[turno] + " bolos." + "\n");
	}

	/**Rellena una posición del array de puntuación y de resultados.
	 * Incluye la posibilidad de una segunda y tercera tirada supeditada a que la primera sea igual a 10. El resultado de la segunda y
	 * tercera tirada afectan al array de resultados.
	 * Si la primera tirada es distinto de 10, se ejecutan solo dos tiradas con los mismos condicionantes y resultados del método jugarTurno.
	 * 
	 */
	public void jugarTurnoFinal() {
		System.out.println("---TIRADA FINAL DE " + nombre + "---");
		int primeraTirada = 0;
		int segundaTirada = 0;
		int terceraTirada = 0;
		primeraTirada = (int) (Math.random() * 11);
		puntuacion[PartidaBolos.NUMERO_DE_RONDAS - 1] = primeraTirada;
		if (primeraTirada == 10) {
			System.out.println("Tirada 1 de " + nombre + ":" + primeraTirada);
			segundaTirada = (int) (Math.random() * 11);
			if (segundaTirada == 10) {
				System.out.println("Tirada 2 de " + nombre + ":" + segundaTirada);
				terceraTirada = (int) (Math.random() * 11);
				if (terceraTirada == 10) {
					System.out.println("Tirada 3 de " + nombre + ":" + terceraTirada);
					System.out.println(nombre + " ha tirado 30 bolos." + "\n");
					resultados[PartidaBolos.NUMERO_DE_RONDAS - 1] = "XXX";

				} else {
					System.out.println("Tirada 3 de " + nombre + ":" + terceraTirada);
					System.out.println(nombre + " ha tirado " + (20 + terceraTirada) + " bolos." + "\n");
					resultados[PartidaBolos.NUMERO_DE_RONDAS - 1] = "XX";

				}
			} else {
				System.out.println("Tirada 2 de " + nombre + ":" + segundaTirada);
				terceraTirada = (int) (Math.random() * (11 - segundaTirada));
				if (segundaTirada + terceraTirada == 10) {
					System.out.println("Tirada 3 de " + nombre + ":" + terceraTirada);
					System.out.println(nombre + " ha tirado " + (10 + segundaTirada + terceraTirada) + " bolos." + "\n");
					resultados[PartidaBolos.NUMERO_DE_RONDAS - 1] = "X/";
				} else {
					System.out.println("Tirada 3 de " + nombre + ":" + terceraTirada);
					System.out.println(nombre + " ha tirado " + (10 + segundaTirada + terceraTirada) + " bolos." + "\n");
					resultados[PartidaBolos.NUMERO_DE_RONDAS - 1] = "X ";//cuidado con el espacio para el switch del método calcularPuntuacion.
				}

			}
			puntuacion[PartidaBolos.NUMERO_DE_RONDAS - 1] = (primeraTirada + segundaTirada + terceraTirada);
		} else {
			System.out.println("Tirada 1 de " + nombre + ": " + primeraTirada);
			segundaTirada = (int) (Math.random() * (11 - primeraTirada));
			System.out.println("Tirada 2 de " + nombre + ": " + segundaTirada);
			if (primeraTirada + segundaTirada == 10) {
				System.out.println(nombre + " ha tirado " + (primeraTirada + segundaTirada) + " bolos." + "\n");
				resultados[PartidaBolos.NUMERO_DE_RONDAS - 1] = "/ ";//cuidado con el espacio para el switch del método calcularPuntuacion.
			} else {
				System.out.println(nombre + " ha tirado " + (primeraTirada + segundaTirada) + " bolos." + "\n");
				resultados[PartidaBolos.NUMERO_DE_RONDAS - 1] = "-";
			}
			puntuacion[PartidaBolos.NUMERO_DE_RONDAS - 1] = (primeraTirada + segundaTirada);
		}
	}
	/**
	 * Recorre los dos arrays e imprime cada posición con un espacio entre ellos.
	 */
	public void mostrarPuntuacion() {
		for (int i = 0; i < puntuacion.length; i++) {
			System.out.print(puntuacion[i] + " ");
		}
		System.out.println();

		for (int i = 0; i < resultados.length; i++) {
			System.out.print(resultados[i] + " ");
		}
		System.out.println();
	}

	/**
	 * Recorre el array de puntuación y suma todas las posiciones.
	 * Después recorre el array de resultados y, mediante un switch, compara las posiciones y añade puntos extra si se da el caso.
	 * @return puntosFinales: La suma final.
	 */
	public int calcularPuntuacion() {
		int puntosFinales = 0;
		for (int i = 0; i < resultados.length; i++) {
			puntosFinales = puntosFinales + puntuacion[i];
			switch (resultados[i]) {
			case "XXX":
				puntosFinales = puntosFinales + 30;
				break;
			case "XX":
				puntosFinales = puntosFinales + 20;
				break;
			case "X/":
				puntosFinales = puntosFinales + 15;
				break;
			case "X ":
				puntosFinales = puntosFinales + 10;
				break;
			case "/ ":
				puntosFinales = puntosFinales + 5;
			}
		}
		return puntosFinales;

	}

}
