package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import data.Page;

public class Parser {
	
	
	private File file;
	private List<Proces> allProcesses;
	
	public Parser() {
		file = new File("processes.txt");
		allProcesses = new ArrayList<Proces>();
	}
	
	public Parser(String fileName) {
		file = new File(fileName);
		allProcesses = new ArrayList<Proces>();
	}

	
	public List<Proces> getAllProcesses() {
		return allProcesses;
	}

	public void setAllProcesses(List<Proces> allProcesses) {
		this.allProcesses = allProcesses;
	}

	public void  read() {
		
		try{
			
            Scanner sc = new Scanner(file);

            String process_name = " ";
            
            String line = sc.nextLine();


            while(sc.hasNext()) {

                List<Page> process_numbers = new ArrayList<Page>();
                
                if (line.matches(".*process.*")) {
                	
                    process_name = line;
                    line = sc.next();
                }

                while (!line.matches(".*process.*") && sc.hasNext()) {
                	
                    process_numbers.add(new Page(Integer.parseInt(line)));
                    line = sc.next();
                    
                    if(!sc.hasNext()) {
                        process_numbers.add(new Page(Integer.parseInt(line)));
                    }
                    
                }
                
                allProcesses.add(new Proces(process_name, process_numbers));
            }
            sc.close();

            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

		
	}
    
}








