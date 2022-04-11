package seedu.address.testutil.meeting;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL;
import static seedu.address.model.meeting.MeetingDateTime.INPUT_FORMAT;

import java.time.LocalDateTime;
import java.util.Set;

import seedu.address.logic.commands.meeting.AddMeetingCommand;
import seedu.address.logic.commands.meeting.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.tag.Tag;

public class MeetingUtil {
    public static String getAddMeetingCommand(Meeting meeting) {
        return AddMeetingCommand.COMMAND_WORD + " " + getMeetingDetails(meeting);
    }

    public static String getMeetingDetails(Meeting meeting) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + meeting.getName().name + " ");
        sb.append(PREFIX_URL + meeting.getUrl().toString() + " ");
        sb.append(PREFIX_DATETIME + meeting.getStartDateTime().datetime.format(INPUT_FORMAT) + " ");
        sb.append(PREFIX_DURATION + String.valueOf(meeting.getDuration().duration) + " ");
        sb.append(PREFIX_MODULE + "1" + " ");
        sb.append(PREFIX_RECURRING + meeting.getIsRecurring().toString() + " ");
        meeting.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    public static Meeting getRelativeNonRecurringMeeting(long offsetDays) {
        LocalDateTime relativeDateTime = LocalDateTime.now();
        relativeDateTime = relativeDateTime.plusDays(offsetDays);
        Meeting relativeMeeting = new MeetingBuilder()
                .withDateTime(relativeDateTime.format(INPUT_FORMAT)).withIsRecurring("N").build();
        return relativeMeeting;
    }

    /**
     * Returns the part of command string for the given {@code EditMeetingDescriptor}'s details.
     */
    public static String getEditMeetingDescriptorDetails(EditMeetingDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name).append(" "));
        descriptor.getUrl().ifPresent(url -> sb.append(PREFIX_URL).append(url).append(" "));
        descriptor.getDateTime().ifPresent(datetime -> sb.append(PREFIX_DATETIME)
                .append(datetime.datetime.format(INPUT_FORMAT)).append(" "));
        descriptor.getDuration().ifPresent(duration -> sb.append(PREFIX_DURATION).append(duration).append(" "));
        descriptor.getModuleIndex().ifPresent(moduleIndex -> sb.append(PREFIX_MODULE)
                .append(moduleIndex.getOneBased()).append(" "));
        descriptor.getIsRecurring().ifPresent(isRecurring ->
                sb.append(PREFIX_RECURRING).append(isRecurring).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
