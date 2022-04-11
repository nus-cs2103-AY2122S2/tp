package seedu.address.testutil.typical;

import seedu.address.model.LinkyTime;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.module.Module;

/**
 * A utility class containing the data of a typical LinkyTime to be used in tests.
 */
public class TypicalLinkyTime {

    private TypicalLinkyTime() {
    } // prevents instantiation

    /**
     * Returns a {@code LinkyTime} with all the typical meetings.
     */
    public static LinkyTime getTypicalLinkyTime() {
        final LinkyTime typicalLinkyTime = new LinkyTime();
        for (final Meeting sampleMeeting : TypicalMeetings.getTypicalMeetings()) {
            typicalLinkyTime.addMeeting(sampleMeeting);
        }
        for (final Module sampleModule : TypicalModules.getTypicalModules()) {
            typicalLinkyTime.addModule(sampleModule);
        }
        return typicalLinkyTime;
    }
}
