package seedu.address.model.meeting;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;


/**
 * Stores the details to edit the meeting with. Each non-empty field value will replace the
 * corresponding field value of the person.
 */
public class UpdateMeetingDescriptor {
    private Agenda agenda;
    private MeetingPlace meetingPlace;
    private MeetingTime meetingTime;
    private HashSet<Index> indexes;

    public UpdateMeetingDescriptor() {
    }

    /**
     * Copy constructor.
     * A defensive copy of {@code attendees} is used internally.
     */
    public UpdateMeetingDescriptor(UpdateMeetingDescriptor toCopy) {
        setIndexes(toCopy.indexes);
        setMeetingTime(toCopy.meetingTime);
        setMeetingPlace(toCopy.meetingPlace);
        setAgenda(toCopy.agenda);

    }

    /**
     * Meeting constructor.
     */
    public UpdateMeetingDescriptor(Meeting toCopy) {
        setAgenda(toCopy.getAgenda());
        setMeetingPlace(toCopy.getPlace());
        setMeetingTime(toCopy.getTime());
        setIndexes(toCopy.getIndexes());
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(agenda, indexes, meetingPlace, meetingTime);
    }

    /**
     * Returns true if attendees are edited.
     */
    public boolean areAttendeesChanged() {
        return indexes != null;
    }

    //----Single data fields----
    //Agenda
    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Optional<Agenda> getAgenda() {
        return Optional.ofNullable(agenda);
    }

    //MeetingPlace
    public void setMeetingPlace(MeetingPlace meetingPlace) {
        this.meetingPlace = meetingPlace;
    }

    public Optional<MeetingPlace> getMeetingPlace() {
        return Optional.ofNullable(meetingPlace);
    }

    //MeetingTime
    public void setMeetingTime(MeetingTime meetingTime) {
        this.meetingTime = meetingTime;
    }

    public Optional<MeetingTime> getMeetingTime() {
        return Optional.ofNullable(meetingTime);
    }

    /**
     * Sets {@code attendees} to this object's {@code attendees}.
     * A defensive copy of {@code attendees} is used internally.
     */
    public void setIndexes(Set<Index> indexes) {
        this.indexes = (indexes != null) ? new HashSet<>(indexes) : null;
    }

    /**
     * Returns an unmodifiable index set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code attendees} is null.
     */
    public Optional<Set<Index>> getIndexes() {
        return (indexes != null) ? Optional.of(Collections.unmodifiableSet(indexes)) : Optional.empty();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdateMeetingDescriptor)) {
            return false;
        }

        // state check
        UpdateMeetingDescriptor e = (UpdateMeetingDescriptor) other;

        return getAgenda().equals(e.getAgenda())
                && getMeetingPlace().equals(e.getMeetingPlace())
                && getMeetingTime().equals(e.getMeetingTime())
                && getIndexes().equals(e.getIndexes());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UpdateMeetingDescriptor: [");
        Set<Index> attendees = getIndexes().get();
        builder.append("Attendees: ");
        attendees.forEach(index -> builder.append(index).append(" "));

        builder.append("; Agenda: ")
                .append(getAgenda())
                .append("; MeetingPlace: ")
                .append(getMeetingPlace())
                .append("; MeetingTime: ")
                .append(getMeetingTime());

        builder.append("]");
        return builder.toString();
    }
}
