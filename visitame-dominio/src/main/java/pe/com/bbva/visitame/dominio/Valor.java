package pe.com.bbva.visitame.dominio;

import java.io.Serializable;
import javax.persistence.*;

import pe.com.bbva.visitame.dominio.util.Constantes;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tvisita003_cfg_valor database table.
 * 
 */
@Entity
@Table(name="tvisita003_cfg_valor" , schema=Constantes.SCHEMA.VIST)
public class Valor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TVISITA003_CFG_VALOR_CDVALOR_GENERATOR", sequenceName="SEQ_TVISITA003_CFG_VALOR" , schema=Constantes.SCHEMA.VIST)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TVISITA003_CFG_VALOR_CDVALOR_GENERATOR")
	@Column(name="cd_valor")
	private Integer cdValor;

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

	@Column(name="nb_valor")
	private String nbValor;

	@Column(name="nu_orden")
	private Integer nuOrden;

	@Column(name="st_estado")
	private String stEstado;

	@Column(name="tm_creacion")
	private Timestamp tmCreacion;

	@Column(name="tm_edicion")
	private Timestamp tmEdicion;

	//bi-directional many-to-one association to Lista
	@ManyToOne
	@JoinColumn(name="cd_lista")
	private Lista lista;

//	//bi-directional many-to-one association to Valor
//	@ManyToOne
//	@JoinColumn(name="cd_padre")
//	private Valor valor;
//
//	//bi-directional many-to-one association to Valor
//	@OneToMany(mappedBy="valor")
//	private List<Valor> valores;

	public Valor() {
	}

	public Integer getCdValor() {
		return this.cdValor;
	}

	public void setCdValor(Integer cdValor) {
		this.cdValor = cdValor;
	}

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

	public String getNbValor() {
		return this.nbValor;
	}

	public void setNbValor(String nbValor) {
		this.nbValor = nbValor;
	}

	public Integer getNuOrden() {
		return this.nuOrden;
	}

	public void setNuOrden(Integer nuOrden) {
		this.nuOrden = nuOrden;
	}

	public String getStEstado() {
		return this.stEstado;
	}

	public void setStEstado(String stEstado) {
		this.stEstado = stEstado;
	}

	public Timestamp getTmCreacion() {
		return this.tmCreacion;
	}

	public void setTmCreacion(Timestamp tmCreacion) {
		this.tmCreacion = tmCreacion;
	}

	public Timestamp getTmEdicion() {
		return this.tmEdicion;
	}

	public void setTmEdicion(Timestamp tmEdicion) {
		this.tmEdicion = tmEdicion;
	}

	public Lista getLista() {
		return this.lista;
	}

	public void setLista(Lista lista) {
		this.lista = lista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdAlterno == null) ? 0 : cdAlterno.hashCode());
		result = prime * result + ((cdCreador == null) ? 0 : cdCreador.hashCode());
		result = prime * result + ((cdEditor == null) ? 0 : cdEditor.hashCode());
		result = prime * result + ((cdValor == null) ? 0 : cdValor.hashCode());
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result + ((nbEtiqueta == null) ? 0 : nbEtiqueta.hashCode());
		result = prime * result + ((nbGlosa == null) ? 0 : nbGlosa.hashCode());
		result = prime * result + ((nbValor == null) ? 0 : nbValor.hashCode());
		result = prime * result + ((nuOrden == null) ? 0 : nuOrden.hashCode());
		result = prime * result + ((stEstado == null) ? 0 : stEstado.hashCode());
		result = prime * result + ((tmCreacion == null) ? 0 : tmCreacion.hashCode());
		result = prime * result + ((tmEdicion == null) ? 0 : tmEdicion.hashCode());
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
		Valor other = (Valor) obj;
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
		if (cdValor == null) {
			if (other.cdValor != null)
				return false;
		} else if (!cdValor.equals(other.cdValor))
			return false;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
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
		if (nbValor == null) {
			if (other.nbValor != null)
				return false;
		} else if (!nbValor.equals(other.nbValor))
			return false;
		if (nuOrden == null) {
			if (other.nuOrden != null)
				return false;
		} else if (!nuOrden.equals(other.nuOrden))
			return false;
		if (stEstado == null) {
			if (other.stEstado != null)
				return false;
		} else if (!stEstado.equals(other.stEstado))
			return false;
		if (tmCreacion == null) {
			if (other.tmCreacion != null)
				return false;
		} else if (!tmCreacion.equals(other.tmCreacion))
			return false;
		if (tmEdicion == null) {
			if (other.tmEdicion != null)
				return false;
		} else if (!tmEdicion.equals(other.tmEdicion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Valor [cdValor=" + cdValor + ", cdAlterno=" + cdAlterno + ", cdCreador=" + cdCreador + ", cdEditor="
				+ cdEditor + ", nbEtiqueta=" + nbEtiqueta + ", nbGlosa=" + nbGlosa + ", nbValor=" + nbValor
				+ ", nuOrden=" + nuOrden + ", stEstado=" + stEstado + ", tmCreacion=" + tmCreacion + ", tmEdicion="
				+ tmEdicion + ", lista=" + lista + "]";
	}



	
	
}