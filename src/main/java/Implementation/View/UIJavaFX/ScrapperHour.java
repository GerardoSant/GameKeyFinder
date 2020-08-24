package Implementation.View.UIJavaFX;

import Control.Control;
import Implementation.View.Persistence.FileAccountListWriter;
import Model.Account;
import Persistence.AccountListWriter;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;

import static java.lang.Integer.parseInt;


public class ScrapperHour {

    private Account account;
    private Control control;

    public ScrapperHour(Account account, Control control) {
        this.account = account;
        this.control = control;
    }


    @FXML
    private TextField hourTextField;

    @FXML
    private URL location;


    @FXML
    void setScrapperHour(MouseEvent event) {
        account.setScraphour(parseInt(hourTextField.getText()));
        AccountListWriter writer = new FileAccountListWriter("data/accounts/accounts.txt");
        writer.editHour(account);
        control.setScrapHour(parseInt(hourTextField.getText()));
        Stage stageToClose = (Stage) hourTextField.getScene().getWindow();
        stageToClose.close();
    }

    @FXML
    void initialize() {
        hourTextField.setText(Integer.toString(account.getScraphour()));

    }

}
