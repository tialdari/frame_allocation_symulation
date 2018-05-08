package lru;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;
import data.Proces;
 
class LRU2{
    // Method to find page faults using indexes
	
    public int pageFaults(Proces proces, ArrayList<Integer> pages){
    	
    	
    		int capacity = proces.getFramesAmount();
    		int windowSize = 0;
    		int localPageFault = 0;
        // To represent set of current pages. We use
        // an unordered_set so that we quickly check
        // if a page is present in set or not
        HashSet<Integer> s = new HashSet<Integer>(capacity);
      
        // To store least recently used indexes
        // of pages.
        HashMap<Integer, Integer> indexes = new HashMap<>();
      
        // Start from initial page
        int page_faults = 0;
        for (int i=0; i<proces.getPages().size(); i++)
        {
	        	if(windowSize == 7) {
	    			if(localPageFault > 5) {
	    				System.out.println("the process gained one frame");
	    				proces.setFramesAmount(proces.getFramesAmount() + 1);
	   		     	 capacity = proces.getFramesAmount();
	
			    		localPageFault = 0;
			    		windowSize = 0;
	    			}else if(localPageFault < 2){
	    				System.out.println("the process lost one frame");

	    				proces.setFramesAmount(proces.getFramesAmount() - 1);		
	   		     	 capacity = proces.getFramesAmount();
	
			    		localPageFault = 0;
			    		windowSize = 0;
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
                if (!s.contains(pages.get(i)))
                {
                    s.add(pages.get(i));
      
                    // increment page fault
                    page_faults++;
                    localPageFault++;
                }
      
                // Store the recently used index of
                // each page
                indexes.put(pages.get(i), i);
            }
      
            // If the set is full then need to perform lru
            // i.e. remove the least recently used page
            // and insert the current page
            else
            {
                // Check if current page is not already
                // present in the set
                if (!s.contains(pages.get(i)))
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
                    s.add(pages.get(i));
      
                    // Increment page faults
                    page_faults++;
                    localPageFault++;
                }
      
                // Update the current page index
                indexes.put(pages.get(i), i);
            }
            windowSize++;
        }
      
        return page_faults;
    }
    }