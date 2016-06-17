package converter;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
/*
 * Um custom converter JSF deve implementar a interface Converter e os
 * métodos getAsObject e getAsString
 */

import entity.State;
import service.StateService;

@Named
@ApplicationScoped
@FacesConverter(value = "stateConverter")
public class StateConverter implements Converter {

	@Inject
	private StateService stateService;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {

		if (value == null || value.isEmpty()) {
			return null;
		}
		try {
			Object obj = stateService.findById(Integer.valueOf(value));
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		if (!(value instanceof State)) {
			return null;
		}
		String s = String.valueOf(((State) value).getId());
		return s;
	}

}