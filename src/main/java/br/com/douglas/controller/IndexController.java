package br.com.douglas.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * @author douglas
 *
 * Controller da pagina inicial para redirecionar para as telas do sistema
 */
@Named
@ViewScoped
public class IndexController implements Serializable {

	private static final long serialVersionUID = -784519597996507487L;

	public String redirectFuncionario() {
		return "/funcionario/list.xhtml";
	}
}
