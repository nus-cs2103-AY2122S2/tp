package seedu.address.testutil;

import seedu.address.model.TAssist;
import seedu.address.model.student.Student;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class TAssistBuilder {

    private TAssist tassist;

    public TAssistBuilder() {
        tassist = new TAssist();
    }

    public TAssistBuilder(TAssist tassist) {
        this.tassist = tassist;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public TAssistBuilder withStudent(Student student) {
        tassist.addStudent(student);
        return this;
    }

    public TAssist build() {
        return tassist;
    }
}
