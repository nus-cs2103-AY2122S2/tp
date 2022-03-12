package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddProfileWindow extends UiPart<Stage> {

    private static final String FXML = "addProfileWindow.fxml";
    private Logic logic;
    private ResultDisplay resultDisplay;


    @FXML
    private TextField nameInput;
    @FXML
    private TextField phoneNumberInput;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField addressInput;
    @FXML
    private TextField tagInput;

    @FXML
    private StackPane resultDisplayPlaceholder;

    /**
     * Creates a new AddProfileWindow.
     *
     * @param root Stage to use as the root of the AddTagWindow.
     */
    public AddProfileWindow(Stage root, Logic logic) {
        super(FXML, root);
        this.logic = logic;
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());
    }

    /**
     * Creates a new addProfile Window.
     */
    public AddProfileWindow(Logic logic) {
        this(new Stage(), logic);
    }

    /**
     * Shows the help window.
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
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
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
     * Handle the add profile action by collating all the inputs from the form and execute the action
     */
    @FXML
    private void handleAddProfile() throws CommandException, ParseException {
        String profileName = nameInput.getText();
        String profilePhoneNumber = phoneNumberInput.getText();
        String profileEmail = emailInput.getText();
        String profileAddress = addressInput.getText();
        String profileTag = tagInput.getText();

        String[] arrayOfProfileTag = profileTag.split(", ");

        String fullCommand = "add "
                + "n/" + profileName + " "
                + "p/" + profilePhoneNumber + " "
                + "e/" + profileEmail + " "
                + "a/" + profileAddress + " ";

        if (!profileTag.equals("")) {
            for (String tag : arrayOfProfileTag) {
                fullCommand += "t/" + tag + " ";
            }
        }

        try {
            CommandResult result = logic.execute(fullCommand);
            resultDisplay.setFeedbackToUser(result.getFeedbackToUser());
            handleClose();
        } catch (ParseException | CommandException e) {
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    /**
     * Handle close
     */
    @FXML
    private void handleClose() {
        getRoot().hide();
    }

}
