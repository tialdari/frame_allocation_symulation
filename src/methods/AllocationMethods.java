package methods;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import data.Proces;
import java.util.List;
import java.util.ArrayList;
import data.Proces;

public class AllocationMethods {
		
	private List<Proces> processes;
	
	public AllocationMethods() {
		processes = new ArrayList<Proces>();
	}
	
	public AllocationMethods(List<Proces> processes) {
		this.processes = processes;
	}
	
	
	public List<Proces> getProcesses() {
		return processes;
	}

	public void setProcesses(List<Proces> processes) {
		this.processes = processes;
	}

	public int pageFaults(Proces proces) {
		
		  int capacity = proces.getFramesAmount();
			 
	      HashSet<Integer> s = new HashSet<>(capacity);
	      HashMap<Integer, Integer> indexes = new HashMap<>();
	      
	     
	      int page_faults = 0;
	    
	      for (int i = 0; i < proces.getPages().size(); i++){
	        		        		
	            // Check if the set can hold more pages
	            if (s.size() < capacity){
	            	
	                // Insert it into set if not present
	                // already which represents page fault
	                if (!s.contains(proces.getPages().get(i).getPageNumber())){
	                	
	                    s.add(proces.getPages().get(i).getPageNumber());
	     
	                    // increment page fault
	                    page_faults++;
	                }
	     
	                // Store the recently used index of
	                // each page
	                indexes.put(proces.getPages().get(i).getPageNumber(), i);
	            }
	     
	            // If the set is full then need to perform lru
	            // i.e. remove the least recently used page
	            // and insert the current page
	            else{
	            	
	                // Check if current page is not already
	                // present in the set
	                if (!s.contains(proces.getPages().get(i).getPageNumber())){
	                	
	                    // Find the least recently used pages
	                    // that is present in the set
	                    int lru = Integer.MAX_VALUE, val=Integer.MIN_VALUE;
	                    
	                    Iterator<Integer> itr = s.iterator();
	                    
	                    while (itr.hasNext()) {
	                        int temp = itr.next();
	                        if (indexes.get(temp) < lru){
	                        	
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
	                }
	     
	                // Update the current page index
	                indexes.put(proces.getPages().get(i).getPageNumber(), i);
	            }
	        }
	        return page_faults;
	}
	
	
	public int doEqually(Proces proces){
		
		  framesEqually();
		  return pageFaults(proces);
		 
	}	
	
	public int doProportionally(Proces proces){
		
		framesProportionally();
		return pageFaults(proces);

	}
	
	 
	 public int doLocalAllocation(Proces proces){
		 
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
	        			
		        			if(localPageFault > 4) {
		        				
		        				proces.setFramesAmount(proces.getFramesAmount() + 1);
		        				capacity = proces.getFramesAmount();
		        				localPageFault = 0;
			        			windowSize = 0;
			        			
		        			}else if(localPageFault < 2) {
		        				
		        				
		        				if(capacity == 1) {
		        					localPageFault = 0;
				        			windowSize = 0;
		        					
		        				}else {
					        		proces.setFramesAmount(proces.getFramesAmount() - 1);
		        					capacity = proces.getFramesAmount();
			        				localPageFault = 0;
				        			windowSize = 0;
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
	 
	
	public void framesEqually(){
		
		for(Proces proc : processes) {
			proc.setFramesAmount(8);
		}
	}
	
	public void framesProportionally(){
		
		double percentage;
		int framesNum;
		int procNum = 1;
		
		for(Proces proc : processes) {
			
			percentage = (double) proc.getProcSize() / 326.0;
			framesNum = (int)(percentage * 100);
			proc.setFramesAmount(framesNum);
			//System.out.println("frames number: " + proc.getFramesAmount());
			procNum++;
		}
		
	}
	
	public int doZoneModel(Proces proces) {
		
			  System.out.println("process new");
			  int capacity = proces.getFramesAmount();
				 
		      HashSet<Integer> s = new HashSet<>(capacity);
		      HashMap<Integer, Integer> indexes = new HashMap<>();
		      
		      HashSet<Integer> workSet = new HashSet<>();
		      int workWindow = 40;
		      int workWindowNum = 1;
		      int workAreaSum = 0;
		      int workArea;

		      
		      int page_faults = 0;
		    
		      for (int i = 0; i < proces.getPages().size(); i++){
		    	  
                  workSet.add(proces.getPages().get(i).getPageNumber());
  	  			 // System.out.println("New page");


		    	  		if(workWindow == 40 || i == proces.getPages().size() - 1) {
		    	  			//System.out.println("New window");
		    	  			workWindowNum++;
		    	  			workAreaSum += workSet.size();
		    	  			
		    	  			//System.out.println("work area: " + workAreaSum + " work window number: " + workWindowNum);

		    	  			workSet = new HashSet<>();	
		    	  			workWindow = 0;
		    	  		}
		    	
		        		        		
		            // Check if the set can hold more pages
		            if (s.size() < capacity){
		            	
		                // Insert it into set if not present
		                // already which represents page fault
		                if (!s.contains(proces.getPages().get(i).getPageNumber())){
		                	
		                    s.add(proces.getPages().get(i).getPageNumber());

		     
		                    // increment page fault
		                    page_faults++;
		                }
		     
		                // Store the recently used index of
		                // each page
		                indexes.put(proces.getPages().get(i).getPageNumber(), i);
		            }
		     
		            // If the set is full then need to perform lru
		            // i.e. remove the least recently used page
		            // and insert the current page
		            else{
		            	
		                // Check if current page is not already
		                // present in the set
		                if (!s.contains(proces.getPages().get(i).getPageNumber())){
		                	
		                    // Find the least recently used pages
		                    // that is present in the set
		                    int lru = Integer.MAX_VALUE, val=Integer.MIN_VALUE;
		                    
		                    Iterator<Integer> itr = s.iterator();
		                    
		                    while (itr.hasNext()) {
		                        int temp = itr.next();
		                        if (indexes.get(temp) < lru){
		                        	
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
		                }
		     
		                // Update the current page index
		                indexes.put(proces.getPages().get(i).getPageNumber(), i);
		            }
		            
		            workWindow++;
		        }
		      	
		       
		      	workArea = workAreaSum / workWindowNum;
		      	System.out.println("Work area: " + workArea);
		      	proces.setFramesAmount(workArea);
		      	
		      	return workArea;
		
	}
	
	public int doZoneModelAllocation(Proces proc) {
				
		return pageFaults(proc);
	}
	
	
}
