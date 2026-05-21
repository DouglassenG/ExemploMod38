package br.com.douglas.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.douglas.dao.generic.GenericDAO;
import br.com.douglas.domain.Funcionario;
import br.com.douglas.exceptions.DAOException;

/**
 * @author douglas
 *
 * Implementacao do DAO especifico de Funcionario
 */
public class FuncionarioDAO extends GenericDAO<Funcionario, Long> implements IFuncionarioDAO {

	public FuncionarioDAO() {
		super(Funcionario.class);
	}

	@Override
	public List<Funcionario> filtrarPorNome(String query) {
		TypedQuery<Funcionario> tpQuery =
				this.entityManager.createNamedQuery("Funcionario.findByNome", this.persistenteClass);
		tpQuery.setParameter("nome", "%" + query + "%");
		return tpQuery.getResultList();
	}

	@Override
	public Funcionario buscarPorCpf(String cpf) throws DAOException {
		try {
			TypedQuery<Funcionario> query =
					this.entityManager.createQuery(
							"SELECT f FROM Funcionario f WHERE f.cpf = :cpf", this.persistenteClass);
			query.setParameter("cpf", cpf);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException("Erro ao buscar funcionario por CPF", e);
		}
	}
}
