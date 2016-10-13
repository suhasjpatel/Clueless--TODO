package com.todo.clueless;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Suhas on 10/12/2016.
 */
public class Main extends Application{
    public static void main(String args[]){
        //CardDeck deck = new CardDeck();
        //deck.getCaseFile();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("resources/welcomeView.fxml"));

        Scene scene = new Scene(root, 300, 275);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }
}
