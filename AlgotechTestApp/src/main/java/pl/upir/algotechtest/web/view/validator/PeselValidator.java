package pl.upir.algotechtest.web.view.validator;

import org.primefaces.validate.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import pl.upir.algotechtest.utils.SendMessage;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Upir on 2017-05-08.
 */

@ManagedBean
@RequestScoped
@FacesValidator("peselValidator")
public class PeselValidator implements Validator, ClientValidator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if ((o instanceof Long) && o.toString().length() == 11) {
            if (!validatePesel(o.toString())) {
           /* FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Nie poprawny pesel","Nie poprawny pesel");
            FacesContext.getCurrentInstance().addMessage("createUserForm:pesel", msg);*/
                SendMessage.send("Nie poprawny pesel", "globalMessage", null, FacesMessage.SEVERITY_WARN);
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nie poprawny pesel", ""));
            }
        }else {
            SendMessage.send("Nie poprawny pesel", "globalMessage", null, FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nie poprawny pesel", ""));
        }
    }

    private boolean validatePesel(String pesel) {
        int[] wagi = { 1, 3, 7, 9, 1, 3, 7, 9, 1, 3 };

        int suma = 0;

        for (int i = 0; i < 10; i++)
            suma += Integer.parseInt(pesel.substring(i, i + 1)) * wagi[i];
        int  cyfraKontrolna = Integer.parseInt(pesel.substring(10, 11));

        suma %= 10;
        suma = 10 - suma;
        suma %= 10;

        return (suma == cyfraKontrolna);
    }


    @Override
    public Map<String, Object> getMetadata() {
        return null;
    }

    @Override
    public String getValidatorId() {
        return "peselValidator";
    }
}

