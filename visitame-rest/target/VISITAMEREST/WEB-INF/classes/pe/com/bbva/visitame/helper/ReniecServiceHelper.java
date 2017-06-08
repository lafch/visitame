//package pe.com.bbva.visitame.helper;
//
//import java.rmi.RemoteException;
//import java.util.Date;
//
//import org.apache.axis2.AxisFault;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import pe.com.bbva.pic.dominio.Parametro;
//import pe.com.bbva.pic.dominio.reniec.Ciudadano;
//import pe.com.bbva.pic.dominio.reniec.Domicilio;
//import pe.com.bbva.pic.dominio.util.Constantes;
//import pe.com.bbva.pic.exception.NegocioException;
//import pe.com.bbva.pic.exception.SOAPException;
//import pe.com.bbva.pic.exception.ValidacionException;
//import pe.com.bbva.pic.service.ConfiguracionService;
//import pe.com.bbva.pic.util.DateUtil;
//
//import com.grupobbva.pe.sir.ents.body.consultapordni.ConsultaPorDNIResponse;
//import com.grupobbva.pe.sir.ents.body.consultapordni.DatosDomicilio;
//import com.grupobbva.pe.sir.ents.body.consultapordni.DatosNacimiento;
//import com.grupobbva.pe.sir.ents.body.consultapordni.DatosPersona;
//import com.grupobbva.pe.sir.ents.body.consultapordni.RespuestaDNI;
//import com.grupobbva.pe.sir.ents.body.consultapordni.RespuestaDatos;
//import com.grupobbva.pe.sir.ents.header.ResponseHeader;
//import com.grupobbva.pe.sir.service.message.ConsultaPorDNIRequest;
//import com.grupobbva.pe.sir.service.message.ConsultaPorDNIRequestDocument;
//import com.grupobbva.pe.sir.service.message.ConsultaPorDNIResponseDocument;
//import com.grupobbva.pe.sir.service.message.WS_PersonaReniecStub;
////import com.vaadin.server.VaadinSession;
//
//@Component
//public class ReniecServiceHelper {
//	private static final Logger logger = LogManager.getLogger(ReniecServiceHelper.class);
//	private static final String LOG_PROMPT = "ReniecServiceHelper > ";
//	private static final String CODIGO_OK = "0000";
//	
//	@Autowired
//	private ConfiguracionService configuracionService;
////	
//	
//	public Ciudadano obtenerPersonaXDNI(String numerodDoi, String ivUser) throws SOAPException, NegocioException, ValidacionException{
//		
//		
//		logger.info(LOG_PROMPT + "Inicio de consulta persona a RENIEC metodo invocado obtenerPersonaXDNI()");
//		Parametro rutaSoap = configuracionService.obtenerParametro(Constantes.PARAMETRO.URL_WS_RENIEC);
////		Usuario usuario = (Usuario) VaadinSession.getCurrent().getAttribute(Constantes.SESION.USUARIO);
//		String registro = ivUser;//"P017737";
//		StringBuilder sRutaSoap = new StringBuilder();
//		sRutaSoap.append(rutaSoap.getValor());
//		WS_PersonaReniecStub personaReniecStub = null;
//		try {
//			logger.info(LOG_PROMPT + "URL invocacion para servicios de RENIEC: " + sRutaSoap.toString());
//			personaReniecStub = new WS_PersonaReniecStub(sRutaSoap.toString());
//		} catch (AxisFault e) {
//			logger.info(LOG_PROMPT + "error en invocación a servicio obtenerPersonaXDNI" + e);
//			throw new SOAPException(e);
//		}
//		rutaSoap = null;
//		//Parametria requerida para el servicio de RENIEC 
//		Parametro timeOut = configuracionService.obtenerParametro(Constantes.PARAMETRO.TO_WS_RENIEC);
//		Parametro canal = configuracionService.obtenerParametro(Constantes.PARAMETRO.WS_PARAMETRO_RENIEC_CANAL);
//		Parametro codigoAplicacion = configuracionService.obtenerParametro(Constantes.PARAMETRO.COD_APP_PIC_PRE_IDM);
//		Parametro idEmpresa = configuracionService.obtenerParametro(Constantes.PARAMETRO.WS_PARAMETRO_RENIEC_EMPRESA);
//		Parametro codigoInterfaz = configuracionService.obtenerParametro(Constantes.PARAMETRO.WS_PARAMETRO_RENIEC_COD_INTERFAZ);
//		Parametro tipoAplicacion = configuracionService.obtenerParametro(Constantes.PARAMETRO.WS_PARAMETRO_RENIEC_TIP_APLICACION);
//		Parametro indConsultaDatos = configuracionService.obtenerParametro(Constantes.PARAMETRO.WS_PARAMETRO_RENIEC_CONS_DATOS);
//		Parametro indConsultaFoto = configuracionService.obtenerParametro(Constantes.PARAMETRO.WS_PARAMETRO_RENIEC_CONS_FOTO);
//		Parametro indConsultaFirma = configuracionService.obtenerParametro(Constantes.PARAMETRO.WS_PARAMETRO_RENIEC_CONS_FIRMA);
//		String fechaHoraEnvio = DateUtil.getFecha(new Date(), "yyyy-MM-dd-HH.mm.ss.SSS");//P
//		
//		personaReniecStub._getServiceClient().getOptions().setTimeOutInMilliSeconds(Long.valueOf(timeOut.getValor()));
//		ConsultaPorDNIRequest consultaPorDNIRequest = ConsultaPorDNIRequest.Factory.newInstance();
//		try {
//			//TODO ADD-HEADER
//			consultaPorDNIRequest.addNewRefRequestHeader().setCanal(canal.getValor());
//			consultaPorDNIRequest.getRefRequestHeader().setCodigoAplicacion(codigoAplicacion.getValor());//Parametro
//			consultaPorDNIRequest.getRefRequestHeader().setIdEmpresa(idEmpresa.getValor());//Parametro
//			consultaPorDNIRequest.getRefRequestHeader().setUsuario(registro);
//			consultaPorDNIRequest.getRefRequestHeader().setFechaHoraEnvio(fechaHoraEnvio);//Fecha formato yyyy-MM-dd-HH24.mm.ss.sss
//			//TODO EL ID DE TRANSACCION ES LA COMBINACION DE LA fechaEnvio + codAplicacion + registro
//			consultaPorDNIRequest.getRefRequestHeader().setIdTransaccion(fechaHoraEnvio + codigoAplicacion.getValor() + registro);
//			consultaPorDNIRequest.getRefRequestHeader().setCodigoInterfaz(codigoInterfaz.getValor());//Parametro
//			//TODO ADD-REQUEST
//			consultaPorDNIRequest.addNewRefConsultaPorDNIRequest().setTipoAplicacion(tipoAplicacion.getValor());//Parametro
//			consultaPorDNIRequest.getRefConsultaPorDNIRequest().setRegistroCodUsuario(registro);
//			consultaPorDNIRequest.getRefConsultaPorDNIRequest().setNumeroDNIConsultado(numerodDoi);
//			consultaPorDNIRequest.getRefConsultaPorDNIRequest().setIndConsultaDatos(indConsultaDatos.getValor());//Parametro
//			consultaPorDNIRequest.getRefConsultaPorDNIRequest().setIndConsultaFoto(indConsultaFoto.getValor());//Parametro
//			consultaPorDNIRequest.getRefConsultaPorDNIRequest().setIndConsultaFirma(indConsultaFirma.getValor());//Parametro			
//			
//			//TODO IMPRESION DE PARAMETROS DE ENVIO EN EL LOG 
//			logger.info(LOG_PROMPT + "--- Escritura de parametros de consulta de RENIEC ---");
//			logger.info(LOG_PROMPT + "---------> Canal: " + canal.getValor());
//			logger.info(LOG_PROMPT + "---------> Codigo aplicacion: " + codigoAplicacion.getValor());
//			logger.info(LOG_PROMPT + "---------> Id empresa: " + idEmpresa.getValor());
//			logger.info(LOG_PROMPT + "---------> Registro: " + registro);
//			logger.info(LOG_PROMPT + "---------> Fecha hora envio: " + fechaHoraEnvio);
//			logger.info(LOG_PROMPT + "---------> Id transaccion: " + (fechaHoraEnvio + codigoAplicacion.getValor() + registro));
//			logger.info(LOG_PROMPT + "---------> Codgio interfaz: " + codigoInterfaz.getValor());
//			logger.info(LOG_PROMPT + "---------> Tipo aplicacion: " + tipoAplicacion.getValor());
//			logger.info(LOG_PROMPT + "---------> Registro cod usuario: " + registro);
//			logger.info(LOG_PROMPT + "---------> Numero de DNI consultado: " + numerodDoi);
//			logger.info(LOG_PROMPT + "---------> Indicador consulta datos: " + indConsultaDatos.getValor());
//			logger.info(LOG_PROMPT + "---------> Indicador consulta foto: " + indConsultaFoto.getValor());
//			logger.info(LOG_PROMPT + "---------> Indicador consulta firma: " + indConsultaFirma.getValor());
//			logger.info(LOG_PROMPT + "---------------------------------------------------------------------");
//		} catch (Exception e) {
//			logger.error(LOG_PROMPT + "Parametros requeridos no existen en BD para consultar al servicio de RENIEC", e);
//			throw new SOAPException(e);
//		}
//		ConsultaPorDNIRequestDocument consultaPorDNIRequestDocument = ConsultaPorDNIRequestDocument.Factory.newInstance();
//		consultaPorDNIRequestDocument.setConsultaPorDNIRequest(consultaPorDNIRequest);
//		
//		ConsultaPorDNIResponseDocument dniResponseDocument = null; 
//		try {
//			//TODO CONSULTA A RENIEC DATOS DE PERSONA NATURAL
//			dniResponseDocument = personaReniecStub.consultaPorDNI(consultaPorDNIRequestDocument);
//		} catch (RemoteException e) {
//			logger.error(LOG_PROMPT + "Ocurrio un error en busqueda de cliente a los servicios de RENIEC", e);
//			throw new SOAPException(e);
//		} catch (Exception e) {
//			logger.error(LOG_PROMPT + "Ocurrio un error en busqueda de cliente a los servicios de RENIEC", e);
//			throw new SOAPException(e);
//		}
//		//TODO CREACION DE OBJETO DE RETORNO
//		Ciudadano ciudadano = new Ciudadano();
//		ResponseHeader responseHeader = dniResponseDocument.getConsultaPorDNIResponse().getRefResponseHeader();
//		if(responseHeader.getCodigoRespuesta().equals(CODIGO_OK)){
//			ConsultaPorDNIResponse dniResponse = dniResponseDocument.getConsultaPorDNIResponse().getRefConsultaPorDNIResponse();
//			RespuestaDNI respuestaDNI = dniResponse.getRespuestaDNI();
//			RespuestaDatos respuestaDatos = dniResponse.getRespuestaDatos();
//			
//			DatosPersona datosPersona = respuestaDatos.getDatosPersona();
//			DatosDomicilio datosDomicilio = respuestaDatos.getDatosDomicilio();
//			DatosNacimiento datosNacimiento = respuestaDatos.getDatosNacimiento();
//			
//			//TODO DATOS PERSONALES
//			ciudadano.setNumeroDNI(respuestaDNI.getNumDNIConsultado());
//			ciudadano.setNombres(datosPersona.getNombres());
//			ciudadano.setApellidoPaterno(datosPersona.getApellidoPaterno());
//			ciudadano.setApellidoMaterno(datosPersona.getApellidoMaterno());
//			ciudadano.setApellidoCasada(datosPersona.getApellidoCasada());
//			ciudadano.setSexo(datosPersona.getSexo());
//			ciudadano.setCodigoEstadoCivil(datosPersona.getCodigoEstadoCivil());
//			ciudadano.setDescripcionEstadoCivil(datosPersona.getDescripcionEstadoCivil());
//			ciudadano.setFechaNacimiento(datosNacimiento.getFecha());//DATOS DE NACIMIENTO
//			
//			//TODO DATOS DOMICILIO
//			Domicilio domicilio = new Domicilio();
//			domicilio.setCodigoPrefijoDireccion(datosDomicilio.getCodigoPrefijoDireccion());
//			domicilio.setDireccion(datosDomicilio.getDireccion());
//			domicilio.setNumeroDireccion(datosDomicilio.getNumDireccion());
//			domicilio.setBlockChalet(datosDomicilio.getBlockChalet());
//			domicilio.setInterior(datosDomicilio.getInterior());
//			domicilio.setEtapa(datosDomicilio.getEtapa());
//			domicilio.setManzana(datosDomicilio.getManzana());
//			domicilio.setLote(datosDomicilio.getLote());
//			domicilio.setUrbanizacion(datosDomicilio.getUrbanizacion());
//			domicilio.setDistrito(datosDomicilio.getDistrito());
//			domicilio.setProvincia(datosDomicilio.getProvincia());
//			domicilio.setDepartamento(datosDomicilio.getDepartamento());			
//			ciudadano.setDomicilio(domicilio);			
//		}
//		ciudadano.setCodigoRespuesta(responseHeader.getCodigoRespuesta());
//		ciudadano.setMensajeRespuesta(responseHeader.getMensajeRespuesta());
//		logger.info(LOG_PROMPT + responseHeader.getCodigoRespuesta() + " " + responseHeader.getMensajeRespuesta());
//		logger.info(LOG_PROMPT + "Fin de consulta persona a RENIEC metodo invocado obtenerPersonaXDNI() para el DOI: " + numerodDoi);
//		return ciudadano;
//	}
//}