package seedu.address.testutil;

import static seedu.address.testutil.TypicalInterviews.getTypicalInterviews;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import seedu.address.model.AddressBook;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.Interview;


public class TypicalAddressBook {
    /**
     * Returns an {@code AddressBook} with all the typical persons and interviews.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Applicant applicant : getTypicalPersons()) {
            ab.addApplicant(applicant);
        }

        for (Interview interview : getTypicalInterviews()) {
            ab.addInterview(interview);
        }

        return ab;
    }
}
