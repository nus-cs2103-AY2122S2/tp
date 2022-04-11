package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import seedu.address.model.student.Student;

/**
 * A UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";
    private static final String green = "#2E9675";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Student student;
    private final int imageLength = 30;

    @FXML
    private HBox cardPane;
    @FXML
    private HBox telegramBox;
    @FXML
    private HBox gitHubBox;
    @FXML
    private HBox emailBox;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label email;
    @FXML
    private Label telegram;
    @FXML
    private Label gitHub;
    @FXML
    private ImageView telegramImage;
    @FXML
    private ImageView gitHubImage;
    @FXML
    private ImageView emailImage;
    @FXML
    private FlowPane tutorialGroups;

    private Image telegramLogo = new Image(this.getClass().getResourceAsStream("/images/TelegramLogo.png"));
    private Image gitHubLogo = new Image(this.getClass().getResourceAsStream("/images/GithubLogo.png"));
    private Image emailLogo = new Image(this.getClass().getResourceAsStream("/images/EmailLogo.png"));

    /**
     * Creates a {@code PersonCode} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(String.valueOf(displayedIndex));
        name.setText(student.getName().fullName);
        setContacts(telegramBox, telegramImage, telegramLogo, telegram, student.getTelegram().value);
        setContacts(gitHubBox, gitHubImage, gitHubLogo, gitHub, student.getGitHub().value);
        setContacts(emailBox, emailImage, emailLogo, email, student.getEmail().value);
        student.getTutorialGroups().stream()
                .sorted(Comparator.comparing(tutorialGroup -> tutorialGroup.tutorialGroupName))
                .forEach(tutorialGroup -> {
                    Label tgLabel = new Label(tutorialGroup.tutorialGroupName);
                    tgLabel.setBackground(getColorBackground(green));
                    tgLabel.setPadding(new Insets(0, 5, 0, 5));
                    tutorialGroups.getChildren().add(tgLabel); });
    }

    private void setContacts(HBox hb, ImageView iv, Image i, Label l, String s) {
        if (s == null || s.isEmpty()) {
            hb.setManaged(false);
            iv.setManaged(false);
            l.setManaged(false);
        } else {
            l.setText(s);
            iv.setImage(i);
            iv.setClip(getCircleClip(imageLength / 2));
        }
    }

    private Circle getCircleClip(int radius) {
        return new Circle(radius, radius, radius);
    }

    private Background getColorBackground(String colorString) {
        Paint paint = Paint.valueOf(colorString);
        BackgroundFill bf = new BackgroundFill(paint, new CornerRadii(3, false), null);
        return new Background(bf);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentCard)) {
            return false;
        }

        // state check
        StudentCard card = (StudentCard) other;
        return id.getText().equals(card.id.getText())
                && student.equals(card.student);
    }
}
