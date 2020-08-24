package modelTest.Actuator;

import Model.Actuator.SMTPMailSenderActuator;
import Model.Game.GameSale;
import org.junit.Test;

import javax.mail.SendFailedException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class SMTPMailSenderActuatorTest {

    @Test
    public void check_Mail_Is_Sent_When_Mail_Exists() throws SendFailedException {
        /*Configure DOC*/
        //Mock
        GameSale game1 = mock(GameSale.class);
        GameSale game2 = mock(GameSale.class);
        //Stub

        /*Configure SUT*/
        SMTPMailSenderActuator mailSenderActuator = new SMTPMailSenderActuator("test@test.com");
        List<GameSale> gameSaleList = new ArrayList<>();
        gameSaleList.add(game1);
        gameSaleList.add(game2);
        /*Exec*/

        mailSenderActuator.actuate(gameSaleList);

        /*Verify*/

        /*Finalize*/
    }

    @Test(expected = SendFailedException.class)
    public void check_Mail_Is_Not_Sent_When_Mail_Not_Exists() throws SendFailedException {
        /*Configure DOC*/
        //Mock
        GameSale game1 = mock(GameSale.class);
        GameSale game2 = mock(GameSale.class);
        //Stub

        /*Configure SUT*/
        SMTPMailSenderActuator mailSenderActuator = new SMTPMailSenderActuator("FalseEmail");
        List<GameSale> gameSaleList = new ArrayList<>();
        gameSaleList.add(game1);
        gameSaleList.add(game2);
        /*Exec*/

        mailSenderActuator.actuate(gameSaleList);

        /*Verify*/

        /*Finalize*/
    }
}
