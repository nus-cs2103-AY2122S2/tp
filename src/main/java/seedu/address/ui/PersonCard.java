package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TaskList;
import seedu.address.model.person.TelegramHandle;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id; // index of entry
    @FXML
    private Label studentId;
    @FXML
    private Label name;
    @FXML
    private Label moduleCode;
    @FXML
    private Label phone;
    @FXML
    private Label telegramHandle;
    @FXML
    private Label email;
    @FXML
    private Label tasks;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        studentId.setText("Matriculation No.: " + person.getStudentId().id);
        name.setText(person.getName().fullName);
        moduleCode.setText(person.getModuleCode().moduleCode);

        Phone currentPhone = person.getPhone();
        if (currentPhone == null || currentPhone.value == null) {
            phone.setText("Phone No.: ");
        } else {
            phone.setText("Phone No.: " + currentPhone.value);
        }

        TelegramHandle currentTelegramHandle = person.getTelegramHandle();
        if (currentTelegramHandle == null || currentTelegramHandle.telegramHandle == null) {
            telegramHandle.setText("Telegram Handle: ");
        } else {
            telegramHandle.setText("Telegram Handle: @" + currentTelegramHandle.telegramHandle);
        }

        Email currentEmail = person.getEmail();
        if (currentEmail == null || currentEmail.value == null) {
            email.setText("Email: ");
        } else {
            email.setText("Email: " + currentEmail.value);
        }

        TaskList currentTasks = person.getTaskList();
        if (currentTasks == null || currentTasks.isEmpty()) {
            tasks.setText("Tasks: ");
        } else {
            tasks.setText("Tasks: \n" + currentTasks.toString());
        }

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
