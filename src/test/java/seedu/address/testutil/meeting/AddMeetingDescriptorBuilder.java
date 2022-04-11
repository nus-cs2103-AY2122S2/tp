package seedu.address.testutil.meeting;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meeting.AddMeetingCommand.AddMeetingDescriptor;
import seedu.address.model.meeting.IsRecurring;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDateTime;
import seedu.address.model.meeting.MeetingDuration;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.MeetingUrl;
import seedu.address.model.tag.Tag;

public class AddMeetingDescriptorBuilder {
    private AddMeetingDescriptor descriptor;

    public AddMeetingDescriptorBuilder() {
        descriptor = new AddMeetingDescriptor();
    }

    public AddMeetingDescriptorBuilder(AddMeetingDescriptor descriptor) {
        this.descriptor = new AddMeetingDescriptor(descriptor);
    }

    /**
     * Returns an {@code AddMeetingDescriptor} with fields containing {@code meeting}'s details except module index.
     */
    public AddMeetingDescriptorBuilder(Meeting meeting) {
        descriptor = new AddMeetingDescriptor();
        descriptor.setName(meeting.getName());
        descriptor.setUrl(meeting.getUrl());
        descriptor.setDateTime(meeting.getStartDateTime());
        descriptor.setDuration(meeting.getDuration());
        descriptor.setIsRecurring(meeting.getIsRecurring());
        descriptor.setTags(meeting.getTags());
    }

    /**
     * Sets the {@code MeetingName} of the {@code AddMeetingDescriptor} that we are building.
     */
    public AddMeetingDescriptorBuilder withName(String name) {
        descriptor.setName(new MeetingName(name));
        return this;
    }

    /**
     * Sets the {@code Url} of the {@code AddMeetingDescriptor} that we are building.
     */
    public AddMeetingDescriptorBuilder withUrl(String url) {
        descriptor.setUrl(new MeetingUrl(url));
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code AddMeetingDescriptor} that we are building.
     */
    public AddMeetingDescriptorBuilder withDateTime(String dateTime) {
        descriptor.setDateTime(new MeetingDateTime(dateTime));
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code AddMeetingDescriptor} that we are building.
     */
    public AddMeetingDescriptorBuilder withDuration(String duration) {
        descriptor.setDuration(new MeetingDuration(duration));
        return this;
    }

    /**
     * Sets the {@code Module} of the {@code AddMeetingDescriptor} that we are building.
     */
    public AddMeetingDescriptorBuilder withModule(Index module) {
        descriptor.setModuleIndex(module);
        return this;
    }

    /**
     * Sets the {@code IsRecurring} of the {@code AddMeetingDescriptor} that we are building.
     */
    public AddMeetingDescriptorBuilder withIsRecurring(String isRecurring) {
        descriptor.setIsRecurring(new IsRecurring(isRecurring));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code AddMeetingDescriptor}
     * that we are building.
     */
    public AddMeetingDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public AddMeetingDescriptor build() {
        return descriptor;
    }

}
