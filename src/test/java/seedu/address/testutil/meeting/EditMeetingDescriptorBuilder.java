package seedu.address.testutil.meeting;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meeting.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.model.meeting.IsRecurring;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDateTime;
import seedu.address.model.meeting.MeetingDuration;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.MeetingUrl;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditMeetingDescriptor objects.
 */
public class EditMeetingDescriptorBuilder {
    private EditMeetingDescriptor descriptor;

    public EditMeetingDescriptorBuilder() {
        descriptor = new EditMeetingDescriptor();
    }

    public EditMeetingDescriptorBuilder(EditMeetingDescriptor descriptor) {
        this.descriptor = new EditMeetingDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditMeetingDescriptor} with fields containing {@code meeting}'s details
     */
    public EditMeetingDescriptorBuilder(Meeting meeting) {
        descriptor = new EditMeetingDescriptor();
        descriptor.setName(meeting.getName());
        descriptor.setUrl(meeting.getUrl());
        descriptor.setDateTime(meeting.getStartDateTime());
        descriptor.setDuration(meeting.getDuration());
        descriptor.setIsRecurring(meeting.getIsRecurring());
        descriptor.setTags(meeting.getTags());
    }

    /**
     * Sets the {@code MeetingName} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withName(String name) {
        descriptor.setName(new MeetingName(name));
        return this;
    }

    /**
     * Sets the {@code Url} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withUrl(String url) {
        descriptor.setUrl(new MeetingUrl(url));
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withDateTime(String dateTime) {
        descriptor.setDateTime(new MeetingDateTime(dateTime));
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withDuration(String duration) {
        descriptor.setDuration(new MeetingDuration(duration));
        return this;
    }

    /**
     * Sets the {@code Module} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withModule(Index module) {
        descriptor.setModuleIndex(module);
        return this;
    }

    /**
     * Sets the {@code IsRecurring} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withIsRecurring(String isRecurring) {
        descriptor.setIsRecurring(new IsRecurring(isRecurring));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditMeetingDescriptor}
     * that we are building.
     */
    public EditMeetingDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditMeetingDescriptor build() {
        return descriptor;
    }
}
