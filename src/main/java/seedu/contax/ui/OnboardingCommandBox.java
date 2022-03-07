package seedu.contax.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.contax.logic.commands.CommandResult;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class OnboardingCommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "OnboardingCommandBox.fxml";

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public OnboardingCommandBox() {
        super(FXML);
    }



}
