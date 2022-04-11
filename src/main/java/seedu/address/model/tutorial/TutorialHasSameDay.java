package seedu.address.model.tutorial;

import java.util.function.Predicate;

public class TutorialHasSameDay implements Predicate<Tutorial> {
    private final Day day;

    public TutorialHasSameDay(Day day) {
        this.day = day;
    }

    public Day getDay() {
        return day;
    }

    @Override
    public boolean test(Tutorial tutorial) {
        return day.equals(tutorial.getDay());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialHasSameDay // instanceof handles nulls
                && day.equals(((TutorialHasSameDay) other).day)); // state check
    }
}
