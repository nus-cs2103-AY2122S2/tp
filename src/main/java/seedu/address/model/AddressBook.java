package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.lineup.UniqueLineupList;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.UniqueScheduleList;

/**
 * Wraps all data at MyGM
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueLineupList lineups;
    private final UniqueScheduleList schedules;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        lineups = new UniqueLineupList();
        schedules = new UniqueScheduleList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    public void setLineups(List<Lineup> lineups) {
        this.lineups.setLineups(lineups);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
        setSchedules(newData.getScheduleList());
        setLineups(newData.getLineupList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        persons.setPerson(target, editedPerson);
        lineups.replacePlayerInAllLineups(editedPerson, target);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        lineups.deletePlayerFromALlLineups(key);
    }

    //// Added to fit MyGM needs
    /**
     * Returns true if {@code targetName} is taken by some player.
     */
    public boolean hasPersonName(Name targetName) {
        requireNonNull(targetName);
        return this.persons.containsName(targetName);
    }

    /**
     * Returns the person with {@code targetName};
     */
    public Person getPerson(Name targetName) {
        return persons.getPerson(targetName);
    }

    /**
     * Returns true if the person to add has a duplicate jersey number.
     */
    public boolean hasJerseyNumber(Person player) {
        return persons.containsJerseyNumber(player.getJerseyNumber());
    }

    /**
     * Returns a list of jersey number that are still available.
     */
    public String getAvailableJerseyNumber() {
        return persons.getAvailableJerseyNumber();
    }

    //// lineup-level operations
    /**
     * Checks for the existence of a lineup name
     * @param targetName The lineup name to check
     * @return Boolean represents the existecne of the lineup name
     */
    public boolean hasLineupName(LineupName targetName) {
        requireNonNull(targetName);
        return this.lineups.containsLineupName(targetName);
    }

    /**
     * Deletes the lineup from all players and lineup lists.
     */
    public void deleteLineup(Lineup lineup) {
        this.lineups.deleteLineupFromList(lineup);
        this.persons.removeAllPlayerFromLineup(lineup);
    }

    public void setLineup(Lineup target, Lineup editedLineup) {
        requireNonNull(editedLineup);
        lineups.replaceLineup(target, editedLineup);
    }

    public Lineup getLineup(LineupName targetName) {
        return lineups.getLineup(targetName);
    }

    public void addPersonToLineup(Person person, Lineup lineup) {
        lineup.addPlayer(person);
    }

    /**
     * Adds a Lineup to MyGM
     *
     * @param lineup The Lineup to be added
     */
    public void addLineup(Lineup lineup) {
        lineups.addLineupToList(lineup);
    }

    /**
     * Returns true if MyGM has reached maximum capacity.
     */
    public boolean isFull() {
        return persons.isFull();
    }

    //// schedule-level operations
    /**
     * Replaces the contents of the schedule list with {@code schedules}.
     * {@code schedules} must not contain duplicate schedules.
     */
    public void setSchedules(List<Schedule> schedules) {
        this.schedules.setSchedules(schedules);
    }

    /**
     * Returns true if a schedule with the same identity as {@code schedule} exists in MyGM.
     */
    public boolean hasSchedule(Schedule schedule) {
        requireNonNull(schedule);
        return schedules.contains(schedule);
    }


    /**
     * Add a person and update the respective lineups
     * The person must not already exist in the address book and the lineups must exist.
     */
    public void initalizePerson(Person p) {
        persons.add(p);

        for (LineupName lineupName : p.getLineupNames()) {
            Lineup lineup = lineups.getLineup(lineupName);
            lineups.putPlayerToLineup(p, lineup);
        }
    }
  
    /**
     * Adds a schedule to MyGM.
     * The schedule must not already exist in MyGM.
     */
    public void addSchedule(Schedule s) {
        schedules.add(s);
    }

    /**
     * Replaces the given schedule {@code target} in the list with {@code editedSchedule}.
     * {@code target} must exist in MyGM.
     * The schedule identity of {@code editedSchedule} must not be the same as another existing schedule in MyGM.
     */
    public void setSchedule(Schedule target, Schedule editedSchedule) {
        requireNonNull(editedSchedule);
        schedules.setSchedule(target, editedSchedule);
    }

    /**
     * Removes {@code key} from this {@code MyGM}.
     * {@code key} must exist in MyGM.
     */
    public void removeSchedule(Schedule key) {
        schedules.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons\n"
                + schedules.asUnmodifiableObservableList().size() + " schedules\n";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public List<Lineup> getLineupList() {
        return lineups.getList();
    }

    @Override
    public ObservableList<Schedule> getScheduleList() {
        return schedules.asUnmodifiableObservableList();
    }

    public void refresh() {
        persons.refresh();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons))
                && lineups.equals(((AddressBook) other).lineups)
                && schedules.equals(((AddressBook) other).schedules);
    }

    @Override
    public int hashCode() {
        return persons.hashCode() + lineups.hashCode() + schedules.hashCode();
    }

}
