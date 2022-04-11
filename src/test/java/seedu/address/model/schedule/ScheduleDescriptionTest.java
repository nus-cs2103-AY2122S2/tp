package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ScheduleDescriptionTest {
    private static final ScheduleDescription VALID_SCHEDULE_DESCRIPTION =
        new ScheduleDescription("dummy description");
    private static final ScheduleDescription VALID_SCHEDULE_DESCRIPTION_2 =
            new ScheduleDescription("dummy description");

    @Test
    public void sameDescription() {
        assertTrue(VALID_SCHEDULE_DESCRIPTION.equals(VALID_SCHEDULE_DESCRIPTION));
        assertTrue(VALID_SCHEDULE_DESCRIPTION.equals(VALID_SCHEDULE_DESCRIPTION_2));
    }
}
