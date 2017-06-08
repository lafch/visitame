package pe.com.bbva.visitame.dao;

import java.io.Serializable;

import java.util.List;

import pe.com.bbva.visitame.exception.DAOException;
import pe.com.bbva.visitame.util.Busqueda;

public interface BaseDAO<Entidad, Id extends Serializable> extends Serializable {

	Entidad crear(Entidad entidad) throws DAOException;
	
	List<Entidad> crearEntidades(List<Entidad> entidades) throws DAOException;

	Entidad obtener(Id id) throws DAOException;

	Entidad modificar(Entidad entidad) throws DAOException;

	void eliminar(Id id) throws DAOException;

	List<Entidad> listar(Busqueda busqueda) throws DAOException;

	<T> List<T> proyectar(Busqueda busqueda) throws DAOException;

	Long contar(Busqueda busqueda, String propertyName) throws DAOException;

	Long maximo(Busqueda busqueda, String propertyName) throws DAOException;
}