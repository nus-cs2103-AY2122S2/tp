package seedu.address.storage;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SerializableTempAddressBookStorage implements TempAddressBookStorage {
    private static final int LIMIT = 10;

    private final Path fileDirectory;
    private final List<Path> tempFiles;

    public SerializableTempAddressBookStorage(Path fileDirectory) {
        this.fileDirectory = fileDirectory;
        tempFiles = new ArrayList<>();
    }

    @Override
    public Path getTempAddressBookFilepath() {
        return fileDirectory;
    }

    @Override
    public void addNewTempAddressBookFile(ReadOnlyAddressBook addressBook) throws IOException {
        if (!Files.exists(fileDirectory)) {
            Files.createDirectory(fileDirectory);
        }

        String tempFileName = "temp";
        Path tempFile = Files.createTempFile(fileDirectory, tempFileName, ".tmp");
        tempFiles.add(tempFile);

        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(tempFile.toFile(), false)));
        objectOutputStream.writeObject(new ArrayList<>(addressBook.getPersonList()));
        objectOutputStream.flush();
        objectOutputStream.close();

        //TODO:: HANDLE ERRORS WITH READING OR CREATING DIRECTORY
    }

    @Override
    public Optional<ReadOnlyAddressBook> popTempAddressFileData() {
        Path prevDataTempFile;
        if (tempFiles.size() <= 0) {
            return Optional.empty();
        }

        int lastIndex = tempFiles.size() - 1;
        prevDataTempFile = tempFiles.get(lastIndex);
        tempFiles.remove(lastIndex);

        return getTempAddressBookFileData(prevDataTempFile);
    }

    public Optional<ReadOnlyAddressBook> getTempAddressBookFileData(Path tempDataTempFile) {
        try {
            ObjectInputStream objectInputStream =
                    new ObjectInputStream(new BufferedInputStream(new FileInputStream(tempDataTempFile.toString())));
            ArrayList<Person> personList = (ArrayList<Person>) objectInputStream.readObject();
            objectInputStream.close();
            return Optional.of(new AddressBook(personList));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
