package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyBuyerAddressBook;

public class JsonBuyerAddressBookStorage implements BuyerAddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonBuyerAddressBookStorage.class);

    private Path filePath;

    public JsonBuyerAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getBuyerAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyBuyerAddressBook> readBuyerAddressBook() throws DataConversionException {
        return readBuyerAddressBook(filePath);
    }

    /**
     * Similar to {@link #readBuyerAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyBuyerAddressBook> readBuyerAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableBuyerAddressBook> jsonBuyerAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableBuyerAddressBook.class);
        if (!jsonBuyerAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonBuyerAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveBuyerAddressBook(ReadOnlyBuyerAddressBook addressBook) throws IOException {
        saveBuyerAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveBuyerAddressBook(ReadOnlyBuyerAddressBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveBuyerAddressBook(ReadOnlyBuyerAddressBook buyerAddressBook, Path filePath) throws IOException {
        requireNonNull(buyerAddressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableBuyerAddressBook(buyerAddressBook), filePath);
    }

}
