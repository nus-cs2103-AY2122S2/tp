package seedu.address.model.person.lab;

import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Student} has a {@code Lab} that matches the specified one
 */
public class StudentHasLabPredicate implements Predicate<Person> {

    private final Lab lab;

    public StudentHasLabPredicate(Lab lab) {
        this.lab = lab;
    }

    @Override
    public boolean test(Person student) {
        if (!student.getLabs().contains(lab)) { // student does not have lab -> return false
            return false;
        } else { // student has lab -> check if labStatus is same
            Lab labForStudent = student.getLabs().getLab(lab);
            return labForStudent.equals(lab);
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentHasLabPredicate // instanceof handle units
                && lab.equals(((StudentHasLabPredicate) other).lab)); // state check
    }

}
