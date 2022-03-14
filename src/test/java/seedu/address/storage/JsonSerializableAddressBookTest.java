package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.SimilarPets;
import seedu.address.testutil.TypicalPets;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PETS_FILE = TEST_DATA_FOLDER.resolve("typicalPetsAddressBook.json");
    private static final Path INVALID_PET_FILE = TEST_DATA_FOLDER.resolve("invalidPetsAddressBook.json");
    private static final Path SIMILAR_PET_FILE = TEST_DATA_FOLDER.resolve("similarPetsAddressBook.json");

    @Test
    public void toModelType_typicalPetsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PETS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalPetsAddressBook = TypicalPets.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalPetsAddressBook);
    }

    @Test
    public void toModelType_invalidPetFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_PET_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_sameNamePetsDifferentOwner_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(SIMILAR_PET_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook similarPetsAddressBook = SimilarPets.getSimilarAddressBook();
        assertEquals(addressBookFromFile, similarPetsAddressBook);
    }

}
