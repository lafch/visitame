package pe.com.bbva.visitame.dominio.dto;

import java.util.List;

public class ConsultaConfiguracion {

	private List<CodigoParametro> parametros;
	public List<CodigoParametro> getParametros() { return parametros; }
	public void setParametros(List<CodigoParametro> parametros) { this.parametros = parametros; }
}
