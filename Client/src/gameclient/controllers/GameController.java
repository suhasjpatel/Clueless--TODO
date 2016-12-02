package gameclient.controllers;

import enums.Card;
import enums.Room;
import enums.Suspect;
import enums.Weapon;
import gameclient.Client;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import players.Player;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller on the client-side to control the gameView screen
 *
 * Created by Suhas on 11/20/2016.
 */
public class GameController implements Initializable {

    @FXML
    private VBox suspectVBox;

    @FXML
    private VBox weaponVBox;

    @FXML
    private VBox roomVBox;

    @FXML
    private VBox cardsVBox;

    @FXML
    private VBox seenCardsVBox;

    @FXML
    private RadioButton suggestRadio;

    @FXML
    private RadioButton accuseRadio;

    @FXML
    private TextArea messageBox;

    @FXML
    private Label playerName;

    @FXML
    private Label characterName;

    @FXML
    private Label currentLocationLabel;

    @FXML
    private ComboBox<String> movementComboBox;

    @FXML
    private Button moveButton;

    @FXML
    private Button confirmButton;

    @FXML
    private Button endTurnButton;

    private ToggleGroup suspectGroup = new ToggleGroup();
    private ToggleGroup roomGroup = new ToggleGroup();
    private ToggleGroup weaponGroup = new ToggleGroup();
    private ToggleGroup suggestOrAccuse = new ToggleGroup();
    private boolean suggestMode = true;
    private BooleanProperty turnProperty = new SimpleBooleanProperty(Client.getPlayer().isTurn());
    private boolean firstUpdate = true;

    @FXML
    protected void endTurnAction() {
        if (Client.getPlayer().isTurn()) {
            Client.getPlayer().setHasSuggested(false);
            Client.getPlayer().setHasMoved(false);
            firstUpdate = true;
            stringHelper(playerNameHelper() + "has ended his/her turn.\n");
            updateOptions();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Client.endTurn();
        }
    }

    /**
     * Depending on which radio button is selected when the confirm button is pressed, performs either the Suggest
     * or the Accuse action.
     */
    @FXML
    protected void confirmAction() {
        if (suggestOrAccuse.getSelectedToggle().equals(suggestRadio) && Client.getPlayer().isTurn()) {
            //Check if everything is not null, and check the suggestion
            if (suspectGroup.getSelectedToggle() != null && weaponGroup.getSelectedToggle() != null && roomGroup.getSelectedToggle() != null) {
                Client.checkSuggestion(((RadioButton) suspectGroup.getSelectedToggle()).getText(), ((RadioButton) weaponGroup.getSelectedToggle()).getText(), ((RadioButton) roomGroup.getSelectedToggle()).getText());
                Client.getPlayer().setHasSuggested(true);
                stringHelper(playerNameHelper() + "has made a suggestion.\n");
                if (!Client.getPlayer().getCharacter().toString().equals(((RadioButton) suspectGroup.getSelectedToggle()).getText())) {
                    String s = "";
                    for (Player p : Client.getGame().getPlayers()) {
                        if (p.getCharacter().toCard().toString().equals(((RadioButton) suspectGroup.getSelectedToggle()).getText())) {
                            s = p.getName() + " (" + p.getCharacter().toCard().toString() + ") ";
                            stringHelper(playerNameHelper() + "has moved player " + s + ".\n");
                        }
                    }

                    if (s.isEmpty()) {
                        stringHelper(playerNameHelper() + "has moved npc " + ((RadioButton) suspectGroup.getSelectedToggle()).getText() + ".\n");
                    }
                }
                updateOptions();
            } else {
                String s = messageBox.getStyle();
                messageBox.setStyle("-fx-text-fill: red;");
                messageBox.appendText("Please make sure you select a suspect, a weapon, and a room.\n");
                messageBox.setStyle(s);
            }

        } else if (suggestOrAccuse.getSelectedToggle().equals(accuseRadio) && Client.getPlayer().isTurn()) {
            if (suspectGroup.getSelectedToggle() != null && weaponGroup.getSelectedToggle() != null && roomGroup.getSelectedToggle() != null) {
                boolean result = Client.checkAccusation(((RadioButton) suspectGroup.getSelectedToggle()).getText(), ((RadioButton) weaponGroup.getSelectedToggle()).getText(), ((RadioButton) roomGroup.getSelectedToggle()).getText());
                if (!result) {
                    Client.getPlayer().setHasFalselyAccused(true);
                    stringHelper(playerNameHelper() + "has made a false accusation.\n");
                    updateOptions();
                }
            } else {
                String s = messageBox.getStyle();
                messageBox.setStyle("-fx-text-fill: red;");
                messageBox.appendText("Please make sure you select a suspect, a weapon, and a room.\n");
                messageBox.setStyle(s);
            }
        }
    }

