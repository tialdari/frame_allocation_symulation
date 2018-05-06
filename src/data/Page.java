package data;

public class Page {
	
	private int pageNumber;
	private int callsNumber;
		
	
	public Page() {
		pageNumber = 0;
		callsNumber = 0;
	}
	
	public Page(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getCallsNumber() {
		return callsNumber;
	}

	public void setCallsNumber(int callsNumber) {
		this.callsNumber = callsNumber;
	}
	
	
}
