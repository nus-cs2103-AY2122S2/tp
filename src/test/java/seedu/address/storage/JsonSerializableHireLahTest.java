package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.HireLah;
import seedu.address.testutil.TypicalApplicants;

public class JsonSerializableHireLahTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableHireLahTest");
    private static final Path TYPICAL_APPLICANTS_FILE = TEST_DATA_FOLDER.resolve("typicalApplicantsHireLah.json");
    private static final Path INVALID_APPLICANT_FILE = TEST_DATA_FOLDER.resolve("invalidApplicantHireLah.json");
    private static final Path DUPLICATE_APPLICANT_FILE = TEST_DATA_FOLDER.resolve("duplicateApplicantHireLah.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableHireLah dataFromFile = JsonUtil.readJsonFile(TYPICAL_APPLICANTS_FILE,
                JsonSerializableHireLah.class).get();
        HireLah addressBookFromFile = dataFromFile.toModelType();
        HireLah typicalApplicantsHireLah = TypicalApplicants.getTypicalHireLah();
        assertEquals(addressBookFromFile, typicalApplicantsHireLah);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableHireLah dataFromFile = JsonUtil.readJsonFile(INVALID_APPLICANT_FILE,
            JsonSerializableHireLah.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableHireLah dataFromFile = JsonUtil.readJsonFile(DUPLICATE_APPLICANT_FILE,
                JsonSerializableHireLah.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableHireLah.MESSAGE_DUPLICATE_APPLICANT,
                dataFromFile::toModelType);
    }
}
