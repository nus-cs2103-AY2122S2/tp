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
import seedu.contax.testutil.AppointmentBuilder;

public class AppointmentsBetweenCommandTest {

    private static final LocalDateTime BASE_DATE_TIME = APPOINTMENT_ALONE.getStartDateTime();
    private static final Appointment APPOINTMENT1 = new AppointmentBuilder(APPOINTMENT_ALONE)
            .withStartDateTime(BASE_DATE_TIME).withDuration(30).build();
    private static final Appointment APPOINTMENT2 = new AppointmentBuilder(APPOINTMENT_ALONE)
            .withStartDateTime(BASE_DATE_TIME.plusMinutes(30)).withDuration(30).build();
    private static final Appointment APPOINTMENT3 = new AppointmentBuilder(APPOINTMENT_ALONE)
            .withStartDateTime(BASE_DATE_TIME.plusMinutes(60)).withDuration(60).build();
    private static final Appointment APPOINTMENT4 = new AppointmentBuilder(APPOINTMENT_ALONE)
            .withStartDateTime(BASE_DATE_TIME.plusMinutes(120)).withDuration(10).build();

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
            -> new AppointmentsBetweenCommand(null, null));
        assertThrows(NullPointerException.class, ()
            -> new AppointmentsBetweenCommand(refDateTime, null));
        assertThrows(NullPointerException.class, ()
            -> new AppointmentsBetweenCommand(null, refDateTime));
    }

    @Test
    public void execute_borderDates_filtersAppointmentList() throws Exception {

        ModelManager copyModel = resetModel();
        new AppointmentsBetweenCommand(BASE_DATE_TIME, BASE_DATE_TIME).execute(copyModel);
        assertEquals(List.of(APPOINTMENT1), copyModel.getFilteredAppointmentList());

        copyModel = resetModel();
        new AppointmentsBetweenCommand(BASE_DATE_TIME, BASE_DATE_TIME.plusMinutes(29)).execute(copyModel);
        assertEquals(List.of(APPOINTMENT1), copyModel.getFilteredAppointmentList());

        copyModel = resetModel();
        new AppointmentsBetweenCommand(BASE_DATE_TIME, BASE_DATE_TIME.plusMinutes(30)).execute(copyModel);
        assertEquals(List.of(APPOINTMENT1, APPOINTMENT2), copyModel.getFilteredAppointmentList());

        copyModel = resetModel();
        new AppointmentsBetweenCommand(BASE_DATE_TIME.plusMinutes(30), BASE_DATE_TIME.plusMinutes(60))
                .execute(copyModel);
        assertEquals(List.of(APPOINTMENT1, APPOINTMENT2, APPOINTMENT3), copyModel.getFilteredAppointmentList());

        copyModel = resetModel();
        new AppointmentsBetweenCommand(BASE_DATE_TIME.plusMinutes(31), BASE_DATE_TIME.plusMinutes(58))
                .execute(copyModel);
        assertEquals(List.of(APPOINTMENT2), copyModel.getFilteredAppointmentList());

        copyModel = resetModel();
        new AppointmentsBetweenCommand(BASE_DATE_TIME.plusMinutes(30), BASE_DATE_TIME.plusMinutes(59))
                .execute(copyModel);
        assertEquals(List.of(APPOINTMENT1, APPOINTMENT2), copyModel.getFilteredAppointmentList());

        copyModel = resetModel();
        new AppointmentsBetweenCommand(BASE_DATE_TIME.plusMinutes(31), BASE_DATE_TIME.plusMinutes(60))
                .execute(copyModel);
        assertEquals(List.of(APPOINTMENT2, APPOINTMENT3), copyModel.getFilteredAppointmentList());

    }

    @Test
    public void execute_partialRanges_filtersAppointmentList() throws Exception {

        ModelManager copyModel = resetModel();
        new AppointmentsBetweenCommand(BASE_DATE_TIME.minusMinutes(1), BASE_DATE_TIME.plusMinutes(10))
                .execute(copyModel);
        assertEquals(List.of(APPOINTMENT1), copyModel.getFilteredAppointmentList());

        copyModel = resetModel();
        new AppointmentsBetweenCommand(BASE_DATE_TIME.plusMinutes(125), BASE_DATE_TIME.plusMinutes(135))
                .execute(copyModel);
        assertEquals(List.of(APPOINTMENT4), copyModel.getFilteredAppointmentList());

        copyModel = resetModel();
        new AppointmentsBetweenCommand(BASE_DATE_TIME.plusMinutes(10), BASE_DATE_TIME.plusMinutes(20))
                .execute(copyModel);
        assertEquals(List.of(APPOINTMENT1), copyModel.getFilteredAppointmentList());

        copyModel = resetModel();
        new AppointmentsBetweenCommand(BASE_DATE_TIME.minusMinutes(10), BASE_DATE_TIME.plusMinutes(150))
                .execute(copyModel);
        assertEquals(List.of(APPOINTMENT1, APPOINTMENT2, APPOINTMENT3, APPOINTMENT4),
                copyModel.getFilteredAppointmentList());

    }

    @Test
    public void execute_noUpperLimit_messageUpperLimitIsForever() {
        assertTrue(new AppointmentsBetweenCommand(BASE_DATE_TIME, LocalDateTime.MAX)
                .execute(new ModelManager()).getFeedbackToUser().contains("to "
                        + AppointmentsBetweenCommand.PHRASE_NO_END_RANGE));
    }

    @Test
    public void equals() {
        LocalDateTime refDate1 = LocalDateTime.parse("2022-12-23T12:34");
        LocalDateTime refDate2 = LocalDateTime.parse("2022-12-24T12:34");
        LocalDateTime beforeRefDate1 = LocalDateTime.parse("2022-12-22T12:34");
        LocalDateTime afterRefDate2 = LocalDateTime.parse("2022-12-25T12:34");
        AppointmentsBetweenCommand command1 = new AppointmentsBetweenCommand(refDate1, refDate2);

        // same object -> returns true
        assertTrue(command1.equals(command1));

        // same values -> returns true
        AppointmentsBetweenCommand command1Copy = new AppointmentsBetweenCommand(refDate1, refDate2);
        assertTrue(command1.equals(command1Copy));

        // different types -> returns false
        assertFalse(command1.equals(1));

        // null -> returns false
        assertFalse(command1.equals(null));

        // different start datetime -> returns false
        assertFalse(command1.equals(new AppointmentsBetweenCommand(beforeRefDate1, refDate2)));

        // different end datetime -> returns false
        assertFalse(command1.equals(new AppointmentsBetweenCommand(refDate1, afterRefDate2)));
    }

    private ModelManager resetModel() {
        return new ModelManager(new AddressBook(), model.getSchedule(), new UserPrefs());
    }
}
