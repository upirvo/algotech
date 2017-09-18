package pl.upir.algotechtest.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.validator.ValidatorException;

/**
 * Created by Upir on 2017-09-17.
 * Send message util
 */
public class SendMessage {

    public SendMessage() {
    }

    public static void send(String message,String messageComponent,String formComponent, FacesMessage.Severity severity ){
        FacesMessage msg = new FacesMessage(severity, message,"");
        FacesContext.getCurrentInstance().addMessage(formComponent, msg);

        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds()
                .add(messageComponent);

    }

    public static  void sendErrorMessage(String message,String toMessageComponent, String fromComponent){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message,"");
        FacesContext fc = FacesContext.getCurrentInstance();
        Flash flash = fc.getExternalContext().getFlash();
        flash.setKeepMessages(true);
        fc.addMessage(fromComponent, msg);

        fc.getPartialViewContext().getRenderIds()
                .add(toMessageComponent);
        fc.renderResponse();
    }
}
