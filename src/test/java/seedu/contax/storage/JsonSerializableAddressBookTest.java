package seedu.contax.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.util.JsonUtil;
import seedu.contax.model.AddressBook;
import seedu.contax.testutil.TypicalPersons;
import seedu.contax.testutil.TypicalTags;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");
    private static final Path NO_PERSONS_FILE = TEST_DATA_FOLDER.resolve("noPersonsAddressBook.json");
    private static final Path NO_TAGS_FILE = TEST_DATA_FOLDER.resolve("noTagsAddressBook.json");
    private static final Path DUPLICATE_TAGS_FILE = TEST_DATA_FOLDER.resolve("duplicateTagsAddressBook.json");

    // Needs to be redifined
    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalPersonsAddressBook = TypicalPersons.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_personsOnlyNoTags_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalPersonsAddressBook = TypicalPersons.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidPersonFile_recordSkipped() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        assertEquals(0, addressBookFromFile.getPersonList().size());
        assertEquals(0, addressBookFromFile.getTagList().size());
    }

    @Test
    public void toModelType_duplicatePersons_recordSkipped() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        assertEquals(1, addressBookFromFile.getPersonList().size());
        assertEquals(1, addressBookFromFile.getTagList().size());
    }

    @Test
    public void toModelType_noPersonsOnlyTags_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(NO_PERSONS_FILE,
                JsonSerializableAddressBook.class).get();

        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook noPersonsAddressBook = TypicalTags.getTagOnlyAddressBook();
        assertEquals(addressBookFromFile, noPersonsAddressBook);
    }

    @Test
    public void toModelType_duplicateTags_tagsSkipped() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TAGS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        assertEquals(1, addressBookFromFile.getTagList().size());
    }
}
