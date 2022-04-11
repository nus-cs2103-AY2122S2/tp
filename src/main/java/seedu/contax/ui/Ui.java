package seedu.contax.ui;

import javafx.stage.Stage;

/**
 * Defines the API of the UI component.
 */
public interface Ui {

    /** Starts the UI (and the App).  */
    void start(Stage primaryStage);

    /** Indicates the first run of the App*/
    void setFirstRun();

}
