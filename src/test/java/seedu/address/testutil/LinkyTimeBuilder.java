package seedu.address.testutil;

import seedu.address.model.LinkyTime;
import seedu.address.model.meeting.Meeting;

/**
 * A utility class to help with building a LinkyTime objects.
 * Example usage: <br>
 * {@code LinkyTime lt = new LinkyTimeBuilder().withMeeting(cs2103tTutorial).withMeeting(cs2106Lab).build();}
 */
public class LinkyTimeBuilder {
    private LinkyTime linkyTime;

    public LinkyTimeBuilder() {
        linkyTime = new LinkyTime();
    }

    public LinkyTimeBuilder(LinkyTime linkyTime) {
        this.linkyTime = linkyTime;
    }

    /**
     * Adds a new {@code Meeting} to the {@code LinkyTime} that we are building.
     */
    public LinkyTimeBuilder withMeeting(Meeting meeting) {
        linkyTime.addMeeting(meeting);
        return this;
    }

    public LinkyTime build() {
        return linkyTime;
    }
}
