package br.com.douglas.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.douglas.domain.Cargo;
import br.com.douglas.domain.Funcionario;
import br.com.douglas.service.IFuncionarioService;

/**
 * @author douglas
 *
 * Controller JSF (Managed Bean) que recebe as informacoes da pagina de funcionario
 * e delega as operacoes para o servico.
 * 
 * Anotacoes:
 * - @Named: torna o bean acessivel via EL (Expression Language) no xhtml
 * - @ViewScoped: o bean vive enquanto o usuario estiver na mesma view/pagina
 */
@Named
@ViewScoped
public class FuncionarioController implements Serializable {

	private static final long serialVersionUID = 8030245985235567808L;

	private Funcionario funcionario;

	private Collection<Funcionario> funcionarios;

	@Inject
	private IFuncionarioService funcionarioService;

	private Boolean isUpdate;

	/**
	 * Metodo executado apos a construcao do bean.
	 * Inicializa o formulario e carrega a lista de funcionarios do banco.
	 */
	@PostConstruct
	public void init() {
		try {
			this.isUpdate = false;
			this.funcionario = new Funcionario();
			this.funcionarios = funcionarioService.buscarTodos();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao tentar listar os funcionarios"));
		}
	}

	/**
	 * Cancela a operacao atual e limpa o formulario
	 */
	public void cancel() {
		try {
			this.isUpdate = false;
			this.funcionario = new Funcionario();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao tentar cancelar acao"));
		}
	}

	/**
	 * Prepara o formulario para edicao de um funcionario existente
	 */
	public void edit(Funcionario funcionario) {
		try {
			this.isUpdate = true;
			this.funcionario = funcionario;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao tentar editar o funcionario"));
		}
	}

	/**
	 * Exclui um funcionario do banco de dados
	 */
	public void delete(Funcionario funcionario) {
		try {
			funcionarioService.excluir(funcionario);
			funcionarios.remove(funcionario);
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Funcionario excluido com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao tentar excluir o funcionario"));
		}
	}

	/**
	 * Cadastra um novo funcionario no banco de dados.
	 * As validacoes de regra de negocio sao feitas no FuncionarioService.
	 */
	public void add() {
		try {
			funcionarioService.cadastrar(funcionario);
			this.funcionarios = funcionarioService.buscarTodos();
			this.funcionario = new Funcionario();
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Funcionario cadastrado com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
		}
	}

	/**
	 * Atualiza um funcionario existente no banco de dados
	 */
	public void update() {
		try {
			funcionarioService.alterar(this.funcionario);
			this.funcionarios = funcionarioService.buscarTodos();
			cancel();
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Funcionario atualizado com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao tentar atualizar o funcionario"));
		}
	}

	/**
	 * Retorna os valores do enum Cargo para popular o selectOneMenu na pagina
	 */
	public Cargo[] getCargos() {
		return Cargo.values();
	}

	public String voltarTelaInicial() {
		return "/index.xhtml";
	}

	// Getters e Setters

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Collection<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(Collection<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
}
