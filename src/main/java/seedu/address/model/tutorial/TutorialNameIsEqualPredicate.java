package seedu.address.model.tutorial;

import java.util.function.Predicate;

public class TutorialNameIsEqualPredicate implements Predicate<Tutorial> {
    private final TutorialName tutorialName;

    public TutorialNameIsEqualPredicate(TutorialName tutorialName) {
        this.tutorialName = tutorialName;
    }

    @Override
    public boolean test(Tutorial tutorial) {
        return tutorialName.equals(tutorial.getTutorialName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TutorialNameIsEqualPredicate) // instanceof handles null
            && tutorialName.equals(((TutorialNameIsEqualPredicate) other).tutorialName);
    }
}
