package modelTest.Sensor;

import Model.Sensor.ClockSensor;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class ClockSensorTest {

    @Test
    public void check_ClockSensor_Sends_Correct_Hour(){
        /*Configure DOC*/
        //Mock

        //Stub

        /*Configure SUT*/
        ClockSensor clockSensor = new ClockSensor();
        /*Exec*/

        /*Verify*/
        assertThat(clockSensor.getValue()).isEqualTo(new Date().getHours());

        /*Finalize*/
    }
}
