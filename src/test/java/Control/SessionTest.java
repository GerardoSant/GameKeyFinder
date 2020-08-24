package Control;

import Model.Account;
import Model.Action.Action;
import Model.Action.CommunicableAction;
import Model.Actuator.SMTPMailSenderActuator;
import Model.Communicator.Communicator;
import Model.Condition.ClockCondition;
import Model.Condition.Condition;
import Model.Condition.ScrapperCommunicableCondition;
import Model.Game.GameDesire;
import Model.Rule;
import Model.Sensor.ClockSensor;
import Model.Sensor.ScrapperSensor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class SessionTest {

    @Test
    public void check_Session_Is_Running(){
        /*Configure DOC*/
        //Mock
        Account account = mock(Account.class);
        GameDesire game1 = mock(GameDesire.class);
        GameDesire game2 = mock(GameDesire.class);

        //Sensors and Actuator
        ClockSensor clockSensor = mock(ClockSensor.class);
        ScrapperSensor ScrapperSensor = mock(Model.Sensor.ScrapperSensor.class);
        SMTPMailSenderActuator mailSenderActuator = mock(SMTPMailSenderActuator.class);

        //Communicator, Action and Conditions
        Communicator communicator = mock(Communicator.class);
        ScrapperCommunicableCondition scrapperCondition = mock(ScrapperCommunicableCondition.class);;
        Condition clockCondition = mock(ClockCondition.class);
        CommunicableAction action = mock(CommunicableAction.class);

        //Rule and lists for Rule
        List<Condition> conditionList = new ArrayList<>();
        conditionList.add(clockCondition);
        conditionList.add(scrapperCondition);
        List<Action> actionList = new ArrayList<>();
        actionList.add(action);
        Rule mainRule = mock(Rule.class);
        //Stub

        /*Configure SUT*/
        List<GameDesire> gameDesireList = new ArrayList<>();
        gameDesireList.add(game1);
        gameDesireList.add(game2);
        Session session = new Session(gameDesireList,10,"test",account);

        /*Exec*/
        session.start();

        /*Verify*/
        assertThat(session.isAlive()).isEqualTo(true);

        /*Finalize*/
        session.stop();
    }


}
