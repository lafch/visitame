package pe.com.bbva.visitame.service.impl;

import java.text.MessageFormat;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.bbva.visitame.dao.ParametroDAO;
import pe.com.bbva.visitame.dao.ValorDAO;
import pe.com.bbva.visitame.dominio.Parametro;
import pe.com.bbva.visitame.dominio.Valor;
import pe.com.bbva.visitame.exception.DAOException;
import pe.com.bbva.visitame.exception.NegocioException;
import pe.com.bbva.visitame.service.ConfiguracionService;
import pe.com.bbva.visitame.util.Busqueda;

@Service
public class ConfiguracionServiceImpl extends BaseServiceImpl implements ConfiguracionService {
	
	private static final long serialVersionUID = -2560214004070561624L;
	
	@Autowired
	private ValorDAO valorDAO;
	@Autowired
	private ParametroDAO parametroDAO;
	
	public List<Valor> listarValores(String listaCodigo) throws NegocioException {
		if(listaCodigo == null){
			lanzarExcepcionMedia(NegocioException.CODIGO.NEG_ARGUMENTO_OBLIGATORIO, new Object[] { "listaCodigo" }, "Debe ingresar los siguientes argumentos: listaCodigo", null);
		}
		try {
			Busqueda busqueda = Busqueda.forClass(Valor.class);
			busqueda.createAlias("lista", "l");
			busqueda.add(Restrictions.eq("l.cdAlterno", listaCodigo));
			busqueda.setCacheable(true);
			busqueda.setComment(MessageFormat.format("buscar valores para el código de lista {0}", listaCodigo));
			return valorDAO.listar(busqueda);
		} catch (DAOException e) {
			lanzarExcepcionGrave(NegocioException.CODIGO.NEG_CONSULTA_FALLIDA, new Object[] { e.getMessage() }, "No se pudo ejecutar satisfactoriamente la consulta: " + e.getMessage(), e);
		}
		return null;
	}
	
	public List<Valor> listarValores(Busqueda busqueda) throws NegocioException {
		if(busqueda == null){
			lanzarExcepcionMedia(NegocioException.CODIGO.NEG_ARGUMENTO_OBLIGATORIO, new Object[] { "busqueda" }, "Debe ingresar los siguientes argumentos: busqueda", null);
		}		
		try {
			return valorDAO.listar(busqueda);
		} catch (DAOException e) {
			lanzarExcepcionGrave(NegocioException.CODIGO.NEG_CONSULTA_FALLIDA, new Object[] { e.getMessage() }, "No se pudo ejecutar satisfactoriamente la consulta: " + e.getMessage(), e);
		}
		return null;
	}
	
	@Override
	public Valor obtenerValor(String listaCodigo, String valorCodigo) throws NegocioException {
		if (StringUtils.isEmpty(listaCodigo)) {
			lanzarExcepcionMedia(NegocioException.CODIGO.NEG_ARGUMENTO_OBLIGATORIO, new Object[] {"listaCodigo"}, "Debe ingresar los siguientes argumentos: listaCodigo", null);
		}	
		if (StringUtils.isEmpty(valorCodigo)) {
			lanzarExcepcionMedia(NegocioException.CODIGO.NEG_ARGUMENTO_OBLIGATORIO, new Object[] {"valorCodigo"}, "Debe ingresar los siguientes argumentos: valorCodigo", null);
		}	
		Busqueda busqueda = Busqueda.forClass(Valor.class);
		try {
			busqueda.createAlias("lista", "l");
			busqueda.add(Restrictions.eq("l.codigo", listaCodigo));
			busqueda.add(Restrictions.eq("codigo", valorCodigo));
			busqueda.setCacheable(true);
			busqueda.setComment(MessageFormat.format("buscar valores de la lista con código {0} y con código de valor {1}", listaCodigo, valorCodigo));
			List<Valor> valores = valorDAO.listar(busqueda);
			if (valores.size() > 0) return valores.get(0);
		} catch (DAOException e) {
			lanzarExcepcionGrave(NegocioException.CODIGO.NEG_CONSULTA_FALLIDA, new Object[] { e.getMessage() }, "No se pudo ejecutar satisfactoriamente la consulta: " + e.getMessage(), e);
		}
		return null;
	}
	
	@Override
	public Parametro obtenerParametro(String parametroCodigo) throws NegocioException {
		if (StringUtils.isEmpty(parametroCodigo)) {
			lanzarExcepcionMedia(NegocioException.CODIGO.NEG_ARGUMENTO_OBLIGATORIO, new Object[] {"listaCodigo"}, "Debe ingresar los siguientes argumentos: listaCodigo", null);
		}	
		try {
			return parametroDAO.obtener(parametroCodigo);
		} catch (DAOException e) {
			lanzarExcepcionGrave(NegocioException.CODIGO.NEG_CONSULTA_FALLIDA, new Object[] { e.getMessage() }, "No se pudo ejecutar satisfactoriamente la consulta: " + e.getMessage(), e);
		}
		return null;
	}

	@Override
	public List<Parametro> obtenerParametros(List<String> parametros) throws NegocioException {
		if (parametros == null) {
			lanzarExcepcionMedia(NegocioException.CODIGO.NEG_ARGUMENTO_OBLIGATORIO, new Object[] {"listaCodigo"}, "Debe ingresar los siguientes argumentos: listaCodigo", null);
		}
		Busqueda busqueda = Busqueda.forClass(Parametro.class);
		try {
			busqueda.add(Restrictions.in("codigo", parametros));
			return parametroDAO.listar(busqueda);
		} catch (DAOException e) {
			lanzarExcepcionGrave(NegocioException.CODIGO.NEG_CONSULTA_FALLIDA, new Object[] { e.getMessage() }, "No se pudo ejecutar satisfactoriamente la consulta: " + e.getMessage(), e);
		}
		return null;
	}
	
	
}
