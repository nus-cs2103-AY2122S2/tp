package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.model.tutorial.TutorialName;

public class StudentHasTutorialNamePredicate implements Predicate<Person> {
    private final TutorialName tutorialName;

    public StudentHasTutorialNamePredicate(TutorialName tutorialName) {
        this.tutorialName = tutorialName;
    }

    @Override
    public boolean test(Person person) {
        if (person instanceof Student) {
            Student student = (Student) person;
            return tutorialName.equals(student.getTutorialName());
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentHasTutorialNamePredicate // instanceof handles nulls
                && tutorialName.equals(((StudentHasTutorialNamePredicate) other).tutorialName)); // state check
    }
}
