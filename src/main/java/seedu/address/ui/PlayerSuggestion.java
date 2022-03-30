package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import seedu.address.logic.DataAnalyzer;
import seedu.address.model.person.Person;

/**
 * A ui for the status bar that is displayed at the bottom of MyGM suggestion.
 */
public class PlayerSuggestion extends UiPart<Region> {

    private static final String FXML = "PlayerSuggestion.fxml";

    @FXML
    private TextArea playerSuggestion;

    public PlayerSuggestion(ObservableList<Person> persons) {
        super(FXML);
        setFeedbackToUser(DataAnalyzer.AnalyzePlayerPosition(persons));
        playerSuggestion.setStyle("-fx-control-inner-background: #383838;");
    }

    public void update(ObservableList<Person> persons) {
        setFeedbackToUser(DataAnalyzer.AnalyzePlayerPosition(persons));
        playerSuggestion.setStyle("-fx-control-inner-background: #383838;");
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        playerSuggestion.setText(feedbackToUser);
    }

}