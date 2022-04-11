package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.meeting.Agenda;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingPlace;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.person.Id;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Meeting objects.
 */
public class MeetingBuilder {

    public static final String DEFAULT_AGENDA = "Metal supply for Greendale College";
    public static final String DEFAULT_PLACE = "Jerry's Office";
    public static final String DEFAULT_TIME = "02-04-2024 13:30";

    private Agenda agenda;
    private MeetingPlace meetingPlace;
    private MeetingTime meetingTime;
    private Set<Id> attendees;

    /**
     * Creates a {@code MeetingBuilder} with the default details.
     */
    public MeetingBuilder() {
        agenda = new Agenda(DEFAULT_AGENDA);
        meetingPlace = new MeetingPlace(DEFAULT_PLACE);
        meetingTime = new MeetingTime(DEFAULT_TIME);
        attendees = new HashSet<>();
    }

    /**
     * Initializes the MeetingBuilder with the data of {@code meetingToCopy}.
     */
    public MeetingBuilder(Meeting meetingToCopy) {
        agenda = meetingToCopy.getAgenda();
        meetingPlace = meetingToCopy.getPlace();
        meetingTime = meetingToCopy.getTime();
        attendees = new HashSet<>(meetingToCopy.getAttendees());
    }

    /**
     * Sets the {@code Agenda} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withAgenda(String agenda) {
        this.agenda = new Agenda(agenda);
        return this;
    }

    /**
     * Sets the {@code MeetingPlace} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withPlace(String place) {
        this.meetingPlace = new MeetingPlace(place);
        return this;
    }

    /**
     * Sets the {@code MeetingTime} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withTime(String time) {
        this.meetingTime = new MeetingTime(time);
        return this;
    }

    /**
     * Parses the {@code attendees} into a {@code Set<Id>} and set it to the {@code Meeting} that we are building.
     */
    public MeetingBuilder withAttendees(String... attendees) {
        this.attendees = SampleDataUtil.getAttendeeSet(attendees);
        return this;
    }

    /**
     * Builds a {@code Meeting} with the fields of {@code MeetingBuilder}
     */
    public Meeting build() {
        return new Meeting(agenda, meetingPlace, meetingTime, attendees);
    }

    @Override
    public String toString() {
        return "MeetingBuilder{"
                + "agenda=" + agenda
                + ", meetingPlace=" + meetingPlace
                + ", meetingTime=" + meetingTime
                + ", attendees=" + attendees
                + '}';
    }
}
