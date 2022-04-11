package seedu.address.testutil;

import static seedu.address.logic.commands.TutorialTestUtil.VALID_DAY_TG1;
import static seedu.address.logic.commands.TutorialTestUtil.VALID_DAY_TG2;
import static seedu.address.logic.commands.TutorialTestUtil.VALID_TIME_TG1;
import static seedu.address.logic.commands.TutorialTestUtil.VALID_TIME_TG2;
import static seedu.address.logic.commands.TutorialTestUtil.VALID_TUTORIAL_NAME_TG1;
import static seedu.address.logic.commands.TutorialTestUtil.VALID_TUTORIAL_NAME_TG2;
import static seedu.address.logic.commands.TutorialTestUtil.VALID_VENUE_TG1;
import static seedu.address.logic.commands.TutorialTestUtil.VALID_VENUE_TG2;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.tutorial.Tutorial;

/**
 * A utility class containing a list of {@code Tutorial} objects to be used in tests.
 */
public class TypicalTutorials {
    public static final Tutorial T01 = new TutorialBuilder().withTutorialName("T01")
            .withDay("Wed").withTime("10:00").withVenue("LT15").withWeeks(13).build();
    public static final Tutorial T02 = new TutorialBuilder().withTutorialName("T02")
            .withDay("Thu").withTime("11:00").withVenue("LT16").withWeeks(13).build();
    public static final Tutorial T03 = new TutorialBuilder().withTutorialName("T03")
            .withDay("Friday").withTime("12:00").withVenue("LT17").withWeeks(13).build();
    public static final Tutorial T04 = new TutorialBuilder().withTutorialName("T04")
            .withDay("Friday").withTime("13:00").withVenue("LT18").withWeeks(13).build();
    public static final Tutorial TG01 = new TutorialBuilder().withTutorialName("TG01")
            .withDay("Monday").withTime("13:00").withVenue("LT18").withWeeks(13).build();

    // Manually added - Tutorial's details found in {@code TutorialTestUtil}
    public static final Tutorial TG1 = new TutorialBuilder().withTutorialName(VALID_TUTORIAL_NAME_TG1)
            .withDay(VALID_DAY_TG1).withTime(VALID_TIME_TG1).withVenue(VALID_VENUE_TG1).withWeeks(13).build();
    public static final Tutorial TG2 = new TutorialBuilder().withTutorialName(VALID_TUTORIAL_NAME_TG2)
            .withDay(VALID_DAY_TG2).withTime(VALID_TIME_TG2).withVenue(VALID_VENUE_TG2).withWeeks(13).build();

    private TypicalTutorials() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical tutorials.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Tutorial tutorial : getTypicalTutorials()) {
            ab.addTutorial(tutorial);
        }
        return ab;
    }

    public static AddressBook getTypicalAddressBookWithPerson() {
        AddressBook ab = new AddressBook();
        for (Tutorial tutorial : getTypicalTutorials()) {
            ab.addTutorial(tutorial);
        }
        for (Person person: getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Tutorial> getTypicalTutorials() {
        return new ArrayList<>(Arrays.asList(T01, T02, T03, T04));
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL));
    }
}
