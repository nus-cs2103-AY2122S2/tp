package seedu.address.storage;

import seedu.address.commons.util.FileUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * A class to manage temporary files of AddressBook data.
 */
public class SerializableTempAddressBookStorage implements TempAddressBookStorage {
    private static final int SAVE_LIMIT = 10;

    private final Path fileDirectory;
    private final List<Path> tempFiles;

    /**
     * Creates a Address Book Storage that manages temporary data.
     * @param fileDirectory directory of where the temp files would be stored
     */
    public SerializableTempAddressBookStorage(Path fileDirectory) {
        this.fileDirectory = fileDirectory;
        tempFiles = new ArrayList<>();
    }

    @Override
    public Path getTempAddressBookFilepath() {
        return fileDirectory;
    }

    @Override
    public void addNewTempAddressBookFile(ReadOnlyAddressBook addressBook) throws Exception {
        if (!Files.exists(fileDirectory)) {
            Files.createDirectory(fileDirectory);
        }

        //delete earliest temp file created
        if (tempFiles.size() >= SAVE_LIMIT) {
            Path earliestFileData = tempFiles.get(0);
            tempFiles.remove(0);

            earliestFileData.toFile().delete();
        }

        String tempFileName = "temp";
        Path tempFile = Files.createTempFile(fileDirectory, tempFileName, ".tmp");
        tempFiles.add(tempFile);

        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(tempFile.toFile(), false)));
        objectOutputStream.writeObject(new ArrayList<>(addressBook.getPersonList()));
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    @Override
    public Optional<ReadOnlyAddressBook> popTempAddressFileData() throws Exception {
        Path prevDataTempFile;
        if (tempFiles.size() <= 0) {
            return Optional.empty();
        }

        int lastIndex = tempFiles.size() - 1;
        prevDataTempFile = tempFiles.get(lastIndex);
        tempFiles.remove(lastIndex);

        Optional<ReadOnlyAddressBook> addressBookData = getTempAddressBookFileData(prevDataTempFile);
        Files.delete(prevDataTempFile);

        return addressBookData;
    }

    /**
     * Gets the data from a temporary file based on the filepath provided.
     *
     * @param tempDataTempFile file path of the temporary files.
     * @return The data read from the file path.
     */
    public Optional<ReadOnlyAddressBook> getTempAddressBookFileData(Path tempDataTempFile) throws Exception {
        ObjectInputStream objectInputStream =
                new ObjectInputStream(new BufferedInputStream(new FileInputStream(tempDataTempFile.toString())));
        ArrayList<Person> personList = (ArrayList<Person>) objectInputStream.readObject();
        objectInputStream.close();

        return Optional.of(new AddressBook(personList));
    }

    @Override
    public void deleteAllTempFilesData() throws Exception {
        if (!Files.exists(fileDirectory)) {
            return;
        }

        for (File file : Objects.requireNonNull(fileDirectory.toFile().listFiles())) {
            file.delete();
        }
    }
}
