package converter;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import entity.CharacterClass;
import service.CharacterClassService;

@Named
@ApplicationScoped
@FacesConverter(value = "characterConverter")
public class CharacterClassConverter implements Converter {

	@Inject
	private CharacterClassService characterClassService;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {

		if (value == null || value.isEmpty()) {
			return null;
		}
		try {
			Object obj = characterClassService.findById(Integer.valueOf(value));
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		if (!(value instanceof CharacterClass)) {
			return null;
		}
		String s = String.valueOf(((CharacterClass) value).getId());
		return s;
	}

}