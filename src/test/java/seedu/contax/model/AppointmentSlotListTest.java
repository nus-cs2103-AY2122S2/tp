package seedu.contax.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.contax.commons.util.DateUtil;
import seedu.contax.model.appointment.AppointmentSlot;
import seedu.contax.model.chrono.TimeRange;
import seedu.contax.testutil.AppointmentBuilder;
import seedu.contax.testutil.ScheduleBuilder;

public class AppointmentSlotListTest {

    private static final LocalDate REF_DATE = LocalDate.of(2022, 5, 29);

    private Schedule schedule;

    @BeforeEach
    public void initializeSchedule() {
        schedule = new ScheduleBuilder()
                .withAppointment(new AppointmentBuilder()
                        .withName("Test 1")
                        .withStartDateTime(DateUtil.combineDateTime(REF_DATE, LocalTime.of(12, 0)))
                        .withDuration(30)
                        .build()
                ).withAppointment(new AppointmentBuilder()
                        .withName("Test 2")
                        .withStartDateTime(DateUtil.combineDateTime(REF_DATE, LocalTime.of(13, 0)))
                        .withDuration(60)
                        .build()
                ).withAppointment(new AppointmentBuilder()
                        .withName("Test 3")
                        .withStartDateTime(DateUtil.combineDateTime(REF_DATE, LocalTime.of(15, 0)))
                        .withDuration(30)
                        .build()
                ).build();
    }

    @Test
    public void constructor_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentSlotList(null));
    }

    @Test
    public void constructor_emptyRange_nothingListed() {
        AppointmentSlotList slotList = new AppointmentSlotList(schedule);
        assertEquals(List.of(), slotList.getSlotList());
    }

    @Test
    public void constructor_validRange_slotsListed() {
        // Thorough testing is done in DisjointAppointmentListTest
        AppointmentSlotList slotList = new AppointmentSlotList(schedule);
        assertEquals(List.of(), slotList.getSlotList());

        slotList.updateFilteredRange(new TimeRange(
                DateUtil.combineDateTime(REF_DATE, LocalTime.of(12, 0)),
                DateUtil.combineDateTime(REF_DATE, LocalTime.of(16, 0))), 60);
        assertEquals(List.of(
                new AppointmentSlot(new TimeRange(
                        DateUtil.combineDateTime(REF_DATE, LocalTime.of(14, 0)),
                        DateUtil.combineDateTime(REF_DATE, LocalTime.of(15, 0))
                ))
        ), slotList.getSlotList());

        // Test delete update listener
        schedule.removeAppointment(schedule.getAppointmentList().get(2));
        assertEquals(List.of(
                new AppointmentSlot(new TimeRange(
                        DateUtil.combineDateTime(REF_DATE, LocalTime.of(14, 0)),
                        DateUtil.combineDateTime(REF_DATE, LocalTime.of(16, 0))
                ))
        ), slotList.getSlotList());

        // Test add update listener
        schedule.addAppointment(new AppointmentBuilder()
                .withName("Test 4")
                .withStartDateTime(DateUtil.combineDateTime(REF_DATE, LocalTime.of(15, 30)))
                .withDuration(15).build());
        assertEquals(List.of(
                new AppointmentSlot(new TimeRange(
                        DateUtil.combineDateTime(REF_DATE, LocalTime.of(14, 0)),
                        DateUtil.combineDateTime(REF_DATE, LocalTime.of(15, 30))
                ))
        ), slotList.getSlotList());

        // Test clearing
        slotList.updateFilteredRange(null, 0);
        assertEquals(List.of(), slotList.getSlotList());
    }

    @Test
    public void equals() {
        AppointmentSlotList slotList = new AppointmentSlotList(schedule);
        AppointmentSlotList slotList2 = new AppointmentSlotList(schedule);
        LocalDateTime refDateTime = LocalDateTime.of(2020, 1, 1, 1, 1);

        // same values -> returns true
        assertTrue(slotList.equals(slotList2));

        // same object -> returns true
        assertTrue(slotList.equals(slotList));

        // null -> returns false
        assertFalse(slotList.equals(null));

        // different schedule -> returns false
        assertFalse(slotList.equals(new AppointmentSlotList(new Schedule())));

        // different time range -> returns false
        slotList.updateFilteredRange(new TimeRange(LocalDateTime.MIN, refDateTime), 1);
        slotList2.updateFilteredRange(new TimeRange(LocalDateTime.MIN, refDateTime.plusYears(1)), 1);
        assertFalse(slotList.equals(slotList2));

        // different duration -> returns false
        slotList.updateFilteredRange(new TimeRange(LocalDateTime.MIN, refDateTime), 1);
        slotList2.updateFilteredRange(new TimeRange(LocalDateTime.MIN, refDateTime), 2);
        assertFalse(slotList.equals(slotList2));
    }

    @Test
    public void hashCodeTest() {
        AppointmentSlotList slotList = new AppointmentSlotList(schedule);
        AppointmentSlotList slotList2 = new AppointmentSlotList(schedule);

        assertEquals(slotList.hashCode(), slotList2.hashCode());
        assertNotEquals(slotList.hashCode(), new AppointmentSlotList(new Schedule()).hashCode());

        // Different time range
        slotList.updateFilteredRange(new TimeRange(LocalDateTime.MIN, LocalDateTime.MIN), 1);
        slotList2.updateFilteredRange(new TimeRange(LocalDateTime.MIN, LocalDateTime.MAX), 1);
        assertNotEquals(slotList.hashCode(), slotList2.hashCode());

        // Different duration
        slotList.updateFilteredRange(new TimeRange(LocalDateTime.MIN, LocalDateTime.MAX), 1);
        slotList2.updateFilteredRange(new TimeRange(LocalDateTime.MIN, LocalDateTime.MAX), 2);
        assertNotEquals(slotList.hashCode(), slotList2.hashCode());
    }
}
