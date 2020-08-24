package Implementation.View.UIJavaFX;


import Control.Control;
import Implementation.View.Persistence.FileAccountListLoader;
import Implementation.View.Persistence.FileGameDesireListLoader;
import Implementation.View.Persistence.FileGameDesireListWriter;
import Model.Account;
import Model.Game.GameDesire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;


public class GamesController {

    private Account account;
    private Control control;


    public GamesController(Account account, Control control) {
        this.account = account;
        this.control= control;
    }




    List<GameDesire> gameDesireList;

    @FXML
    private CheckBox turnOffRuleCheckBox;

    @FXML
    private Button updateButton;


    @FXML
    private Button notificationHourButton;


    @FXML
    private Button addButton;

    @FXML
    private TableColumn<GameDesire,String> gamesColumn;

    @FXML
    private TableView<GameDesire> gamesField;

    @FXML
    private TableColumn<GameDesire,String> priceComumn;

    @FXML
    private Button removeButton;


    @FXML
    void RemoveGame(MouseEvent event) {
        gameDesireList.remove(gamesField.getSelectionModel().getSelectedItem());
        new FileGameDesireListWriter(account).remove(gamesField.getSelectionModel().getSelectedItem());
        control.setGameDesireList(gameDesireList);
        gamesField.getItems().remove(gamesField.getSelectionModel().getSelectedIndex());



    }

    @FXML
    void addGame(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = new File("src/main/java/Implementation/View/UIJavaFX/addGame.fxml").toURI().toURL();
        Parent root = fxmlLoader.load(url);
        Scene scene = new Scene(root, 400, 400);
        Stage stage = new Stage();
        stage.setTitle("CheapKeyFinder");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void update(MouseEvent event) throws IOException {
        gameDesireList = new FileGameDesireListLoader(account).load();
        gamesField.setItems(getItems());
        getGameAndAdd();
    }

    void getGameAndAdd()  {
        if(AddGameController.games != null && AddGameController.games.size() != 0) {
            for (GameDesire game : AddGameController.games) {
                gameDesireList.add(game);
                new FileGameDesireListWriter(account).add(game);
                gamesField.getItems().add(game);
            }
            control.setGameDesireList(gameDesireList);
            AddGameController.games.clear();
            try {
                clearQueue();
            }catch(Exception e){}
        }
        control.setGameDesireList(gameDesireList);

    }

    void clearQueue() throws IOException {
        FileWriter writer = new FileWriter("data/UpdateQueue.txt",false);
        writer.write("");
        writer.close();
    }


    @FXML
    void openScrapperHour(MouseEvent event) throws IOException {
        URL url = new File("src/main/java/Implementation/View/UIJavaFX/scrapperHour.fxml").toURI().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        ScrapperHour controller = new ScrapperHour(account,this.control);
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load(), 650, 500);
        Stage stage = new Stage();
        stage.setTitle("CheapKeyFinder");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void TurnOffPushed(MouseEvent event) {
        if (turnOffRuleCheckBox.isSelected()) {
            control.deactivate();
        }else{
            control.activate();
        }
    }

    @FXML
    void editGame(MouseEvent event) throws IOException {
        if(gamesField.getSelectionModel().getSelectedItem()!=null) {
            URL url = new File("src/main/java/Implementation/View/UIJavaFX/editGame.fxml").toURI().toURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            EditGameController controller = new EditGameController(account, gamesField.getSelectionModel().getSelectedItem());
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load(), 650, 500);
            Stage stage = new Stage();
            stage.setTitle("CheapKeyFinder");
            stage.setScene(scene);
            stage.show();
        }
    }



    @FXML
    void initialize() {
        FileAccountListLoader loader = new FileAccountListLoader("data/accounts/accounts.txt");
        loader.load();
        account.setEmail(loader.loadEmail(account));
        account.setScraphour(Integer.parseInt(loader.loadHour(account)));

        gameDesireList = new FileGameDesireListLoader(account).load();
        control.initGameDesireList(gameDesireList);
        gamesColumn.setCellValueFactory(new PropertyValueFactory<>("gameName"));
        priceComumn.setCellValueFactory(new PropertyValueFactory<>("desiredPrice"));
        gamesField.setItems(getItems());

    }

    private ObservableList<GameDesire> getItems() {
        ObservableList<GameDesire> gameList = FXCollections.observableArrayList(gameDesireList);
        return gameList;
    }

}
