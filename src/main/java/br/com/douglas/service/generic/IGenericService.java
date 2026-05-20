package br.com.douglas.service.generic;

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
 * Interface generica de servico com operacoes CRUD
 */
public interface IGenericService<T extends Persistente, E extends Serializable> {

	T cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException;

	void excluir(T entity) throws DAOException;

	T alterar(T entity) throws TipoChaveNaoEncontradaException, DAOException;

	T consultar(E valor) throws MaisDeUmRegistroException, TableException, DAOException;

	Collection<T> buscarTodos() throws DAOException;
}
