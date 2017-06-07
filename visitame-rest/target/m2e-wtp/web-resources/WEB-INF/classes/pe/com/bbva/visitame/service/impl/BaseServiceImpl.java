package pe.com.bbva.visitame.service.impl;

import java.io.Serializable;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import pe.com.bbva.visitame.exception.NegocioException;

public abstract class BaseServiceImpl implements Serializable {

	private static final long serialVersionUID = -13459401878340477L;
	
	@Autowired
	protected MessageSource diccionario;
	
//	protected abstract Logger getLogger();
	
	private void lanzarExcepcion(String codigo, Object[] valores, String mensajePorDefecto, String severidad, Throwable excepcion) throws NegocioException {
		String mensajeError = diccionario.getMessage(codigo, valores, mensajePorDefecto, new Locale("es", "PE"));
		if (excepcion == null) {
//			getLogger().error(mensajeError);
			throw new NegocioException(codigo, mensajeError, severidad);
		} else {
//			getLogger().error(mensajeError, excepcion);
			throw new NegocioException(codigo, mensajeError, severidad, excepcion);
		}
	}
	
	protected void lanzarExcepcionLeve(String codigo, Object[] valores, String mensajePorDefecto, Throwable excepcion) throws NegocioException {
		lanzarExcepcion(codigo, valores, mensajePorDefecto, NegocioException.SEVERIDAD.BAJA, excepcion);
	}
	
	protected void lanzarExcepcionMedia(String codigo, Object[] valores, String mensajePorDefecto, Throwable excepcion) throws NegocioException {
		lanzarExcepcion(codigo, valores, mensajePorDefecto, NegocioException.SEVERIDAD.MEDIA, excepcion);
	}
	
	protected void lanzarExcepcionGrave(String codigo, Object[] valores, String mensajePorDefecto, Throwable excepcion) throws NegocioException {
		lanzarExcepcion(codigo, valores, mensajePorDefecto, NegocioException.SEVERIDAD.ALTA, excepcion);
	}
}