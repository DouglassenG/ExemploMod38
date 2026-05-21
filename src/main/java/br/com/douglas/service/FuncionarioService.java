package br.com.douglas.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.douglas.dao.IFuncionarioDAO;
import br.com.douglas.domain.Funcionario;
import br.com.douglas.exceptions.DAOException;
import br.com.douglas.exceptions.TipoChaveNaoEncontradaException;
import br.com.douglas.service.generic.GenericService;

/**
 * @author douglas
 *
 * Servico de Funcionario com validacoes de regras de negocio:
 * - CPF nao pode ser duplicado
 * - Salario deve ser positivo
 * - Nome e obrigatorio
 * - Email deve conter @
 * - Data de admissao e obrigatoria
 */
@Stateless
public class FuncionarioService extends GenericService<Funcionario, Long> implements IFuncionarioService {

	private IFuncionarioDAO funcionarioDAO;

	@Inject
	public FuncionarioService(IFuncionarioDAO funcionarioDAO) {
		super(funcionarioDAO);
		this.funcionarioDAO = funcionarioDAO;
	}

	@Override
	public Funcionario cadastrar(Funcionario entity) throws TipoChaveNaoEncontradaException, DAOException {
		validarFuncionario(entity);
		validarCpfDuplicado(entity);
		return super.cadastrar(entity);
	}

	@Override
	public Funcionario alterar(Funcionario entity) throws TipoChaveNaoEncontradaException, DAOException {
		validarFuncionario(entity);
		return super.alterar(entity);
	}

	@Override
	public Funcionario buscarPorCpf(String cpf) throws DAOException {
		return funcionarioDAO.buscarPorCpf(cpf);
	}

	@Override
	public List<Funcionario> filtrarPorNome(String query) {
		return funcionarioDAO.filtrarPorNome(query);
	}

	/**
	 * Valida as regras de negocio basicas do funcionario
	 */
	private void validarFuncionario(Funcionario funcionario) throws DAOException {
		if (funcionario.getNome() == null || funcionario.getNome().trim().isEmpty()) {
			throw new DAOException("O nome do funcionario e obrigatorio",
					new IllegalArgumentException("Nome vazio"));
		}

		if (funcionario.getCpf() == null || funcionario.getCpf().trim().isEmpty()) {
			throw new DAOException("O CPF do funcionario e obrigatorio",
					new IllegalArgumentException("CPF vazio"));
		}

		if (funcionario.getEmail() == null || !funcionario.getEmail().contains("@")) {
			throw new DAOException("O email do funcionario e invalido",
					new IllegalArgumentException("Email invalido"));
		}

		if (funcionario.getSalario() == null || funcionario.getSalario().compareTo(BigDecimal.ZERO) <= 0) {
			throw new DAOException("O salario deve ser um valor positivo",
					new IllegalArgumentException("Salario invalido"));
		}

		if (funcionario.getDataAdmissao() == null) {
			throw new DAOException("A data de admissao e obrigatoria",
					new IllegalArgumentException("Data de admissao nula"));
		}

		if (funcionario.getCargo() == null) {
			throw new DAOException("O cargo e obrigatorio",
					new IllegalArgumentException("Cargo nulo"));
		}
	}

	/**
	 * Valida se ja existe um funcionario com o mesmo CPF no banco
	 */
	private void validarCpfDuplicado(Funcionario funcionario) throws DAOException {
		Funcionario existente = funcionarioDAO.buscarPorCpf(funcionario.getCpf());
		if (existente != null) {
			throw new DAOException("Ja existe um funcionario cadastrado com este CPF",
					new IllegalArgumentException("CPF duplicado"));
		}
	}
}
