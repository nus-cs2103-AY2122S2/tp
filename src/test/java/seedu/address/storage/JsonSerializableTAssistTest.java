//package seedu.address.storage;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static seedu.address.testutil.Assert.assertThrows;
//import static seedu.address.testutil.TypicalTAssist.getTypicalTAssist;
//
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.commons.exceptions.IllegalValueException;
//import seedu.address.commons.util.JsonUtil;
//import seedu.address.model.TAssist;
//
//public class JsonSerializableTAssistTest {
//
//    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTAssistTest");
//    private static final Path TYPICAL_TASSIST_FILE = TEST_DATA_FOLDER.resolve("typicalTAssist.json");
//    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonTAssist.json");
//    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonTAssist.json");
//
//    @Test
//    public void toModelType_typicalPersonsFile_success() throws Exception {
//        JsonSerializableTAssist dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASSIST_FILE,
//                JsonSerializableTAssist.class).get();
//        TAssist addressBookFromFile = dataFromFile.toModelType();
//        TAssist typicalPersonsTAssist = getTypicalTAssist();
//        assertEquals(addressBookFromFile, typicalPersonsTAssist);
//    }
//
//    @Test
//    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
//        JsonSerializableTAssist dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
//                JsonSerializableTAssist.class).get();
//        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
//    }
//
//    @Test
//    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
//        JsonSerializableTAssist dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
//                JsonSerializableTAssist.class).get();
//        assertThrows(IllegalValueException.class, JsonSerializableTAssist.MESSAGE_DUPLICATE_ASSESSMENT,
//                dataFromFile::toModelType);
//    }
//
//}
