package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Schedule;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_LINEUP = "Lineups list contains duplicate lineup(s).";
    public static final String MESSAGE_DUPLICATE_SCHEDULE = "Schedules list contains duplicate schedule(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedLineup> lineups = new ArrayList<>();
    private final List<JsonAdaptedSchedule> schedules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("lineups") List<JsonAdaptedLineup> lineups,
                                       @JsonProperty("schedules") List<JsonAdaptedSchedule> schedules) {
        this.persons.addAll(persons);
        this.lineups.addAll(lineups);
        this.schedules.addAll(schedules);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        lineups.addAll(source.getLineupList().stream().map(JsonAdaptedLineup::new).collect(Collectors.toList()));
        schedules.addAll(source.getScheduleList().stream().map(JsonAdaptedSchedule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedLineup jsonAdaptedLineup : lineups) {
            Lineup lineup = jsonAdaptedLineup.toModelType();
            if (addressBook.hasLineupName(lineup.getLineupName())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_LINEUP);
            }
            addressBook.addLineup(lineup);
        }

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.initializePerson(person);
        }


        for (JsonAdaptedSchedule jsonAdaptedSchedule : schedules) {
            Schedule schedule = jsonAdaptedSchedule.toModelType();
            if (addressBook.hasSchedule(schedule)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SCHEDULE);
            }
            addressBook.addSchedule(schedule);
        }

        return addressBook;
    }

}
