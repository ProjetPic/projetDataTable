/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Contol;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author abdelkader
 */
@FacesValidator(value="noteValidate")
public class NoteValidate implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
       String componentValue = value.toString(); 
       if(((Pattern.compile("[0-9]{1,2}")).matcher(componentValue)).find()){
           FacesMessage msg = new FacesMessage("le champ doit etre un nombree");
	   msg.setSeverity(FacesMessage.SEVERITY_ERROR);
           context.addMessage("note", msg);
       } 
    }
    
}
