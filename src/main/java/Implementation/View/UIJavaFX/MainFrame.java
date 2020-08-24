package Implementation.View.UIJavaFX;


import Control.Control;
import Model.Account;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class MainFrame extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("CheapKeyFinder");
        URL url = new File("src/main/java/Implementation/View/UIJavaFX/Login.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        LoginController loginController = new LoginController(new Account("Paco","xd","email@email.com",14),
                new Control(null));;
        loader.setController(loginController);
        Scene scene = new Scene((Pane)loader.load(),350,275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
