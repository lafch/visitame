package pe.com.bbva.visitame.dominio.dto;


public class DatoConsulta {
	
	private String codigoLista;
	public String getCodigoLista() { return codigoLista; }
	public void setCodigoLista(String codigoLista) { this.codigoLista = codigoLista; }
	
	private String codigoParametro;
	public String getCodigoParametro() { return codigoParametro; }
	public void setCodigoParametro(String codigoParametro) { this.codigoParametro = codigoParametro; }
	
	//SOLO CORRESPONDE PARA LISTAR LOS VALORES MOTIVO DENTRO DE LA VENTANA DE FEEDBACK
	private Long idMotivo;
	public Long getIdMotivo() { return this.idMotivo; }
	public void setIdMotivo(Long idMotivo) { this.idMotivo = idMotivo; }
}