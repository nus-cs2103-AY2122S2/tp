package woofareyou.model.pet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import woofareyou.testutil.Assert;

public class AppointmentTest {
    public static final String RETRIEVED_APPOINTMENT_VALUE = "Mar-04-2022 09:30 AM at NUS VET";
    public static final Appointment EMPTY_APPOINTMENT = new Appointment();
    public static final Appointment RETRIEVED_APPOINTMENT = new Appointment(RETRIEVED_APPOINTMENT_VALUE);

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Appointment(null));
        Assert.assertThrows(NullPointerException.class, () -> new Appointment(null, null));
    }

    @Test
    public void isValidAppointment() {
        // null appointment
        Assert.assertThrows(NullPointerException.class, () -> Appointment.isValidAppointment(null));

        // invalid appointment
        assertFalse(Appointment.isValidAppointment("")); // empty appointment
        assertFalse(Appointment.isValidAppointment("Mar-04-2022 09:30 AM @ NUS VET"));
        assertFalse(Appointment.isValidAppointment("2022-Mar-04 09:30 AM at NUS VET"));
        assertFalse(Appointment.isValidAppointment("04-03-2022 09:30 AM at NUS VET"));
        assertFalse(Appointment.isValidAppointment("Mar-04-2022 09:30 AM at"));

        // valid appointment
        assertTrue(Appointment.isValidAppointment(RETRIEVED_APPOINTMENT_VALUE));
    }

    @Test
    public void compareTo() {

        assertTrue(EMPTY_APPOINTMENT.compareTo(EMPTY_APPOINTMENT) == 0);
        assertTrue(RETRIEVED_APPOINTMENT.compareTo(RETRIEVED_APPOINTMENT) == 0);

        assertTrue(EMPTY_APPOINTMENT.compareTo(RETRIEVED_APPOINTMENT) == 1);
        assertTrue(RETRIEVED_APPOINTMENT.compareTo(EMPTY_APPOINTMENT) == -1);

        assertFalse(RETRIEVED_APPOINTMENT.compareTo(EMPTY_APPOINTMENT) == 0);
        assertFalse(EMPTY_APPOINTMENT.compareTo(RETRIEVED_APPOINTMENT) == 0);
        assertFalse(RETRIEVED_APPOINTMENT.compareTo(EMPTY_APPOINTMENT) == 1);
        assertFalse(EMPTY_APPOINTMENT.compareTo(RETRIEVED_APPOINTMENT) == -1);

    }
}
