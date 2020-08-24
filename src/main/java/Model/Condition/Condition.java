package Model.Condition;


import Model.Sensor.Sensor;

public abstract class Condition {


    private Object threshold;
    private Sensor sensor;

    public Condition(Object threshold, Sensor sensor) {
        this.threshold = threshold;
        this.sensor = sensor;
    }



    public boolean check() {
        return this.evaluate(this.threshold,this.sensor.getValue());
    }

    public abstract boolean evaluate(Object leftObject, Object rightObject);

    public void setThreshold(Object threshold) {
        this.threshold = threshold;
    }
}
