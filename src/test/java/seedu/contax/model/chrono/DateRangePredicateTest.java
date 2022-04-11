package seedu.contax.model.chrono;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.contax.testutil.AppointmentBuilder;

public class DateRangePredicateTest {

    @Test
    public void constructorTest() {
        LocalDateTime refDateTime = LocalDateTime.parse("2022-12-12T12:34");
        assertThrows(NullPointerException.class, () -> new DateRangePredicate(null, null));
        assertThrows(NullPointerException.class, () -> new DateRangePredicate(refDateTime, null));
        assertThrows(NullPointerException.class, () -> new DateRangePredicate(null, refDateTime));
    }

    @Test
    public void predicateTest() {
        LocalDateTime rangeStart = LocalDateTime.parse("2022-12-12T12:34");
        LocalDateTime rangeEnd = LocalDateTime.parse("2022-12-13T12:34");
        DateRangePredicate predicate = new DateRangePredicate(rangeStart, rangeEnd);

        // Inclusive Border Tests
        assertTrue(predicate.test(new AppointmentBuilder().withDuration(60)
                .withStartDateTime(rangeStart).build()));
        assertTrue(predicate.test(new AppointmentBuilder().withDuration(60)
                .withStartDateTime(rangeEnd).build()));
        assertTrue(predicate.test(new AppointmentBuilder().withDuration(60)
                .withStartDateTime(rangeStart.minusMinutes(60)).build()));
        assertTrue(predicate.test(new AppointmentBuilder().withDuration(60)
                .withStartDateTime(rangeEnd.minusMinutes(60)).build()));

        // Containment Tests
        assertTrue(predicate.test(new AppointmentBuilder().withDuration(60)
                .withStartDateTime(rangeStart.plusMinutes(60)).build()));
        assertTrue(predicate.test(new AppointmentBuilder().withDuration(24 * 60 * 4)
                .withStartDateTime(rangeStart.minusDays(1)).build())); // 4 days

        // Appointments are partially in the range
        assertTrue(predicate.test(new AppointmentBuilder().withDuration(180)
                .withStartDateTime(rangeStart.minusMinutes(60)).build()));
        assertTrue(predicate.test(new AppointmentBuilder().withDuration(180)
                .withStartDateTime(rangeEnd.minusMinutes(60)).build()));

        // Completely disjoint
        assertFalse(predicate.test(new AppointmentBuilder().withDuration(60)
                .withStartDateTime(rangeStart.minusMinutes(61)).build()));
        assertFalse(predicate.test(new AppointmentBuilder().withDuration(180)
                .withStartDateTime(rangeEnd.plusMinutes(1)).build()));
    }
}
