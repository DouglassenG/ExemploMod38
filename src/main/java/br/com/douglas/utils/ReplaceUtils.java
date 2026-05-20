package br.com.douglas.utils;

/**
 * @author douglas
 *
 * Utilitario para remover caracteres invalidos de strings formatadas (ex: mascara de CPF, telefone)
 */
public class ReplaceUtils {

	public static String replace(String value, String... patterns) {
		String retorno = value;
		for (String pattern : patterns) {
			retorno = retorno.replace(pattern, "");
		}
		return retorno;
	}
}
