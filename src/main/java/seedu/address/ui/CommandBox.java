package seedu.address.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.exceptions.ExportCsvOpenException;
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
    private ArrayList<String> previousCommands;
    private int commandIndex;

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
        commandTextField.setOnKeyPressed(this::handleKeyPressed);

        previousCommands = new ArrayList<>();
        commandIndex = -1;
    }

    /**
     * Handles the event when a key is pressed.
     */
    private void handleKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.UP && commandIndex >= 0) {
            commandTextField.setText(previousCommands.get(commandIndex));
            commandTextField.positionCaret(commandTextField.getText().length());
            if (commandIndex > 0) {
                commandIndex--;
            }
        } else if (keyEvent.getCode() == KeyCode.DOWN && commandIndex < previousCommands.size()) {
            if (commandIndex == previousCommands.size() - 1) {
                commandTextField.setText("");
            } else {
                commandIndex++;
                commandTextField.setText(previousCommands.get(commandIndex));
                commandTextField.positionCaret(commandTextField.getText().length());
            }
        }
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
            commandTextField.setText("");

            previousCommands.add(commandText);
            commandIndex = previousCommands.size() - 1;
        } catch (CommandException | ParseException | ExportCsvOpenException e) {
            setStyleToIndicateCommandFailure();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
        CommandResult execute(String commandText) throws CommandException, ParseException, FileNotFoundException,
                ExportCsvOpenException;
    }

}
