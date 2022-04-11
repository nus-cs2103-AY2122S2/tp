package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTAssist.getTypicalTAssist;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.TAssist;

public class JsonSerializableTAssistTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTAssistTest");
    private static final Path TYPICAL_TASSIST_FILE = TEST_DATA_FOLDER.resolve("typicalTAssist.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentTAssist.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentTAssist.json");
    private static final Path INVALID_MODULE_FILE = TEST_DATA_FOLDER.resolve("invalidModuleTAssist.json");
    private static final Path DUPLICATE_MODULE_FILE = TEST_DATA_FOLDER.resolve("duplicateModuleTAssist.json");
    private static final Path INVALID_CLASS_GROUP_FILE = TEST_DATA_FOLDER.resolve("invalidClassGroupTAssist.json");
    private static final Path DUPLICATE_CLASS_GROUP_FILE = TEST_DATA_FOLDER.resolve("duplicateClassGroupTAssist.json");
    private static final Path INVALID_ASSESSMENT_FILE = TEST_DATA_FOLDER.resolve("invalidAssessmentTAssist.json");
    private static final Path DUPLICATE_ASSESSMENT_FILE = TEST_DATA_FOLDER.resolve("duplicateAssessmentTAssist.json");

    @Test
    public void toModelType_typicalTAssistFile_success() throws Exception {
        JsonSerializableTAssist dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASSIST_FILE,
                JsonSerializableTAssist.class).get();
        TAssist tAssistFromFile = dataFromFile.toModelType();
        TAssist typicalTAssist = getTypicalTAssist();
        assertEquals(tAssistFromFile, typicalTAssist);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTAssist dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableTAssist.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableTAssist dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableTAssist.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTAssist.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidModuleFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTAssist dataFromFile = JsonUtil.readJsonFile(INVALID_MODULE_FILE,
                JsonSerializableTAssist.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateModules_throwsIllegalValueException() throws Exception {
        JsonSerializableTAssist dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MODULE_FILE,
                JsonSerializableTAssist.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTAssist.MESSAGE_DUPLICATE_MODULE,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidClassGroupFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTAssist dataFromFile = JsonUtil.readJsonFile(INVALID_CLASS_GROUP_FILE,
                JsonSerializableTAssist.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateClassGroups_throwsIllegalValueException() throws Exception {
        JsonSerializableTAssist dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CLASS_GROUP_FILE,
                JsonSerializableTAssist.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTAssist.MESSAGE_DUPLICATE_CLASS_GROUP,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidAssessmentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTAssist dataFromFile = JsonUtil.readJsonFile(INVALID_ASSESSMENT_FILE,
                JsonSerializableTAssist.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateAssessments_throwsIllegalValueException() throws Exception {
        JsonSerializableTAssist dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ASSESSMENT_FILE,
                JsonSerializableTAssist.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTAssist.MESSAGE_DUPLICATE_ASSESSMENT,
                dataFromFile::toModelType);
    }
}
