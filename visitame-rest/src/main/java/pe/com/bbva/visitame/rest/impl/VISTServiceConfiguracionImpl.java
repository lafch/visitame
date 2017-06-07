package pe.com.bbva.visitame.rest.impl;

import java.text.MessageFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.com.bbva.visitame.dominio.Parametro;
import pe.com.bbva.visitame.dominio.Valor;
import pe.com.bbva.visitame.dominio.dto.CodigoParametro;
import pe.com.bbva.visitame.dominio.dto.ConsultaConfiguracion;
import pe.com.bbva.visitame.dominio.dto.DatoConsulta;
import pe.com.bbva.visitame.dominio.util.Constantes;
import pe.com.bbva.visitame.exception.NegocioException;
import pe.com.bbva.visitame.exception.ValidacionException;
import pe.com.bbva.visitame.rest.IVISTServiceConfiguracion;
import pe.com.bbva.visitame.service.ConfiguracionService;
import pe.com.bbva.visitame.util.Busqueda;

@Component
public class VISTServiceConfiguracionImpl implements IVISTServiceConfiguracion {
	
	private static final Logger logger = LogManager.getLogger(VISTServiceConfiguracionImpl.class);
	private static final String LOG_PROMPT = "VISTServiceConfiguracionImpl > ";
	
	@Autowired
	private ConfiguracionService configuracionService;

	@Override
	public Map<String, List<Valor>> obtenerValores(String codigoLista) throws ValidacionException, NegocioException {
		logger.info(LOG_PROMPT + "Inicio de consulta de valores con codigo de lista: " + codigoLista);
		logger.debug(LOG_PROMPT + " DEBUG->Inicio de consulta de valores con codigo de lista: " + codigoLista);
		logger.error(LOG_PROMPT + " ERROR->Inicio de consulta de valores con codigo de lista: " + codigoLista);
		logger.trace(LOG_PROMPT + " TRACE->Inicio de consulta de valores con codigo de lista: " + codigoLista);
		List<Valor> valores;
		valores = configuracionService.listarValores(codigoLista);
		Map<String, List<Valor>> mapValores = new HashMap<String, List<Valor>>();
		mapValores.put("valores", valores);
		logger.info(LOG_PROMPT + "Fin de consulta de valores");
		return mapValores;
	}

	@Override
	public Map<String, Parametro> obtenerParametro(String codigoParametro) throws ValidacionException, NegocioException {
		logger.info(LOG_PROMPT + "Inicio de consulta de parametro con codigo de parametro: " + codigoParametro);
		if(StringUtils.isBlank(codigoParametro)){
			throw new ValidacionException(400, "Error en codigoParametro", "El codigo de parámetro a consultar no puede ser vacío o nulo");
		}
		Parametro parametro = configuracionService.obtenerParametro(codigoParametro);
		Map<String, Parametro> mapParametro = new HashMap<String, Parametro>();
		mapParametro.put("parametro", parametro);
		logger.info(LOG_PROMPT + "Fin de consulta de valores");
		return mapParametro;
	}
	
	@Override
	public Map<String, List<Parametro>> obtenerParametro(ConsultaConfiguracion consultaConfiguracion)
			throws ValidacionException, NegocioException {
		logger.info(LOG_PROMPT + "Inicio de consulta de parametros con codigos de parametro");
		if(consultaConfiguracion == null){
			throw new ValidacionException(400, "Error en consultaConfiguracion", "La consultaConfiguracion ser vacío o nulo");
		}
		if(consultaConfiguracion.getParametros() == null || consultaConfiguracion.getParametros().isEmpty()){
			throw new ValidacionException(400, "Error en consultaConfiguracion.getParametros()", "Los codigos de parámetro a consultar no pueden ser vacíos o nulos");
		}
		List<String> codigosParametro = new ArrayList<String>();
		for (CodigoParametro codigoParametro : consultaConfiguracion.getParametros()) {
			codigosParametro.add(codigoParametro.getCodigoParametro());
		}
		List<Parametro> parametrosEcontrados = configuracionService.obtenerParametros(codigosParametro);
		Map<String, List<Parametro>> mapParametro = new HashMap<String, List<Parametro>>();
		mapParametro.put("parametros", parametrosEcontrados);
		logger.info(LOG_PROMPT + "Fin de consulta de parametros con codigos de parametro");
		return mapParametro;
	}

	@Override
	public Map<String, List<Valor>> obtenerValoresRespuesta(String codigoLista)
			throws ValidacionException, NegocioException {
		logger.info(LOG_PROMPT + "Inicio de consulta de valores con codigo de lista: " + codigoLista);
		if(StringUtils.isBlank(codigoLista)){
			throw new ValidacionException(400, "Error en codigoLista", "El codigo de lista a consultar no puede ser vacío o nulo");
		}
		List<Valor> valores;
		Busqueda busqueda = Busqueda.forClass(Valor.class);
		busqueda.createAlias("lista", "l");
		busqueda.add(Restrictions.eq("l.codigo", codigoLista));
		busqueda.setCacheable(true);
		List<String> codigosIncluyentes = new ArrayList<String>();
		codigosIncluyentes.add(Constantes.VALOR.ACCION_RESPUESTA_ACE_CAMP.CODIGO);		
		codigosIncluyentes.add(Constantes.VALOR.ACCION_RESPUESTA_NOA_CAMP.CODIGO);
		codigosIncluyentes.add(Constantes.VALOR.ACCION_RESPUESTA_NO_CALIF.CODIGO);
		codigosIncluyentes.add(Constantes.VALOR.ACCION_RESPUESTA_NO_CONTA.CODIGO);	
		
		busqueda.add(Restrictions.in("codigo", codigosIncluyentes));
		busqueda.setComment(MessageFormat.format("buscar valores para el código de lista {0}", codigoLista));
		valores = configuracionService.listarValores(busqueda);
		Map<String, List<Valor>> mapValores = new HashMap<String, List<Valor>>();
		mapValores.put("valores", valores);
		logger.info(LOG_PROMPT + "Fin de consulta de valores");
		return mapValores;
	}
		
	@Override
	public Map<String, List<Valor>> obtenerValoresMotivo(DatoConsulta datoConsulta) throws ValidacionException, NegocioException {
		logger.info(LOG_PROMPT + "Inicio de consulta de valores Padre: " + datoConsulta.getIdMotivo());
		if(datoConsulta.getIdMotivo().compareTo((long)0) < 0){
			throw new ValidacionException(400, "Error en codigoLista", "El codigo de padre  a consultar no puede ser menor que 0");
		}
		List<Valor> valores;
		Busqueda busqueda = Busqueda.forClass(Valor.class);
		busqueda.add(Restrictions.eq("padre", datoConsulta.getIdMotivo()));
		busqueda.setComment(MessageFormat.format("buscar valores para el codigo padre {0}", datoConsulta.getIdMotivo()));
		valores = configuracionService.listarValores(busqueda);
		Map<String, List<Valor>> mapValores = new HashMap<String, List<Valor>>();
		mapValores.put("valores", valores);
		logger.info(LOG_PROMPT + "Fin de consulta de valores por el codigo padre");
		return mapValores;		
	}
	
}