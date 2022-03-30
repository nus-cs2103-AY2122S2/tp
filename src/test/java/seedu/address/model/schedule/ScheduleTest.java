package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ScheduleBuilder;

public class ScheduleTest {
    private static final Schedule VALID_SCHEDULE = new ScheduleBuilder()
            .withScheduleDescription("final")
            .withScheduleDescription("nba final")
            .withDateTime("01/01/2030 2000")
            .build();
    private static final Schedule VALID_SCHEDULE_2 = new ScheduleBuilder()
            .withScheduleDescription("final")
            .withScheduleDescription("nba final")
            .withDateTime("01/01/2030 2000")
            .build();
    private static final Schedule VALID_SCHEDULE_3 = new ScheduleBuilder()
            .withScheduleDescription("training")
            .withScheduleDescription("shooting training")
            .withDateTime("01/01/2028 2000")
            .build();

    @Test
    public void sameSchedule() {
        assertTrue(VALID_SCHEDULE.equals(VALID_SCHEDULE));
        assertTrue(VALID_SCHEDULE.equals(VALID_SCHEDULE_2));
        assertFalse(VALID_SCHEDULE.equals(VALID_SCHEDULE_3));
    }

    @Test
    public void isActive() {
        assertTrue(VALID_SCHEDULE.isActive());
        assertTrue(VALID_SCHEDULE_2.isActive());
        assertTrue(VALID_SCHEDULE_3.isActive());
    }
}
