package br.com.douglas.converter;

import java.util.HashMap;
import java.util.Map;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Named;

import br.com.douglas.domain.Funcionario;

/**
 * @author douglas
 *
 * Converter JSF para a entidade Funcionario.
 * Necessario para que o PrimeFaces consiga converter entre objetos Java e Strings
 * nas operacoes de autocomplete e selectOneMenu.
 */
@Named
@FacesConverter(value = "funcionarioConverter", forClass = Funcionario.class)
public class FuncionarioConverter implements Converter {

	private static final String key = "br.com.douglas.converter.FuncionarioConverter";

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		return getViewMap(context).get(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object funcionario) {
		if (funcionario == null) {
			return "";
		}
		String id = ((Funcionario) funcionario).getId().toString();
		getViewMap(context).put(id, funcionario);
		return id;
	}

	private Map<String, Object> getViewMap(FacesContext context) {
		Map<String, Object> viewMap = context.getViewRoot().getViewMap();
		@SuppressWarnings({"unchecked", "rawtypes"})
		Map<String, Object> idMap = (Map) viewMap.get(key);
		if (idMap == null) {
			idMap = new HashMap<>();
			viewMap.put(key, idMap);
		}
		return idMap;
	}
}
