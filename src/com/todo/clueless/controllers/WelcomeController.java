package com.todo.clueless.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WelcomeController {

    @FXML
    private Button createRoomButton;

    @FXML
    private Button joinRoomButton;

    @FXML protected void createRoomButtonAction(){
        System.out.println("Creating room.");
    }

    @FXML protected void joinRoomButtonAction(){
        System.out.println("Joining room.");
    }
}
