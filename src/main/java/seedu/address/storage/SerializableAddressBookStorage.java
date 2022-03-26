package seedu.address.storage;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;

public class SerializableAddressBookStorage implements AddressBookStorage {
    private static final Logger logger = LogsCenter.getLogger(SerializableAddressBookStorage.class);
    private Path filePath;

    public SerializableAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() {
        return readAddressBook(filePath);
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) {
        requireAllNonNull(filePath);
        try {
            ObjectInputStream objectInputStream =
                new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath.toString())));
            Map<String, Object> storageMap = (Map<String, Object>) objectInputStream.readObject();
            objectInputStream.close();

            return Optional.of(new AddressBook(storageMap));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        requireAllNonNull(addressBook, filePath);
        FileUtil.createIfMissing(filePath);
        File file = new File(filePath.toString());
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            //noinspection ResultOfMethodCallIgnored
            file.getParentFile().mkdirs();
        }
        //noinspection ResultOfMethodCallIgnored
        file.createNewFile();

        ObjectOutputStream objectOutputStream =
            new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file, false)));
        objectOutputStream.writeObject(addressBook.generateStorageMap());
        objectOutputStream.flush();
        objectOutputStream.close();
    }
}
