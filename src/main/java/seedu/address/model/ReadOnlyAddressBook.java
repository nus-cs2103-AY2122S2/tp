package seedu.address.model;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Schedule;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns the lineups list.
     * This list will not contain any duplicate lineups.
     */
    List<Lineup> getLineupList();

    /**
     * Returns an unmodifiable view of the schedule list.
     * This list will not contain any duplicate schedules.
     */
    ObservableList<Schedule> getScheduleList();

}