    /**
     * Move the player to the value listed in the movementComboBox when the move button is pressed.
     * Update the GUI menus to reflect new location.
     */
    @FXML
    protected void moveAction() {
        if (movementComboBox.getSelectionModel().getSelectedItem() != null && Client.getPlayer().isTurn()) {
            String s = movementComboBox.getSelectionModel().getSelectedItem();
            Client.getPlayer().getLocation().getPlayers().remove(Client.getPlayer());
            Client.getPlayer().setLocation(Client.getGame().getMap().nodeFromString(s));
            Client.getPlayer().getLocation().getPlayers().add(Client.getPlayer());
            Client.getPlayer().setHasMoved(true);
            stringHelper(playerNameHelper() + "has moved to " + s + ".\n");
            updateOptions();
        }
    }

    /**
     * General update all function. Updates everything on the main GUI, from player locations, to notebook information
     */
    @FXML
    private void updateOptions() {
        //Get information from the player notebook
        ObservableList<Suspect> suspects = FXCollections.observableArrayList(Client.getPlayer().getNotebook().getPossibleSuspects());
        ObservableList<Room> rooms = FXCollections.observableArrayList(Client.getPlayer().getNotebook().getPossibleRooms());
        ObservableList<Weapon> weapons = FXCollections.observableArrayList(Client.getPlayer().getNotebook().getPossibleWeapons());

        //Wipe all gameclient.views to prevent double writes when new entries are added
        suspectVBox.getChildren().clear();
        roomVBox.getChildren().clear();
        weaponVBox.getChildren().clear();
        seenCardsVBox.getChildren().clear();

        //Set up toggle groups
        suspectGroup = new ToggleGroup();
        roomGroup = new ToggleGroup();
        weaponGroup = new ToggleGroup();

        //For all suspects in the notebook, set the color to Green if player has the card, Red if the player has seen
        //the card. Disable all suspect selection if player is in a hallway in suggestMode. Player is allowed to accuse
        //in hallways.
        for (Suspect s : suspects) {
            RadioButton button = new RadioButton(s.toCard().toString());
            button.setToggleGroup(suspectGroup);
            if (suggestMode && !Client.getPlayer().getLocation().isRoom()) {
                button.setDisable(true);
            }
            if (Client.getPlayer().getCards().contains(s.toCard())) {
                button.setTextFill(Color.GREEN);
            }
            if (Client.getPlayer().getNotebook().getSeenCards().contains(s.toCard())) {
                button.setTextFill(Color.RED);
            }
            suspectVBox.getChildren().add(button);
        }

        //For all rooms in the notebook, set the color to Green if player has the card, Red if the player has seen
        //the card. Only allow a player to select the room they are in if they are suggesting. Player is allowed to
        //accuse in hallways and rooms they are not in.
        for (Room r : rooms) {
            RadioButton button = new RadioButton(r.toCard().toString());
            button.setToggleGroup(roomGroup);
            if (suggestMode) {
                if (Client.getPlayer().getLocation().isRoom()) {
                    if (!Client.getPlayer().getLocation().getRoom().equals(r)) {
                        button.setDisable(true);
                    }
                } else {
                    button.setDisable(true);
                }
            }
            if (Client.getPlayer().getCards().contains(r.toCard())) {
                button.setTextFill(Color.GREEN);
            }
            if (Client.getPlayer().getNotebook().getSeenCards().contains(r.toCard())) {
                button.setTextFill(Color.RED);
            }
            roomVBox.getChildren().add(button);
        }

        //For all weapons in the notebook, set the color to Green if player has the card, Red if the player has seen
        //the card. Disable all weapon selection if player is in a hallway. Player is allowed to accuse in hallways.
        for (Weapon w : weapons) {
            RadioButton button = new RadioButton(w.toCard().toString());
            button.setToggleGroup(weaponGroup);
            if (suggestMode && !Client.getPlayer().getLocation().isRoom()) {
                button.setDisable(true);
            }
            if (Client.getPlayer().getCards().contains(w.toCard())) {
                button.setTextFill(Color.GREEN);
            }
            if (Client.getPlayer().getNotebook().getSeenCards().contains(w.toCard())) {
                button.setTextFill(Color.RED);
            }
            weaponVBox.getChildren().add(button);
        }

        //Update player's location label
        currentLocationLabel.setText(Client.getPlayer().getLocation().getName());

        //Update player's allowable movable locations
        ObservableList<String> movableLocations = FXCollections.observableArrayList(Client.getGame().getMap().availableMovesFromNode(Client.getPlayer().getLocation()));
        movementComboBox.setItems(movableLocations);

        //Update list of cards player has seen
        ObservableList<Card> seenCards = FXCollections.observableArrayList(Client.getPlayer().getNotebook().getSeenCards());
        for (Card c : seenCards) {
            seenCardsVBox.getChildren().add(new Label(c.toString()));
        }

        moveButton.setDisable(!Client.getPlayer().isTurn() || Client.getPlayer().isHasMoved());
        confirmButton.setDisable(!Client.getPlayer().isTurn());
        endTurnButton.setDisable(!Client.getPlayer().isTurn());

        suggestRadio.setDisable(Client.getPlayer().isHasSuggested());

        stringHelper("");
    }

