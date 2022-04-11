package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.entry.Date;
import seedu.address.model.entry.Event;
import seedu.address.model.entry.Location;
import seedu.address.model.entry.Name;
import seedu.address.model.entry.Time;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

    public static final String DEFAULT_NAME = "DBSSS Online Assessment";
    public static final String DEFAULT_COMPANY = "DBSSS";
    public static final String DEFAULT_DATE = "2022-05-01";
    public static final String DEFAULT_TIME = "10:00";
    public static final String DEFAULT_LOCATION = "Zoom";

    private Name name;
    private Name companyName;
    private Date date;
    private Time time;
    private Location location;
    private Set<Tag> tags;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        name = new Name(DEFAULT_NAME);
        companyName = new Name(DEFAULT_COMPANY);
        date = new Date(DEFAULT_DATE);
        time = new Time(DEFAULT_TIME);
        location = new Location(DEFAULT_LOCATION);
        tags = new HashSet<>();
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        name = eventToCopy.getName();
        companyName = eventToCopy.getCompanyName();
        date = eventToCopy.getDate();
        time = eventToCopy.getTime();
        location = eventToCopy.getLocation();
        tags = new HashSet<>(eventToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Event} that we are building.
     */
    public EventBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code companyName} of the {@code Event} that we are building.
     */
    public EventBuilder withCompanyName(String companyName) {
        this.companyName = new Name(companyName);
        return this;
    }

    /**
     * Sets the {@code date} of the {@code Event} that we are building.
     */
    public EventBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code time} of the {@code Event} that we are building.
     */
    public EventBuilder withTime(String time) {
        this.time = new Time(time);
        return this;
    }

    /**
     * Sets the {@code location} of the {@code Event} that we are building.
     */
    public EventBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    public Event build() {
        return new Event(name, companyName, date, time, location, tags);
    }

}
