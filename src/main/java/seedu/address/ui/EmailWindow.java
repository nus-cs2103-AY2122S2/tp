package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;


public class EmailWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(EmailWindow.class);
    private static final String FXML = "EmailWindow.fxml";
    private String emailStringForCopy;

    @FXML
    private Button copyButton;

    @FXML
    private Label emailList;

    /**
     * Creates a new EmailWindow.
     *
     * @param root Stage to use as the root of the EmailWindow.
     */
    public EmailWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new EmailWindow.
     *
     * @param personList list of Person objects currently in the PersonList.
     */
    public EmailWindow(ObservableList<Person> personList) {
        this(new Stage());
        String emailString = parseEmailFromList(personList);
        emailList.setText(emailString);
        this.emailStringForCopy = emailString;
    }

    /**
     * Iterate through the list of persons to obtain the email string of each Person.
     *
     * @param personList list of Person objects currently in the PersonList.
     */
    public String parseEmailFromList(ObservableList<Person> personList) {
        String stringOfEmails = "";
        for (Person p : personList) {
            Email personEmail = p.getEmail();
            stringOfEmails = stringOfEmails + personEmail.toString() + "\n";
        }
        return stringOfEmails;
    }

    /**
     * Shows the email window.
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
        logger.fine("Showing email list page.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the email window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the email window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the email window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the emails in the string of emails to the clipboard.
     */
    @FXML
    private void copyEmail() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent copyEmail = new ClipboardContent();
        copyEmail.putString(emailStringForCopy);
        clipboard.setContent(copyEmail);
    }

}