    /**
     * Setup initial GUI state.
     *
     * @param location  Not used.
     * @param resources Not used.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Update player and character name information
        playerName.setText(Client.getPlayer().getName());
        characterName.setText(Client.getPlayer().getCharacter().toCard().toString());

        //Update the players cards
        for (Card c : Client.getPlayer().getCards()) {
            Label l = new Label(c.toString());
            cardsVBox.getChildren().add(l);
        }

        //Setup the suggest/accuse radio button toggle groups
        suggestRadio.setToggleGroup(suggestOrAccuse);
        accuseRadio.setToggleGroup(suggestOrAccuse);

        //Add a listener to switch between suggest and accuse modes.
        suggestOrAccuse.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(accuseRadio)) {
                suggestMode = false;
                updateOptions();
            } else if (newValue.equals(suggestRadio)) {
                suggestMode = true;
                updateOptions();
            }
        });

        //Set the default toggle to suggest
        turnProperty.addListener((observable, oldValue, newValue) -> updateOptions());

        Timeline secondUpdate = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (firstUpdate && Client.getPlayer().isTurn()) {
                if (!Client.getPlayer().isHasFalselyAccused()) {
                    updateOptions();
                } else {
                    endTurnAction();
                }
                firstUpdate = false;
            }
        }));
        secondUpdate.setCycleCount(Timeline.INDEFINITE);
        secondUpdate.play();

        suggestOrAccuse.selectToggle(suggestRadio);

        updateOptions();
    }

    private void stringHelper(String s) {
        messageBox.clear();
        Client.getGame().setMessage(Client.getGame().getMessage() + s);
        messageBox.setText(Client.getGame().getMessage());
        messageBox.positionCaret(Client.getGame().getMessage().length());
    }

    private String playerNameHelper() {
        return Client.getPlayer().getName() + " (" + Client.getPlayer().getCharacter().toCard().toString() + ") ";
    }
}


