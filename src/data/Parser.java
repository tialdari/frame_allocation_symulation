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
		int nextNumber;
		

		
		while(scanner.hasNext()) {
			
			 if(scanner.nextLine().startsWith("process")) {
				processNumber++;
				System.out.println("This is a process no: " + processNumber);
				processes[processNumber] = new Process(); 		

			}
				nextNumber = scanner.nextInt();
				processes[processNumber].getPagesSequence().add(new Page(nextNumber));
				System.out.println("This is a page call of a process no " + processNumber + " : " + nextNumber);

		 }
		
	}
	/*
	 * while(scanner.hasNext()) {
   String lineOfText = scanner.nextLine();
   if (lineOfText.startsWith("//") || lineOfText.startsWith(" ")) {
      continue; //Exit this iteration if line starts with space or /
   }
   System.out.println(lineOfText);
}
	 */
	 
}












