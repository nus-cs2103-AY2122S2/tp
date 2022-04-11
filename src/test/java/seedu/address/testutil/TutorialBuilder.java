package seedu.address.testutil;

import javafx.collections.transformation.FilteredList;
import seedu.address.model.person.Person;
import seedu.address.model.tutorial.Day;
import seedu.address.model.tutorial.Time;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.model.tutorial.Venue;

/**
 * A utility class to help with building Tutorial objects.
 */
public class TutorialBuilder {

    public static final String DEFAULT_TUTORIAL_NAME = "Default Tutorial";
    public static final String DEFAULT_VENUE = "LT13";
    public static final String DEFAULT_DAY = "Monday";
    public static final String DEFAULT_TIME = "13:00";
    public static final int DEFAULT_WEEKS = 13;

    private TutorialName tutorialName;
    private Venue venue;
    private Day day;
    private Time time;
    private int weeks;

    /**
     * Creates a {@code TutorialBuilder} with the default details.
     */
    public TutorialBuilder() {
        tutorialName = new TutorialName(DEFAULT_TUTORIAL_NAME);
        venue = new Venue(DEFAULT_VENUE);
        day = new Day(DEFAULT_DAY);
        time = new Time(DEFAULT_TIME);
        weeks = DEFAULT_WEEKS;
    }

    /**
     * Initializes the TutorialBuilder with the data of {@code tutorialToCopy}.
     */
    public TutorialBuilder(Tutorial tutorialToCopy) {
        tutorialName = tutorialToCopy.getTutorialName();
        venue = tutorialToCopy.getVenue();
        day = tutorialToCopy.getDay();
        time = tutorialToCopy.getTime();
        weeks = tutorialToCopy.getWeeks();
    }

    /**
     * Sets the {@code TutorialName} of the {@code Tutorial} that we are building.
     */
    public TutorialBuilder withTutorialName(String name) {
        this.tutorialName = new TutorialName(name);
        return this;
    }

    /**
     * Sets the {@code Venue} of the {@code Tutorial} that we are building.
     */
    public TutorialBuilder withVenue(String venue) {
        this.venue = new Venue(venue);
        return this;
    }

    /**
     * Sets the {@code Day} of the {@code Tutorial} that we are building.
     */
    public TutorialBuilder withDay(String day) {
        this.day = new Day(day);
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code Tutorial} that we are building.
     */
    public TutorialBuilder withTime(String time) {
        this.time = new Time(time);
        return this;
    }

    /**
     * Sets the {@code weeks} of the {@code Tutorial} that we are building.
     */
    public TutorialBuilder withWeeks(int weeks) {
        this.weeks = weeks;
        return this;
    }

    public Tutorial build() {
        return new Tutorial(tutorialName, venue, day, time, weeks);
    }

    public Tutorial buildWithStudentList(FilteredList<Person> studentList) {
        return new Tutorial(tutorialName, venue, day, time, weeks, studentList);
    }
}
