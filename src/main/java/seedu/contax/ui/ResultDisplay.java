package seedu.contax.ui;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
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
     * Constructor for ResultDisplay UI component
     * Used to set initial properties of resultDisplay TextFlow
     */
    public ResultDisplay() {
        super(FXML);
        resultDisplay.setPadding(new Insets(5, 10, 5, 10));

        //Code to make the text not wrap, wip to make it scroll
        resultDisplay.setPrefWidth(Region.USE_COMPUTED_SIZE);
        resultDisplay.setMinWidth(Region.USE_PREF_SIZE);
    }


    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.getChildren().clear();
        List<Text> generateStyledText = TextStyleHelper.formattedTextParser(feedbackToUser);
        for (int i = 0; i < generateStyledText.size(); i++) {
            resultDisplay.getChildren().add(generateStyledText.get(i));
        }
    }
}
