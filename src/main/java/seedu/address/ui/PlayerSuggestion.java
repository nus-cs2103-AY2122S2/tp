package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.address.logic.DataAnalyzer;
import seedu.address.model.person.Person;

/**
 * A ui for the status bar that is displayed at the bottom of MyGM suggestion.
 */
public class PlayerSuggestion extends UiPart<Region> {

    private static final String FXML = "PlayerSuggestion.fxml";

    @FXML
    private TextArea playerSuggestion;

    /**
     * Creates a player suggestion.
     *
     * @param persons List of persons.
     */
    public PlayerSuggestion(ObservableList<Person> persons) {
        super(FXML);
        playerSuggestion.setWrapText(true);
        setFeedbackToUser(DataAnalyzer.analyzePlayerPosition(persons));
        // playerSuggestion.setStyle("-fx-control-inner-background: #383838;");
    }

    /**
     * Update the suggestion.
     *
     * @param persons List of persons.
     */
    public void update(ObservableList<Person> persons) {
        setFeedbackToUser(DataAnalyzer.analyzePlayerPosition(persons));
        // playerSuggestion.setStyle("-fx-control-inner-background: #383838;");
    }

    /**
     * Set the feedback into TextField.
     *
     * @param feedbackToUser Feedback to be displayed.
     */
    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        playerSuggestion.setText(feedbackToUser);
    }

}
