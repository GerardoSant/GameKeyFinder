package modelTest.Account;

import Model.Account;
import Model.Actuator.Actuator;
import Model.Rule;
import Model.Sensor.Sensor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class AccountTest {
    @Test
    public void check_if_addSensor_adds_sensors(){
        /*Configure DOC*/
        //Mock
        Sensor sensor = mock(Sensor.class);
        List<Sensor> sensorList = new ArrayList<>();
        sensorList.add(sensor);
        /*Configure SUT*/
        Account account = new Account("test","test","test@gmail.com",12);
        /*Exec*/
        account.addSensor(sensor);
        /*Verify*/
        assertThat(account.getSensors()).isEqualTo(sensorList);
        /*Finalize*/
    }

    @Test
    public void check_if_addActuator_adds_actuators(){
        /*Configure DOC*/
        //Mock
        Actuator actuator = mock(Actuator.class);
        List<Actuator> actuatorList = new ArrayList<>();
        actuatorList.add(actuator);
        /*Configure SUT*/
        Account account = new Account("test","test","test@gmail.com",12);
        /*Exec*/
        account.addActuator(actuator);
        /*Verify*/
        assertThat(account.getActuators()).isEqualTo(actuatorList);
        /*Finalize*/
    }

    @Test
    public void check_if_addRule_adds_rules(){
        /*Configure DOC*/
        //Mock
        Rule rule = mock(Rule.class);
        List<Rule> ruleList = new ArrayList<>();
        ruleList.add(rule);
        /*Configure SUT*/
        Account account = new Account("test","test","test@gmail.com",12);
        /*Exec*/
        account.addRule(rule);
        /*Verify*/
        assertThat(account.getRules()).isEqualTo(ruleList);
        /*Finalize*/
    }

}
