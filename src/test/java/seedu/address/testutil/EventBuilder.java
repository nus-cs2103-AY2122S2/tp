package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.person.Address;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.Log;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.UniqueLogList;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

    public static final String DEFAULT_NAME = "Default Event";
    public static final String DEFAULT_DATE_TIME = "12-5-2022 1500";
    public static final String DEFAULT_DESCRIPTION = "Default Description";
    public static final List<Name> DEFAULT_FRIENDLIST = List.of(new Name("Amy Bee"), new Name("Alex Yeoh"));

    private Name name;
    private DateTime dateTime;
    private Description description;
    private Set<Name> friendNames = new HashSet<>();

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        name = new Name(DEFAULT_NAME);
        dateTime = new DateTime(DEFAULT_DATE_TIME);
        description = new Description(DEFAULT_DESCRIPTION);
        friendNames.addAll(DEFAULT_FRIENDLIST);
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        name = eventToCopy.getName();
        dateTime = eventToCopy.getDateTime();
        description = eventToCopy.getDescription();
        friendNames.addAll(eventToCopy.getFriendNames());
    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code names} into a {@code Set<Name>} and set it to the {@code Event} that we are building.
     */
    public EventBuilder withNames(String ... names) {
        this.friendNames = SampleDataUtil.getNameSet(names);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public EventBuilder withDateTime(String dateTime) {
        this.dateTime = new DateTime(dateTime);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Person} that we are building.
     */
    public EventBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    public Event build() {
        return new Event(name, dateTime, description, friendNames);
    }
}
