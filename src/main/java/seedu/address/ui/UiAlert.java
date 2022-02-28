package seedu.address.ui;

import javafx.scene.control.Alert;
import javafx.scene.layout.Region;

public class UiAlert {

    public static Alert makeConfirmationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(message);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
        return alert;
    }
}
