package modelTest.Condition;

import Model.Condition.ClockCondition;
import Model.Sensor.ClockSensor;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class ClockConditionTest {

    @Test
    public void exampleTest(){
        /*Configure DOC*/
        //Mock

        //Stub

        /*Configure SUT*/

        /*Exec*/


        /*Verify*/

        /*Finalize*/
    }
    @Test
    public void check_true_when_scrap_hour_equals_current_hour(){
        /*Configure DOC*/
        //Mock
        ClockSensor clockSensor = mock(ClockSensor.class);
        //Stub
        when(clockSensor.getValue()).thenReturn(new Date().getHours());
        /*Configure SUT*/
        ClockCondition clockCondition = new ClockCondition(new Date().getHours(),clockSensor);
        /*Exec*/
        boolean conditionCheck = clockCondition.check();
        /*Verify*/
        assertThat(conditionCheck).isEqualTo(true);
        /*Finalize*/
    }

    @Test
    public void check_false_when_scrap_hour_not_equal_current_hour(){
        /*Configure DOC*/
        //Mock
        ClockSensor clockSensor = mock(ClockSensor.class);
        //Stub
        when(clockSensor.getValue()).thenReturn(new Date().getHours());
        /*Configure SUT*/
        ClockCondition clockCondition = new ClockCondition(new Date().getHours(),clockSensor);
        /*Exec*/
        boolean conditionCheck = clockCondition.check();
        /*Verify*/
        assertThat(conditionCheck).isEqualTo(true);
        /*Finalize*/
    }
}
