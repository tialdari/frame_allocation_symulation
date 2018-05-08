package main;

import data.Parser;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import data.Page;

import data.Proces;

public class Main {

	public static void main(String[] args) {
		
		Parser parser = new Parser("processes.txt");
		parser.read();

		int processNumber = 1;
		


		int globalFaults = 0;
		int procNum = 1;
		int summedSize = 0;
		double percentage;
		int framesNum;
		int framesNumGlobal = 0;
		
		
		
		for(Proces proc : parser.getAllProcesses()) {
			
			summedSize += proc.getProcSize();
			percentage = (double) proc.getProcSize() / 326.0;
			framesNum = (int)(percentage * 100);
			framesNumGlobal +=framesNum;
			proc.setFramesAmount(framesNum);
			//System.out.println("process no " + procNum + " size is: " + proc.getProcSize() + 
			//		" and the percentage: " + percentage + "and the frames number: " + framesNum);

			procNum++;
		}
		
		for(Proces proc : parser.getAllProcesses()) {
			
			System.out.println("A new process ");
			globalFaults += pageFaults2(proc);
			
		}
		
		
		System.out.println("Global faults: " + globalFaults);
		
	
	}

	 
	 
	 
	 static int pageFaults2(Proces proces){
	        // To represent set of current pages. We use
	        // an unordered_set so that we quickly check
	        // if a page is present in set or not
		 int localPageFault = 0;
		 int windowSize = 0;
		 
		 int capacity = proces.getFramesAmount();
		 
	      HashSet<Integer> s = new HashSet<>(capacity);
	     
	       // To store least recently used indexes
	        // of pages.
	      HashMap<Integer, Integer> indexes = new HashMap<>();
	     
	        // Start from initial page
	      int page_faults = 0;
	      for (int i=0; i<proces.getPages().size(); i++){
	        	
	        		if(windowSize == 7) {
	        			
		        			System.out.println("local page faults: " + localPageFault);
			        		System.out.println("current frames no: " + proces.getFramesAmount());
	
		        			
		        			if(localPageFault > 4) {
		        				
		        				proces.setFramesAmount(proces.getFramesAmount() + 1);
		        				capacity = proces.getFramesAmount();
		        				localPageFault = 0;
			        			windowSize = 0;
			        			System.out.println("frames no after change: " + proces.getFramesAmount());
			        			
		        			}else if(localPageFault < 2) {
		        				
		        				
		        				if(capacity == 1) {
		        					System.out.println("This is my only frame!");
		        					localPageFault = 0;
				        			windowSize = 0;
		        					
		        				}else {
					        		proces.setFramesAmount(proces.getFramesAmount() - 1);
		        					capacity = proces.getFramesAmount();
			        				localPageFault = 0;
				        			windowSize = 0;
					        		System.out.println("frames no after change: " + proces.getFramesAmount());
		        				}
		        			}else {
			        				localPageFault = 0;
				        			windowSize = 0;
					       }
	        			
	        			}
	        		
	            // Check if the set can hold more pages
	            if (s.size() < capacity)
	            {
	                // Insert it into set if not present
	                // already which represents page fault
	                if (!s.contains(proces.getPages().get(i).getPageNumber()))
	                {
	                    s.add(proces.getPages().get(i).getPageNumber());
	     
	                    // increment page fault
	                    page_faults++;
	                    localPageFault++;
	                }
	     
	                // Store the recently used index of
	                // each page
	                indexes.put(proces.getPages().get(i).getPageNumber(), i);
	            }
	     
	            // If the set is full then need to perform lru
	            // i.e. remove the least recently used page
	            // and insert the current page
	            else
	            {
	                // Check if current page is not already
	                // present in the set
	                if (!s.contains(proces.getPages().get(i).getPageNumber()))
	                {
	                    // Find the least recently used pages
	                    // that is present in the set
	                    int lru = Integer.MAX_VALUE, val=Integer.MIN_VALUE;
	                    
	                    Iterator<Integer> itr = s.iterator();
	                    
	                    while (itr.hasNext()) {
	                        int temp = itr.next();
	                        if (indexes.get(temp) < lru)
	                        {
	                            lru = indexes.get(temp);
	                            val = temp;
	                        }
	                    }
	                
	                    // Remove the indexes page
	                    s.remove(val);
	     
	                    // insert the current page
	                    s.add(proces.getPages().get(i).getPageNumber());
	     
	                    // Increment page faults
	                    page_faults++;
	                    localPageFault++;
	                }
	     
	                // Update the current page index
	                indexes.put(proces.getPages().get(i).getPageNumber(), i);
	            }
	            windowSize++;
	        }
	     
	        return page_faults;
	    }
	 
	
	 
}
