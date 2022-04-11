package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commandhistory.CommandHistoryEntry;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final HistoryGetter historyGetter;
    private int historyPosition = 0;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor, HistoryGetter historyGetter) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.historyGetter = historyGetter;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        historyPosition = 0; // Reset history pointer on new input

        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    @FXML
    private void handleKeyPressed(KeyEvent e) {
        if (e.getCode().equals(KeyCode.UP)) {
            getHistory(+1);
        }
        if (e.getCode().equals(KeyCode.DOWN)) {
            getHistory(-1);
        }
    }

    private void getHistory(int step) {
        int expectedHistoryPosition = historyPosition + step;

        if (expectedHistoryPosition < 0) {
            return;
        }

        if (expectedHistoryPosition == 0) {
            commandTextField.setText("");
            historyPosition = expectedHistoryPosition;
            return;
        }

        CommandHistoryEntry commandText = requireNonNull(historyGetter.getCommandText(expectedHistoryPosition));
        if (commandText.exists()) {
            historyPosition = expectedHistoryPosition;
            commandTextField.setText(commandText.getCommandText());
        }
    }


    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

    /**
     * Represents a function that can get history from the command history.
     */
    @FunctionalInterface
    public interface HistoryGetter {
        /**
         * Retrieves a command from the command history
         *
         * @see seedu.address.logic.Logic#getCommandText(int)
         */
        CommandHistoryEntry getCommandText(int i);
    }

}
