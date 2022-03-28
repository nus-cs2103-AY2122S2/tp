package seedu.contax.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALONE;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.contax.model.AddressBook;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.UserPrefs;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.AppointmentSlot;
import seedu.contax.model.chrono.TimeRange;
import seedu.contax.testutil.AppointmentBuilder;

public class FreeBetweenCommandTest {

    private static final LocalDateTime BASE_DATE_TIME = APPOINTMENT_ALONE.getStartDateTime();
    private static final Appointment APPOINTMENT1 = new AppointmentBuilder(APPOINTMENT_ALONE)
            .withStartDateTime(BASE_DATE_TIME).withDuration(30).build();
    private static final Appointment APPOINTMENT2 = new AppointmentBuilder(APPOINTMENT_ALONE)
            .withStartDateTime(BASE_DATE_TIME.plusMinutes(60)).withDuration(30).build();
    private static final Appointment APPOINTMENT3 = new AppointmentBuilder(APPOINTMENT_ALONE)
            .withStartDateTime(BASE_DATE_TIME.plusMinutes(150)).withDuration(10).build();
    private static final Appointment APPOINTMENT4 = new AppointmentBuilder(APPOINTMENT_ALONE)
            .withStartDateTime(BASE_DATE_TIME.plusMinutes(170)).withDuration(30).build();

    /*
        Test Appointments visualization
        Appointment1: 0 - 30
        Appointment2: 60 - 90
        Appointment3: 150 - 160
        Appointment4: 170 - 200
     */

    private Model model;

    @BeforeEach
    public void setup() {
        model = new ModelManager();

        model.addAppointment(APPOINTMENT1);
        model.addAppointment(APPOINTMENT2);
        model.addAppointment(APPOINTMENT3);
        model.addAppointment(APPOINTMENT4);
    }

    @Test
    public void constructor_nullRangeDates_throwsNullPointerException() {
        LocalDateTime refDateTime = LocalDateTime.parse("2022-12-12T12:34");
        assertThrows(NullPointerException.class, ()
            -> new FreeBetweenCommand(null, null, 20));
        assertThrows(NullPointerException.class, ()
            -> new FreeBetweenCommand(refDateTime, null, 20));
        assertThrows(NullPointerException.class, ()
            -> new FreeBetweenCommand(null, refDateTime, 20));
    }

    @Test
    public void execute_borderDates_freeSlots() throws Exception {

        ModelManager copyModel = resetModel();
        new FreeBetweenCommand(BASE_DATE_TIME, BASE_DATE_TIME.plusMinutes(60), 30).execute(copyModel);
        assertEquals(List.of(
                new AppointmentSlot(new TimeRange(BASE_DATE_TIME.plusMinutes(30), BASE_DATE_TIME.plusMinutes(60)))
        ), copyModel.getDisplayedAppointmentSlots());

        new FreeBetweenCommand(BASE_DATE_TIME, BASE_DATE_TIME.plusMinutes(60), 31).execute(copyModel);
        assertEquals(List.of(), copyModel.getDisplayedAppointmentSlots());

        new FreeBetweenCommand(BASE_DATE_TIME, BASE_DATE_TIME.plusMinutes(150), 30).execute(copyModel);
        assertEquals(List.of(
                new AppointmentSlot(new TimeRange(BASE_DATE_TIME.plusMinutes(30), BASE_DATE_TIME.plusMinutes(60))),
                new AppointmentSlot(new TimeRange(BASE_DATE_TIME.plusMinutes(90), BASE_DATE_TIME.plusMinutes(150)))
        ), copyModel.getDisplayedAppointmentSlots());

        new FreeBetweenCommand(BASE_DATE_TIME, BASE_DATE_TIME.plusMinutes(150), 35).execute(copyModel);
        assertEquals(List.of(
                new AppointmentSlot(new TimeRange(BASE_DATE_TIME.plusMinutes(90), BASE_DATE_TIME.plusMinutes(150)))
        ), copyModel.getDisplayedAppointmentSlots());
    }

    @Test
    public void execute_partialRanges_filtersAppointmentList() throws Exception {

        ModelManager copyModel = resetModel();
        new FreeBetweenCommand(BASE_DATE_TIME.plusMinutes(35), BASE_DATE_TIME.plusMinutes(50), 15)
                .execute(copyModel);
        assertEquals(List.of(
                new AppointmentSlot(new TimeRange(BASE_DATE_TIME.plusMinutes(35), BASE_DATE_TIME.plusMinutes(50)))
        ), copyModel.getDisplayedAppointmentSlots());

        new FreeBetweenCommand(BASE_DATE_TIME.plusMinutes(35), BASE_DATE_TIME.plusMinutes(50), 20)
                .execute(copyModel);
        assertEquals(List.of(), copyModel.getDisplayedAppointmentSlots());

        new FreeBetweenCommand(BASE_DATE_TIME.plusMinutes(35), BASE_DATE_TIME.plusMinutes(70), 25)
                .execute(copyModel);
        assertEquals(List.of(
                new AppointmentSlot(new TimeRange(BASE_DATE_TIME.plusMinutes(35), BASE_DATE_TIME.plusMinutes(60)))
        ), copyModel.getDisplayedAppointmentSlots());

        new FreeBetweenCommand(BASE_DATE_TIME.plusMinutes(35), BASE_DATE_TIME.plusMinutes(70), 26)
                .execute(copyModel);
        assertEquals(List.of(), copyModel.getDisplayedAppointmentSlots());

    }

    @Test
    public void execute_noUpperLimit_messageUpperLimitIsForever() {
        assertTrue(new FreeBetweenCommand(BASE_DATE_TIME, LocalDateTime.MAX, 60)
                .execute(new ModelManager()).getFeedbackToUser().contains("to "
                        + FreeBetweenCommand.PHRASE_NO_END_RANGE));
    }

    @Test
    public void equals() {
        LocalDateTime refDate1 = LocalDateTime.parse("2022-12-23T12:34");
        LocalDateTime refDate2 = LocalDateTime.parse("2022-12-24T12:34");
        LocalDateTime beforeRefDate1 = LocalDateTime.parse("2022-12-22T12:34");
        LocalDateTime afterRefDate2 = LocalDateTime.parse("2022-12-25T12:34");
        FreeBetweenCommand command1 = new FreeBetweenCommand(refDate1, refDate2, 30);

        // same object -> returns true
        assertTrue(command1.equals(command1));

        // same values -> returns true
        FreeBetweenCommand command1Copy = new FreeBetweenCommand(refDate1, refDate2, 30);
        assertTrue(command1.equals(command1Copy));

        // different types -> returns false
        assertFalse(command1.equals(1));

        // null -> returns false
        assertFalse(command1.equals(null));

        // different start datetime -> returns false
        assertFalse(command1.equals(new FreeBetweenCommand(beforeRefDate1, refDate2, 30)));

        // different end datetime -> returns false
        assertFalse(command1.equals(new FreeBetweenCommand(refDate1, afterRefDate2, 30)));

        // different duration -> returns false
        assertFalse(command1.equals(new FreeBetweenCommand(refDate1, refDate2, 29)));
    }

    private ModelManager resetModel() {
        return new ModelManager(new AddressBook(), model.getSchedule(), new UserPrefs());
    }
}
