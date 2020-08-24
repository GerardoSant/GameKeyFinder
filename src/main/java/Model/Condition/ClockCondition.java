package Model.Condition;


import Model.Sensor.Sensor;

public class ClockCondition extends Condition {
    
    public static final int millisecondsToHour = 3600000;
    public static final String TIME_FOR_SCRAPPING = "It's the hour for scrapping";

    public ClockCondition(Object threshold, Sensor sensor) {
        super(threshold, sensor);
    }

    @Override
    public boolean evaluate(Object leftObject, Object rightObject) {
        if (leftObject.equals(rightObject)){
            return true;
        } else {
            int aux = (int)leftObject - (int)rightObject;
            if(aux < 0) aux = 24 + aux;
            try {
                countdown(aux);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private void countdown(int aux) throws InterruptedException {
        for (int i = 0; i < aux; i++) {
            System.out.println("The scrap will be in " + (aux - i) + " Hours...");
            Thread.sleep(millisecondsToHour);
        }
        System.out.println(TIME_FOR_SCRAPPING);
    }
}
