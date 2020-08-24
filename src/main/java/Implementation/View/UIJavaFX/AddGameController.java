package Implementation.View.UIJavaFX;

import Model.Game.GameDesire;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class AddGameController {

    public static List<GameDesire> games;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;


    @FXML
    void addGame(MouseEvent event) throws IOException {
        games.add(new GameDesire(nameField.getText(),Integer.parseInt(priceField.getText())));
        FileWriter writer = new FileWriter("data/UpdateQueue.txt",true); //the true will append the new data
        writer.write(nameField.getText()+","+priceField.getText());//appends the string to the file
        writer.write("\n");
        writer.close();
        Stage stageToClose = (Stage) nameField.getScene().getWindow();
        stageToClose.close();
    }


    @FXML
    void initialize() throws IOException {
        games = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("data/UpdateQueue.txt")));
        while (true){
            String line = reader.readLine();
            if (line == null) break;
            String[] parts = line.split(",");
            games.add(new GameDesire(parts[0],Integer.parseInt(parts[1])));
        }
    }




}
