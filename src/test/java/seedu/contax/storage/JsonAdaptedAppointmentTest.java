package seedu.contax.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.contax.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_NAME;
import static seedu.contax.storage.JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.contax.testutil.Assert.assertThrows;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALICE;
import static seedu.contax.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.exceptions.IllegalValueException;
import seedu.contax.model.AddressBook;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.Name;
import seedu.contax.model.appointment.Priority;
import seedu.contax.model.appointment.StartDateTime;

public class JsonAdaptedAppointmentTest {
    private static final AddressBook addressBook = getTypicalAddressBook();

    private static final String INVALID_START_DATETIME = "2022-12-2212:39";
    private static final int INVALID_DURATION = -1;
    private static final String INVALID_PERSON = "Bobby";

    private static final String VALID_NAME = APPOINTMENT_ALICE.getName().toString();
    private static final String VALID_START_DATETIME =
            DateTimeFormatter.ofPattern(JsonAdaptedAppointment.DATETIME_FORMAT)
                    .format(APPOINTMENT_ALICE.getStartDateTimeObject().value);
    private static final int VALID_DURATION = 30;
    private static final String VALID_PERSON = addressBook.getPersonList().get(0).getName().toString();

    private static final String VALID_PRIORITY_HIGH = "HIGH";
    private static final String VALID_PRIORITY_MEDIUM = "MEDIUM";
    private static final String VALID_PRIORITY_LOW = "LOW";

    @Test
    public void toModelType_validAppointmentDetails_returnsAppointment() throws Exception {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(APPOINTMENT_ALICE);
        assertEquals(APPOINTMENT_ALICE, appointment.toModelType(addressBook));
    }

    @Test
    public void toModelType_validAppointmentDetailsHigh_returnsAppointment() throws Exception {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NAME,
                VALID_START_DATETIME, VALID_DURATION, VALID_PERSON, VALID_PRIORITY_HIGH);
        Appointment expectedAppointmentModel = new Appointment(
                APPOINTMENT_ALICE.getName(), APPOINTMENT_ALICE.getStartDateTimeObject(),
                APPOINTMENT_ALICE.getDuration(), APPOINTMENT_ALICE.getPerson(), Priority.HIGH);
        assertEquals(expectedAppointmentModel, appointment.toModelType(addressBook));
    }

    @Test
    public void toModelType_validAppointmentDetailsMedium_returnsAppointment() throws Exception {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NAME,
                VALID_START_DATETIME, VALID_DURATION, VALID_PERSON, VALID_PRIORITY_MEDIUM);
        Appointment expectedAppointmentModel = new Appointment(
                APPOINTMENT_ALICE.getName(), APPOINTMENT_ALICE.getStartDateTimeObject(),
                APPOINTMENT_ALICE.getDuration(), APPOINTMENT_ALICE.getPerson(), Priority.MEDIUM);
        assertEquals(expectedAppointmentModel, appointment.toModelType(addressBook));
    }

    @Test
    public void toModelType_validAppointmentDetailsLow_returnsAppointment() throws Exception {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NAME,
                VALID_START_DATETIME, VALID_DURATION, VALID_PERSON, VALID_PRIORITY_LOW);
        Appointment expectedAppointmentModel = new Appointment(
                APPOINTMENT_ALICE.getName(), APPOINTMENT_ALICE.getStartDateTimeObject(),
                APPOINTMENT_ALICE.getDuration(), APPOINTMENT_ALICE.getPerson(), Priority.LOW);
        assertEquals(expectedAppointmentModel, appointment.toModelType(addressBook));
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(INVALID_APPOINTMENT_NAME,
                VALID_START_DATETIME, VALID_DURATION, VALID_PERSON, VALID_PRIORITY_HIGH);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, ()
            -> appointment.toModelType(addressBook));
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(
                        null, VALID_START_DATETIME, VALID_DURATION, VALID_PERSON, VALID_PRIORITY_HIGH);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, ()
            -> appointment.toModelType(addressBook));
    }

    @Test
    public void toModelType_invalidStartDateTime_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(
                        VALID_NAME, INVALID_START_DATETIME, VALID_DURATION, VALID_PERSON, VALID_PRIORITY_HIGH);
        String expectedMessage = JsonAdaptedAppointment.INVALID_DATETIME_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, ()
            -> appointment.toModelType(addressBook));
    }

    @Test
    public void toModelType_nullStartDateTime_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(
                        VALID_NAME, null, VALID_DURATION, VALID_PERSON, VALID_PRIORITY_HIGH);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                StartDateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, ()
            -> appointment.toModelType(addressBook));
    }

    @Test
    public void toModelType_invalidDuration_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(
                        VALID_NAME, VALID_START_DATETIME, INVALID_DURATION, VALID_PERSON, VALID_PRIORITY_HIGH);
        String expectedMessage = JsonAdaptedAppointment.INVALID_DURATION_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, ()
            -> appointment.toModelType(addressBook));
        JsonAdaptedAppointment appointment2 =
                new JsonAdaptedAppointment(
                        VALID_NAME, VALID_START_DATETIME, 0, VALID_PERSON, VALID_PRIORITY_HIGH);
        assertThrows(IllegalValueException.class, expectedMessage, ()
            -> appointment2.toModelType(addressBook));
    }

    @Test
    public void toModelType_invalidPerson_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(
                        VALID_NAME, VALID_START_DATETIME, VALID_DURATION, INVALID_PERSON, VALID_PRIORITY_HIGH);
        String expectedMessage = JsonAdaptedAppointment.INVALID_PERSON_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, ()
            -> appointment.toModelType(addressBook));
    }

    @Test
    public void toModelType_nullPerson_success() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(
                        VALID_NAME, VALID_START_DATETIME, VALID_DURATION, null, VALID_PRIORITY_HIGH);
        try {
            Appointment modelAppointment = appointment.toModelType(addressBook);
            assertEquals(null, modelAppointment.getPerson());
        } catch (IllegalValueException ex) {
            fail();
        }
    }
}
