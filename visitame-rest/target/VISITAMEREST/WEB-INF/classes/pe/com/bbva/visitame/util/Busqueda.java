/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2008, Red Hat Middleware LLC or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Middleware LLC.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 *
 */
package pe.com.bbva.visitame.util;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.ResultTransformer;

/**
 * Some applications need to create criteria queries in "detached
 * mode", where the Hibernate session is not available. This class
 * may be instantiated anywhere, and then a <literal>Criteria</literal>
 * may be obtained by passing a session to 
 * <literal>getExecutableCriteria()</literal>. All methods have the
 * same semantics and behavior as the corresponding methods of the
 * <literal>Criteria</literal> interface.
 * 
 * @see org.hibernate.Criteria
 * @author Gavin King
 */

public class Busqueda implements CriteriaSpecification, Serializable {
	
	private static final long serialVersionUID = 758036182726890300L;
	
	private final CriteriaImpl criteriaImpl;
	private final Criteria criteria;
	
	private int maxResults = -1;
    private int firstResult = 0;
    private boolean cacheable = false;
	
	protected Busqueda(String entityName) {
		criteriaImpl = new CriteriaImpl(entityName, null);
		criteria = criteriaImpl;
	}
	
	protected Busqueda(String entityName, String alias) {
		criteriaImpl = new CriteriaImpl(entityName, alias, null);
		criteria = criteriaImpl;
	}
	
	protected Busqueda(CriteriaImpl criteriaImpl, Criteria criteria) {
		this.criteriaImpl = criteriaImpl;
		this.criteria = criteria;
	}

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public int getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }
	
    public boolean getCacheable() {
    	return cacheable;
    }
    
    public void setCacheable(boolean cacheable) {
    	this.cacheable = cacheable;
    }
    
	/**
	 * Get an executable instance of <literal>Criteria</literal>,
	 * to actually run the query.
	 */
	public Criteria getExecutableCriteria(Session session) {
		criteriaImpl.setSession( ( SessionImplementor ) session );
		return criteriaImpl;
	}
	
	public static Busqueda forEntityName(String entityName) {
		return new Busqueda(entityName);
	}
	
	public static Busqueda forEntityName(String entityName, String alias) {
		return new Busqueda(entityName, alias);
	}
	
	@SuppressWarnings("rawtypes")
	public static Busqueda forClass(Class clazz) {
		return new Busqueda( clazz.getName() );
	}
	
	@SuppressWarnings("rawtypes")
	public static Busqueda forClass(Class clazz, String alias) {
		return new Busqueda( clazz.getName() , alias );
	}
	
	public Busqueda add(Criterion criterion) {
		criteria.add(criterion);
		return this;
	}

	private java.util.List<Order> orders = new java.util.ArrayList<Order>();
	public Busqueda addOrder(Order order) {
		orders.add(order);
		criteria.addOrder(order);
		return this;
	}

	public Busqueda createAlias(String associationPath, String alias) throws HibernateException {
		criteria.createAlias(associationPath, alias);
		return this;
	}

	public Busqueda createCriteria(String associationPath, String alias) throws HibernateException {
		return new Busqueda( criteriaImpl, criteria.createCriteria(associationPath, alias) );
	}

	public Busqueda createCriteria(String associationPath) throws HibernateException {
		return new Busqueda( criteriaImpl, criteria.createCriteria(associationPath) );
	}

	public String getAlias() {
		return criteria.getAlias();
	}

	public Busqueda setFetchMode(String associationPath, FetchMode mode) throws HibernateException {
		criteria.setFetchMode(associationPath, mode);
		return this;
	}

	public Busqueda setProjection(Projection projection) {
		criteria.setProjection(projection);
		return this;
	}

	public Busqueda setResultTransformer(ResultTransformer resultTransformer) {
		criteria.setResultTransformer(resultTransformer);
		return this;
	}
	
	public String toString() {
		StringBuilder texto = new StringBuilder(this.comentario + "\n");
		texto.append("Busqueda = { " + criteria.toString() + " } ");
		for (Order o : orders) {
			texto.append(" [" + o.toString() + "]");
		}
		return texto.toString().replace("pe.com.bbva.pic.dominio.", "#");
	}
	
	CriteriaImpl getCriteriaImpl() {
		return criteriaImpl;
	}

    public Busqueda createAlias(String associationPath, String alias, JoinType joinType) throws HibernateException {
        criteria.createAlias(associationPath, alias, joinType);
        return this;
    }
	
	public Busqueda createAlias(String associationPath, String alias, JoinType joinType, Criterion withClause) throws HibernateException {
		criteria.createAlias(associationPath, alias, joinType, withClause);
		return this;
	}
	
	public Busqueda createCriteria(String associationPath, JoinType joinType) throws HibernateException {
        return new Busqueda(criteriaImpl, criteria.createCriteria(associationPath, joinType));
    }

    public Busqueda createCriteria(String associationPath, String alias, JoinType joinType) throws HibernateException {
        return new Busqueda(criteriaImpl, criteria.createCriteria(associationPath, alias, joinType));
    }
	
	public Busqueda createCriteria(String associationPath, String alias, JoinType joinType, Criterion withClause) throws HibernateException {
		return new Busqueda(criteriaImpl, criteria.createCriteria(associationPath, alias, joinType, withClause));
	}

	/**
	 * @deprecated use {@link #createAlias(String, String, JoinType)}
	 */
	@Deprecated
	public Busqueda createAlias(String associationPath, String alias, int joinType) throws HibernateException {
       return createAlias( associationPath, alias, JoinType.parse( joinType ) );
    }
	/**
	 * @deprecated use {@link #createAlias(String, String, JoinType, Criterion)}
	 */
	@Deprecated
	public Busqueda createAlias(String associationPath, String alias, int joinType, Criterion withClause) throws HibernateException {
		return createAlias( associationPath, alias, JoinType.parse( joinType ), withClause );
	}
	/**
	 * @deprecated use {@link #createCriteria(String, JoinType)}
	 */
	@Deprecated
	public Busqueda createCriteria(String associationPath, int joinType) throws HibernateException {
        return createCriteria( associationPath, JoinType.parse( joinType ) );
    }
	/**
	 * @deprecated use {@link #createCriteria(String, String, JoinType)}
	 */
	@Deprecated
    public Busqueda createCriteria(String associationPath, String alias, int joinType) throws HibernateException {
        return createCriteria( associationPath, alias, JoinType.parse( joinType ) );
    }
	/**
	 * @deprecated use {@link #createCriteria(String, String, JoinType, Criterion)}
	 */
	@Deprecated
	public Busqueda createCriteria(String associationPath, String alias, int joinType, Criterion withClause) throws HibernateException {
		return createCriteria( associationPath, alias, JoinType.parse( joinType ), withClause );
	}
	
	private String comentario = "Sin comentario";
	public Busqueda setComment(String comment) {
		comentario = comment;
        criteria.setComment(comment);
        return this;
    }

    public Busqueda setLockMode(LockMode lockMode) {
        criteria.setLockMode(lockMode);
        return this;
    }

    public Busqueda setLockMode(String alias, LockMode lockMode) {
        criteria.setLockMode(alias, lockMode);
        return this;
    }
    
    public Criteria getCriteria() {
		return criteria;
	}
    
}