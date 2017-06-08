package pe.com.bbva.visitame.exception;

public class DAOException extends Exception {

	private static final long serialVersionUID = 2960937062117890862L;

	public DAOException() {
		super();
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}
}