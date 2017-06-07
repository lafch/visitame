package pe.com.bbva.visitame.exception;

public class ValidacionException extends Exception {
	
	private static final long serialVersionUID = -403767194220536370L;
	
	public ValidacionException(int statusCode, String statusDescription, String errorMessage) {
		super(errorMessage);
		this.statusCode = statusCode;
        this.statusDescription = statusDescription;
        this.errorMessage = errorMessage;
    }
	
	public ValidacionException(){
		
	}
	
	private int statusCode;
	public int getStatusCode() { return statusCode; }
    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }
    
    private String statusDescription;
    public String getStatusDescription() { return statusDescription; }
    public void setStatusDescription(String statusDescription) { this.statusDescription = statusDescription; }

    private String errorMessage;
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}
