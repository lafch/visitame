package pe.com.bbva.visitame.dao.impl;

import org.springframework.stereotype.Repository;
import pe.com.bbva.visitame.dao.ValorDAO;
import pe.com.bbva.visitame.dominio.Valor;

@Repository
public class ValorDAOImpl extends BaseDAOImpl<Valor, Long> implements ValorDAO {

	private static final long serialVersionUID = -8375244206052720532L;

	@Override
	protected Class<Valor> getClase() { return Valor.class; }
	
}