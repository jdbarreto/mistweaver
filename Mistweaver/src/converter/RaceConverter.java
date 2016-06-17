package converter;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Race;
import service.RaceService;


@Named
@ApplicationScoped
@FacesConverter(value = "raceConverter")
public class RaceConverter implements Converter {

	@Inject
	private RaceService raceService;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {

		if (value == null || value.isEmpty()) {
			return null;
		}
		try {
			Object obj = raceService.findById(Integer.valueOf(value));
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		if (!(value instanceof Race)) {
			return null;
		}
		String s = String.valueOf(((Race)value).getId());
		return s;
	}

}