package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import util.CpfUtil;

@FacesValidator("cpfValidator")
public class CpfValidator implements Validator {

	@Override
	public void validate(FacesContext context, 
			UIComponent component, 
			Object value) {
        String nCpf = String.valueOf(value);
        CpfUtil.cpfComZeros(nCpf);
        if (!CpfUtil.validaCpf(nCpf)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
	    			"CPF inválido!",  null);
            throw new ValidatorException(message);
       }
		
	}
}
