package Implementation.MailSender;

import Model.Game.GameSale;

import javax.mail.SendFailedException;
import java.util.List;

public interface MailSender {
    void send(List<GameSale> list) throws SendFailedException;
}
