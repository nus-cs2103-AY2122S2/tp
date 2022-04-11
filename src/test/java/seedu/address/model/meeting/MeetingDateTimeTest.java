package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.exceptions.InvalidDateTimeException;

public class MeetingDateTimeTest {
    // Null meeting date time and empty string.
    final List<String> nullAndEmptyDateTimes = Arrays.asList("", null);

    // Invalid meeting date times in the right format.
    final List<String> invalidDateTimesInRightFormat = List.of(
        "00-01-2022 1400",
        "29-02-2022 1400",
        "32-03-2022 1400",
        "04-04-2022 2400"
    );

    // Valid date times in the incorrect format.
    final List<String> validDateTimesInIncorrectFormat = List.of(
        "1-01-2022 1400",
        "01-1-2022 1400",
        "01-1-2022 14",
        "1-1-2022 1400",
        "01-1-2022 14",
        "1-01-2022 14",
        "1-01-2022 14",
        "01-01-2022",
        "1400"
    );

    // Valid date times in the right format.
    final List<String> validDateTimesInRightFormat = List.of(
        "14-10-2022 0000",
        "28-02-2022 1030",
        "29-02-2020 1030"
    );

    @Test
    public void isValidDateTime() {
        final Consumer<String> processPositiveTestInput = dateTimeInput ->
                assertTrue(MeetingDateTime.isValidDateTime(dateTimeInput));
        final Consumer<String> processNegativeTestInput = dateTimeInput ->
                assertFalse(MeetingDateTime.isValidDateTime(dateTimeInput));

        testInputs(processPositiveTestInput, processNegativeTestInput);
    }

    @Test
    public void parseDateTime() {
        final Consumer<String> processPositiveTestInput = dateTimeInput ->
                assertEquals(makeLocalDateTime(dateTimeInput), MeetingDateTime.parseDateTime(dateTimeInput));
        final Consumer<String> processNegativeTestInput = dateTimeInput ->
                assertThrows(InvalidDateTimeException.class, () -> MeetingDateTime.parseDateTime(dateTimeInput));

        testInputs(processPositiveTestInput, processNegativeTestInput);
    }

    private void testInputs(Consumer<String> processPositiveTestInput, Consumer<String> processNegativeTestInput) {
        final Stream<String> negativeTestInputs = Stream.of(
                nullAndEmptyDateTimes,
                invalidDateTimesInRightFormat,
                validDateTimesInIncorrectFormat
        ).flatMap(Collection::stream);

        negativeTestInputs.forEach(processNegativeTestInput);
        validDateTimesInRightFormat.forEach(processPositiveTestInput);
    }

    private LocalDateTime makeLocalDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, MeetingDateTime.INPUT_FORMAT);
    }

    @Test
    public void testToString() {
        final LocalDateTime testDateTime = makeLocalDateTime("04-05-2022 1035");
        final MeetingDateTime testMeetingDateTime = new MeetingDateTime(testDateTime);
        final String expected = testDateTime.format(MeetingDateTime.DISPLAY_FORMAT);
        assertEquals(expected, testMeetingDateTime.toString());
    }

    @Test
    public void equals() {
        final MeetingDateTime firstTestDateTime = new MeetingDateTime("01-01-2022 1400");
        final MeetingDateTime firstWithDifferentTime = new MeetingDateTime("01-01-2022 1500");
        final MeetingDateTime secondTestDateTime = new MeetingDateTime("02-02-2022 1600");

        // Same values -> returns true.
        final MeetingDateTime firstTestDateTimeCopy = new MeetingDateTime(firstTestDateTime.datetime);
        assertTrue(firstTestDateTime.equals(firstTestDateTimeCopy));

        // Same object -> returns true.
        assertTrue(firstTestDateTime.equals(firstTestDateTime));

        // Null -> returns false.
        assertFalse(firstTestDateTime.equals(null));

        // Different type -> returns false.
        assertFalse(firstTestDateTime.equals(10));

        // Different meeting datetime -> returns false.
        assertFalse(firstTestDateTime.equals(secondTestDateTime));

        // Same date different time -> returns false.
        assertFalse(firstTestDateTime.equals(firstWithDifferentTime));
    }
}
