package seedu.address.model.contact;

import java.util.function.Predicate;

import seedu.address.model.patient.Nric;

public class ContactWithNricPredicate implements Predicate<Contact> {
    private final Nric nric;

    public ContactWithNricPredicate(Nric nric) {
        this.nric = nric;
    }

    @Override
    public boolean test(Contact contact) {
        return contact.getOwnerNric().equals(nric);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactWithNricPredicate // instanceof handles nulls
                && nric.equals(((ContactWithNricPredicate) other).nric)); // state check
    }
}
