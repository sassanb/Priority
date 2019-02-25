import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class IO {
	private ArrayList<Integer> keys;
	private ArrayList<String> commands;
	private Scanner scanner;

	public IO(){
		readKeys();
		readCommands();
	}

	public void readKeys(){
		try{
			scanner = new Scanner(new File("input.txt"));
		}catch(FileNotFoundException e){
			System.out.println("File input.txt not found");
		}finally{
			if(scanner != null){
				String[] line = scanner.nextLine().split(" ");
				keys = new ArrayList<Integer>();
				for(int i = 0;i<line.length;i++){
					try{
						keys.add(Integer.parseInt(line[i]));
					}catch(NumberFormatException e){
						System.out.println(line[i] + " is not a number!");
					}
				}
				scanner = null;
			}else System.exit(1);
		}
	}

	public void readCommands(){
		try{
			scanner = new Scanner(new File("script.txt"));
		}catch(FileNotFoundException e){
			System.out.println("File script.txt not found");
		}finally{
			if(scanner != null){
				commands = new ArrayList<String>();
				while(scanner.hasNextLine()){
					String line = scanner.nextLine();
					if(!line.isEmpty()) commands.add(line);
				}
			}else System.exit(1);
		}
	}

	public ArrayList<Integer> getKeys(){return keys;}
	public ArrayList<String> getCommands(){return commands;}

	public static String[] trimArrayContents(String[] args){
		String[] contents = new String[args.length];
		for(int i = 0;i<args.length;i++) contents[i] = args[i].trim();
		return contents;
	}
}
