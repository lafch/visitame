package pe.com.bbva.visitame.dominio;

import java.io.Serializable;
import javax.persistence.*;

import pe.com.bbva.visitame.dominio.util.Constantes;

import java.sql.Timestamp;


/**
 * The persistent class for the tvisita001_cfg_paramet database table.
 * 
 */
@Entity
@Table(name="tvisita001_cfg_paramet" , schema=Constantes.SCHEMA.VIST)
public class Parametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cd_parametro")
	private String cdParametro;

	@Column(name="cd_creador")
	private Integer cdCreador;

	@Column(name="cd_editor")
	private Integer cdEditor;

	@Column(name="nb_etiqueta")
	private String nbEtiqueta;

	@Column(name="nb_glosa")
	private String nbGlosa;

	@Column(name="tx_valor")
	private String txValor;

	public Parametro() {
	}

	public String getCdParametro() {
		return this.cdParametro;
	}

	public void setCdParametro(String cdParametro) {
		this.cdParametro = cdParametro;
	}

	public Integer getCdCreador() {
		return this.cdCreador;
	}

	public void setCdCreador(Integer cdCreador) {
		this.cdCreador = cdCreador;
	}

	public Integer getCdEditor() {
		return this.cdEditor;
	}

	public void setCdEditor(Integer cdEditor) {
		this.cdEditor = cdEditor;
	}

	public String getNbEtiqueta() {
		return this.nbEtiqueta;
	}

	public void setNbEtiqueta(String nbEtiqueta) {
		this.nbEtiqueta = nbEtiqueta;
	}

	public String getNbGlosa() {
		return this.nbGlosa;
	}

	public void setNbGlosa(String nbGlosa) {
		this.nbGlosa = nbGlosa;
	}


	public String getTxValor() {
		return this.txValor;
	}

	public void setTxValor(String txValor) {
		this.txValor = txValor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdCreador == null) ? 0 : cdCreador.hashCode());
		result = prime * result + ((cdEditor == null) ? 0 : cdEditor.hashCode());
		result = prime * result + ((cdParametro == null) ? 0 : cdParametro.hashCode());
		result = prime * result + ((nbEtiqueta == null) ? 0 : nbEtiqueta.hashCode());
		result = prime * result + ((nbGlosa == null) ? 0 : nbGlosa.hashCode());
		result = prime * result + ((txValor == null) ? 0 : txValor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parametro other = (Parametro) obj;
		if (cdCreador == null) {
			if (other.cdCreador != null)
				return false;
		} else if (!cdCreador.equals(other.cdCreador))
			return false;
		if (cdEditor == null) {
			if (other.cdEditor != null)
				return false;
		} else if (!cdEditor.equals(other.cdEditor))
			return false;
		if (cdParametro == null) {
			if (other.cdParametro != null)
				return false;
		} else if (!cdParametro.equals(other.cdParametro))
			return false;
		if (nbEtiqueta == null) {
			if (other.nbEtiqueta != null)
				return false;
		} else if (!nbEtiqueta.equals(other.nbEtiqueta))
			return false;
		if (nbGlosa == null) {
			if (other.nbGlosa != null)
				return false;
		} else if (!nbGlosa.equals(other.nbGlosa))
			return false;
		if (txValor == null) {
			if (other.txValor != null)
				return false;
		} else if (!txValor.equals(other.txValor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Parametro [cdParametro=" + cdParametro + ", cdCreador=" + cdCreador + ", cdEditor=" + cdEditor
				+ ", nbEtiqueta=" + nbEtiqueta + ", nbGlosa=" + nbGlosa + ", txValor=" + txValor + "]";
	}

}