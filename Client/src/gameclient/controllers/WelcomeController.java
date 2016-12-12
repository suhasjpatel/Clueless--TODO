package gameclient.controllers;

import gameclient.Client;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import players.Player;

import java.io.IOException;

import static java.lang.System.exit;

public class WelcomeController {

    @FXML
    private TextField name;

    @FXML
    protected void joinButtonAction(ActionEvent event) {
        Client.createPlayerAndSession(new Player(name.getText()));

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gameclient/views/gameView.fxml"));
        try {
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setHeight(725);
            stage.setWidth(800);
            stage.setResizable(false);
            stage.setOnCloseRequest(event2 -> {
                Platform.exit();
                exit(0);
            });
            stage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
