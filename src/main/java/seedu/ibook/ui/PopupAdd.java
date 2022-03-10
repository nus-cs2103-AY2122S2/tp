package seedu.ibook.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.ibook.logic.commands.AddCommand;
import seedu.ibook.logic.parser.CliSyntax;

import static java.util.Objects.requireNonNull;

public class PopupAdd extends UiPart<Stage> {

    private static final String FXML = "PopupAdd.fxml";

    private final MainWindow.CommandExecutor commandExecutor;

    @FXML
    private Text error;

    @FXML
    private TextField name;
    @FXML
    private TextField category;
    @FXML
    private TextField expiryDate;
    @FXML
    private TextField price;
    @FXML
    private TextArea description;

    public PopupAdd(MainWindow.CommandExecutor commandExecutor) {
        super(FXML, new Stage());
        this.commandExecutor = commandExecutor;
    }

    void show() {
        getRoot().show();
    }

    void hide() {
        getRoot().hide();
    }

    boolean isShowing() {
        return getRoot().isShowing();
    }

    void focus() {
        getRoot().requestFocus();
    }

    void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        error.setText(feedbackToUser);
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCreateProduct() {
        String commandText = AddCommand.COMMAND_WORD
                + " " + CliSyntax.PREFIX_NAME.getPrefix()
                + name.getText()
                + " " + CliSyntax.PREFIX_CATEGORY.getPrefix()
                + category.getText()
                + " " + CliSyntax.PREFIX_EXPIRY_DATE.getPrefix()
                + expiryDate.getText()
                + " " + CliSyntax.PREFIX_PRICE.getPrefix()
                + price.getText()
                + " " + CliSyntax.PREFIX_DESCRIPTION.getPrefix()
                + description.getText();

        commandExecutor.execute(commandText);
    }

}
