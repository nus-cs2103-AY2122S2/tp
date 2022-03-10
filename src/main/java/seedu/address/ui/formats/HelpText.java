package seedu.address.ui.formats;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

public class HelpText extends UiPart<Region> {
    private static final String FXML = "HelpText.fxml";

    @FXML
    private TextArea helpMessage;

    public HelpText(String text){
        super(FXML);
        helpMessage.setText(text);
    }

}