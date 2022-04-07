package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.BuyerAddressBook;
import seedu.address.testutil.TypicalBuyers;

public class JsonSerializableBuyerAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableBuyerAddressBookTest");
    private static final Path TYPICAL_BUYER_FILE = TEST_DATA_FOLDER.resolve("typicalBuyerAddressBook.json");
    private static final Path INVALID_BUYER_FILE = TEST_DATA_FOLDER.resolve("invalidBuyerAddressBook.json");
    private static final Path DUPLICATE_BUYER_FILE = TEST_DATA_FOLDER.resolve("duplicateBuyerAddressBook.json");

    @Test
    public void toModelType_typicalBuyersFile_success() throws Exception {
        JsonSerializableBuyerAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_BUYER_FILE,
                JsonSerializableBuyerAddressBook.class).get();
        BuyerAddressBook addressBookFromFile = dataFromFile.toModelType();
        BuyerAddressBook typicalClientsAddressBook = TypicalBuyers.getTypicalBuyerAddressBook();
        assertEquals(addressBookFromFile, typicalClientsAddressBook);
    }

    @Test
    public void toModelType_invalidBuyerFile_throwsIllegalValueException() throws Exception {
        JsonSerializableBuyerAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_BUYER_FILE,
                JsonSerializableBuyerAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateBuyers_throwsIllegalValueException() throws Exception {
        JsonSerializableBuyerAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_BUYER_FILE,
                JsonSerializableBuyerAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableBuyerAddressBook.MESSAGE_DUPLICATE_BUYER,
                dataFromFile::toModelType);
    }

}
