package seedu.address.ui;

import java.io.InputStream;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;

/**
 * The manager of the UI component.
 */
public class UiManager implements Ui {

    public static final String ALERT_DIALOG_PANE_FIELD_ID = "alertDialogPane";
    public static final String FATAL_ERROR_MESSAGE = "Fatal error during initializing";

    private static final Logger logger = LogsCenter.getLogger(UiManager.class);
    private static final String ICON_APPLICATION = "/images/edit.png";

    private final Logic logic;
    private MainWindow mainWindow;

    /**
     * Creates a {@code UiManager} with the given {@code Logic}.
     */
    public UiManager(Logic logic) {
        this.logic = logic;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting UI...");

        //Set the application icon.
        primaryStage.getIcons().add(getIcon());

        try {
            mainWindow = new MainWindow(primaryStage, logic);
            mainWindow.show(); //This should be called before creating other UI parts
            mainWindow.fillInnerParts();

        } catch (Throwable e) {
            logger.severe(StringUtil.getDetails(e));
            showFatalErrorDialogAndShutdown(e);
        }
    }

    private Image getIcon() {
        InputStream icon = MainApp.class.getResourceAsStream(UiManager.ICON_APPLICATION);
        assert icon != null;
        return new Image(icon);
    }

    /**
     * Shows an error alert dialog with {@code title} and error message, {@code e},
     * and exits the application after the user has closed the alert dialog.
     */
    private void showFatalErrorDialogAndShutdown(Throwable e) {
        logger.severe(UiManager.FATAL_ERROR_MESSAGE
                + " " + e.getMessage() + StringUtil.getDetails(e));

        final Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(mainWindow.getPrimaryStage());
        alert.setTitle(UiManager.FATAL_ERROR_MESSAGE);
        alert.setHeaderText(e.getMessage());
        alert.setContentText(e.toString());
        alert.getDialogPane().setId(ALERT_DIALOG_PANE_FIELD_ID);
        alert.showAndWait();

        Platform.exit();
        System.exit(1);
    }

}
