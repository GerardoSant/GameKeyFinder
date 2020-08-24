package implementationTests.MailSender;

import Implementation.MailSender.SMTPMailSender;
import Model.Game.GameSale;
import org.junit.Test;

import javax.mail.SendFailedException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class SMTPMailSenderTest {

    @Test
    public void check_Mail_Is_Sent_When_Mail_Exists() throws SendFailedException {
        /*Configure DOC*/
        //Mock
        GameSale game1 = mock(GameSale.class);
        GameSale game2 = mock(GameSale.class);
        //Stub

        /*Configure SUT*/
        SMTPMailSender mailSender = new SMTPMailSender("test@test.com");
        List<GameSale> gameSaleList = new ArrayList<>();
        gameSaleList.add(game1);
        gameSaleList.add(game2);
        /*Exec*/

        mailSender.send(gameSaleList);

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
        SMTPMailSender mailSender = new SMTPMailSender("FalseEmail");
        List<GameSale> gameSaleList = new ArrayList<>();
        gameSaleList.add(game1);
        gameSaleList.add(game2);
        /*Exec*/

        mailSender.send(gameSaleList);

        /*Verify*/

        /*Finalize*/
    }
}
