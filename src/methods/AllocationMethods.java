package methods;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import data.Proces;

import data.Proces;

public class AllocationMethods {
		
	
	public int allocateEqually(Proces proces){
		
		 
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
}
