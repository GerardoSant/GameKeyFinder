package Model.Action;

import Model.Actuator.Actuator;

import javax.mail.SendFailedException;

public class Action {

    protected Object threshold; //Pasarle el objecto al actuator que necesita para ejecutarse : por ejemplo
    //muevete 10 metros o algo así. Creo que sería mejor nombre actuatorParam
    protected Actuator actuator;

    public Action(Object threshold, Actuator actuator) {
        this.threshold = threshold;
        this.actuator = actuator;
    }

    public void actuate(){
        try {
            this.actuator.actuate(this.threshold);
        } catch (SendFailedException e) {
            e.printStackTrace();
        }
    }
    
}



