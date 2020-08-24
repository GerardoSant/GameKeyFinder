package Model;

import Model.Actuator.Actuator;
import Model.Sensor.Sensor;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private List<Sensor> sensors;
    private List<Actuator> actuators;
    private List<Rule> rules;

    private String name;
    private String password;
    private String email;
    private int scraphour;

    public Account(String name, String password, String email, int scraphour){
        this.name = name;
        this.password = password;
        this.email = email;
        this.scraphour = scraphour;
        sensors = new ArrayList<>();
        actuators = new ArrayList<>();
        rules = new ArrayList<>();
    }

    public Account(String name, String password){
        this.name = name;
        this.password = password;
        sensors = new ArrayList<>();
        actuators = new ArrayList<>();
        rules = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){this.email = email;}

    public int getScraphour(){return scraphour; }

    public void setScraphour(int hour){this.scraphour = hour;}

    public List<Rule> getRules() {
        return rules;
    }

    public List<Sensor> getSensors() { return sensors; }

    public List<Actuator> getActuators() { return actuators; }

    public void addSensor(Sensor sensor){
        if(!sensors.contains(sensor)) sensors.add(sensor);
    }

    public void addActuator(Actuator actuator){
        if(!actuators.contains(actuator))actuators.add(actuator);
    }

    public void addRule(Rule rule){
        if(!rules.contains(rule))rules.add(rule);
    }


}
