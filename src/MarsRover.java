import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MarsRover {

	public static void main(String[] args) {
		menu();
	}

	private static RoverManager manager;
	private static Scanner in = new Scanner(System.in);

	public static void menu() {
		String option;
		do {
			System.out.println("--MARZ ROVER--\n"+
						 "1 - Ler dados de um arquivo .txt" +
						 "\n2 - Inserir dados manualmente."+
						 "\n3 - Ver atual situaï¿½ï¿½o de todos os rovers."+
						 "\n0 - Sair.");
			option = in.next();

			switch (option) {
				case "1":
					readFile();
					break;
				case "2":
					read();
					break;
				case "3":
					printResults();
			}
		} while (!option.equals("0"));
	}


	public static void read() {
		String planSize, roverSituation, roverCommands;
		do {
			System.out.print("Informe o tamanho do plano separando largura e altura por espaço (x y):");
			if((planSize = in.nextLine().trim()).equals("0")) {System.exit(0);}
		} while (!loadManager(planSize));

		do {
			System.out.println("Informe a posição inicial do rover e sua direção (x y direção)");
			roverSituation = in.nextLine().trim();
			if(roverSituation.equals("0")) {System.exit(0);}

			System.out.println("Informe as instruções para que o rover explore o plano(Apenas caracteres L, R, M, sem espaços.):");
			roverCommands = in.nextLine().trim();
			if(roverCommands.equals("0")) {System.exit(0);}
		} while (!loadRover(roverSituation, roverCommands));
		printResults();
	}

	public static void readFile() {
		String fileName;
		System.out.println("Informe o nome do arquivo:");
		fileName = in.next();
		if(fileName.equals("0")) {System.exit(0);}
		Path path = Paths.get(fileName);
		try(Scanner reader = new Scanner(Files.newBufferedReader(path, Charset.forName("utf-8")))) {
			if(loadManager(reader.nextLine().trim()) == false) {
				System.out.println("O tamanho do plano estï¿½ com formato incorreto.");
				return;
			}
			String roverSituation, roverCommands;
			while(reader.hasNextLine()) {
				roverSituation = reader.nextLine().trim();
				roverCommands = reader.nextLine().trim();
				if(loadRover(roverSituation, roverCommands) == false) {
					System.out.println("Arquivo contï¿½m dados invï¿½lidos referente aos rovers.");
					break;
				}
			}
		}
		catch(IOException e) {
			System.out.println("Arquivo nï¿½o encontrado.");
			return;
		}
		printResults();
	}

	public static boolean loadRover(String roverSituation, String roverCommands) {
		char direction;
		int x, y;

		if(!roverSituation.matches("\\d+ \\d+ [N|S|E|W]") || !roverCommands.matches("[R|L|M]+")) {
			return false;
		}

		String roverSituationSplited [] = roverSituation.split(" ");
		try {
			x = Integer.parseInt(roverSituationSplited[0]);
			y = Integer.parseInt(roverSituationSplited[1]);
			direction = roverSituationSplited[2].charAt(0);
		}
		catch(Exception e) {
			System.out.println("Posição inicial ou direção do rover mal formatados.");
			return false;
		}
		if(!manager.validatePosition(x, y)) {
			System.out.println("Posiï¿½ï¿½o inicial do rover ja estï¿½ ocupada ou valores fora das dimensï¿½es do plano.");
			return false;
		}
		manager.addRover(x, y, direction, roverCommands);
		return true;
	}

	public static boolean loadManager(String upperRightCoordinates) {
		int x, y;
		String splitedCoordinates[] = upperRightCoordinates.split(" ");
		if(splitedCoordinates.length != 2) {
			return false;
		}
		try {
			x = Integer.parseInt(splitedCoordinates[0]);
			y = Integer.parseInt(splitedCoordinates[1]);
		}
		catch(Exception e) {
			return false;
		}
		manager = new RoverManager(x, y);
		return true;
	}

	public static void setManager(RoverManager n) {
		manager = n;
	}

	public static void printResults() {
		System.out.println("Posiï¿½ï¿½o de todos os rovers:");
		manager.printAllRovers();
	}
}
