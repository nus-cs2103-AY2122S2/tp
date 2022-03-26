package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.inputhistory.InputHistoryResult;
import seedu.address.logic.inputhistory.UserInputString;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final RetrievePreviousHistoryExecutor previousHistoryExecutor;
    private final RetrieveNextHistoryExecutor nextHistoryExecutor;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor, RetrievePreviousHistoryExecutor previousHistoryExecutor,
                      RetrieveNextHistoryExecutor nextHistoryExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.previousHistoryExecutor = previousHistoryExecutor;
        this.nextHistoryExecutor = nextHistoryExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        try {
            commandExecutor.execute(commandText);
            clearCommandBoxText();
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Action handler which processes which key is pressed and runs the appropriate method.
     * @param event KeyEvent object.
     */
    @FXML
    private void handleOnKeyPressed(KeyEvent event) {
        KeyCode keyPressed = event.getCode();
        switch (keyPressed) {
        case UP:
            handlePreviousHistoryInput();
            break;
        case DOWN:
            handleNextHistoryInput();
            break;
        default:
            break;
        }
    }

    /**
     * Handles the Up button pressed event.
     */
    private void handlePreviousHistoryInput() {
        InputHistoryResult userInputResult = previousHistoryExecutor.getPreviousUserInput();
        if (!userInputResult.isChanged()) {
            return;
        }
        UserInputString previousUserInput = userInputResult.getUserInputString();
        setCommandBoxText(previousUserInput.toString());
        setCaretPositionToEnd();
    }

    /**
     * Handles the Down button pressed event.
     */
    private void handleNextHistoryInput() {
        InputHistoryResult userInputResult = nextHistoryExecutor.getNextUserInput();
        if (!userInputResult.isChanged()) {
            return;
        }
        UserInputString nextUserInput = userInputResult.getUserInputString();
        setCommandBoxText(nextUserInput.toString());
        setCaretPositionToEnd();
    }

    /**
     * Sets the caret position to the end.
     */
    private void setCaretPositionToEnd() {
        commandTextField.positionCaret(commandTextField.getLength());
    }

    /**
     * Sets the command box text to the provided String.
     * @param newText New text to be set.
     */
    private void setCommandBoxText(String newText) {
        commandTextField.setText(newText);
    }

    /**
     * Clears the text from the command box.
     */
    private void clearCommandBoxText() {
        commandTextField.setText("");
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
     * Represents a function to retrieve the previous user's input from {@code Logic}.
     */
    @FunctionalInterface
    public interface RetrievePreviousHistoryExecutor {
        /**
         * Retrieves the previous user's input from {@code Logic}.
         *
         * @return Previous user's input in {@code InputHistoryResult} format.
         */
        InputHistoryResult getPreviousUserInput();
    }

    /**
     * Represents a function to retrieve the following user's input from {@code Logic}.
     */
    @FunctionalInterface
    public interface RetrieveNextHistoryExecutor {
        /**
         * Retrieves the following user's input from {@code Logic}.
         *
         * @return Following user's input in {@code InputHistoryResult} format.
         */
        InputHistoryResult getNextUserInput();
    }
}
