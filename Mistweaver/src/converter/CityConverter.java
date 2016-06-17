package converter;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import entity.City;
import service.CityService;

/*
 * Um custom converter JSF deve implementar a interface Converter e os
 * métodos getAsObject e getAsString
 */

@Named
@ApplicationScoped
@FacesConverter(value = "cityConverter")
public class CityConverter implements Converter {

	@Inject
	private CityService cityService;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {

		if (value == null || value.isEmpty()) {
			return null;
		}
		try {
			Object obj = cityService.findById(Integer.valueOf(value));
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		if (!(value instanceof City)) {
			return null;
		}
		String s = String.valueOf(((City) value).getId());
		return s;
	}

}