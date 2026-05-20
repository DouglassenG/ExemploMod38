package br.com.douglas.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author douglas
 *
 * Entidade que representa um funcionario no sistema
 */
@Entity
@Table(name = "TB_FUNCIONARIO")
@NamedQuery(name = "Funcionario.findByNome", query = "SELECT f FROM Funcionario f WHERE f.nome LIKE :nome")
public class Funcionario implements Persistente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "func_seq")
	@SequenceGenerator(name = "func_seq", sequenceName = "sq_funcionario", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "NOME", nullable = false, length = 100)
	private String nome;

	@Column(name = "CPF", nullable = false, unique = true, length = 14)
	private String cpf;

	@Column(name = "EMAIL", nullable = false, length = 100)
	private String email;

	@Column(name = "TELEFONE", nullable = false, length = 20)
	private String telefone;

	@Enumerated(EnumType.STRING)
	@Column(name = "CARGO", nullable = false, length = 30)
	private Cargo cargo;

	@Column(name = "SALARIO", nullable = false)
	private BigDecimal salario;

	@Column(name = "DATA_ADMISSAO", nullable = false)
	private LocalDate dataAdmissao;

	@Column(name = "ATIVO", nullable = false)
	private Boolean ativo;

	public Funcionario() {
		this.ativo = true;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
