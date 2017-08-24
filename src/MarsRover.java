import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MarsRover {

	private RoverManager manager;
	private Scanner in;

	public MarsRover() {
		in = new Scanner (System.in);
		menu();
	}

	public void menu() {
		String option;
		do {
			System.out.println("--MARZ ROVER--\n"+
						 "1 - Ler dados de um arquivo .txt" +
						 "\n2 - Inserir dados manualmente."+
						 "\n3 - Ver atual situa��o de todos os rovers."+
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

	public void read() {
		// FIXME: instanciando um novo manager sempre que vai adicionar um rover
		
		String planSize, roverSituation, roverCommands;
		do { 
			System.out.println("Informe o tamanho do plano separando largura e altura por espa�o (x y):");
			planSize = in.nextLine();
		} while(!loadManager(planSize));
		
		do { 
			System.out.println("Informe a posi��o inicial do rover e sua dire��o (x y dire��o)");
			roverSituation = in.nextLine();
			System.out.println("Informe as instru��es para que o rover explore o plano(Apenas caracteres L, R, M, sem espa�os.):");
			roverCommands = in.nextLine();
		} while(!loadRover(roverSituation, roverCommands));
	}

	public void readFile() {
		String fileName;
		System.out.println("Informe o nome do arquivo:");
		fileName = in.next();
		Path path = Paths.get(fileName);
		try(Scanner reader = new Scanner(Files.newBufferedReader(path, Charset.forName("utf-8")))) {
			if(loadManager(reader.nextLine().trim()) == false) {
				System.out.println("O tamanho do plano est� com formato incorreto.");
				return;
			}
			String roverSituation, roverCommands;
			while(reader.hasNextLine()) {
				roverSituation = reader.nextLine().trim();
				roverCommands = reader.nextLine().trim();
				if(loadRover(roverSituation, roverCommands) == false) {
					System.out.println("Arquivo cont�m dados inv�lidos referente aos rovers.");
					break;
				}
			}
		}
		catch(IOException e) {
			System.out.println("Arquivo n�o encontrado.");
		}
		printResults();
	}

	public boolean loadRover(String roverSituation, String roverCommands) {
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
			return false;
		}
		if(!manager.validatePosition(x, y)) {
			System.out.println("Posi��o inicial do rover ja est� ocupada ou valores fora das dimens�es do plano.");
			return false;
		}
		manager.addRover(x, y, direction, roverCommands);
		return true;
	}

	public boolean loadManager(String upperRightCoordinates) {
		int x, y;
		String splitedCoordinates[] = upperRightCoordinates.split(" ");
		if(splitedCoordinates.length != 2){
			return false;
		}
		try {
			x = Integer.parseInt(splitedCoordinates[0]);
			y = Integer.parseInt(splitedCoordinates[1]);
		}
		catch(Exception e) {
			return false;
		}
		this.manager = new RoverManager(x, y);
		return true;
	}

	public void printResults() {
		System.out.println("Posi��o de todos os rovers:");
		manager.printAllRovers();
	}
}
