package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_APPOINTMENT1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_APPOINTMENT2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_APPOINTMENT3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_APPOINTMENT1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_APPOINTMENT2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_APPOINTMENT3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AppointmentBook;
import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {
    public static final Appointment APPOINTMENT1 = new AppointmentBuilder()
            .withDescription(VALID_DESC_APPOINTMENT1)
            .withDateTime(VALID_DATETIME_APPOINTMENT1).build();

    public static final Appointment APPOINTMENT2 = new AppointmentBuilder()
            .withDescription(VALID_DESC_APPOINTMENT2)
            .withDateTime(VALID_DATETIME_APPOINTMENT2).build();

    public static final Appointment APPOINTMENT3 = new AppointmentBuilder()
            .withDescription(VALID_DESC_APPOINTMENT3)
            .withDateTime(VALID_DATETIME_APPOINTMENT3).build();

    private TypicalAppointments() {} // prevents instantiation

    /**
     * Returns an {@code AppointmentBook} with all the typical appointments.
     */
    public static AppointmentBook getTypicalAppointmentBook() {
        AppointmentBook ab = new AppointmentBook();
        for (Appointment appointment : getTypicalAppointments()) {
            ab.addAppointment(appointment);
        }
        return ab;
    }

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(APPOINTMENT1, APPOINTMENT2, APPOINTMENT3));
    }
}
