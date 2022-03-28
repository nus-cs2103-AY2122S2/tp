package seedu.address.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

class SerializableTempAddressBookStorageTest {
    private SerializableTempAddressBookStorage tempAddressBookStorage;

    @TempDir
    public Path testFolder;

    @BeforeEach
    public void setUp() {
        testFolder = testFolder.resolve("temp");
        tempAddressBookStorage = new SerializableTempAddressBookStorage(testFolder);
    }

    private void deleteAllFilesInFolder() {
        for (File file : Objects.requireNonNull(testFolder.toFile().listFiles())) {
            file.delete();
        }
    }

    private SerializableTempAddressBookStorage populateTemporaryFileStorage(int populateCount) throws Exception {
        AddressBook originalAddressBook = getTypicalAddressBook();

        for (int i = 0; i < populateCount; ++i) {
            tempAddressBookStorage.addNewTempAddressBookFile(originalAddressBook);
        }

        return tempAddressBookStorage;
    }

    @Test
    public void loadLatestTemporaryFileSaved_success() throws Exception {
        AddressBook originalAddressBook = getTypicalAddressBook();
        tempAddressBookStorage.addNewTempAddressBookFile(originalAddressBook);
        Optional<ReadOnlyAddressBook> retrieved = tempAddressBookStorage.popTempAddressFileData();

        assertEquals(retrieved.get(), originalAddressBook);
    }

    @Test
    public void loadLatestTemporaryFileSaved_failure() throws Exception {
        SerializableTempAddressBookStorage populatedTempAddressBookStorage = populateTemporaryFileStorage(5);

        //populate address book, delete temporary files made, try to read from deleted files
        deleteAllFilesInFolder();
        assertThrows(IOException.class, ()-> populatedTempAddressBookStorage.popTempAddressFileData());
    }

    @Test
    public void noPreviousFileToLoad_success() throws Exception {
        Optional<ReadOnlyAddressBook> retrieved = tempAddressBookStorage.popTempAddressFileData();

        assertTrue(retrieved.isEmpty());
    }

    @Test
    public void addMultipleTempFile_success() throws Exception {
        int noToPopulate = 4;
        populateTemporaryFileStorage(noToPopulate);
        String[] noOfTempFiles = testFolder.toFile().list();
        assertEquals(noOfTempFiles.length, noToPopulate);
    }

    @Test
    public void maxSaveFileAddedLimitReached_success() throws Exception {
        int noToPopulate = SerializableTempAddressBookStorage.SAVE_LIMIT + 1;
        populateTemporaryFileStorage(noToPopulate);
        String[] noOfTempFiles = testFolder.toFile().list();
        assertEquals(noOfTempFiles.length, SerializableTempAddressBookStorage.SAVE_LIMIT);
    }

    @Test
    public void deleteAllTemporaryFiles_success() throws Exception {
        int noToPopulate = 4;
        SerializableTempAddressBookStorage populatedTempAddressBookStorage = populateTemporaryFileStorage(noToPopulate);
        populatedTempAddressBookStorage.deleteAllTempFilesData();

        String[] noOfTempFiles = testFolder.toFile().list();
        assertEquals(noOfTempFiles.length, 0);
    }

    @Test
    public void deleteAllTemporaryFiles_noPreviousFileToLoad_success() throws Exception {
        int noToPopulate = 4;
        SerializableTempAddressBookStorage populatedTempAddressBookStorage = populateTemporaryFileStorage(noToPopulate);
        populatedTempAddressBookStorage.deleteAllTempFilesData();

        Optional<ReadOnlyAddressBook> retrieved = tempAddressBookStorage.popTempAddressFileData();

        assertTrue(retrieved.isEmpty());
    }
}