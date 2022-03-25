package unibook.ui;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import unibook.model.module.group.Group;

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

    /**
     * Set the view to show list of modules.
     */
    void setModuleListPanel();

    /**
     * Set the view to show list of persons.
     */
    void setPersonListPanel();

    /**
     * Set the view to show given list of groups.
     *
     * @param group list of groups to show.
     */
    void setGroupListPanel(ObservableList<Group> group);
}
