package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.address.model.attendance.AbsentAttendanceEntry;
import seedu.address.model.attendance.AttendanceEntry;
import seedu.address.model.attendance.MissingAttendanceEntry;
import seedu.address.model.attendance.PresentAttendanceEntry;
import seedu.address.model.pet.AttendanceHashMap;

public class AttendanceUtilTest {

    public static final LocalDate DATE_TODAY = LocalDate.now();
    public static final LocalTime PICKUP_TIME = LocalTime.of(9, 0);
    public static final LocalTime DROPOFF_TIME = LocalTime.of(18, 0);

    private static AttendanceHashMap allPresentAttendanceHashMap;
    private static AttendanceHashMap allAbsentAttendanceHashMap;
    private static AttendanceHashMap mixedAttendanceHashMap;
    private static final AttendanceHashMap missingAttendanceHashMap = new AttendanceHashMap();

    private static ArrayList<AttendanceEntry> allPresentAttendanceList;
    private static ArrayList<AttendanceEntry> allAbsentAttendanceList;
    private static ArrayList<AttendanceEntry> mixedAttendanceList;
    private static ArrayList<AttendanceEntry> missingAttendanceList;


    @BeforeAll
    public static void createAllPresentAttendanceScenario() {
        HashMap<LocalDate, AttendanceEntry> fullPresentHashMap = new HashMap<>();

        ArrayList<AttendanceEntry> fullPresentArrayList = new ArrayList<>();

        // creates an attendance hashmap with present entries
        // from 30 days ago till now.
        for (LocalDate d = DATE_TODAY.minusDays(30); d.isBefore(DATE_TODAY.plusDays(1)); d = d.plusDays(1)) {
            PresentAttendanceEntry currentEntry =
                    new PresentAttendanceEntry(d, PICKUP_TIME, DROPOFF_TIME);

            fullPresentHashMap.put(d, currentEntry);

            // creates an attendance list with entries from a week before till now.
            if (isWithinWeek(d)) {
                fullPresentArrayList.add(currentEntry);
            }
        }

        allPresentAttendanceHashMap = new AttendanceHashMap(fullPresentHashMap);
        allPresentAttendanceList = fullPresentArrayList;
    }

    @BeforeAll
    public static void createAllAbsentAttendanceScenario() {
        HashMap<LocalDate, AttendanceEntry> fullAbsentHashMap = new HashMap<>();

        ArrayList<AttendanceEntry> fullAbsentArrayList = new ArrayList<>();

        // creates an attendance hashmap with absent entries
        // from 30 days ago till now.
        for (LocalDate d = DATE_TODAY.minusDays(30); d.isBefore(DATE_TODAY.plusDays(1)); d = d.plusDays(1)) {
            AbsentAttendanceEntry currentEntry =
                    new AbsentAttendanceEntry(d);

            fullAbsentHashMap.put(d, currentEntry);

            // creates an attendance list with entries from a week before till now.
            if (isWithinWeek(d)) {
                fullAbsentArrayList.add(currentEntry);
            }
        }

        allAbsentAttendanceHashMap = new AttendanceHashMap(fullAbsentHashMap);
        allAbsentAttendanceList = fullAbsentArrayList;
    }

    @BeforeAll
    public static void createMixedAttendanceScenario() {
        // randomizer to create a mixed attendance hash map.
        Random rng = new Random();

        HashMap<LocalDate, AttendanceEntry> mixedHashMap = new HashMap<>();

        ArrayList<AttendanceEntry> mixedArrayList = new ArrayList<>();

        // creates an attendance hashmap with either absent or present entries
        // from 30 days ago till now.
        for (LocalDate d = DATE_TODAY.minusDays(30); d.isBefore(DATE_TODAY.plusDays(1)); d = d.plusDays(1)) {
            boolean isPresent = rng.nextBoolean();
            AttendanceEntry currentEntry;

            if (isPresent) {
                currentEntry = new PresentAttendanceEntry(d, PICKUP_TIME, DROPOFF_TIME);
            } else {
                currentEntry = new AbsentAttendanceEntry(d);
            }

            mixedHashMap.put(d, currentEntry);

            // creates an attendance list with entries from a week before till now.
            if (isWithinWeek(d)) {
                mixedArrayList.add(currentEntry);
            }
        }

        mixedAttendanceHashMap = new AttendanceHashMap(mixedHashMap);
        mixedAttendanceList = mixedArrayList;
    }

    @BeforeAll
    public static void createMissingAttendanceScenario() {
        ArrayList<AttendanceEntry> missingArrayList = new ArrayList<>();

        // creates an attendance list with missing entries for the week.
        for (LocalDate d = DATE_TODAY.minusDays(30); d.isBefore(DATE_TODAY.plusDays(1)); d = d.plusDays(1)) {
            if (isWithinWeek(d)) {
                missingArrayList.add(new MissingAttendanceEntry(d));
            }
        }

        missingAttendanceList = missingArrayList;
    }

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
        assertThrows(NullPointerException.class, () -> AttendanceUtil.getPastWeekAttendance(null));
    }

    @Test
    public void getPastWeekAttendance_missingAttendance_returnsMissingEntriesWeeklyAttendanceList() {
        assertEquals(missingAttendanceList, AttendanceUtil.getPastWeekAttendance(missingAttendanceHashMap));
    }

    @Test
    public void getPastWeekAttendance_allPresentAttendance_returnsCorrectWeeklyAttendanceList() {
        assertEquals(allPresentAttendanceList, AttendanceUtil.getPastWeekAttendance(allPresentAttendanceHashMap));
    }

    @Test
    public void getPastWeekAttendance_allAbsentAttendance_returnsCorrectWeeklyAttendanceList() {
        assertEquals(allAbsentAttendanceList, AttendanceUtil.getPastWeekAttendance(allAbsentAttendanceHashMap));
    }

    @Test
    public void getPastWeekAttendance_mixedAttendance_returnsCorrectWeeklyAttendanceList() {
        assertEquals(mixedAttendanceList, AttendanceUtil.getPastWeekAttendance(mixedAttendanceHashMap));
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
