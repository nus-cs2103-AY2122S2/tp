package seedu.contax.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class DateUtilTest {
    @Test
    public void parseDateTest() {
        assertThrows(NullPointerException.class, () -> DateUtil.parseDate(null));
        assertEquals(Optional.empty(), DateUtil.parseDate("32-10-2022")); // Invalid Day
        assertEquals(Optional.empty(), DateUtil.parseDate("0-10-2022")); // Invalid Day
        assertEquals(Optional.empty(), DateUtil.parseDate("ab-10-2022")); // Invalid Day
        assertEquals(Optional.empty(), DateUtil.parseDate("20-13-2022")); // Invalid Month
        assertEquals(Optional.empty(), DateUtil.parseDate("20-0-2022")); // Invalid Month
        assertEquals(Optional.empty(), DateUtil.parseDate("20-ab-2022")); // Invalid Month
        assertEquals(Optional.empty(), DateUtil.parseDate("20-10-abcd")); // Invalid Year
        assertEquals(Optional.empty(), DateUtil.parseDate("20-10-212")); // Invalid Year

        assertEquals(LocalDate.parse("2022-10-20"), DateUtil.parseDate("20-10-2022").get());
        assertEquals(LocalDate.parse("1971-03-30"), DateUtil.parseDate("30-03-1971").get());
    }

    @Test
    public void parseTimeTest() {
        assertThrows(NullPointerException.class, () -> DateUtil.parseTime(null));
        assertEquals(Optional.empty(), DateUtil.parseTime("25:00")); // Invalid Hour
        assertEquals(Optional.empty(), DateUtil.parseTime("ab:00")); // Invalid Hour
        assertEquals(Optional.empty(), DateUtil.parseTime("-1:00")); // Invalid Hour
        assertEquals(Optional.empty(), DateUtil.parseTime("12:61")); // Invalid Minute
        assertEquals(Optional.empty(), DateUtil.parseTime("12:ba")); // Invalid Minute
        assertEquals(Optional.empty(), DateUtil.parseTime("12:-1")); // Invalid Minute

        assertEquals(LocalTime.parse("00:00:00"), DateUtil.parseTime("00:00").get());
        assertEquals(LocalTime.parse("23:59:00"), DateUtil.parseTime("23:59").get());
        assertEquals(LocalTime.parse("01:59:00"), DateUtil.parseTime("01:59").get());
        assertEquals(LocalTime.parse("12:01:00"), DateUtil.parseTime("12:01").get());
    }

    @Test
    public void combineDateTimeTest() {
        LocalDate refDate = LocalDate.parse("2022-03-20");
        LocalTime refTime = LocalTime.parse("12:34");
        LocalTime offsetTime = LocalTime.parse("12:34:04");

        assertThrows(NullPointerException.class, () -> DateUtil.combineDateTime(null, null));
        assertThrows(NullPointerException.class, () -> DateUtil.combineDateTime(refDate, null));
        assertThrows(NullPointerException.class, () -> DateUtil.combineDateTime(null, refTime));

        assertEquals(LocalDateTime.parse("2022-03-20T12:34:00"), DateUtil.combineDateTime(refDate, refTime));
        assertEquals(DateUtil.combineDateTime(refDate, offsetTime),
                DateUtil.combineDateTime(refDate, refTime));
    }

    @Test
    public void updateDateTest() {
        LocalDateTime baselineDateTime1 = LocalDateTime.parse("2022-03-31T12:34:00");
        LocalDateTime baselineDateTime2 = LocalDateTime.parse("2022-02-28T12:34:03");
        LocalDate refDate1 = LocalDate.parse("2021-05-23");
        LocalDate refDate2 = LocalDate.parse("2020-03-31");

        assertThrows(NullPointerException.class, () -> DateUtil.updateDate(null, null));
        assertThrows(NullPointerException.class, () -> DateUtil.updateDate(baselineDateTime1, null));
        assertThrows(NullPointerException.class, () -> DateUtil.updateDate(null, refDate1));

        assertEquals(LocalDateTime.parse("2021-05-23T12:34:00"),
                DateUtil.updateDate(baselineDateTime1, refDate1));
        assertEquals(LocalDateTime.parse("2021-05-23T12:34:03"),
                DateUtil.updateDate(baselineDateTime2, refDate1)); // Time is unaffected, regardless of value

        // Test update order, since there is no 31 Feb and will cause an error
        assertEquals(LocalDateTime.parse("2020-03-31T12:34:03"),
                DateUtil.updateDate(baselineDateTime2, refDate2));
    }

    @Test
    public void updateTimeTest() {
        LocalDateTime baselineDateTime1 = LocalDateTime.parse("2022-03-31T12:34:00");
        LocalDateTime baselineDateTime2 = LocalDateTime.parse("2022-02-28T12:34:00");
        LocalTime refTime1 = LocalTime.parse("00:00:59");
        LocalTime refTime2 = LocalTime.parse("23:59");

        assertThrows(NullPointerException.class, () -> DateUtil.updateTime(null, null));
        assertThrows(NullPointerException.class, () -> DateUtil.updateTime(baselineDateTime1, null));
        assertThrows(NullPointerException.class, () -> DateUtil.updateTime(null, refTime1));

        assertEquals(LocalDateTime.parse("2022-03-31T00:00:00"),
                DateUtil.updateTime(baselineDateTime1, refTime1));
        assertEquals(LocalDateTime.parse("2022-02-28T23:59:00"),
                DateUtil.updateTime(baselineDateTime2, refTime2));
        assertNotEquals(DateUtil.updateTime(baselineDateTime1, refTime1),
                DateUtil.updateTime(baselineDateTime1, refTime2));
    }

    @Test
    public void isBeforeOrEqualTest() {
        LocalDateTime refDateTime = LocalDateTime.parse("2022-03-31T12:34:00");
        LocalDateTime timeAfterDateTime = LocalDateTime.parse("2022-03-31T12:35:00");
        LocalDateTime dayAfterDateTime = LocalDateTime.parse("2022-04-01T12:34:00");

        assertThrows(NullPointerException.class, () -> DateUtil.isBeforeOrEqual(null, null));
        assertThrows(NullPointerException.class, () -> DateUtil.isBeforeOrEqual(refDateTime, null));
        assertThrows(NullPointerException.class, () -> DateUtil.isBeforeOrEqual(null, refDateTime));

        assertTrue(DateUtil.isBeforeOrEqual(refDateTime, LocalDateTime.parse("2022-03-31T12:34:00")));
        assertFalse(DateUtil.isBeforeOrEqual(timeAfterDateTime, refDateTime));
        assertTrue(DateUtil.isBeforeOrEqual(refDateTime, timeAfterDateTime));

        assertFalse(DateUtil.isBeforeOrEqual(dayAfterDateTime, refDateTime));
        assertTrue(DateUtil.isBeforeOrEqual(refDateTime, dayAfterDateTime));
    }

    @Test
    public void isAfterOrEqualTest() {
        LocalDateTime refDateTime = LocalDateTime.parse("2022-03-31T12:34:00");
        LocalDateTime timeAfterDateTime = LocalDateTime.parse("2022-03-31T12:35:00");
        LocalDateTime dayAfterDateTime = LocalDateTime.parse("2022-04-01T12:34:00");

        assertThrows(NullPointerException.class, () -> DateUtil.isAfterOrEqual(null, null));
        assertThrows(NullPointerException.class, () -> DateUtil.isAfterOrEqual(refDateTime, null));
        assertThrows(NullPointerException.class, () -> DateUtil.isAfterOrEqual(null, refDateTime));

        assertTrue(DateUtil.isAfterOrEqual(refDateTime, LocalDateTime.parse("2022-03-31T12:34:00")));
        assertTrue(DateUtil.isAfterOrEqual(timeAfterDateTime, refDateTime));
        assertFalse(DateUtil.isAfterOrEqual(refDateTime, timeAfterDateTime));

        assertTrue(DateUtil.isAfterOrEqual(dayAfterDateTime, refDateTime));
        assertFalse(DateUtil.isAfterOrEqual(refDateTime, dayAfterDateTime));
    }
}
