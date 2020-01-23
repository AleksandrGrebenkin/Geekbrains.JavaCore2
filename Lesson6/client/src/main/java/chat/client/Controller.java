package chat.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    TextArea textArea;

    @FXML
    TextField msgField;

    private Network network;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            network = new Network(8189);
            Thread thread = new Thread(() -> {
                try {
                    while (true){
                        String message = network.readMessage();
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
                }
            });
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendMessage(ActionEvent actionEvent) {
        try {
            String message = msgField.getText();
            network.sendMessage(message);
            if(message.equals("/end")){
                network.closeAll();
            }
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
}
