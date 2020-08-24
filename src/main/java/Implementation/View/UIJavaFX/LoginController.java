package Implementation.View.UIJavaFX;


import Control.Control;
import Implementation.View.Persistence.FileAccountListLoader;
import Model.Account;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LoginController {

    private Account account;
    private Control control;

    public LoginController(Account account, Control control) {
        this.account = account;
        this.control=control;
    }


    @FXML
    private Text failText;

    @FXML
    private Button loginbutton;

    @FXML
    private PasswordField passwordField;


    @FXML
    private TextField usernameField;


    @FXML
    void register(MouseEvent event) {
        try {

            URL url = new File("src/main/java/Implementation/View/UIJavaFX/register.fxml").toURI().toURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            RegisterController registerController= new RegisterController(account);
            fxmlLoader.setController(registerController);
            Scene scene = new Scene((Pane)fxmlLoader.load(), 350, 275);
            Stage stage = new Stage();
            stage.setTitle("Create Account");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    };

    @FXML
    void login(MouseEvent event) {
        FileAccountListLoader loader = new FileAccountListLoader("data/accounts/accounts.txt");
        loader.load();
        if (accountExists(new Account(usernameField.getText(),passwordField.getText()))){
            try {
                this.account = new Account(usernameField.getText(),passwordField.getText());
                control.init(this.account, loader.loadEmail(account), Integer.parseInt(loader.loadHour(account)));
                Stage stageToClose = (Stage) loginbutton.getScene().getWindow();
                stageToClose.close();
                URL url = new File("src/main/java/Implementation/View/UIJavaFX/games.fxml").toURI().toURL();
                FXMLLoader fxmlLoader = new FXMLLoader(url);
                GamesController controller = new GamesController(account,control);
                fxmlLoader.setController(controller);
                Scene scene = new Scene(fxmlLoader.load(), 650, 500);
                Stage stage = new Stage();
                stage.setTitle("CheapKeyFinder");
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
        }else{
            failText.setVisible(true);
        }
    }

    private boolean accountExists(Account account){
        FileAccountListLoader loader = new FileAccountListLoader("data/accounts/accounts.txt");
        for (Account acc : loader.load()){
            if (acc.getName().equals(account.getName())) return true;
        }
        return false;
    }


}
