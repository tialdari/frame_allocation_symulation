package lru;

import data.Proces;
import data.Page;

public class LRU {
	
		public static int min(int counter[],int nFrames){
		     
			 int minimum = counter[0];
		     int pos = 0;
		     
		     for(int i=0;i<nFrames;i++){
		    	 	if(minimum > counter[i])
		           pos = i;
		     }
		  return pos;   
		}
	      
	     
	    public  int doLRU(Proces proces) {
	       
		    int n, recent = 0, pageFault = 0, nFrames;
		        
		  //  System.out.print("Enter the number of pages: ");
		     n = proces.getPages().size();
		      
		    int pageString[] = new int[n];
		    
		  //  System.out.print("Enter the page reference string: ");
		    
		    for(int i = 0; i < n; i++) {
		        pageString[i] = proces.getPages().get(i).getPageNumber();
		    }
		    
		  //   System.out.print("\nEnter the number of frames: "); 
		         
		     	 nFrames = proces.getFramesAmount();
		     	 
			    // System.out.print("\nNumber of frames is: " + nFrames); 

		         int frames[] = new int[nFrames];
		         int counter[] = new int[nFrames];
		    
		     for(int i = 0; i < nFrames; i++){
		    	 	
		    	    frames[i] = 0; 
		        counter[i] = 0;//here 0 referes an empty space in frame
		    }
		     
		    for(int i = 0; i < n; i++){
		    		
		    		int flag = 0;
		        
		    		for(int j = 0; j < nFrames; j++){
		    			
		    			if(frames[j] == pageString[i]){
		        	 	
		    				flag = 1;
		    				counter[j] = recent++; //counter holds which frame is recently used,
		                                  //recently used page in frame will have a bigger number
		                                  //and least recently used page in frame will have a lower number
		    				break;
		           }
		        }
		         
		        if(flag == 0){
		        	
		            for(int j = 0 ;j < nFrames; j++){
		            		
		            	if(frames[j] == 0){
		            		
		            		frames[j] = pageString[i];
		                 counter[j] = recent++;
		                 flag = 1;
		                 pageFault++;
		                 break;
		                } 
		            }
		        }
		         
		        if(flag == 0){
		            int PositionToreplace = min(counter,nFrames);
		            frames[PositionToreplace] = pageString[i];
		            counter[PositionToreplace] = recent++;
		            pageFault++;
		        }
		         
		        //print frames
		     //  System.out.println();
		      for(int j=0;j<nFrames;j++)
		      {
		          // System.out.print(frames[j]+" ");
		      }
		       
		    }
		      
		      //System.out.print("\nPage Fault: "+pageFault);
			     return pageFault;

		    }
		

}