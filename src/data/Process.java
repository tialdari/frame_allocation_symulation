package data;

import java.util.List;

public class Process {
    private String process_name;
    private List<String> process_numbers;


    public Process(String process_name, List<String> process_numbers){
        this.process_name = process_name;
        this.process_numbers = process_numbers;
    }

    public String getProcess_name() {
        return process_name;
    }

    public void setProcess_name(String process_name) {
        this.process_name = process_name;
    }

	public List<String> getProcess_numbers() {
		return process_numbers;
	}

	public void setProcess_numbers(List<String> process_numbers) {
		this.process_numbers = process_numbers;
	}

}
