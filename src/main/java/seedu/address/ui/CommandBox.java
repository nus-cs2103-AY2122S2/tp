package seedu.address.ui;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
    private final ArrayList<String> pastHistory = new ArrayList<>();
    private final ArrayList<String> nextHistory = new ArrayList<>();

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Checks if there are any commands before the current.
     */
    public boolean hasPrevCommands() {
        return !(pastHistory.isEmpty());
    }

    /**
     * Checks if there are any commands after the current.
     */
    public boolean hasNextCommands() {
        return !(nextHistory.isEmpty());
    }

    /**
     * Gets the most recent command before the current in the past command history inputted by user, if any.
     */
    public String getPrevCommand(String currentCommand) {
        if (this.hasPrevCommands()) {
            nextHistory.add(currentCommand);
            return pastHistory.remove(pastHistory.size() - 1);
        } else {
            return "";
        }
    }

    /**
     * Gets the command after the current command in the command history inputted by user, if any.
     */
    public String getNextCommand(String currentCommand) {
        if (this.hasNextCommands()) {
            pastHistory.add(currentCommand);
            return nextHistory.remove(nextHistory.size() - 1);
        } else {
            return "";
        }
    }

    /**
     * Adds the command into the command history after its execution
     */
    public void logCommand(String currentCommand) {
        pastHistory.add(currentCommand);
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
            logCommand(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    // Solution below adapted from
    // https://stackoverflow.com/questions/22014950/javafx-moving-image-with-arrow-keys-and-spacebar
    @FXML
    private void handleUpDownArrowsPressed(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            event.consume();
            commandTextField.setText(getPrevCommand(commandTextField.getText()));
            break;
        case DOWN:
            event.consume();
            commandTextField.setText(getNextCommand(commandTextField.getText()));
            break;
        default:
            break;
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

}
