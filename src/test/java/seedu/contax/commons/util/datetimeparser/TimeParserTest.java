package seedu.contax.commons.util.datetimeparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class TimeParserTest {
    @Test
    public void parseTime_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> TimeParser.parseTime(null));
    }

    @Test
    public void parse24HourTime() {
        // Missing Components
        assertEquals(Optional.empty(), TimeParser.parseTime(":20"));
        assertEquals(Optional.empty(), TimeParser.parseTime("10:"));
        assertEquals(Optional.empty(), TimeParser.parseTime(":"));

        // Invalid Components
        assertEquals(Optional.empty(), TimeParser.parseTime("aa:20"));
        assertEquals(Optional.empty(), TimeParser.parseTime("25:20"));
        assertEquals(Optional.empty(), TimeParser.parseTime("-1:20"));
        assertEquals(Optional.empty(), TimeParser.parseTime("20:aa"));
        assertEquals(Optional.empty(), TimeParser.parseTime("20:60"));
        assertEquals(Optional.empty(), TimeParser.parseTime("20:-1"));
        assertEquals(Optional.empty(), TimeParser.parseTime("aa:aa"));

        // Different delimiters
        assertEquals(Optional.empty(), TimeParser.parseTime("aa-20"));
        assertEquals(Optional.empty(), TimeParser.parseTime("20-aa"));
        assertEquals(Optional.empty(), TimeParser.parseTime("aa-aa"));

        // Successful parsing
        assertEquals(LocalTime.of(0, 0), TimeParser.parseTime("00:00").get());
        assertEquals(LocalTime.of(23, 59), TimeParser.parseTime("23:59").get());
        assertEquals(LocalTime.of(12, 30), TimeParser.parseTime("12:30").get());
        assertEquals(LocalTime.of(11, 59), TimeParser.parseTime("11:59").get());
        assertEquals(LocalTime.of(12, 2), TimeParser.parseTime("12:02").get());

        // Different delimiters
        assertEquals(LocalTime.of(12, 30), TimeParser.parseTime("12-30").get());
        assertEquals(LocalTime.of(11, 59), TimeParser.parseTime("11-59").get());
        assertEquals(LocalTime.of(12, 2), TimeParser.parseTime("12-02").get());
    }

    @Test
    public void parse12HourTime() {
        // Missing Components
        assertEquals(Optional.empty(), TimeParser.parseTime(":20 pm"));
        assertEquals(Optional.empty(), TimeParser.parseTime("10: pm"));
        assertEquals(Optional.empty(), TimeParser.parseTime(": pm"));

        // Invalid Components
        assertEquals(Optional.empty(), TimeParser.parseTime("aa:20 pm"));
        assertEquals(Optional.empty(), TimeParser.parseTime("13:20 pm"));
        assertEquals(Optional.empty(), TimeParser.parseTime("13:20 am"));
        assertEquals(Optional.empty(), TimeParser.parseTime("00:05 pm"));
        assertEquals(Optional.empty(), TimeParser.parseTime("00:05 am"));
        assertEquals(Optional.empty(), TimeParser.parseTime("10:aa pm"));
        assertEquals(Optional.empty(), TimeParser.parseTime("10:60 pm"));
        assertEquals(Optional.empty(), TimeParser.parseTime("10:-1 pm"));
        assertEquals(Optional.empty(), TimeParser.parseTime("10:10 fm"));
        assertEquals(Optional.empty(), TimeParser.parseTime("aa:aa pm"));

        // Different delimiters
        assertEquals(Optional.empty(), TimeParser.parseTime("13-20 pm"));
        assertEquals(Optional.empty(), TimeParser.parseTime("13-20 am"));
        assertEquals(Optional.empty(), TimeParser.parseTime("10-10 fm"));

        // Fallback to 24 hour without pm/am modifier
        assertEquals(LocalTime.of(10, 0), TimeParser.parseTime("10:00").get());

        // Successful parsing
        assertEquals(LocalTime.of(0, 2), TimeParser.parseTime("12:02 am").get());
        assertEquals(LocalTime.of(12, 3), TimeParser.parseTime("12:03 pm").get());
        assertEquals(LocalTime.of(0, 59), TimeParser.parseTime("12:59 AM").get());
        assertEquals(LocalTime.of(12, 59), TimeParser.parseTime("12:59 PM").get());
        assertEquals(LocalTime.of(11, 59), TimeParser.parseTime("11:59 am").get());
        assertEquals(LocalTime.of(23, 59), TimeParser.parseTime("11:59 pm").get());
        assertEquals(LocalTime.of(0, 0), TimeParser.parseTime("12:00 am").get());
        assertEquals(LocalTime.of(12, 0), TimeParser.parseTime("12:00 pm").get());

        // Different delimiters
        assertEquals(LocalTime.of(12, 30), TimeParser.parseTime("12-30 pm").get());
        assertEquals(LocalTime.of(11, 59), TimeParser.parseTime("11-59 am").get());
        assertEquals(LocalTime.of(0, 2), TimeParser.parseTime("12-02 am").get());
    }
}
