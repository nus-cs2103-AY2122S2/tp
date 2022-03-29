package seedu.contax.ui;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    @FXML
    private TextFlow resultDisplay;

    /**
     * Construct the ResultDisplay UI component
     * Used to set initial properties of resultDisplay TextFlow
     */
    public ResultDisplay() {
        super(FXML);
    }


    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.getChildren().clear();
        List<Text> generatedStyledText = TextStyleHelper.formattedTextParser(feedbackToUser);
        resultDisplay.getChildren().setAll(generatedStyledText);
    }
}
