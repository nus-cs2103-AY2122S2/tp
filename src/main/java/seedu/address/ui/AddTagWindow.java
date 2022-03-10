package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;



public class AddTagWindow extends UiPart<Stage> {

    private static final String FXML = "addTagWindow.fxml";
    private Logic logic;
    private ResultDisplay resultDisplay;


    @FXML
    private TextField tagNameInput;

    @FXML
    private StackPane resultDisplayPlaceholder;

    /**
     * Creates a new AddTagWindow.
     *
     * @param root Stage to use as the root of the AddTagWindow.
     */
    public AddTagWindow(Stage root, Logic logic) {
        super(FXML, root);
        this.logic = logic;
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());
    }

    /**
     * Creates a new HelpWindow.
     */
    public AddTagWindow(Logic logic) {
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


    @FXML
    private void addTagHandler() throws CommandException, ParseException {
        String tagName = tagNameInput.getText();
        String fullCommand = "add_tag t/" + tagName;
        try {
            CommandResult result = logic.execute(fullCommand);
            resultDisplay.setFeedbackToUser(result.getFeedbackToUser());
        } catch (ParseException | CommandException e) {
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    @FXML
    private void handleClose() {
        getRoot().hide();
    }





}
