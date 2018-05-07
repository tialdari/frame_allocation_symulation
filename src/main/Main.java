package main;

import data.Parser;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import data.Proces;

public class Main {

	public static void main(String[] args) {
		
		Parser parser = new Parser("processes.txt");
		parser.read();

		int processNumber = 1;
		
		for(Proces process : parser.getAllProcesses()) {
			
			System.out.println("This is the process number" + processNumber);
			
			for(int i = 0; i < process.getPages().size(); i++) {
				
				System.out.println("page: " + process.getPages().get(i).getPageNumber());
			}
			
			processNumber++;
		}

	}

}
