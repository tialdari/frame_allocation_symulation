package main;

import data.Parser;

public class Main {

	public static void main(String[] args) {
		
		Parser parser = new Parser("processes.txt");
		parser.read();
		
	}

}
