package Implementation.View.UIJavaFX;

import Implementation.View.Persistence.FileAccountListWriter;
import Model.Account;
import Persistence.AccountListWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class RegisterController {

    private Account account;

    public RegisterController(Account account) {
        this.account = account;
    }


    @FXML
    private Button createAccountButton;

    @FXML
    private TextField emailField;

    @FXML
    private Text incorrectData;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField scraphourTextField;


    @FXML
    void createAccount(MouseEvent event) {
        Account account = new Account(usernameField.getText(),passwordField.getText(),emailField.getText(), Integer.parseInt(scraphourTextField.getText()));
        AccountListWriter writer = new FileAccountListWriter("data/accounts/accounts.txt");
        writer.add(account);
        if(true /*account.createAccount()*/) {
            Stage stageToClose = (Stage) usernameField.getScene().getWindow();
            stageToClose.close();
            //LoginController.createdSuccess.setVisible(true);
        }else{
            incorrectData.setVisible(true);
        }

    }

}
