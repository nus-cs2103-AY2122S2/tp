package seedu.address.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.address.model.pet.Pet;

/**
 * An UI component that displays information of a {@code Pet}.
 */
public class PetCard extends UiPart<Region> {

    private static final String FXML = "PetListCard.fxml";
    private static LocalDate testDate = LocalDate.now();
    private static Pair<LocalDate, Boolean> present = new Pair<>(testDate, true);
    private static Pair<LocalDate, Boolean> absent = new Pair<>(testDate, false);
    private final ArrayList<Pair<LocalDate, Boolean>> attendanceList = new ArrayList<>();

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Pet pet;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label ownerName;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label diet;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane attendanceTags;


    /**
     * Creates a {@code PetCode} with the given {@code Pet} and index to display.
     */
    public PetCard(Pet pet, int displayedIndex) {
        super(FXML);
        this.pet = pet;
        id.setText(displayedIndex + ". ");
        name.setText(pet.getName().fullName);
        ownerName.setText(pet.getOwnerName().value);
        phone.setText(pet.getPhone().value);
        address.setText(pet.getAddress().value);
        diet.setText(pet.getDiet().value);
        pet.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        attendanceList.add(present);
        attendanceList.add(absent);
        attendanceList.add(present);
        attendanceList.add(absent);
        attendanceList.add(present);
        attendanceList.add(absent);
        attendanceList.add(present);
        attendanceList.add(absent);
        attendanceList.add(present);
        attendanceList.add(absent);
        attendanceList.add(present);
        attendanceList.add(absent);
        attendanceList.add(present);
        attendanceList.add(absent);
        attendanceList.stream().limit(7)
                .forEach(attendance -> {
                    attendanceTags.getChildren()
                        .add(AttendanceTag.createAttendanceTag(attendance));
                }
                );
    }

    public void setColour(String colour) {
        cardPane.setStyle("-fx-background-color: " + colour);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PetCard)) {
            return false;
        }

        // state check
        PetCard card = (PetCard) other;
        return id.getText().equals(card.id.getText())
                && pet.equals(card.pet);
    }
}
