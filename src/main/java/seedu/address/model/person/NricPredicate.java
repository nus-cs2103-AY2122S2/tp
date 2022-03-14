package seedu.address.model.person;

import java.util.function.Predicate;

public class NricPredicate implements Predicate<Person> {
    private final Nric nric;

    public NricPredicate(Nric nric) {
        this.nric = nric;
    }

    @Override
    public boolean test(Person person) {
        return person.getNric().equals(nric);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NricPredicate // instanceof handles nulls
                && nric.equals(((NricPredicate) other).nric)); // state check
    }
}
