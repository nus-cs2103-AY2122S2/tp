package seedu.contax.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.contax.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_NAME;
import static seedu.contax.storage.JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.contax.testutil.Assert.assertThrows;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALICE;
import static seedu.contax.testutil.TypicalPersons.ALICE;
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

    // Invalid JSON data fields
    private static final String INVALID_START_DATETIME = "2022-12-2212:39";
    private static final int INVALID_DURATION = -1;
    private static final String INVALID_PERSON = "Bobby";
    private static final String INVALID_PRIORITY = "invalid priority";

    private static final String VALID_NAME = APPOINTMENT_ALICE.getName().toString();
    private static final String VALID_START_DATETIME =
            DateTimeFormatter.ofPattern(JsonAdaptedAppointment.DATETIME_FORMAT)
                    .format(APPOINTMENT_ALICE.getStartDateTime());
    private static final int VALID_DURATION = 30;
    private static final String VALID_PERSON = ALICE.getName().toString();

    private static final String VALID_PRIORITY_HIGH = "High";
    private static final String VALID_PRIORITY_MEDIUM = "Medium";
    private static final String VALID_PRIORITY_LOW = "Low";

    private final AddressBook addressBook = getTypicalAddressBook();

    @Test
    public void toModelType_validAppointmentDetails_returnsAppointment() throws Exception {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(APPOINTMENT_ALICE);
        assertEquals(APPOINTMENT_ALICE, appointment.toModelType(addressBook.getPersonList()));
    }

    //@@author HanJiyao
    @Test
    public void toModelType_validAppointmentDetails_returnsAppointmentWithPriority() throws Exception {
        JsonAdaptedAppointment appointmentHigh = new JsonAdaptedAppointment(VALID_NAME,
                VALID_START_DATETIME, VALID_DURATION, VALID_PERSON, VALID_PRIORITY_HIGH);
        JsonAdaptedAppointment appointmentMedium = new JsonAdaptedAppointment(VALID_NAME,
                VALID_START_DATETIME, VALID_DURATION, VALID_PERSON, VALID_PRIORITY_MEDIUM);
        JsonAdaptedAppointment appointmentLow = new JsonAdaptedAppointment(VALID_NAME,
                VALID_START_DATETIME, VALID_DURATION, VALID_PERSON, VALID_PRIORITY_LOW);

        assertEquals(APPOINTMENT_ALICE.withPriority(Priority.HIGH),
                appointmentHigh.toModelType(addressBook.getPersonList()));
        assertEquals(APPOINTMENT_ALICE.withPriority(Priority.MEDIUM),
                appointmentMedium.toModelType(addressBook.getPersonList()));
        assertEquals(APPOINTMENT_ALICE.withPriority(Priority.LOW),
                appointmentLow.toModelType(addressBook.getPersonList()));
    }
    //@@author

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(INVALID_APPOINTMENT_NAME,
                VALID_START_DATETIME, VALID_DURATION, VALID_PERSON, VALID_PRIORITY_LOW);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, ()
            -> appointment.toModelType(addressBook.getPersonList()));
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(null, VALID_START_DATETIME,
                VALID_DURATION, VALID_PERSON, VALID_PRIORITY_LOW);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, ()
            -> appointment.toModelType(addressBook.getPersonList()));
    }

    @Test
    public void toModelType_invalidStartDateTime_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NAME, INVALID_START_DATETIME,
                VALID_DURATION, VALID_PERSON, VALID_PRIORITY_LOW);
        String expectedMessage = JsonAdaptedAppointment.INVALID_DATETIME_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, ()
            -> appointment.toModelType(addressBook.getPersonList()));
    }

    @Test
    public void toModelType_nullStartDateTime_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NAME, null,
                VALID_DURATION, VALID_PERSON, VALID_PRIORITY_LOW);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                StartDateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, ()
            -> appointment.toModelType(addressBook.getPersonList()));
    }

    @Test
    public void toModelType_invalidDuration_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NAME, VALID_START_DATETIME,
                INVALID_DURATION, VALID_PERSON, VALID_PRIORITY_LOW);
        JsonAdaptedAppointment zeroDuration = new JsonAdaptedAppointment(VALID_NAME, VALID_START_DATETIME,
                0, VALID_PERSON, VALID_PRIORITY_LOW);

        String expectedMessage = JsonAdaptedAppointment.INVALID_DURATION_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, ()
            -> appointment.toModelType(addressBook.getPersonList()));
        assertThrows(IllegalValueException.class, expectedMessage, ()
            -> zeroDuration.toModelType(addressBook.getPersonList()));
    }

    @Test
    public void toModelType_invalidPerson_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NAME, VALID_START_DATETIME,
                VALID_DURATION, INVALID_PERSON, VALID_PRIORITY_LOW);
        String expectedMessage = JsonAdaptedAppointment.INVALID_PERSON_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, ()
            -> appointment.toModelType(addressBook.getPersonList()));
    }

    @Test
    public void toModelType_nullPerson_success() throws IllegalValueException {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NAME, VALID_START_DATETIME,
                VALID_DURATION, null, VALID_PRIORITY_LOW);

        Appointment modelAppointment = appointment.toModelType(addressBook.getPersonList());
        assertNull(modelAppointment.getPerson());
    }

    //@@author HanJiyao
    @Test
    public void toModelType_nullPriority_success() throws IllegalValueException {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NAME, VALID_START_DATETIME,
                VALID_DURATION, VALID_PERSON, null);

        Appointment modelAppointment = appointment.toModelType(addressBook.getPersonList());
        assertNull(modelAppointment.getPriority());
    }

    @Test
    public void toModelType_invalidAppointmentPriority_throwsIllegalValueException() throws Exception {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NAME,
                VALID_START_DATETIME, VALID_DURATION, VALID_PERSON, INVALID_PRIORITY);
        String expectedMessage = JsonAdaptedAppointment.INVALID_PRIORITY_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, ()
            -> appointment.toModelType(addressBook.getPersonList()));
    }
    //@@author
}
