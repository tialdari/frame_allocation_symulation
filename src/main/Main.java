package main;

import data.Parser;
import methods.AllocationMethods;

import data.Proces;

public class Main {

	public static void main(String[] args) {
		
		Parser parser = new Parser("processes.txt");
		parser.read();
		int globalFaults = 0;
		
		AllocationMethods am = new AllocationMethods(parser.getAllProcesses());

		
		for(Proces proc : parser.getAllProcesses()) {
			
			globalFaults += am.doEqually(proc);
			
		}
		System.out.println("Global faults for equal method: " + globalFaults);
		
		

		globalFaults = 0;

		for(Proces proc : parser.getAllProcesses()) {
			
			globalFaults += am.doProportionally(proc);
			
		}
		System.out.println("Global faults for proportional method: " + globalFaults);

		globalFaults = 0;
		
		for(Proces proc : parser.getAllProcesses()) {
			
			globalFaults += am.doLocalAllocation(proc);
			
		}
		System.out.println("Global faults for local allocation method: " + globalFaults);
		
		globalFaults = 0;
		int allFrames = 0;
	
		for(Proces proc : parser.getAllProcesses()) {
			allFrames += am.doZoneModel(proc);			
		}
		
		double percentage;
		int framesNum;
		int procNum = 1;
		
		for(Proces proc : parser.getAllProcesses()) {
			
			percentage = (double) proc.getFramesAmount() / allFrames;
			framesNum = (int)(percentage * 100);
			proc.setFramesAmount(framesNum);
			//System.out.println("frames number: " + proc.getFramesAmount());
			procNum++;
		}
		
		for(Proces proc : parser.getAllProcesses()) {
			globalFaults += am.doZoneModelAllocation(proc);			
		}
		
		System.out.println("Global faults for zone model method: " + globalFaults + "all Frames: " + allFrames);
	}

}
