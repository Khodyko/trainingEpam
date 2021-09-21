package by.hodyko.www.dao;

public class DAOException extends Exception {
	private static final long serialVersionUID = 1L;
	String message="";
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DAOException() {
		this.message="Something wrong, use our service after a time";
		System.out.println("It is DAOException");
	}

	public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.message=message;
		System.out.println("DaoException : "+message);
		
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
		System.out.println(message);
		
	}

	public DAOException(String message) {
		this.message=message;
		System.out.println("DaoException : "+message);
	}

	public DAOException(Throwable cause) {
		super(cause);
		this.message="Something wrong, use our service after a time";
		System.out.println("DAOException");
		
	}

}
