package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Controller for a help page
 */
public class AddWindow extends UiPart<Stage> {

    public static final String HELP_MESSAGE = "You can add a person easily here";
    public static final String NAME_LABEL = "Name: ";
    public static final String PHONE_LABEL = "Number: ";
    public static final String ADDRESS_LABEL = "Address: ";
    public static final String EMAIL_LABEL = "Email: ";


    private static final Logger logger = LogsCenter.getLogger(AddWindow.class);
    private static final String FXML = "AddWindow.fxml";

    @FXML
    private Button addButton;

    @FXML
    private Label addMessageLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;


    /**
     * Creates a new AddWindow.
     *
     * @param root Stage to use as the root of the AddWindow.
     */
    public AddWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new AddWindow.
     */
    public AddWindow() {
        this(new Stage());
        addMessageLabel.setText(HELP_MESSAGE);
        nameLabel.setText(NAME_LABEL);
        phoneLabel.setText(PHONE_LABEL);
        addressLabel.setText(ADDRESS_LABEL);
        emailLabel.setText(EMAIL_LABEL);
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
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Resets the fields when handleCancel() is triggered
     */
    public void resetFields() {
        nameField.setText("");
        phoneField.setText("");
        addressField.setText("");
        emailField.setText("");
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
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void handleAdd() {
        String name = "n/" + nameField.getText();
        String phone = "p/" + phoneField.getText();
        String address = "a/" + addressField.getText();
        String email = "e/" + emailField.getText();
        StringBuilder userInput = new StringBuilder();
        String[] personFields = {"add", name, phone, address, email};

        // Craft the user input
        for (int i = 0; i < personFields.length; i++) {
            userInput.append(personFields[i]).append(" ");
        }

        System.out.println(userInput.toString());
        // TODO: Handle the proper adding of a new Person into ModuleMate Finder
        // For now, leaving it as a UI addition with no functionality.
        // When submitting, basically treat
        this.hide();
    }

    @FXML
    private void handleCancel() {
        this.resetFields();
        this.hide();
    }
}
