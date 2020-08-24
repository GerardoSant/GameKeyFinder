package Model.Actuator;

import Implementation.MailSender.SMTPMailSender;
import Model.Game.GameSale;

import javax.mail.SendFailedException;
import java.util.List;

public class SMTPMailSenderActuator extends SMTPMailSender implements Actuator {

    public SMTPMailSenderActuator(String mailTo) {
        super(mailTo);
    }

    @Override
    public void actuate(Object object) throws SendFailedException {
        super.send((List<GameSale>) object);
    }

    public String getMailTo(){
        return super.getMailTo();
    }

}
