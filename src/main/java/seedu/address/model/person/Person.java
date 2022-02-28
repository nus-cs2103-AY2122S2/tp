package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final StudentId studentId;
    private final Name name;

    // Data fields
    private final ModuleCode moduleCode;
    private final Phone phone;
    private final TelegramHandle telegramHandle;
    private final Email email;

    /**
     * Every field must be present and not null.
     */
    public Person(StudentId studentId, Name name, ModuleCode moduleCode, Phone phone,
                  TelegramHandle telegramHandle, Email email) {
        requireAllNonNull(studentId, name, moduleCode); // Compulsory fields
        this.studentId = studentId;
        this.name = name;
        this.moduleCode = moduleCode;
        this.phone = phone;
        this.telegramHandle = telegramHandle;
        this.email = email;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public Name getName() {
        return name;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public Phone getPhone() {
        return phone;
    }

    public TelegramHandle getTelegramHandle() {
        return telegramHandle;
    }

    public Email getEmail() {
        return email;
    }

    /**
     * Returns true if both persons have the same student id.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getStudentId().equals(getStudentId());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getStudentId().equals(getStudentId())
                && otherPerson.getName().equals(getName())
                && otherPerson.getModuleCode().equals(getModuleCode())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getTelegramHandle().equals(getTelegramHandle())
                && otherPerson.getEmail().equals(getEmail());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(studentId, name, moduleCode, phone, telegramHandle, email);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getStudentId())
                .append("; Name: ")
                .append(getName())
                .append("; Module Code: ")
                .append(getModuleCode());

        Phone currentPhone = getPhone();
        if (currentPhone != null) {
            builder.append("; Phone Number: ").append(currentPhone);
        }

        TelegramHandle currentTelegramHandle = getTelegramHandle();
        if (currentTelegramHandle != null) {
            builder.append("; Telegram Handle: @").append(currentTelegramHandle);
        }

        Email currentEmail = getEmail();
        if (currentEmail != null) {
            builder.append("; Email: ").append(currentEmail);
        }

        return builder.toString();
    }

}
