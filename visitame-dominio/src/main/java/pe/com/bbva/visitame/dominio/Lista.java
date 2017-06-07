package pe.com.bbva.visitame.dominio;

import java.io.Serializable;
import javax.persistence.*;

import pe.com.bbva.visitame.dominio.util.Constantes;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tvisita002_cfg_lista database table.
 * 
 */
@Entity
@Table(name="tvisita002_cfg_lista", schema=Constantes.SCHEMA.VIST)
public class Lista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TVISITA002_CFG_LISTA_CDLISTA_GENERATOR", sequenceName="SEQ_TVISITA002_CFG_LISTA" , schema=Constantes.SCHEMA.VIST)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TVISITA002_CFG_LISTA_CDLISTA_GENERATOR")
	@Column(name="cd_lista")
	private Integer id;

	@Column(name="cd_alterno")
	private String cdAlterno;

	@Column(name="cd_creador")
	private Integer cdCreador;

	@Column(name="cd_editor")
	private Integer cdEditor;

	@Column(name="nb_etiqueta")
	private String nbEtiqueta;

	@Column(name="nb_glosa")
	private String nbGlosa;

	@Column(name="st_estado")
	private String stEstado;


	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	public String getCdAlterno() {
		return this.cdAlterno;
	}

	public void setCdAlterno(String cdAlterno) {
		this.cdAlterno = cdAlterno;
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

	public String getStEstado() {
		return this.stEstado;
	}

	public void setStEstado(String stEstado) {
		this.stEstado = stEstado;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdAlterno == null) ? 0 : cdAlterno.hashCode());
		result = prime * result + ((cdCreador == null) ? 0 : cdCreador.hashCode());
		result = prime * result + ((cdEditor == null) ? 0 : cdEditor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nbEtiqueta == null) ? 0 : nbEtiqueta.hashCode());
		result = prime * result + ((nbGlosa == null) ? 0 : nbGlosa.hashCode());
		result = prime * result + ((stEstado == null) ? 0 : stEstado.hashCode());
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
		Lista other = (Lista) obj;
		if (cdAlterno == null) {
			if (other.cdAlterno != null)
				return false;
		} else if (!cdAlterno.equals(other.cdAlterno))
			return false;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (stEstado == null) {
			if (other.stEstado != null)
				return false;
		} else if (!stEstado.equals(other.stEstado))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Lista [id=" + id + ", cdAlterno=" + cdAlterno + ", cdCreador=" + cdCreador + ", cdEditor=" + cdEditor
				+ ", nbEtiqueta=" + nbEtiqueta + ", nbGlosa=" + nbGlosa + ", stEstado=" + stEstado + "]";
	}


	
}