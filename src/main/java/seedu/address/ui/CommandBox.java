package seedu.address.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandRegistry;
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
    private final ArrayList<String> historyBuffer;
    private final ArrayList<String> activeBuffer;
    private int activeBufferIndex;
    private final Set<String> commands = CommandRegistry.PARSERS.keySet();

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;

        // Emulates how the bash terminal handles history.
        this.historyBuffer = new ArrayList<>();
        this.activeBuffer = new ArrayList<>(Arrays.asList(""));
        this.activeBufferIndex = 0;

        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void onAction() {
        String commandText = commandTextField.getText();
        commandTextField.clear();
        if (commandText.equals("")) {
            return;
        }

        // Replace the index of edited & executed command in the active buffer with actual history.
        if (activeBufferIndex < historyBuffer.size()) {
            activeBuffer.set(activeBufferIndex, historyBuffer.get(activeBufferIndex));
        }
        activeBuffer.set(activeBuffer.size() - 1, "");

        // If this command is different from the previous command, add it to the list.
        if (historyBuffer.isEmpty() || !historyBuffer.get(historyBuffer.size() - 1).equals(commandText)) {
            historyBuffer.add(commandText);
            activeBuffer.set(activeBuffer.size() - 1, commandText);
            activeBuffer.add("");
        }
        activeBufferIndex = activeBuffer.size() - 1;

        try {
            commandExecutor.execute(commandText);
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    @FXML
    private void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {
            setPreviousCommand();
        } else if (event.getCode() == KeyCode.DOWN) {
            setNextCommand();
        } else if (event.getCode() == KeyCode.TAB) {
            autocomplete(commandTextField.getText());
        }
    }

    private void setPreviousCommand() {
        activeBuffer.set(activeBufferIndex, commandTextField.getText());
        activeBufferIndex = Math.max(activeBufferIndex - 1, 0);
        commandTextField.setText(activeBuffer.get(activeBufferIndex));
        commandTextField.end();
    }

    private void setNextCommand() {
        activeBuffer.set(activeBufferIndex, commandTextField.getText());
        activeBufferIndex = Math.min(activeBufferIndex + 1, activeBuffer.size() - 1);
        commandTextField.setText(activeBuffer.get(activeBufferIndex));
        commandTextField.end();
    }

    private void autocomplete(String input) {
        String[] values = input.split("[ |]");
        String last = values[values.length - 1];

        if (!commands.contains(last)) {
            String completed = last;
            int min = Integer.MAX_VALUE;

            for (String command : commands) {
                int distance = editDistance(last, command);
                if (distance < min) {
                    completed = command;
                    min = distance;
                }
            }

            commandTextField.setText(input.substring(0, input.length() - last.length()) + completed);
        }
    }

    private int editDistance(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int [][]DP = new int[2][len1 + 1];

        for (int i = 0; i <= len1; i++)
            DP[0][i] = i;

        for (int i = 1; i <= len2; i++) {
            for (int j = 0; j <= len1; j++) {
                if (j == 0) {
                    DP[i % 2][j] = i;
                } else if (str1.charAt(j - 1) == str2.charAt(i - 1)) {
                    DP[i % 2][j] = DP[(i - 1) % 2][j - 1];
                } else {
                    DP[i % 2][j] = 1 + Math.min(DP[(i - 1) % 2][j], Math.min(DP[i % 2][j - 1], DP[(i - 1) % 2][j - 1]));
                }
            }
        }

        return DP[len2 % 2][len1];
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
