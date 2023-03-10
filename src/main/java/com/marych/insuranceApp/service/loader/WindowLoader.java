package com.marych.insuranceApp.service.loader;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class WindowLoader {
    private ActionEvent event;

    public WindowLoader(ActionEvent event) {
        this.event = event;
    }

    public void load(URL url) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(url));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
