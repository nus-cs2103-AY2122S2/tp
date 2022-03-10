package seedu.ibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ibook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.ibook.commons.exceptions.IllegalValueException;
import seedu.ibook.commons.util.JsonUtil;
import seedu.ibook.model.IBook;
import seedu.ibook.testutil.TypicalProducts;

public class JsonSerializableIBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableIBookTest");
    private static final Path TYPICAL_PRODUCTS_FILE = TEST_DATA_FOLDER.resolve("typicalProductsIBook.json");
    private static final Path INVALID_PRODUCT_FILE = TEST_DATA_FOLDER.resolve("invalidProductIBook.json");
    private static final Path DUPLICATE_PRODUCT_FILE = TEST_DATA_FOLDER.resolve("duplicateProductIBook.json");

    @Test
    public void toModelType_typicalProductsFile_success() throws Exception {
        JsonSerializableIBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PRODUCTS_FILE,
                JsonSerializableIBook.class).get();
        IBook iBookFromFile = dataFromFile.toModelType();
        IBook typicalProductsIBook = TypicalProducts.getTypicalIBook();
        assertEquals(iBookFromFile, typicalProductsIBook);
    }

    @Test
    public void toModelType_invalidProductFile_throwsIllegalValueException() throws Exception {
        JsonSerializableIBook dataFromFile = JsonUtil.readJsonFile(INVALID_PRODUCT_FILE,
                JsonSerializableIBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateProducts_throwsIllegalValueException() throws Exception {
        JsonSerializableIBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PRODUCT_FILE,
                JsonSerializableIBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableIBook.MESSAGE_DUPLICATE_PRODUCT,
                dataFromFile::toModelType);
    }

}
