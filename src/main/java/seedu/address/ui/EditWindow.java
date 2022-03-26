package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Controller for an Edit page
 */
public class EditWindow extends UiPart<Stage> {

    public static final String HELP_MESSAGE = "You can edit a person easily here."
            + "\nIndex must be provided, and at least one other field.";
    public static final String INDEX_LABEL = "Index: ";
    public static final String NAME_LABEL = "Name: ";
    public static final String PHONE_LABEL = "Number: ";
    public static final String ADDRESS_LABEL = "Address: ";
    public static final String EMAIL_LABEL = "Email: ";

    private static final Logger logger = LogsCenter.getLogger(EditWindow.class);
    private static final String FXML = "EditWindow.fxml";
    private Logic logic;

    @FXML
    private Button submitButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label editMessageLabel;

    @FXML
    private Label indexLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField indexField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;


    /**
     * Creates a new EditWindow.
     *
     * @param root Stage to use as the root of the EditWindow.
     */
    public EditWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new EditWindow.
     */
    public EditWindow(Logic logic) {
        this(new Stage());
        this.logic = logic;
        editMessageLabel.setText(HELP_MESSAGE);
        indexLabel.setText(INDEX_LABEL);
        nameLabel.setText(NAME_LABEL);
        phoneLabel.setText(PHONE_LABEL);
        addressLabel.setText(ADDRESS_LABEL);
        emailLabel.setText(EMAIL_LABEL);
        errorLabel.setText("");
    }

    /**
     * Shows the add window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing edit window of the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Resets the fields when handleCancel() is triggered, or when submission is successful
     */
    public void resetFields() {
        indexField.setText("");
        nameField.setText("");
        phoneField.setText("");
        addressField.setText("");
        emailField.setText("");
        errorLabel.setText("");
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the add window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }


    /**
     * Handles submission of the fields inputted by the user through EditWindow's Ui.
     */
    @FXML
    private void handleSubmit() {
        String index = indexField.getText();
        String name = nameField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String email = emailField.getText();
        StringBuilder userInput = new StringBuilder();

        // First 2 are empty because they are for
        // 0: command word
        // 1: index, which has no prefix.
        String[] prefixes = {"", "", "n/", "p/", "a/", "e"};
        String[] personFields = {"edit", index, name, phone, address, email};

        if (index.equals("")) {
            errorLabel.setText("Index cannot be empty");
            return;
        }

        if (name.equals("") && phone.equals("") && address.equals("") && email.equals("")) {
            errorLabel.setText("At least one of the optional fields must be filled");
            return;
        }

        // Craft the user input to be fed into executeCommand
        for (int i = 0; i < personFields.length; i++) {
            if (!personFields[i].equals("")) {
                userInput.append(prefixes[i]).append(personFields[i]).append(" ");
            }
        }

        try {
            executeCommand(userInput.toString());
            // reset all fields and then hide the panel
            this.resetFields();
            this.hide();
        } catch (CommandException | ParseException e) {
            errorLabel.setText("Error encountered");
        }
    }


    /**
     * Handles resetting of fields and closing EditWindow.
     */
    @FXML
    private void handleCancel() {
        this.resetFields();
        this.hide();
    }

    /**
     * Handles the case where ENTER key is pressed on a TextField
     */
    @FXML
    private void handleEnter() {
        this.handleSubmit();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            MainWindow.resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            MainWindow.resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
