package seedu.address.ui.formats;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * For changing the contents in the help window to show text
 */
public class HelpText extends UiPart<Region> {
    private static final String FXML = "HelpText.fxml";

    @FXML
    private TextArea helpMessage;


    /**
     * Displays text from string provided
     *
     * @param text image file to be displayed
     */
    public HelpText(String text) {
        super(FXML);
        helpMessage.setEditable(false);
        helpMessage.setText(text);
    }

}
