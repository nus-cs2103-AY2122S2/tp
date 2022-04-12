package seedu.address.testutil;

import seedu.address.model.HireLah;
import seedu.address.model.applicant.Applicant;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code HireLah ab = new HireLahBuilder().withApplicant("John", "Doe").build();}
 */
public class HireLahBuilder {

    private HireLah hireLah;

    public HireLahBuilder() {
        hireLah = new HireLah();
    }

    public HireLahBuilder(HireLah hireLah) {
        this.hireLah = hireLah;
    }

    /**
     * Adds a new {@code Applicant} to the {@code HireLah} that we are building.
     */
    public HireLahBuilder withApplicant(Applicant applicant) {
        hireLah.addApplicant(applicant);
        return this;
    }

    public HireLah build() {
        return hireLah;
    }
}
