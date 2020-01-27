package chat.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    TextArea textArea;

    @FXML
    TextField msgField, loginField;

    @FXML
    PasswordField passField;

    @FXML
    HBox loginBox;

    private Network network;
    private boolean authenticated;
    private String nickname;

    public void setAuthenticated(boolean authenticated){
        this.authenticated = authenticated;
        loginBox.setVisible(!authenticated);
        loginBox.setManaged(!authenticated);
        msgField.setVisible(authenticated);
        msgField.setManaged(authenticated);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setAuthenticated(false);
            network = new Network(8189);
            Thread thread = new Thread(() -> {
                try {
                    while (true){
                        String message = network.readMessage();
                        if(message.startsWith("/authok ")){
                            nickname = message.split(" ")[1];
                            setAuthenticated(true);
                            break;
                        }
                        textArea.appendText(message + "\n");
                    }
                    while (true){
                        String message = network.readMessage();
                        if(message.equals("/end_confirm")){
                            textArea.appendText("Connection with server ended");
                            break;
                        }
                        textArea.appendText(message + "\n");
                    }
                } catch (IOException e) {
                    Platform.runLater(()-> {
                        Alert alert = new Alert(
                                Alert.AlertType.WARNING,
                                "Connection with server lost",
                                ButtonType.CLOSE);
                        alert.showAndWait();
                    });
                } finally {
                    network.closeAll();
                    Platform.exit();
                }
            });
            thread.setDaemon(true);
            thread.start();
        } catch (IOException e) {
            throw new RuntimeException("Невозможно подключиться к серверу");
        }
    }


    public void sendMessage(ActionEvent actionEvent) {
        try {
            network.sendMessage(msgField.getText());
            msgField.clear();
            msgField.requestFocus();
        } catch (IOException e){
            Alert alert = new Alert(
                    Alert.AlertType.WARNING,
                    "Can't sent message. Check network connection",
                    ButtonType.CLOSE);
            alert.showAndWait();
        }
    }

    public void tryToAuth(ActionEvent actionEvent) {
        try {
            network.sendMessage("/auth " + loginField.getText() + " " + passField.getText());
            loginField.clear();
            passField.clear();
        } catch (IOException e){
            Alert alert = new Alert(
                    Alert.AlertType.WARNING,
                    "Can't sent message. Check network connection",
                    ButtonType.CLOSE);
            alert.showAndWait();
        }
    }
}
