package seedu.contax.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.contax.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.exceptions.IllegalValueException;
import seedu.contax.commons.util.JsonUtil;
import seedu.contax.model.AddressBook;
import seedu.contax.model.Schedule;
import seedu.contax.testutil.TypicalAppointments;
import seedu.contax.testutil.TypicalPersons;

public class JsonSerializableScheduleTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableScheduleTest");
    private static final Path TYPICAL_APPOINTMENTS_FILE =
            TEST_DATA_FOLDER.resolve("typicalAppointmentsSchedule.json");
    private static final Path INVALID_APPOINTMENT_FILE =
            TEST_DATA_FOLDER.resolve("invalidAppointmentSchedule.json");
    private static final Path OVERLAPPING_APPOINTMENTS_FILE =
            TEST_DATA_FOLDER.resolve("overlappingAppointmentsSchedule.json");
    private static final AddressBook GLOBAL_ADDRESSBOOK = TypicalPersons.getTypicalAddressBook();

    @Test
    public void toModelType_typicalAppointmentsFile_success() throws Exception {
        JsonSerializableSchedule dataFromFile = JsonUtil.readJsonFile(TYPICAL_APPOINTMENTS_FILE,
                JsonSerializableSchedule.class).get();
        Schedule addressBookFromFile = dataFromFile.toModelType(GLOBAL_ADDRESSBOOK.getPersonList());
        Schedule typicalPersonsAddressBook = TypicalAppointments.getTypicalSchedule();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidAppointmentFile_recordSkipped() throws Exception {
        JsonSerializableSchedule dataFromFile = JsonUtil.readJsonFile(INVALID_APPOINTMENT_FILE,
                JsonSerializableSchedule.class).get();
        Schedule schedule = dataFromFile.toModelType(GLOBAL_ADDRESSBOOK.getPersonList());
        assertEquals(TypicalAppointments.getTypicalSchedule(), schedule);
    }

    @Test
    public void toModelType_overlappingAppointments_throwsIllegalValueException() throws Exception {
        JsonSerializableSchedule dataFromFile = JsonUtil.readJsonFile(OVERLAPPING_APPOINTMENTS_FILE,
                JsonSerializableSchedule.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSchedule.MESSAGE_OVERLAPPING_APPOINTMENT, ()
            -> dataFromFile.toModelType(GLOBAL_ADDRESSBOOK.getPersonList()));
    }

}
