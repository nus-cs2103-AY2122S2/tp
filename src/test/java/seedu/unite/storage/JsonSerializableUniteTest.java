package seedu.unite.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.unite.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.unite.commons.exceptions.IllegalValueException;
import seedu.unite.commons.util.JsonUtil;
import seedu.unite.model.Unite;
import seedu.unite.model.person.Person;
import seedu.unite.model.tag.Tag;
import seedu.unite.testutil.TypicalPersons;

public class JsonSerializableUniteTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableUniteTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsUnite.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonUnite.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonUnite.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableUnite dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableUnite.class).get();
        Unite uniteFromFile = dataFromFile.toModelType();
        for (Person eachPerson : uniteFromFile.getPersonList()) {
            for (Tag eachTag: eachPerson.getTags()) {
                if (!uniteFromFile.hasTag(eachTag)) {
                    uniteFromFile.addTag(eachTag);
                }
            }
        }
        Unite typicalPersonsUnite = TypicalPersons.getTypicalUnite();
        assertEquals(uniteFromFile, typicalPersonsUnite);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableUnite dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableUnite.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableUnite dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableUnite.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableUnite.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
