package seedu.address.model.lab;

import java.util.function.Predicate;

import seedu.address.model.student.Student;

/**
 * Tests that a {@code Student} has a {@code Lab} that matches the specified one
 */
public class StudentHasLabPredicate implements Predicate<Student> {

    private final Lab lab;

    public StudentHasLabPredicate(Lab lab) {
        this.lab = lab;
    }

    /**
     * @return lab contained in the predicate
     */
    public Lab getLab() {
        return this.lab;
    }

    @Override
    public boolean test(Student studentToTest) {
        LabList studentToTestLabs = studentToTest.getLabs();

        if (!studentToTestLabs.contains(lab)) { // student does not have lab -> return false
            return false;
        } else { // student has lab -> check if labStatus is same
            Lab labForStudent = studentToTestLabs.getLab(lab);
            return labForStudent.hasSameLabStatus(lab);
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentHasLabPredicate // instanceof handle units
                && lab.equals(((StudentHasLabPredicate) other).lab)); // state check
    }

}
