package data;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import data.Page;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner; 


public class Parser {
	
	private 	Scanner scanner;

	
	public Parser() {
		
		File file = new File("processes.txt");
		try {
			scanner = new Scanner(file);
		}catch(FileNotFoundException ex) {
			System.out.println("File not found");
		}
	}
	
	public Parser(String fileName) {
		
		File file = new File(fileName);
		try {
			scanner = new Scanner(file);
		}catch(FileNotFoundException ex) {
			System.out.println("File not found");
		}
	}
	
	
	public void read(){
		
		Process [] processes = new Process [11];
		int processNumber = 0;
		
		while(scanner.hasNext()) {
			
				scanner.skip("process");
				scanner.nextInt();
				processes[processNumber] = new Process(); 
				processes[processNumber].getPagesSequence().add(new Page(scanner.nextInt()));
				System.out.println(processes[processNumber].getPagesSequence().get(0).getPageNumber());
				
				
				processNumber++;
				break;
		}
		
	}

	 
}












