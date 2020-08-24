package Model.Actuator;

import javax.mail.SendFailedException;

public interface Actuator {

    void actuate(Object object) throws SendFailedException;
}
