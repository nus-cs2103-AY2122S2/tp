package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.CsvUtil;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;

/**
 * A class to access AddressBook data stored as a CSV file on the hard disk.
 */
public class CsvAddressBookStorage implements AddressBookStorage {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private static final Logger logger = LogsCenter.getLogger(CsvAddressBookStorage.class);

    private Path filePath;

    public CsvAddressBookStorage() {
        this.filePath = null;
    }

    public CsvAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public void setFilePath(Path p) {
        this.filePath = p;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @return Either a ReadOnlyAddressBook, or nothing.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        if (!Files.exists(filePath)) {
            return Optional.empty();
        }

        // get the people in the addressbook
        List<CsvAdaptedPerson> persons = CsvUtil.loadCsvFile(filePath);
        Person convertedPerson;

        AddressBook addressBook = new AddressBook();
        for (CsvAdaptedPerson csvPerson : persons) {
            try {
                convertedPerson = csvPerson.toModelType();
                if (addressBook.hasPerson(convertedPerson)) {
                    logger.info(MESSAGE_DUPLICATE_PERSON);
                    throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
                }
                addressBook.addPerson(convertedPerson);
            } catch (IllegalValueException ive) {
                logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
                throw new DataConversionException(ive);
            }
        }
        return Optional.of(addressBook);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyAddressBook)}, but for CSV.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);

        // get the people in the addressbook
        List<CsvAdaptedPerson> persons = new ArrayList<>();
        persons.addAll(addressBook.getPersonList().stream().map(CsvAdaptedPerson::new).collect(Collectors.toList()));

        CsvUtil.saveCsvFile(persons, filePath);
    }

}
