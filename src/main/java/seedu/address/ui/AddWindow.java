package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
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
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;


/**
 * Controller for an Add page
 */
public class AddWindow extends UiPart<Stage> {

    public static final String HELP_MESSAGE = "Add a new contact";
    public static final String NAME_LABEL = "Name: ";
    public static final String PHONE_LABEL = "Number: ";
    public static final String ADDRESS_LABEL = "Address: ";
    public static final String EMAIL_LABEL = "Email: ";
    public static final String COMMENT_LABEL = "Comment: ";
    public static final String STAT_LABEL = "Status: ";
    public static final String MODULES_LABEL = "Modules: ";


    private static final Logger logger = LogsCenter.getLogger(AddWindow.class);
    private static final String FXML = "AddWindow.fxml";
    private Logic logic;

    @FXML
    private Button submitButton;

    @FXML
    private Button cancelButton;

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
    private Label commentLabel;

    @FXML
    private Label statLabel;

    @FXML
    private Label modulesLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField commentField;

    @FXML
    private TextField statField;

    @FXML
    private TextField modulesField;


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
    public AddWindow(Logic logic) {
        this(new Stage());
        this.logic = logic;
        addMessageLabel.setText(HELP_MESSAGE);
        nameLabel.setText(NAME_LABEL);
        phoneLabel.setText(PHONE_LABEL);
        addressLabel.setText(ADDRESS_LABEL);
        emailLabel.setText(EMAIL_LABEL);
        commentLabel.setText(COMMENT_LABEL);
        statLabel.setText(STAT_LABEL);
        modulesLabel.setText(MODULES_LABEL);
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
        logger.fine("Showing add page for the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Resets the fields when handleCancel() is triggered, or when submission is successful
     */
    public void resetFields() {
        nameField.setText("");
        phoneField.setText("");
        addressField.setText("");
        emailField.setText("");
        commentField.setText("");
        statField.setText("");
        modulesField.setText("");
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
     * Retrieves the last index in {@code ReadOnlyAddressBook}'s list of Person.
     * Indexing starts from 1.
     * @return last 1-based index
     */
    private int getAbLastIndex() {
        ReadOnlyAddressBook ab = logic.getAddressBook();
        ObservableList<Person> personList = ab.getPersonList();
        return personList.size();
    }

    /**
     * Adds a Status for the newly created Person object.
     */
    private void addStatusForNewPerson() {
        int lastIndex = getAbLastIndex();
        StringBuilder commandText = new StringBuilder("status ").append(lastIndex);
        String status = statField.getText();

        if (status.equals("")) {
            return;
        }

        // Do not need to handle the fact that the given status might not be valid.
        // This is handled by executeCommand
        commandText.append(" s/").append(status);
        try {
            executeCommand(commandText.toString());
        } catch (CommandException | ParseException e) {
            return;
        }
    }

    /**
     * Adds the {@code module} for the newly created {@code Person} object.
     */
    private void addModulesForNewPerson() {
        int lastIndex = getAbLastIndex();
        StringBuilder commandText = new StringBuilder("addmodule ").append(lastIndex);
        String[] modules = modulesField.getText().split(" ");

        if (!modules[0].equals("")) {
            String modsToAdd = "";
            for (int i = 0; i < modules.length; i++) {
                modsToAdd += "m/" + modules[i];

                // Append whitespace if it's not the last module to add.
                if (i != modules.length - 1) {
                    modsToAdd += " ";
                }
            }

            // Then, execute the addmodule command.
            commandText.append(" ").append(modsToAdd);
            try {
                executeCommand(commandText.toString());
            } catch (CommandException | ParseException e) {
                return;
            }
        }
    }

    /**
     * Adds a {@code comment} to the {@code Person} object.
     */
    private void addCommentForNewPerson() {
        int lastIndex = getAbLastIndex();
        StringBuilder commandText = new StringBuilder("comment ").append(lastIndex);
        String comment = commentField.getText();

        // If comment is empty, simply do nothing.
        if (comment.equals("")) {
            return;
        }

        // Otherwise, execute the comment command.
        commandText.append(" c/").append(comment);
        try {
            executeCommand(commandText.toString());
        } catch (CommandException | ParseException e) {
            return;
        }
    }

    /**
     * Checks if the Status field provided by the user is valid or not.
     * A valid Status is either empty, "blacklist", or "favourite"
     * @return true if it is valid
     */
    private boolean isValidStatus() {
        return Status.isValidStatus(statField.getText());
    }

    /**
     * Checks if the given modules are of the correct format
     * @return true if all modules are valid
     */
    private boolean isValidModule() {
        String[] modules = modulesField.getText().split(" ");

        if (modules.length == 1 && modules[0].equals("")) {
            return true;
        } else {
            for (String module : modules) {
                if (!Module.isValidModuleName(module)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks all the fields to make sure none of it is empty.
     * @return true if any of the field is empty
     */
    private boolean isAnyCompulsoryFieldEmpty() {
        if (nameField.getText().equals("") || phoneField.getText().equals("")
                || addressField.getText().equals("") || emailField.getText().equals("")) {
            return true;
        }

        return false;
    }

    /**
     * Handles submission of the fields inputted by the user through AddWindow's Ui.
     */
    @FXML
    private void handleSubmit() {
        String name = "n/" + nameField.getText();
        String phone = "p/" + phoneField.getText();
        String address = "a/" + addressField.getText();
        String email = "e/" + emailField.getText();
        StringBuilder userInput = new StringBuilder();
        String[] personFields = {"add", name, phone, address, email};

        if (isAnyCompulsoryFieldEmpty()) {
            errorLabel.setText("You must input all mandatory fields!");
            return;
        }

        // Craft the user input to be fed into executeCommand
        for (int i = 0; i < personFields.length; i++) {
            userInput.append(personFields[i]).append(" ");
        }

        // Ensure that modules and status are valid before attempting to add a Person.
        if (!isValidStatus()) {
            errorLabel.setText(Status.MESSAGE_CONSTRAINTS);
            return;
        }

        if (!isValidModule()) {
            // Cannot use Module.MESSAGE_CONSTRAINTS here as it would be too long to fit within AddWindow
            errorLabel.setText("Modules names should have 2-3 letters prefix\n"
                    + "followed by 4 digits and an optional letter\n");
            return;
        }

        try {
            executeCommand(userInput.toString());
        } catch (CommandException | ParseException e) {
            errorLabel.setText("Error encountered");
            return;
        }

        // Since user command execution is successful, then we do the other stuff next.
        // Notice that whenever a new Person is added into AddressBook, it'll list out all Persons.
        // So we simply need to retrieve the last Person added...
        addCommentForNewPerson();
        addStatusForNewPerson();
        addModulesForNewPerson();

        // reset all fields and then hide the panel
        this.resetFields();
        this.hide();
    }

    /**
     * Handles the case where the "Cancel" button is pressed.
     */
    @FXML
    private void handleCancel() {
        this.resetFields();
        this.hide();
    }

    /**
     * Handles the case where ENTER key is pressed on the last TextField
     */
    @FXML
    private void handleEnter() {
        this.handleSubmit();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
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
