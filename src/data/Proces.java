package data;

import java.util.List;

public class Proces {
    private String process_name;
    private List<Page> process_numbers;


    public Proces(String process_name, List<Page> process_numbers){
        this.process_name = process_name;
        this.process_numbers = process_numbers;
    }

    public String getProcess_name() {
        return process_name;
    }

    public void setProcess_name(String process_name) {
        this.process_name = process_name;
    }

	public List<Page> getProcess_numbers() {
		return process_numbers;
	}

	public void setProcess_numbers(List<Page> process_numbers) {
		this.process_numbers = process_numbers;
	}

}
