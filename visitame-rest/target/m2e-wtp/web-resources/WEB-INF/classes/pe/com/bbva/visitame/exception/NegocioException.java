package pe.com.bbva.visitame.exception;

public class NegocioException extends Exception {

	private static final long serialVersionUID = -3670465299206740638L;

	public static class CODIGO {
		public static final String NEG_ARGUMENTO_INVALIDO = "NEG_ARGUMENTO_INVALIDO";
		public static final String NEG_ARGUMENTO_MINIMO = "NEG_ARGUMENTO_MINIMO";
		public static final String NEG_ARGUMENTO_OBLIGATORIO = "NEG_ARGUMENTO_OBLIGATORIO";
		public static final String NEG_COMUNICACION_GOOGLE = "NEG_COMUNICACION_GOOGLE";
		public static final String NEG_CONSULTA_FALLIDA = "NEG_CONSULTA_FALLIDA";
		public static final String NEG_DEPENDENCIA_FALTANTE = "NEG_DEPENDENCIA_FALTANTE";
		public static final String NEG_DEPENDENCIA_BLOQUEANTE = "NEG_DEPENDENCIA_BLOQUEANTE";
		public static final String NEG_ELIMINACION_FALLIDA = "NEG_ELIMINACION_FALLIDA";
		public static final String NEG_ENTIDAD_INEXISTENTE = "NEG_ENTIDAD_INEXISTENTE";
		public static final String NEG_ERROR_INTERNO = "NEG_ERROR_INTERNO";
		public static final String NEG_INSERCION_FALLIDA = "NEG_INSERCION_FALLIDA";
		public static final String NEG_MODIFICACION_FALLIDA = "NEG_MODIFICACION_FALLIDA";
		public static final String NEG_OPERACION_INVALIDA = "NEG_OPERACION_INVALIDA";
		public static final String NEG_REGLA_BLOQUEANTE = "NEG_REGLA_BLOQUEANTE";
		public static final String NEG_REGLA_BLOQUEANTE_CORTO = "NEG_REGLA_BLOQUEANTE_CORTO";
		public static final String NEG_REGLA_BLOQUEANTE_PERFIL = "NEG_REGLA_BLOQUEANTE_PERFIL";
	}
	
	public static class SEVERIDAD {
		public static final String BAJA = "BAJA";
		public static final String MEDIA = "MEDIA";
		public static final String ALTA = "ALTA";
	}
	
	private String codigo;
	public String getCodigo() { return codigo; }
	public void setCodigo(String codigo) { this.codigo = codigo; }
	
	private String mensaje;
	public String getMensaje() { return mensaje; }
	public void setMensaje(String mensaje) { this.mensaje = mensaje; }
	
	private String severidad;
	public String getSeveridad() { return severidad; }
	public void setSeveridad(String severidad) { this.severidad = severidad; }
	
	public NegocioException(String mensaje) {
		super(mensaje);
		this.severidad = NegocioException.SEVERIDAD.MEDIA;
	}
	
	public NegocioException(String codigo, String mensaje) {
		super(mensaje);
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.severidad = NegocioException.SEVERIDAD.BAJA;
	}
	
	public NegocioException(String codigo, String mensaje, Throwable causa) {
		super(mensaje, causa);
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.severidad = NegocioException.SEVERIDAD.BAJA;
	}
	
	public NegocioException(String codigo, String mensaje, String severidad) {
		super(mensaje);
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.severidad = severidad;
	}
	
	public NegocioException(String codigo, String mensaje, String severidad, Throwable causa) {
		super(mensaje, causa);
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.severidad = severidad;
	}
}