package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalJobs;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_RECLINE_FILE = TEST_DATA_FOLDER.resolve("typicalReCLIne.json");
    private static final Path INVALID_APPLICANTS_FILE = TEST_DATA_FOLDER.resolve("invalidApplicantsReCLIne.json");
    private static final Path DUPLICATE_APPLICANTS_FILE =
            TEST_DATA_FOLDER.resolve("duplicateApplicantsReCLIne.json");
    private static final Path INVALID_JOBS_FILE = TEST_DATA_FOLDER.resolve("invalidJobsReCLIne.json");
    private static final Path DUPLICATE_JOBS_FILE =
            TEST_DATA_FOLDER.resolve("duplicateJobsReCLIne.json");

    @Test
    public void toModeType_typicalReclineFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_RECLINE_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalRecline = TypicalJobs.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalRecline);
    }

    @Test
    public void toModelType_invalidApplicantsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_APPLICANTS_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateApplicants_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_APPLICANTS_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_APPLICANT,
                dataFromFile::toModelType);
    }
    @Test
    public void toModelType_invalidJobsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_JOBS_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateJobs_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_JOBS_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_JOB,
                dataFromFile::toModelType);
    }
}
