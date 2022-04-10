package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.SellerAddressBook;
import seedu.address.testutil.TypicalSellers;

public class JsonSerializableSellerAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableSellerAddressBookTest");
    private static final Path TYPICAL_SELLER_FILE = TEST_DATA_FOLDER.resolve("typicalSellerAddressBook.json");
    private static final Path INVALID_SELLER_FILE = TEST_DATA_FOLDER.resolve("invalidSellerAddressBook.json");
    private static final Path DUPLICATE_SELLER_FILE = TEST_DATA_FOLDER.resolve("duplicateSellerAddressBook.json");

    @Test
    public void toModelType_typicalSellersFile_success() throws Exception {
        JsonSerializableSellerAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_SELLER_FILE,
                JsonSerializableSellerAddressBook.class).get();
        SellerAddressBook addressBookFromFile = dataFromFile.toModelType();
        SellerAddressBook typicalSellersAddressBook = TypicalSellers.getTypicalSellerAddressBook();
        assertEquals(addressBookFromFile, typicalSellersAddressBook);
    }

    @Test
    public void toModelType_invalidSellerFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSellerAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_SELLER_FILE,
                JsonSerializableSellerAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateBuyers_throwsIllegalValueException() throws Exception {
        JsonSerializableSellerAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_SELLER_FILE,
                JsonSerializableSellerAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSellerAddressBook.MESSAGE_DUPLICATE_SELLER,
                dataFromFile::toModelType);
    }

}
