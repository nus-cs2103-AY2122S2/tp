package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.schedule.ScheduleDateTime.isValidScheduleDateTime;

import org.junit.jupiter.api.Test;

public class ScheduleDateTimeTest {

    @Test
    public void validDateTime() {
        assertFalse(isValidScheduleDateTime("01/01/2022"));
        assertFalse(isValidScheduleDateTime("2022/01/01"));
        assertFalse(isValidScheduleDateTime("01/01/20221234"));
        assertFalse(isValidScheduleDateTime("60/13/1034 2022"));

        assertTrue(isValidScheduleDateTime("01/01/2000 2200"));
        assertTrue(isValidScheduleDateTime("31/10/2049 2000"));
    }

    @Test
    public void sameDateTime() {
        ScheduleDateTime dt1 = new ScheduleDateTime("01/01/2000 2200");
        ScheduleDateTime dt2 = new ScheduleDateTime("31/10/2049 2000");
        ScheduleDateTime dt3 = new ScheduleDateTime("31/10/2049 2100");

        assertTrue(dt1.equals(dt1));
        assertFalse(dt1.equals(dt2));
        assertFalse(dt2.equals(dt3));
    }
}
