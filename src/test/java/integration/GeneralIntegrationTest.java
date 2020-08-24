package integration;

import Model.Account;
import Model.Action.Action;
import Model.Action.CommunicableAction;
import Model.Actuator.SMTPMailSenderActuator;
import Model.Communicator.Communicator;
import Model.Communicator.GameCommunicator;
import Model.Condition.ClockCondition;
import Model.Condition.Condition;
import Model.Condition.ScrapperCommunicableCondition;
import Model.Game.GameDesire;
import Model.Rule;
import Model.Sensor.ClockSensor;
import Model.Sensor.ScrapperSensor;
import Model.Sensor.Sensor;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class GeneralIntegrationTest {

    private Account account;
    private Rule rule;
    private SMTPMailSenderActuator mailSenderActuator;
    private List<Action> actionList;
    private List<Condition> conditionList;
    private Sensor scrapperSensor;
    private Sensor clockSensor;

    @Before
    public void init(){
        List<GameDesire> gameDesiresList = new ArrayList<>();
        gameDesiresList.add(new GameDesire("Left 4 Dead 2",15));
        gameDesiresList.add(new GameDesire("FIFA 20",40));
        account = new Account("Test","test","gbuenclima@gmail.com", new Date().getHours());


        scrapperSensor = new ScrapperSensor(gameDesiresList);
        clockSensor = new ClockSensor();
        mailSenderActuator = new SMTPMailSenderActuator(account.getEmail());

        Communicator communicator = new GameCommunicator();
        Condition scrapperCondition = new ScrapperCommunicableCondition(gameDesiresList,scrapperSensor,communicator);
        Condition clockCondition = new ClockCondition(account.getScraphour(),clockSensor);
        Action action = new CommunicableAction(null,mailSenderActuator,communicator);


        conditionList = new ArrayList<>();
        conditionList.add(clockCondition);
        conditionList.add(scrapperCondition);
        actionList = new ArrayList<>();
        actionList.add(action);


        rule= new Rule("IFTTT", true, conditionList, actionList);
        account.addRule(rule);
        account.addActuator(mailSenderActuator);
        account.addSensor(clockSensor);
        account.addSensor(scrapperSensor);

    }
    @Test
    public void rule_execute_works_properly(){
        boolean conditionsCheck= rule.isTrueAllConditions();
        AssertionsForClassTypes.assertThat(conditionsCheck).isTrue();
        rule.actAllAction();
    }


    @Test
    public void account_is_working_properly(){
        AssertionsForClassTypes.assertThat(account.getScraphour()).isEqualTo(new Date().getHours());
        AssertionsForClassTypes.assertThat(account.getName()).isEqualTo("Test");
        AssertionsForClassTypes.assertThat(account.getPassword()).isEqualTo("test");
        AssertionsForClassTypes.assertThat(account.getEmail()).isEqualTo("gbuenclima@gmail.com");
        AssertionsForClassTypes.assertThat(account.getRules().get(0)).isEqualTo(rule);
        AssertionsForClassTypes.assertThat(account.getActuators().get(0)).isEqualTo(mailSenderActuator);
        AssertionsForClassTypes.assertThat(account.getSensors().get(0)).isEqualTo(clockSensor);
        AssertionsForClassTypes.assertThat(account.getSensors().get(1)).isEqualTo(scrapperSensor);
    }
    @Test
    public void rule_is_working_properly(){
        AssertionsForClassTypes.assertThat(rule.getName()).isEqualTo("IFTTT");
        assertThat(rule.getActions()).isEqualTo(actionList);
        assertThat(rule.getConditions()).isEqualTo(conditionList);
    }
    @Test
    public void mailActuator_has_correct_email(){
        AssertionsForClassTypes.assertThat(mailSenderActuator.getMailTo()).isEqualTo("gbuenclima@gmail.com");
    }
}
