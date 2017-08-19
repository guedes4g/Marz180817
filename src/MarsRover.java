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
		}
		catch(Exception e) {
			return false;
		}
//		Aqui � chamado algum m�todo da classe RoverManager,
//		passando a posi��o, dire��o e comandos de um rover, ex:
//		manager.addRover(x, y, direction, roverCommands);
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
//		Aqui � instanciado o manager com as coordenadas do canto superior direito como par�metro, ex:
//		manager = new RoverManager(x, y);
		return true;
	}

	public void printResults() {
//		Esse m�todo ir� printar a situa��o de cada rover conforme output no enunciado do exerc�cio.
//		Tais informa��es ser�o buscadas na classe RoverManager.
	};
}
