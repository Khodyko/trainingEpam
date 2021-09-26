package by.mycloud_zapchast.www.dao.connectionpool;

public class ConnectionPoolException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message="";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ConnectionPoolException() {
		super();
	}

	public ConnectionPoolException(String message) {
		this.message=message;
		System.out.println("Service: "+message);
	}

	public ConnectionPoolException(Exception e) {
		
	}

	public ConnectionPoolException(String message, Exception e) {
		
		this.message=message;
		
		System.out.println("Service: "+message);
	}

}
