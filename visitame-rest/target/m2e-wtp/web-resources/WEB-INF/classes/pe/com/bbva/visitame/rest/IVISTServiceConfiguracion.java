package pe.com.bbva.visitame.rest;

import java.util.List;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import pe.com.bbva.visitame.dominio.Parametro;
import pe.com.bbva.visitame.dominio.Valor;
import pe.com.bbva.visitame.dominio.dto.ConsultaConfiguracion;
import pe.com.bbva.visitame.dominio.dto.DatoConsulta;
import pe.com.bbva.visitame.exception.NegocioException;
import pe.com.bbva.visitame.exception.ValidacionException;

@Path("/configuracion")
public interface IVISTServiceConfiguracion {
	
	@GET
	@Path("/valores")
	@Produces({MediaType.APPLICATION_JSON})
	public Map<String, List<Valor>> obtenerValores(
			@QueryParam("codigoLista") String codigoLista) throws ValidacionException, NegocioException;
	
	@GET
	@Path("/valoresRespuesta")
	@Produces({MediaType.APPLICATION_JSON})
	public Map<String, List<Valor>> obtenerValoresRespuesta(
			@QueryParam("codigoLista") String codigoLista) throws ValidacionException, NegocioException;
	
	@GET
	@Path("/parametro")
	@Produces({MediaType.APPLICATION_JSON})
	public Map<String, Parametro> obtenerParametro(
			@QueryParam("codigoParametro") String codigoParametro) throws ValidacionException, NegocioException;
	
	@POST
	@Path("/parametros")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Map<String, List<Parametro>> obtenerParametro(ConsultaConfiguracion consultaConfiguracion) throws ValidacionException, NegocioException;
	
	@POST
	@Path("/valoresMotivo")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Map<String, List<Valor>> obtenerValoresMotivo(DatoConsulta datoConsulta) throws ValidacionException, NegocioException;
	
}