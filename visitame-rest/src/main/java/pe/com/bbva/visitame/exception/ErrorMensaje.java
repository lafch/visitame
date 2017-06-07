package pe.com.bbva.visitame.exception;

import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName(value = "error")
public class ErrorMensaje {
	
	public ErrorMensaje() {}
	
	public ErrorMensaje(int statusCode, String statusDescription, String errorMessage) {
		this.statusCode = statusCode;
        this.statusDescription = statusDescription;
        this.errorMessage = errorMessage;
    }
	
	private int statusCode;
	public int getStatusCode() { return statusCode; }
    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }
    
    private String statusDescription;
    public String getStatusDescription() { return statusDescription; }
    public void setStatusDescription(String statusDescription) { this.statusDescription = statusDescription; }

    private String severity;
    public String getSeverity() { return severity; }
	public void setSeverity(String severity) { this.severity = severity; }
    
    private String errorMessage;
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}