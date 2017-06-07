package pe.com.bbva.visitame.dao.impl;

import org.springframework.stereotype.Repository;

import pe.com.bbva.visitame.dao.ParametroDAO;
import pe.com.bbva.visitame.dominio.Parametro;

@Repository
public class ParametroDAOImpl extends BaseDAOImpl<Parametro, String> implements ParametroDAO {

	private static final long serialVersionUID = -2071812794675984637L;

	@Override
	protected Class<Parametro> getClase() { return Parametro.class; }

}