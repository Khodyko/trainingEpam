package by.mycloud_zapchast.www.service;

public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message="";
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		this.message=message;
		System.out.println("Service: "+message);
	}

	public ServiceException(Exception e) {
		
	}

	public ServiceException(String message, Exception e) {
		
		this.message=message;
		
		System.out.println("Service: "+message);
	}

}
