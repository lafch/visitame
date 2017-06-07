package pe.com.bbva.visitame.service;

import java.util.List;


import pe.com.bbva.visitame.dominio.Parametro;
import pe.com.bbva.visitame.dominio.Valor;
import pe.com.bbva.visitame.exception.NegocioException;
import pe.com.bbva.visitame.util.Busqueda;

public interface ConfiguracionService {
	
	List<Valor> listarValores(String listaCodigo) throws NegocioException;
	
	List<Valor> listarValores(Busqueda busqueda) throws NegocioException;

	Parametro obtenerParametro(String parametroCodigo) throws NegocioException;
	
	Valor obtenerValor(String listaCodigo, String valorCodigo) throws NegocioException;

	List<Parametro> obtenerParametros(List<String> parametros) throws NegocioException;
	
}
