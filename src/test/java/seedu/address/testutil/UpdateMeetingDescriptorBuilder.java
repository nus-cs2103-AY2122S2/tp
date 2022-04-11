package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.model.meeting.Agenda;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingPlace;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.meeting.UpdateMeetingDescriptor;

/**
 * A utility class to help with building UpdateMeetingDescriptor objects.
 */
public class UpdateMeetingDescriptorBuilder {

    private UpdateMeetingDescriptor descriptor;

    public UpdateMeetingDescriptorBuilder() {
        descriptor = new UpdateMeetingDescriptor();
    }

    public UpdateMeetingDescriptorBuilder(UpdateMeetingDescriptor descriptor) {
        this.descriptor = new UpdateMeetingDescriptor(descriptor);
    }

    /**
     * Returns an {@code UpdateMeetingDescriptor} with fields containing {@code meeting}'s details
     */
    public UpdateMeetingDescriptorBuilder(Meeting meeting) {
        descriptor = new UpdateMeetingDescriptor();

        descriptor.setAgenda(meeting.getAgenda());
        descriptor.setMeetingTime(meeting.getTime());
        descriptor.setMeetingPlace(meeting.getPlace());
        descriptor.setIndexes(meeting.getIndexes());
    }

    /**
     * Sets the {@code Agenda} of the {@code UpdateMeetingDescriptor} that we are building.
     */
    public UpdateMeetingDescriptorBuilder withAgenda(String agenda) {
        descriptor.setAgenda(new Agenda(agenda));
        return this;
    }

    /**
     * Sets the {@code MeetingTime} of the {@code UpdateMeetingDescriptor} that we are building.
     */
    public UpdateMeetingDescriptorBuilder withTime(String meetingTime) {
        descriptor.setMeetingTime(new MeetingTime(meetingTime));
        return this;
    }

    /**
     * Sets the {@code MeetingPlace} of the {@code UpdateMeetingDescriptor} that we are building.
     */
    public UpdateMeetingDescriptorBuilder withPlace(String meetingPlace) {
        descriptor.setMeetingPlace(new MeetingPlace(meetingPlace));
        return this;
    }

    /**
     * Sets the {@code attendees} of the {@code UpdateMeetingDescriptor} that we are building.
     */
    public UpdateMeetingDescriptorBuilder withAttendees(String... attendees) {
        Set<Index> indexes = Stream.of(attendees).map(a -> Index.fromOneBased(Integer.parseInt(a)))
                .collect(Collectors.toSet());
        descriptor.setIndexes(indexes);
        return this;
    }



    public UpdateMeetingDescriptor build() {
        return descriptor;
    }
}
