package modelTest.Action;

import Model.Action.Action;
import Model.Actuator.Actuator;
import org.junit.Test;

import javax.mail.SendFailedException;

import static org.assertj.core.api.Assertions.assertThat;

public class ActionTest {


    @Test
    public void actuate_calls_actuator_actuate_implementation() throws SendFailedException {
        /*Configure DOC*/

        boolean[] changedVariable=new boolean[]{false};
        Actuator fakeActuator = new Actuator() {
            @Override
            public void actuate(Object object) throws SendFailedException {
                changedVariable[0]=true;
            }
        };
        /*Configure SUT*/
        Action action = new Action(10, fakeActuator);
        /*Exec*/
        action.actuate();

        /*Verify*/
        assertThat(changedVariable[0]).isTrue();
        /*Finalize*/
    }
}
