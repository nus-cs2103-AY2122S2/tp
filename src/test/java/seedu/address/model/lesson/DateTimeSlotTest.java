package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DateTimeSlotTest {
    private final LocalDate d = LocalDate.of(2022, 1, 20);
    private final LocalDateTime dt = LocalDateTime.of(2022, 1, 20, 18, 0, 0);

    @Test
    public void constructor_validInputs_instantiatesSuccessfully() {
        // a datetime slot with zero hour and positive minutes is valid
        assertNotNull(new DateTimeSlot(dt, 0, 50));

        // valid inputs given to the constructor
        assertNotNull(new DateTimeSlot(d, "18:00", 1, 30));
    }

    @Test
    public void constructor_invalidInputs_throwsExceptions() {
        // null date should throw an exception
        assertThrows(NullPointerException.class, () -> new DateTimeSlot(null, 2));
    }

    @Test
    public void getDateString() {
        LocalDateTime d = LocalDateTime.of(2022, 1, 5, 17, 50, 0);
        DateTimeSlot dateTimeSlot = new DateTimeSlot(d, 1);

        String dateString = dateTimeSlot.getDateString();
        String expectedDateString = "Wednesday [5 January 2022]";

        assertEquals(dateString, expectedDateString);
    }

    @Test
    public void getDateOfLesson() {
        DateTimeSlot dateTimeSlot1 = new DateTimeSlot(dt, 1);
        assertEquals(dateTimeSlot1.getDateOfLesson(), dt);

        DateTimeSlot dateTimeSlot2 = new DateTimeSlot(d, "18:00", 1, 30);
        LocalDateTime dateTimeOfDateTimeSlot2 = d.atTime(18, 0);
        assertEquals(dateTimeSlot2.getDateOfLesson(), dateTimeOfDateTimeSlot2);
    }

    @Test
    public void getHours() {
        int durationInHours = 1;

        DateTimeSlot dateTimeSlot1 = new DateTimeSlot(dt, durationInHours);
        assertEquals(dateTimeSlot1.getHours(), durationInHours);

        DateTimeSlot dateTimeSlot2 = new DateTimeSlot(dt, durationInHours, 0);
        assertEquals(dateTimeSlot2.getHours(), durationInHours);
    }

    @Test
    public void getMinutes() {
        int durationInMinutes = 30;

        DateTimeSlot dateTimeSlot = new DateTimeSlot(dt, 0, durationInMinutes);
        assertEquals(dateTimeSlot.getMinutes(), durationInMinutes);
    }

    @Test
    public void getDayString() {
        String expectedDayString = "every Thursday";

        DateTimeSlot dateTimeSlot = new DateTimeSlot(dt, 1, 30);
        assertEquals(dateTimeSlot.getDayString(), expectedDayString);
    }

    @Test
    public void isValidStartTime_validInput_returnsTrue() {
        String validStartTime1 = "00:00";
        assertTrue(DateTimeSlot.isValidStartTime(validStartTime1));

        String validStartTime2 = "23:59";
        assertTrue(DateTimeSlot.isValidStartTime(validStartTime2));

        String validStartTime3 = "18:00";
        assertTrue(DateTimeSlot.isValidStartTime(validStartTime3));
    }

    @Test
    public void isValidDurationHours_validInput_returnsTrue() {
        // zero hours is acceptable
        String validDurationHours1 = "0"; // boundary-value
        assertTrue(DateTimeSlot.isValidDurationHours(validDurationHours1));

        // hours more than 24 are also acceptable
        String validDurationHours2 = "25";
        assertTrue(DateTimeSlot.isValidDurationHours(validDurationHours2));
    }

    @Test
    public void isValidDurationHours_invalidInputs_returnFalse() {
        // negative hours do not make sense in the context of this application
        String negativeDurationHours = "-1";
        assertFalse(DateTimeSlot.isValidDurationHours(negativeDurationHours));

        // un-parsable inputs should be rejected
        String nonNumericInput = "abc";
        assertFalse(DateTimeSlot.isValidDurationHours(nonNumericInput));

        // blank inputs should be rejected
        String blankInput = "";
        assertFalse(DateTimeSlot.isValidDurationHours(blankInput));
    }

    @Test
    public void isValidDurationMinutes_validInput_returnsTrue() {
        // zero minutes is acceptable
        String validDurationMinutes1 = "0"; // boundary-value
        assertTrue(DateTimeSlot.isValidDurationMinutes(validDurationMinutes1));

        String validDurationMinutes2 = "59";
        assertTrue(DateTimeSlot.isValidDurationMinutes(validDurationMinutes2));
    }

    @Test
    public void isValidDurationMinutes_invalidInputs_returnFalse() {
        // negative minutes do not make sense in the context of this application
        String negativeDurationMinutes = "-1";
        assertFalse(DateTimeSlot.isValidDurationMinutes(negativeDurationMinutes));

        // minutes greater than 59 do not make sense as users should then specify hours instead
        String exceedingDurationMinutes1 = "60"; // boundary-value
        assertFalse(DateTimeSlot.isValidDurationMinutes(exceedingDurationMinutes1));

        String exceedingDurationMinutes2 = "75";
        assertFalse(DateTimeSlot.isValidDurationMinutes(exceedingDurationMinutes2));

        // un-parsable inputs should be rejected
        String nonNumericInput = "abc";
        assertFalse(DateTimeSlot.isValidDurationMinutes(nonNumericInput));

        // blank inputs should be rejected
        String blankInput = "";
        assertFalse(DateTimeSlot.isValidDurationHours(blankInput));
    }

    @Test
    public void parseLessonDate_validInputs_returnsValidDate() {
        String validDateString1 = "18-01-2022";
        LocalDate expectedDate1 = LocalDate.of(2022, 1, 18);
        assertEquals(DateTimeSlot.parseLessonDate(validDateString1), expectedDate1);

        String validDateString2 = "1-1-2022";
        LocalDate expectedDate2 = LocalDate.of(2022, 1, 1);
        assertEquals(DateTimeSlot.parseLessonDate(validDateString2), expectedDate2);
    }

    @Test
    public void parseLessonDate_invalidInputs_throwsException() {
        String emptyString = "";
        assertThrows(DateTimeParseException.class, () ->
                DateTimeSlot.parseLessonDate(emptyString));

        // dates should be given in DD-MM-YYYY format (with dashes as separators, not slashes)
        String invalidDateString = "18/01/2022";
        assertThrows(DateTimeParseException.class, () ->
                DateTimeSlot.parseLessonDate(invalidDateString));
    }

    @Test
    public void parseLessonDurationHours_validInputs_returnsValidDurationHours() {
        // zero hours is acceptable
        String validDurationHours1 = "0"; // boundary-value
        Integer expectedDurationHours1 = 0;
        assertEquals(DateTimeSlot.parseLessonDurationHours(validDurationHours1), expectedDurationHours1);

        // hours more than 24 are also acceptable
        String validDurationHours2 = "25";
        Integer expectedDurationHours2 = 25;
        assertEquals(DateTimeSlot.parseLessonDurationHours(validDurationHours2), expectedDurationHours2);
    }

    @Test
    public void parseLessonDurationMinutes_validInputs_returnsValidDurationMinutes() {
        // zero minutes is acceptable
        String validDurationMinutes1 = "0"; // boundary-value
        Integer expectedDurationMinutes1 = 0;
        assertEquals(DateTimeSlot.parseLessonDurationMinutes(validDurationMinutes1), expectedDurationMinutes1);

        String validDurationMinutes2 = "59";
        Integer expectedDurationMinutes2 = 59;
        assertEquals(DateTimeSlot.parseLessonDurationMinutes(validDurationMinutes2), expectedDurationMinutes2);
    }

    @Test
    public void getJsonDate() {
        LocalDateTime dateTime = LocalDateTime.of(2022, 1, 1, 18, 0);
        String expectedJsonString = "1-1-2022";
        DateTimeSlot dateTimeSlot = new DateTimeSlot(dateTime, 1, 30);

        assertEquals(dateTimeSlot.getJsonDate(), expectedJsonString);
    }

    @Test
    public void getJsonStartTime() {
        LocalDate date = LocalDate.of(2022, 1, 1);
        String startTime = "18:00";
        String expectedJsonString = "18:00";
        DateTimeSlot dateTimeSlot = new DateTimeSlot(date, startTime, 1, 30);

        assertEquals(dateTimeSlot.getJsonStartTime(), expectedJsonString);
    }

    @Test
    public void getJsonDurationHours() {
        LocalDateTime dateTime = LocalDateTime.of(2022, 1, 1, 18, 0);

        Integer durationHours1 = 0;
        String expectedJsonString1 = "0";
        DateTimeSlot dateTimeSlot1 = new DateTimeSlot(dateTime, durationHours1);

        assertEquals(dateTimeSlot1.getJsonDurationHours(), expectedJsonString1);

        Integer durationHours2 = 5;
        String expectedJsonString2 = "5";
        DateTimeSlot dateTimeSlot2 = new DateTimeSlot(dateTime, durationHours2);

        assertEquals(dateTimeSlot2.getJsonDurationHours(), expectedJsonString2);
    }

    @Test
    public void getJsonDurationMinutes() {
        LocalDateTime dateTime = LocalDateTime.of(2022, 1, 1, 18, 0);

        Integer durationMinutes1 = 0; // boundary-value
        String expectedJsonString1 = "0";
        DateTimeSlot dateTimeSlot1 = new DateTimeSlot(dateTime, 1, durationMinutes1);

        assertEquals(dateTimeSlot1.getJsonDurationMinutes(), expectedJsonString1);

        Integer durationMinutes2 = 30;
        String expectedJsonString2 = "30";
        DateTimeSlot dateTimeSlot2 = new DateTimeSlot(dateTime, 0, durationMinutes2);

        assertEquals(dateTimeSlot2.getJsonDurationMinutes(), expectedJsonString2);
    }

    @Test
    public void getTimeString() {
        LocalDateTime d = LocalDateTime.of(2022, 1, 5, 17, 50, 0);
        DateTimeSlot dateTimeSlot = new DateTimeSlot(d, 1);

        String timeString = dateTimeSlot.getTimeString();
        String expectedTimeString = "5:50 PM - 6:50 PM";

        assertEquals(timeString, expectedTimeString);
    }
}
