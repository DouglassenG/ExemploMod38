package br.com.douglas.service;

import java.util.List;

import br.com.douglas.domain.Funcionario;
import br.com.douglas.exceptions.DAOException;
import br.com.douglas.service.generic.IGenericService;

/**
 * @author douglas
 *
 * Interface de servico especifica de Funcionario com regras de negocio
 */
public interface IFuncionarioService extends IGenericService<Funcionario, Long> {

	/**
	 * Busca funcionario pelo CPF
	 */
	Funcionario buscarPorCpf(String cpf) throws DAOException;

	/**
	 * Filtra funcionarios pelo nome (autocomplete)
	 */
	List<Funcionario> filtrarPorNome(String query);
}
