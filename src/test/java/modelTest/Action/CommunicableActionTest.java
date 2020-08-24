package modelTest.Action;

import Model.Action.CommunicableAction;
import Model.Actuator.Actuator;
import Model.Communicator.Communicator;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class CommunicableActionTest {

    @Test
    public void check_Actuate_Works_Properly(){
        /*Configure DOC*/
        //Mock
        Actuator actuator = mock(Actuator.class);
        Communicator communicator = mock(Communicator.class);
        //Stub

        /*Configure SUT*/
        CommunicableAction communicableAction = new CommunicableAction(null,actuator,communicator);
        /*Exec*/
        communicableAction.actuate();

        /*Verify*/

        /*Finalize*/
    }
}
