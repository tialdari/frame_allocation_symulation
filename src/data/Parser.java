package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
	
	
	private File file;
	private List<Process> allProcesses;
	
	public Parser() {
		file = new File("processes.txt");
		allProcesses = new ArrayList<Process>();
	}
	
	public Parser(String fileName) {
		file = new File(fileName);
		allProcesses = new ArrayList<Process>();
	}

	
	public List<Process> getAllProcesses() {
		return allProcesses;
	}

	public void setAllProcesses(List<Process> allProcesses) {
		this.allProcesses = allProcesses;
	}

	public void  read() {
		
		try {
			
            Scanner sc = new Scanner(file);

            String process_name = " ";
            
            String line = sc.nextLine();


            while(sc.hasNextLine()) {

                List<String> process_numbers = new ArrayList<String>();
                if (line.matches(".*process.*")) {
                    process_name = line;
                    line = sc.nextLine();
                }

                while (!line.matches(".*process.*") && sc.hasNextLine()) {
                    process_numbers.add(line);
                    line = sc.nextLine();
                }

                Process o = new Process(process_name, process_numbers);
                allProcesses.add(o);

            }
            
            sc.close();
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

	}
        
    
}








