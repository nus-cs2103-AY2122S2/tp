package unibook.ui;

import javafx.stage.Stage;

/**
 * API of UI component
 */
public interface Ui {

    /**
     * Starts the UI (and the App).
     */
    void start(Stage primaryStage);

    /**
     * Display the list of Modules instead of People.
     */
    void seeModuleList();

    /**
     * Display the list of Persons instead of Modules.
     */
    void seePersonList();

    /**
     * Check if module list showing in gui.
     *
     * @return boolean variable indicating if module list being shown
     */
    boolean isModuleListShowing();

    /**
     * Check if person list showing in gui.
     *
     * @return boolean variable indicating if person list being shown
     */
    boolean isPersonListShowing();
}
