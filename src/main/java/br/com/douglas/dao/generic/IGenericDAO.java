package br.com.douglas.dao.generic;

import java.io.Serializable;
import java.util.Collection;

import br.com.douglas.domain.Persistente;
import br.com.douglas.exceptions.DAOException;
import br.com.douglas.exceptions.MaisDeUmRegistroException;
import br.com.douglas.exceptions.TableException;
import br.com.douglas.exceptions.TipoChaveNaoEncontradaException;

/**
 * @author douglas
 *
 * Interface generica para metodos de CRUD (Create, Read, Update and Delete)
 */
public interface IGenericDAO<T extends Persistente, E extends Serializable> {

	/**
	 * Metodo para cadastrar novos registros no banco de dados
	 */
	T cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException;

	/**
	 * Metodo para excluir um registro do banco de dados
	 */
	void excluir(T entity) throws DAOException;

	/**
	 * Metodo para alterar um registro no banco de dados
	 */
	T alterar(T entity) throws TipoChaveNaoEncontradaException, DAOException;

	/**
	 * Metodo para consultar um registro no banco de dados
	 */
	T consultar(E id) throws MaisDeUmRegistroException, TableException, DAOException;

	/**
	 * Metodo que retorna todos os registros do banco de dados
	 */
	Collection<T> buscarTodos() throws DAOException;
}
