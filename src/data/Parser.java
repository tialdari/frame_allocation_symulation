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
	}
	
	public Parser(String fileName) {
				
		try {
			reader = new BufferedReader(new FileReader(new File(fileName)));

		}catch(FileNotFoundException ex) {
			
		}
		
	}
	
	
	public ArrayList<Integer> read(){
		
		ArrayList<Integer> fileData = new ArrayList<Integer>();
		
		try {
			String text;
		    while ((text = reader.readLine()) != null) {
		        fileData.add(Integer.parseInt(text));
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
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
