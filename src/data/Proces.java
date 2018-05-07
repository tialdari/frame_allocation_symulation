package data;

import java.util.List;

public class Proces {
	
    private String process_name;
    private List<Page> pages;
    private int framesAmount;


    public Proces(String process_name, List<Page> pages){
        this.process_name = process_name;
        this.pages = pages;
        framesAmount = 8;
    }

    public String getProcess_name() {
        return process_name;
    }

    public void setProcess_name(String process_name) {
        this.process_name = process_name;
    }

	public List<Page> getPages() {
		return pages;
	}

	public void setProcess_numbers(List<Page> pages) {
		this.pages = pages;
	}

	public int getFramesAmount() {
		return framesAmount;
	}

	public void setFramesAmount(int framesAmount) {
		this.framesAmount = framesAmount;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}
	
	

}
