package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class AttendanceUtilTest {

    public static final LocalDate DATE_TODAY = LocalDate.now();

    @Test
    public void isValidIsPresentString_validInputs_correctResult() {
        assertTrue(AttendanceUtil.isValidIsPresentString("true"));
        assertTrue(AttendanceUtil.isValidIsPresentString("false"));

        assertFalse(AttendanceUtil.isValidIsPresentString(""));
        assertFalse(AttendanceUtil.isValidIsPresentString("tue"));
        assertFalse(AttendanceUtil.isValidIsPresentString("True"));
        assertFalse(AttendanceUtil.isValidIsPresentString("False"));
    }

    @Test
    public void convertToModelDate_invalidInputs_throwsDateTimeParseException() {
        final String[] invalidDateStringArray =
                {"18-03-2022",
                        "18/03/2022",
                        "03-18-2022",
                        "03/18/2022",
                        "",
                        "ABC",
                        "09:00:00",
                        "0900",
                        "09:00",
                        "9",
                        "9.30 pm"};

        for (String s : invalidDateStringArray) {
            assertThrows(DateTimeParseException.class, () -> AttendanceUtil.convertToModelDate(s));
        }
    }

    @Test
    public void convertToModelTime_invalidInputs_throwsDateTimeParseException() {
        final String[] invalidTimeStringArray =
                {"0900",
                        "9",
                        "9.30 pm",
                        "18-03-2022",
                        "18/03/2022",
                        "03-18-2022",
                        "03/18/2022"};

        for (String s : invalidTimeStringArray) {
            assertThrows(DateTimeParseException.class, () -> AttendanceUtil.convertToModelTime(s));
        }
    }

    @Test
    public void getPastWeekAttendance_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> AttendanceUtil.getPastWeekAttendance(null));
    }

    /**
     * Checks that a given date is within the past week,
     * i.e. within the range of [n - 6, n] dates, where n is the date today.
     *
     * @param date the date to be checked
     * @return true if the given date is within the past week, false otherwise.
     */
    public static boolean isWithinWeek(LocalDate date) {
        LocalDate dateRangeStart = DATE_TODAY.minusDays(7);
        LocalDate dateRangeEnd = DATE_TODAY.plusDays(1);

        return date.isAfter(dateRangeStart) && date.isBefore(dateRangeEnd);
    }
}
