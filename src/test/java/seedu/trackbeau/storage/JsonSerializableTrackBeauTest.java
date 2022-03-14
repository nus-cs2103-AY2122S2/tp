package seedu.trackbeau.storage;

import static seedu.trackbeau.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.commons.exceptions.IllegalValueException;
import seedu.trackbeau.commons.util.JsonUtil;

public class JsonSerializableTrackBeauTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTrackBeauTest");
    private static final Path TYPICAL_CUSTOMERS_FILE = TEST_DATA_FOLDER.resolve("typicalCustomersTrackBeau.json");
    private static final Path INVALID_CUSTOMER_FILE = TEST_DATA_FOLDER.resolve("invalidCustomerTrackBeau.json");
    private static final Path DUPLICATE_CUSTOMER_FILE = TEST_DATA_FOLDER.resolve("duplicateCustomerTrackBeau.json");

    /*
    @Test
    public void toModelType_typicalCustomerFile_success() throws Exception {
        JsonSerializableTrackBeau dataFromFile = JsonUtil.readJsonFile(TYPICAL_CUSTOMERS_FILE,
                JsonSerializableTrackBeau.class).get();
        TrackBeau addressBookFromFile = dataFromFile.toModelType();
        TrackBeau typicalPersonsAddressBook = TypicalCustomers.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }
    */

    @Test
    public void toModelType_invalidCustomerFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTrackBeau dataFromFile = JsonUtil.readJsonFile(INVALID_CUSTOMER_FILE,
                JsonSerializableTrackBeau.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    /*
    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableTrackBeau dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CUSTOMER_FILE,
                JsonSerializableTrackBeau.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTrackBeau.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }
    */

}
