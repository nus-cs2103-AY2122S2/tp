package seedu.address.model.person.lab;

import seedu.address.model.person.Person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student} has a {@code Lab} that matches the specified one
 */
public class StudentHasLabPredicate implements Predicate<Person> {

    private final Lab lab;

    public StudentHasLabPredicate(Lab lab) {
        this.lab = lab;
    }

    @Override
    public boolean test(Person person) {
        return person.getLabs().contains(lab);
    }

    @Override
    public boolean equals(Object other) {
        return other == this    // short circuit if same object
                || (other instanceof StudentHasLabPredicate // instanceof handle units
                && lab.equals(((StudentHasLabPredicate) other).lab));   // state check
    }

}
