package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.application.Application;

/**
 * An UI component that displays information of an {@code Application}.
 */
public class ApplicationCard extends UiPart<Region> {

    private static final String FXML = "ApplicationListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Application application;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private HBox tags;
    @FXML
    private Label jobTitle;
    @FXML
    private Label interviewSlot;
    @FXML
    private ScrollPane detailScroll;
    @FXML
    private Label detail;

    /**
     * Creates an {@code ApplicationCard} with the given {@code Application} and index to display.
     */
    public ApplicationCard(Application application, int displayedIndex) {
        super(FXML);
        this.application = application;
        id.setText(displayedIndex + ". ");
        name.setText(application.getName().fullName);
        phone.setText(application.getPhone().value);
        address.setText(application.getAddress().value);
        interviewSlot.setText(application.getInterviewSlot().toString());
        email.setText(application.getEmail().value);
        tags.getChildren().add(new TagBox(application.getTags()));
        jobTitle.setText(application.getJobTitle().value);
        detail.setText(application.getDetails().details);
        detailScroll.setContent(detail);
        cardPane.getChildren().add(detailScroll);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplicationCard)) {
            return false;
        }

        // state check
        ApplicationCard card = (ApplicationCard) other;
        return id.getText().equals(card.id.getText())
                && application.equals(card.application);
    }
}
