package woofareyou.ui;

import static woofareyou.commons.util.AttendanceUtil.getPastWeekAttendance;

import java.util.Comparator;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import woofareyou.commons.util.AttendanceUtil;
import woofareyou.model.pet.Appointment;
import woofareyou.model.pet.AttendanceHashMap;
import woofareyou.model.pet.Diet;
import woofareyou.model.pet.Pet;
import woofareyou.model.tag.Tag;

/**
 * A UI component that displays information of a {@code Pet}.
 */
public class PetCard extends UiPart<Region> {

    private static final String FXML = "PetListCard.fxml";

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
    private HBox dietTag;
    @FXML
    private HBox appointmentTag;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane attendanceTags;
    @FXML
    private FlowPane transportTags;

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
        setDietTag(pet.getDiet());
        setAppointmentTag(pet.getAppointment());
        setBreedTag(pet.getTags());
        setAttendanceTags(pet.getAttendanceHashMap());
        setTransportTags(pet.getAttendanceHashMap());
    }

    /**
     * Sets the breed tag in the pet card.
     *
     * @param breedTag the breed of the pet.
     */
    private void setBreedTag(Set<Tag> breedTag) {
        breedTag.stream()
            .sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Sets the diet tag in the pet card.
     *
     * @param diet the diet of the pet, if any.
     */
    private void setDietTag(Diet diet) {
        if (!pet.getDiet().value.isEmpty()) {
            dietTag.getChildren().add(new DietTag(pet.getDiet().value));
        }
    }

    /**
     * Sets the appointment tag in the pet card.
     *
     * @param appointment the appointment of the pet, if any.
     */
    private void setAppointmentTag(Appointment appointment) {
        if (!pet.getAppointment().value.isEmpty()) {
            appointmentTag.getChildren()
                .add(new AppointmentTag(pet.getAppointment()));
        }
    }

    /**
     * Sets the attendance tags in the pet card.
     *
     * @param attendanceHashMap the attendance hashmap of the pet.
     */
    private void setAttendanceTags(AttendanceHashMap attendanceHashMap) {
        getPastWeekAttendance(attendanceHashMap)
            .forEach(attendance -> {
                attendanceTags
                    .getChildren()
                    .add(new AttendanceTag(attendance));
            });
    }

    /**
     * Sets the transport tags in the pet card.
     *
     * @param attendanceHashMap the attendance hashmap of the pet.
     */
    private void setTransportTags(AttendanceHashMap attendanceHashMap) {
        AttendanceUtil.getNextTwoDaysTransport(attendanceHashMap)
            .forEach(attendance -> {
                transportTags
                    .getChildren()
                    .add(new TransportTag(attendance));
            });
    }

    /**
     * Sets the colour of the pet list cell in WoofAreYou.
     *
     * @param colour the colour of the pet list cell to be set.
     */
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
