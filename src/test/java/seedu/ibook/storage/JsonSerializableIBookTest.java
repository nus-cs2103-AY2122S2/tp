package seedu.ibook.storage;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static seedu.ibook.testutil.Assert.assertThrows;
//
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.ibook.commons.exceptions.IllegalValueException;
//import seedu.ibook.commons.util.JsonUtil;
//import seedu.ibook.model.IBook;
//import seedu.ibook.testutil.TypicalPersons;

public class JsonSerializableIBookTest {
    /*
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableIBookTest");
    private static final Path TYPICAL_PRODUCTS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsIBook.json");
    private static final Path INVALID_PRODUCT_FILE = TEST_DATA_FOLDER.resolve("invalidPersonIBook.json");
    private static final Path DUPLICATE_PRODUCT_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonIBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableIBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PRODUCTS_FILE,
                JsonSerializableIBook.class).get();
        IBook addressBookFromFile = dataFromFile.toModelType();
        IBook typicalPersonsIBook = TypicalPersons.getTypicalIBook();
        assertEquals(addressBookFromFile, typicalPersonsIBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableIBook dataFromFile = JsonUtil.readJsonFile(INVALID_PRODUCT_FILE,
                JsonSerializableIBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableIBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PRODUCT_FILE,
                JsonSerializableIBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableIBook.MESSAGE_DUPLICATE_PRODUCT,
                dataFromFile::toModelType);
    }
    */
}
