package seedu.contax.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.contax.model.chrono.DateRangePredicate;
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
                .withStartDateTime(LocalDateTime.parse("2022-12-12T12:34")).build()));
        assertTrue(predicate.test(new AppointmentBuilder().withDuration(60)
                .withStartDateTime(LocalDateTime.parse("2022-12-13T12:34")).build()));
        assertTrue(predicate.test(new AppointmentBuilder().withDuration(60)
                .withStartDateTime(LocalDateTime.parse("2022-12-12T11:34")).build()));
        assertTrue(predicate.test(new AppointmentBuilder().withDuration(60)
                .withStartDateTime(LocalDateTime.parse("2022-12-13T11:34")).build()));

        // Containment Tests
        assertTrue(predicate.test(new AppointmentBuilder().withDuration(60)
                .withStartDateTime(LocalDateTime.parse("2022-12-12T13:34")).build()));
        assertTrue(predicate.test(new AppointmentBuilder().withDuration(24 * 60 * 4)
                .withStartDateTime(LocalDateTime.parse("2022-12-11T13:34")).build())); // 4 days

        // Appointments are partially in the range
        assertTrue(predicate.test(new AppointmentBuilder().withDuration(180)
                .withStartDateTime(LocalDateTime.parse("2022-12-12T11:34")).build()));
        assertTrue(predicate.test(new AppointmentBuilder().withDuration(180)
                .withStartDateTime(LocalDateTime.parse("2022-12-12T11:34")).build()));

        // Completely disjoint
        assertFalse(predicate.test(new AppointmentBuilder().withDuration(60)
                .withStartDateTime(LocalDateTime.parse("2022-12-12T11:33")).build()));
        assertFalse(predicate.test(new AppointmentBuilder().withDuration(180)
                .withStartDateTime(LocalDateTime.parse("2022-12-13T12:35")).build()));
    }
}
