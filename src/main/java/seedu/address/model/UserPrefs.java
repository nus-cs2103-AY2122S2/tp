package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();

    private Path addressBookFilePath = Paths.get("data", "addressbook.json");
    private Path appointmentBookFilePath = Paths.get("data", "appointmentbook.json");
    private Path insuranceBookFilePath = Paths.get("data", "insurancebook.json");
    private Path recordBookFilePath = Paths.get("data", "recordbook.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {
    }

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
        setAppointmentBookFilePath(newUserPrefs.getAppointmentBookFilePath());
        setInsuranceBookFilePath(newUserPrefs.getInsuranceBookFilePath());
        setRecordBookFilePath(newUserPrefs.getRecordBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    public Path getAppointmentBookFilePath() {
        return appointmentBookFilePath;
    }

    public void setAppointmentBookFilePath(Path appointmentBookFilePath) {
        requireNonNull(appointmentBookFilePath);
        this.appointmentBookFilePath = appointmentBookFilePath;

    }

    public Path getRecordBookFilePath() {
        return recordBookFilePath;
    }

    public void setRecordBookFilePath(Path recordBookFilePath) {
        requireNonNull(recordBookFilePath);
        this.recordBookFilePath = recordBookFilePath;

    }

    public Path getInsuranceBookFilePath() {
        return insuranceBookFilePath;
    }

    public void setInsuranceBookFilePath(Path insuranceBookFilePath) {
        requireNonNull(insuranceBookFilePath);
        this.insuranceBookFilePath = insuranceBookFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && addressBookFilePath.equals(o.addressBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath, insuranceBookFilePath,
                appointmentBookFilePath, recordBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal address file location : " + addressBookFilePath);
        sb.append("\nLocal appointment file location : " + appointmentBookFilePath);
        sb.append("\nLocal insuranceBook data file location : " + insuranceBookFilePath);
        sb.append("\nLocal recordBook data file location : " + recordBookFilePath);

        return sb.toString();
    }
}
