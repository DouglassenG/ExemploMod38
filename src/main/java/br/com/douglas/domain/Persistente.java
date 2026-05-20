package br.com.douglas.domain;

/**
 * @author douglas
 *
 * Interface que representa todas as entidades da aplicacao que serao salvas no banco de dados
 */
public interface Persistente {

	Long getId();
	
	void setId(Long id);
}
