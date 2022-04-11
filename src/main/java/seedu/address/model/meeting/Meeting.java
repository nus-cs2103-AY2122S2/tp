package seedu.address.model.meeting;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Id;

/**
 * Represents a meeting of which the user is a part.
 */
public class Meeting implements Comparable<Meeting> {
    private final Agenda agenda;
    private final MeetingPlace place;
    private final MeetingTime time;
    private final HashSet<Index> indexes = new HashSet<>();
    private final HashSet<Id> attendees = new HashSet<>();

    /**
     * Constructs a new meeting with the given parameters
     *
     * @param agenda    Agenda of the meeting
     * @param place     Meeting place
     * @param time      Meeting time and date
     * @param indexes   Indexes of attendees in the displayed list
     * @param attendees Ids of attendees of the meeting
     */
    public Meeting(Agenda agenda, MeetingPlace place, MeetingTime time, Set<Index> indexes, Set<Id> attendees) {
        this.agenda = agenda;
        this.place = place;
        this.time = time;
        this.indexes.addAll(indexes);
        this.attendees.addAll(attendees);
    }

    /**
     * Constructs a new meeting with the given parameters
     *
     * @param agenda    Agenda of the meeting
     * @param place     Meeting place
     * @param time      Meeting time and date
     * @param attendees Ids of attendees of the meeting
     */
    public Meeting(Agenda agenda, MeetingPlace place, MeetingTime time, Set<Id> attendees) {
        this.agenda = agenda;
        this.place = place;
        this.time = time;
        this.attendees.addAll(attendees);
    }

    public Agenda getAgenda() {
        return this.agenda;
    }

    public MeetingPlace getPlace() {
        return this.place;
    }

    public MeetingTime getTime() {
        return this.time;
    }

    public HashSet<Index> getIndexes() {
        return this.indexes;
    }

    public HashSet<Id> getAttendees() {
        return this.attendees;
    }

    /**
     * Constructs a new meeting with the given parameters
     *
     * @param attendees Other attendees of the meeting
     */
    public Meeting setAttendees(Set<Id> attendees) {
        return new Meeting(agenda, place, time, attendees);
    }

    /**
     * Returns true if both meetings have the same agenda, time, place and attendees.
     */
    public boolean isSameMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
                && otherMeeting.getTime().equals(getTime())
                && otherMeeting.getAgenda().equals(getAgenda())
                && otherMeeting.getPlace().equals(getPlace())
                && otherMeeting.getAttendees().equals(getAttendees());
    }

    public boolean contains(Id attendee) {
        return attendees.contains(attendee);
    }

    /**
     * Enforces chronological order on meetings. If the timing is same, then we compare agenda, place and attendees.
     */
    @Override
    public int compareTo(Meeting other) {
        int timeCompare = getTime().compareTo((other.getTime()));
        int agendaCompare = getAgenda().compareTo((other.getAgenda()));
        int placeCompare = getPlace().compareTo((other.getPlace()));
        int attendeesCompare = getAttendees().toString().compareTo(other.getAttendees().toString());

        return timeCompare == 0
                ? (agendaCompare == 0
                ? (placeCompare == 0
                ? attendeesCompare
                : placeCompare)
                : agendaCompare)
                : timeCompare;
    }

    @Override
    public String toString() {
        //Cannot list attendees without Id implementation
        return "Agenda: " + agenda
                + "; place: " + place
                + "; time: " + time.toPrettyString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return otherMeeting != null
                && otherMeeting.getTime().equals(getTime())
                && otherMeeting.getAgenda().equals(getAgenda())
                && otherMeeting.getPlace().equals(getPlace())
                && otherMeeting.getAttendees().equals(getAttendees());
    }
}
