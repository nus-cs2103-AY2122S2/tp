package seedu.address.testutil;

import seedu.address.model.MedBook;
import seedu.address.model.patient.Patient;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class MedBookBuilder {

    private MedBook medBook;

    public MedBookBuilder() {
        medBook = new MedBook();
    }

    public MedBookBuilder(MedBook medBook) {
        this.medBook = medBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public MedBookBuilder withPatient(Patient patient) {
        medBook.addPatient(patient);
        return this;
    }

    public MedBook build() {
        return medBook;
    }
}
