package Model.Action;

import Model.Actuator.Actuator;
import Model.Communicator.Communicator;

import javax.mail.SendFailedException;

public class CommunicableAction extends Action {
    private final Communicator communicator;

    public CommunicableAction(Object threshold, Actuator actuator, Communicator communicator) {
        super(threshold, actuator);
        this.communicator = communicator;
    }

    @Override
    public void actuate(){
        try {
            this.actuator.actuate(communicator.get());
        } catch (SendFailedException e) {
            e.printStackTrace();
        }
    }
}
