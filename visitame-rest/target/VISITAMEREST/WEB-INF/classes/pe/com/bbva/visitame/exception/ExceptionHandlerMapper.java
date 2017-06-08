package pe.com.bbva.visitame.exception;

import java.net.ConnectException;
import java.util.Locale;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandlerMapper implements ExceptionMapper<Throwable> {
	
	private static final String BAJA = "BAJA";
	private static final String MEDIA = "MEDIA";
	
	@Override
	public Response toResponse(Throwable ex) {
		ErrorMensaje errorMensaje = new ErrorMensaje();				
		if(ex instanceof ValidacionException){
			ValidacionException validacionException = (ValidacionException) ex;
			errorMensaje.setStatusCode(validacionException.getStatusCode());
			errorMensaje.setStatusDescription(validacionException.getStatusDescription());
			errorMensaje.setErrorMessage(validacionException.getErrorMessage());
			errorMensaje.setSeverity(BAJA);
		} else if(ex instanceof NegocioException){
			NegocioException negocioException = (NegocioException) ex;
			setHttpStatus(ex, errorMensaje);
			errorMensaje.setStatusDescription(negocioException.getCodigo());
			errorMensaje.setErrorMessage(negocioException.getMessage());
			errorMensaje.setSeverity(negocioException.getSeveridad());
		} else if(ex instanceof ConnectException) {
			setHttpStatus(ex, errorMensaje);
			errorMensaje.setStatusDescription("Error en conexión con el servicio");
			errorMensaje.setErrorMessage("Servicio no disponible");
			errorMensaje.setSeverity(MEDIA);
		} else {
			setHttpStatus(ex, errorMensaje);
			errorMensaje.setStatusDescription("Error interno de la aplicación");
			errorMensaje.setErrorMessage("Ocurrió algo inesperado");
			errorMensaje.setSeverity(MEDIA);
		}
		return Response.status(errorMensaje.getStatusCode())
                .entity(errorMensaje).language(new Locale("es", "PE")).type(MediaType.APPLICATION_JSON).build();
	}
	
	private void setHttpStatus(Throwable ex, ErrorMensaje errorMensaje) {
		if(ex instanceof WebApplicationException) {
			errorMensaje.setStatusCode(((WebApplicationException) ex).getResponse().getStatus());
		} else {
			errorMensaje.setStatusCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		}
	}
}