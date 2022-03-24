package seedu.address.ui;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private ArrayList<String> commandHistory;
    private int commandHistoryPointerIndex;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        this.commandHistory = new ArrayList<>();
        this.commandHistoryPointerIndex = -1;
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
            commandHistory.add(commandText);
            commandHistoryPointerIndex = commandHistory.size();
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Handles arrow key (up and down) pressed event.
     */
    @FXML
    private void handleArrowKeyPressed(KeyEvent keyEvent) {
        KeyCode keyPressed = keyEvent.getCode();
        switch (keyPressed) {
        case UP:
            keyEvent.consume();
            showPreviousCommand();
            break;
        case DOWN:
            keyEvent.consume();
            showNextCommand();
            break;
        default:
        }
    }

    private void showPreviousCommand() {
        if (commandHistoryPointerIndex < 0) {
            return;
        }
        try {
            commandHistoryPointerIndex--;
            String previousCommand = commandHistory.get(commandHistoryPointerIndex);
            setCommandBoxText(previousCommand);
        } catch(IndexOutOfBoundsException e) {
            return; //Nothing needs to be done for requests beyond what is stored in history.
        }
    }

    private void showNextCommand() {
        if (commandHistoryPointerIndex >= commandHistory.size() - 1) {
            setCommandBoxText("");
            commandHistoryPointerIndex = commandHistory.size();
            return;
        }
        try {
            commandHistoryPointerIndex++;
            String nextCommand = commandHistory.get(commandHistoryPointerIndex);
            setCommandBoxText(nextCommand);
        } catch(IndexOutOfBoundsException e) {
            return; //Nothing needs to be done for requests beyond what is stored in history.
        }
    }

    /**
     * Sets the text in command box with the input string
     * and positions the cursor at the end of the text.
     * @param str
     */
    private void setCommandBoxText(String str) {
        commandTextField.setText(str);
        int newCursorPosition = commandTextField.getLength();
        commandTextField.positionCaret(newCursorPosition);
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

}
