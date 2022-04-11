package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AppointmentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null));
    }

    @Test
    public void isValidAppointment() {
        assertFalse(Appointment.isValidAppointment(" "));
        assertFalse(Appointment.isValidAppointment("2022-02-29-00-00"));
        assertFalse(Appointment.isValidAppointment("2022-02-30-00-00"));
        assertFalse(Appointment.isValidAppointment("2022-02-31-00-00"));
        assertFalse(Appointment.isValidAppointment("2022-04-31-00-00"));
        assertFalse(Appointment.isValidAppointment("2022-04-20-0-00"));
        assertFalse(Appointment.isValidAppointment("2022-04-20-12-60"));
        assertFalse(Appointment.isValidAppointment("2022-04-20-25-00"));
        assertFalse(Appointment.isValidAppointment("2022-11-31-00-00"));
        assertFalse(Appointment.isValidAppointment("2020-002-29-00-00"));
        assertTrue(Appointment.isValidAppointment(""));
        assertTrue(Appointment.isValidAppointment("2000-01-01-00-00"));
        assertTrue(Appointment.isValidAppointment("2022-12-31-00-00"));
        assertTrue(Appointment.isValidAppointment("2022-02-28-00-00"));
        assertTrue(Appointment.isValidAppointment("2020-02-29-00-00"));
        assertTrue(Appointment.isValidAppointment("1999-04-30-00-00"));
        assertTrue(Appointment.isValidAppointment("2022-04-20-11-59"));
    }


}
