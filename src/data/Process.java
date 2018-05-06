package data;

import java.util.ArrayList;
import data.Page;

public class Process {	
	
	private ArrayList<Page> pagesSequence;
	private boolean ifExecuted;
	
	public Process() {
		pagesSequence = new ArrayList<Page>();
		ifExecuted = false;
	}
	
	public Process(ArrayList<Page> pagesSequence) {
		this.pagesSequence = pagesSequence;
		ifExecuted = false;
	}


	public ArrayList<Page> getPagesSequence() {
		return pagesSequence;
	}

	public void setPagesSequence(ArrayList<Page> pagesSequence) {
		this.pagesSequence = pagesSequence;
	}

	public boolean isIfExecuted() {
		return ifExecuted;
	}

	public void setIfExecuted(boolean ifExecuted) {
		this.ifExecuted = ifExecuted;
	}
	
	

}
