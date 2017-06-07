package pe.com.bbva.visitame.dao.impl;

import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import pe.com.bbva.visitame.dao.BaseDAO;
import pe.com.bbva.visitame.exception.DAOException;
import pe.com.bbva.visitame.util.Busqueda;

public abstract class BaseDAOImpl<Entidad extends Serializable, Id extends Serializable> extends HibernateDaoSupport implements BaseDAO<Entidad, Id> {

	private static final long serialVersionUID = -843202252066514168L;

	protected Class<Entidad> clase;
	
	@Autowired private SessionFactory sessionFactory;
	
	public BaseDAOImpl() {
		super();
		this.clase = getClase();
	}
	
	@PostConstruct
	private void postConstruct(){
		super.setSessionFactory(sessionFactory);
	}
	
	protected abstract Class<Entidad> getClase();
	
	@Override
	public Entidad crear(Entidad entidad) throws DAOException {
		try {
			getHibernateTemplate().persist(entidad);
			return entidad;
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}
	
	public List<Entidad> crearEntidades(List<Entidad> entidades) throws DAOException {
		try {
			for (Entidad entidad : entidades) {
				getHibernateTemplate().save(entidad);
				getHibernateTemplate().flush();
			}
			return entidades;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Entidad obtener(Id id) throws DAOException {
		try {
			return getHibernateTemplate().get(getClase(), id);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Entidad modificar(Entidad entidad)  throws DAOException {
		try {
			getHibernateTemplate().update(entidad);
			getHibernateTemplate().flush();
			return entidad;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void eliminar(Id id) throws DAOException {
		try {
			getHibernateTemplate().delete(obtener(id));
			getHibernateTemplate().flush();
		} catch (DataIntegrityViolationException e) {
			throw new DAOException(e);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Entidad> listar(final Busqueda busqueda) throws DAOException {
		try {
			return (List<Entidad>) this.getHibernateTemplate().executeWithNativeSession(
	                new HibernateCallback<List<Entidad>>() {
						public List<Entidad> doInHibernate(Session session){
	                        Criteria criteria = busqueda.getExecutableCriteria(session);
	                        criteria.setFirstResult(busqueda.getFirstResult());
	                        if (busqueda.getMaxResults() > 0) {
	                            criteria.setMaxResults(busqueda.getMaxResults());
	                        }
	                        criteria.setCacheable(busqueda.getCacheable());
	                        return (List<Entidad>) criteria.list();
	                    }
	                });
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> proyectar(final Busqueda busqueda) throws DAOException {
		try {
			return (List<T>) this.getHibernateTemplate().executeWithNativeSession(
	                new HibernateCallback<List<T>>() {
						public List<T> doInHibernate(Session session){
	                        Criteria criteria = busqueda.getExecutableCriteria(session);
	                        criteria.setFirstResult(busqueda.getFirstResult());
	                        if (busqueda.getMaxResults() > 0) {
	                            criteria.setMaxResults(busqueda.getMaxResults());
	                        }
	                        return (List<T>) criteria.list();
	                    }
	                });
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	@Override
	public Long contar(final Busqueda busqueda, final String propertyName) throws DAOException {
		try {
			return this.getHibernateTemplate().executeWithNativeSession(
	                new HibernateCallback<Long>() {
						public Long doInHibernate(Session session){
	                        Criteria criteria = busqueda.getExecutableCriteria(session);
	                        criteria.setProjection(Projections.count(propertyName));
	                        return (Long)criteria.uniqueResult();
	                    }
	                });
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	@Override
	public Long maximo(final Busqueda busqueda, final String propertyName) throws DAOException {
		try {
			return this.getHibernateTemplate().executeWithNativeSession(
	                new HibernateCallback<Long>() {
						public Long doInHibernate(Session session){
	                        Criteria criteria = busqueda.getExecutableCriteria(session);
	                        criteria.setProjection(Projections.max(propertyName));
	                        return (Long)criteria.uniqueResult();
	                    }
	                });
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}