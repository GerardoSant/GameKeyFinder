package Implementation.View.UIJavaFX;

import Implementation.View.Persistence.FileGameDesireListWriter;
import Model.Account;
import Model.Game.GameDesire;
import Persistence.GameDesireListWriter;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class EditGameController {
    private GameDesire toedit;
    private Account account;

    public EditGameController(Account account, GameDesire toedit){
        this.toedit = toedit;
        this.account = account;
    }


    @FXML
    private TextField desiredEditTextField;

    @FXML
    private TextField gameNameTextField;


    @FXML
    void saveEdit(MouseEvent event) {
        GameDesire edited = new GameDesire(gameNameTextField.getText(),Integer.parseInt(desiredEditTextField.getText()));
        GameDesireListWriter writer = new FileGameDesireListWriter(account);
        writer.remove(toedit);
        writer.add(edited);
        Stage stageToClose = (Stage) desiredEditTextField.getScene().getWindow();
        stageToClose.close();

    }

    @FXML
    void initialize() {
        gameNameTextField.setText(toedit.getGameName());
        desiredEditTextField.setText(Integer.toString(toedit.getDesiredPrice()));

    }

}