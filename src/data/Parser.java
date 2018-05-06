package data;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import data.Page;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
 


public class Parser {
	
	private 	BufferedReader reader;

	
	public Parser() {
		
		try {
			reader = new BufferedReader(new FileReader(new File("processes.txt")));
		}catch(FileNotFoundException ex) {
			System.out.println("File not found");
		}
	}
	
	public Parser(String fileName) {
				
		try {
			reader = new BufferedReader(new FileReader(new File(fileName)));
		}catch(FileNotFoundException ex) {
			System.out.println("File not found");
		}
	}
	
	
	public ArrayList<Integer> read(){
		
		ArrayList<Integer> fileData = new ArrayList<Integer>();
		
		try {
			String text;
		    while ((text = reader.readLine()) != null) {
		    		
		    		if(text == "process") {
		    		}
		        fileData.add(Integer.parseInt(text));
		    }
		} catch (FileNotFoundException ex) {
		    ex.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        if (reader != null) {
		            reader.close();
		        }
		    } catch (IOException e) {
		    }
		}

	return fileData;
		
	}

	
	 
}
