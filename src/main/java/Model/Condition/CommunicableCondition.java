package Model.Condition;

import Model.Communicator.Communicator;
import Model.Sensor.Sensor;

public abstract class CommunicableCondition extends Condition {

    Communicator communicator;

    public CommunicableCondition(Object treshold, Sensor sensor, Communicator communicator) {
        super(treshold, sensor);
        this.communicator = communicator;
    }

    @Override
    public abstract boolean evaluate(Object leftObject, Object rightObject);
}
