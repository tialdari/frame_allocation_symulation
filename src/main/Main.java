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
		int procNum = 1;
		int summedSize = 0;
		double percentage;
		int framesNum;
		int framesNumGlobal = 0;
		
		
		LRU lru = new LRU();
		
		for(Proces proc : parser.getAllProcesses()) {
			
			summedSize += proc.getProcSize();
			percentage = (double) proc.getProcSize() / 326.0;
			framesNum = (int)(percentage * 100);
			framesNumGlobal +=framesNum;
			proc.setFramesAmount(framesNum);
			System.out.println("process no " + procNum + " size is: " + proc.getProcSize() + 
					" and the percentage: " + percentage + "and the frames number: " + framesNum);

			procNum++;
		}
		
		for(Proces proc : parser.getAllProcesses()) {
			
			globalFaults += lru.doLRU(proc);
			
		}
		
		
		System.out.println("Global faults: " + globalFaults);
		
	}

}
