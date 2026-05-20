package br.com.douglas.domain;

/**
 * @author douglas
 *
 * Enum que representa os cargos disponiveis para um funcionario
 */
public enum Cargo {

	DESENVOLVEDOR,
	ANALISTA,
	GERENTE,
	DIRETOR,
	ESTAGIARIO;

	public static Cargo getByName(String value) {
		for (Cargo cargo : Cargo.values()) {
			if (cargo.name().equals(value)) {
				return cargo;
			}
		}
		return null;
	}
}
