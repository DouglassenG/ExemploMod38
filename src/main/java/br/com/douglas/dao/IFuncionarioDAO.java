package br.com.douglas.dao;

import java.util.List;

import br.com.douglas.dao.generic.IGenericDAO;
import br.com.douglas.domain.Funcionario;
import br.com.douglas.exceptions.DAOException;

/**
 * @author douglas
 *
 * Interface especifica do DAO de Funcionario com metodos adicionais
 */
public interface IFuncionarioDAO extends IGenericDAO<Funcionario, Long> {

	/**
	 * Filtra funcionarios pelo nome (busca parcial)
	 */
	List<Funcionario> filtrarPorNome(String query);

	/**
	 * Busca funcionario pelo CPF (unico)
	 */
	Funcionario buscarPorCpf(String cpf) throws DAOException;
}
