package main;

import data.Parser;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import data.Proces;
import lru.LRU;

public class Main {

	public static void main(String[] args) {
		
		Parser parser = new Parser("processes.txt");
		parser.read();

		int processNumber = 1;
		
//		for(Proces process : parser.getAllProcesses()) {
//			
//			System.out.println("This is the process number" + processNumber);
//			
//			for(int i = 0; i < process.getPages().size(); i++) {
//				
//				System.out.println("page: " + process.getPages().get(i).getPageNumber());
//			}
//			
//			processNumber++;
//		}

		int globalFaults = 0;
		
		LRU lru = new LRU();
		
		for(Proces proc : parser.getAllProcesses()) {
			globalFaults += lru.doLRU(proc);

		}
		
		System.out.println("global faults: " + globalFaults);
		
		
		
		int procNum = 1;
		
		for(Proces proc : parser.getAllProcesses()) {

			int size = 0;
		
			ArrayList<Integer> variousNumbers = new ArrayList<Integer>();
			
			for(int i = 0; i < proc.getPages().size(); i++) {
				
				if(!variousNumbers.contains(proc.getPages().get(i).getPageNumber())) {
					variousNumbers.add(proc.getPages().get(i).getPageNumber());
					size++;
				}
			}
			
			System.out.println("Process no " + procNum + " has size: " + size);
			procNum++;
		}
	}

}
