package seedu.contax.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.contax.testutil.Assert.assertThrows;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALICE;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_EXTRA;
import static seedu.contax.testutil.TypicalAppointments.getTypicalSchedule;
import static seedu.contax.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.contax.commons.exceptions.DataConversionException;
import seedu.contax.model.AddressBook;
import seedu.contax.model.ReadOnlySchedule;
import seedu.contax.model.Schedule;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.testutil.AppointmentBuilder;
import seedu.contax.testutil.ScheduleBuilder;

public class JsonScheduleStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test",
            "data", "JsonScheduleStorageTest");
    private static final AddressBook GLOBAL_ADDRESSBOOK = getTypicalAddressBook();

    @TempDir
    public Path testFolder;

    @Test
    public void readSchedule_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readSchedule("FileDoesNotExist.json", null));
    }

    @Test
    public void readSchedule_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readSchedule(null, GLOBAL_ADDRESSBOOK));
    }

    private java.util.Optional<ReadOnlySchedule> readSchedule(String filePath, AddressBook addressBook)
            throws Exception {
        return new JsonScheduleStorage(Paths.get(filePath))
                .readSchedule(addToTestDataPathIfNotNull(filePath), addressBook);
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readSchedule("NonExistentFile.json", GLOBAL_ADDRESSBOOK).isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, ()
            -> readSchedule("notJsonFormatSchedule.json", GLOBAL_ADDRESSBOOK));
    }

    @Test
    public void readSchedule_invalidAppointment_recordSkipped() throws Exception {
        ReadOnlySchedule schedule =
                readSchedule("invalidAppointmentSchedule.json", GLOBAL_ADDRESSBOOK).get();
        assertEquals(0, schedule.getAppointmentList().size());
    }

    @Test
    public void readAddressBook_invalidAndValidAppointment_recordSkipped() throws Exception {
        ReadOnlySchedule schedule =
                readSchedule("invalidAndValidAppointmentSchedule.json", GLOBAL_ADDRESSBOOK).get();
        Appointment expectedAppointment = new AppointmentBuilder()
                .withName("Valid appointment")
                .withStartDateTime(LocalDateTime.parse("2022-11-11T12:34"))
                .withDuration(10)
                .build();
        Schedule expectedSchedule = new ScheduleBuilder().withAppointment(expectedAppointment).build();

        assertEquals(schedule, expectedSchedule);
    }

    @Test
    public void readSchedule_overlappingAppointment_throwsDataConversionException() {
        assertThrows(DataConversionException.class, ()
            -> readSchedule("overlappingAppointmentSchedule.json", GLOBAL_ADDRESSBOOK));
    }

    @Test
    public void readAddressBook_validAppointments_success() throws Exception {
        Optional<ReadOnlySchedule> schedule = readSchedule("validAppointmentSchedule.json",
                GLOBAL_ADDRESSBOOK);
        if (schedule.isEmpty()) {
            fail();
        }
        assertEquals(1, schedule.get().getAppointmentList().size());
    }

    @Test
    public void readAndSaveSchedule_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempSchedule.json");
        Schedule original = getTypicalSchedule();
        JsonScheduleStorage jsonScheduleStorage = new JsonScheduleStorage(filePath);

        // Save in new file and read back
        jsonScheduleStorage.saveSchedule(original, filePath);
        ReadOnlySchedule readBack = jsonScheduleStorage.readSchedule(filePath, GLOBAL_ADDRESSBOOK).get();
        assertEquals(original, new Schedule(readBack));

        // Modify data, overwrite exiting file, and read back
        Appointment edittedAppointment = new AppointmentBuilder(APPOINTMENT_ALICE)
                .withStartDateTime(APPOINTMENT_ALICE.getStartDateTime().minusDays(1))
                .withName("Different Meeting").build();
        original.addAppointment(edittedAppointment);
        jsonScheduleStorage.saveSchedule(original, filePath);
        readBack = jsonScheduleStorage.readSchedule(filePath, GLOBAL_ADDRESSBOOK).get();
        assertEquals(original, new Schedule(readBack));

        // Save and read without specifying file path
        original.addAppointment(APPOINTMENT_EXTRA);
        jsonScheduleStorage.saveSchedule(original); // file path not specified
        readBack = jsonScheduleStorage.readSchedule(GLOBAL_ADDRESSBOOK).get(); // file path not specified
        assertEquals(original, new Schedule(readBack));

    }

    @Test
    public void saveSchedule_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSchedule(null, "SomeFile.json"));
    }

    /**
     * Saves {@code schedule} at the specified {@code filePath}.
     */
    private void saveSchedule(ReadOnlySchedule schedule, String filePath) {
        try {
            new JsonScheduleStorage(Paths.get(filePath))
                    .saveSchedule(schedule, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSchedule(new Schedule(), null));
    }
}
